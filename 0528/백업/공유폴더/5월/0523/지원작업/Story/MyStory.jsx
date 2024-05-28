import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/Story/MyStory.scss';
import UserStore from '../../store/UserStore';

function MyStory() {
	const [board, setBoard] = useState([]);

	useEffect(() => {
		const fetchPosts = async () => {
			try {
				const accessToken = localStorage.getItem('accessToken');
				if (!accessToken) {
					throw new Error('No access token found');
				}
				console.log(`Fetching posts for user ID: ${UserStore.id}`);
				const response = await fetch(`/members/${UserStore.id}/posts`, {
					method: 'GET',
					headers: {
						Authorization: `Bearer ${accessToken}`,
					},
				});
				console.log(`Response status: ${response.status}`);
				const contentType = response.headers.get('content-type');
				console.log(`Content-Type: ${contentType}`);
				if (!response.ok || !contentType || !contentType.includes('application/json')) {
					throw new Error(`Failed to fetch posts, received: ${contentType}`);
				}
				const data = await response.json();
				console.log('Fetched data:', data);
				if (data) {
					setBoard(data);
				}
			} catch (error) {
				console.error('Failed to fetch posts:', error);
			}
		};

		fetchPosts();
	}, []);

	return (
		<section className="Mystory_container">
			<StoryInfo />
			<Storys board={board} />
		</section>
	);
}

function Storys({ board }) {
	return board.map((el) => (
		<div className="MyStory" key={el.post_idx}>
			<span className="story_number story_child">{el.post_idx}</span>
			<span className="story_name story_child">{el.nickname}</span>
			<Link to={`/Read=${el.post_idx}`}>
				<span className="story_title story_child">
					{el.title.length < 25 ? el.title : `${el.title.substr(0, 25)}...`}
				</span>
			</Link>
			<span className="story_time story_child">{el.date.substr(0, 10)}</span>
		</div>
	));
}

function StoryInfo() {
	return (
		<div className="story storyInfo">
			<span className="story_number story_child">번호</span>
			<span className="story_title story_child">게시글 제목</span>
			<span className="story_name story_child">작성자</span>
			<span className="story_time story_child">작성일</span>
		</div>
	);
}

export default MyStory;
