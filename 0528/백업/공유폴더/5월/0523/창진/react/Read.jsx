import React, { useState, useEffect } from 'react';
import * as DOMPurify from 'dompurify';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { deletePost, getPostById } from '../service/PostService';
import { getChatRoomByPostId, createChatRoom } from '../service/ChatService';

import '../styles/Read.scss';

export default function Read() {
	return <ReadContent />;
}

function ReadContent() {
	const { id } = useParams();
	const navigate = useNavigate();
	const [post, setPost] = useState(null);
	const [currentUser, setCurrentUser] = useState(null);

	useEffect(() => {
		const fetchCurrentUser = async () => {
			const userJson = localStorage.getItem('user');
			const user = userJson ? JSON.parse(userJson) : null;
			console.log('로컬유저: ', user);
			setCurrentUser(user);
		};
		fetchCurrentUser();

		console.log('게시판 아이디: ', id);
		if (id) {
			getPostById(id)
				.then((response) => {
					setPost(response.data);
				})
				.catch((error) => console.error('Error fetching post:', error));
		} else {
			console.error('Error: ID is undefined');
		}
	}, [id]);

	function handleEdit() {
		navigate(`/Writing/${id}`);
	}

	function handleDelete() {
		if (window.confirm('삭제 하시겠습니까?')) {
			try {
				(async () => {
					await deletePost(id);
					alert('삭제되었습니다.');
					navigate(`/page=1`);
				})();
			} catch (error) {
				console.error('Error deleting post:', error);
				alert('삭제에 실패했습니다.');
			}
		} else {
			alert('삭제 취소');
		}
	}

	function handleJoinChat() {
		if (id) {
			getChatRoomByPostId(id)
				.then((response) => {
					navigate(`/chat/${response.data.id}`);
				})
				.catch((error) => {
					if (error.response && error.response.status === 404) {
						createChatRoom(id)
							.then((response) => {
								navigate(`/chat/${response.data.id}`);
							})
							.catch((createError) => {
								console.error('Error creating chat room:', createError);
								alert('채팅방 생성에 실패했습니다.');
							});
					} else {
						console.error('Error fetching chat room:', error);
						alert('채팅방 조회에 실패했습니다.');
					}
				});
		} else {
			console.error('Error: ID is undefined');
			alert('포스트 ID가 정의되지 않았습니다.');
		}
	}
	console.log('포트스 유저: ', post);
	const isAuthor = currentUser && post && currentUser.id === post.userId;

	if (!post) return <div>Loading...</div>;

	return (
		<section className="read_container">
			<div className="read_title">
				{post.title}
				<span>({post.numberOfUsers})</span>
			</div>
			<div className="read_info">
				<span className="read_name">{post.name}</span>
				<span className="read_day">
					{post.regdate ? post.regdate : 'Unknown Date'} ~ {post.endDate ? post.endDate : 'Unknown Date'}
				</span>
			</div>
			<div
				className="read_content"
				dangerouslySetInnerHTML={{
					__html: DOMPurify.sanitize(post.content),
				}}
			/>
			<div className="button_box">
				{isAuthor && (
					<>
						<input type="submit" className="read_button" value="수정" onClick={handleEdit} />
						<input type="submit" className="read_button delete_button" value="삭제" onClick={handleDelete} />
					</>
				)}
				<input type="submit" className="read_button chat_button" value="채팅방으로 이동" onClick={handleJoinChat} />
			</div>
		</section>
	);
}
