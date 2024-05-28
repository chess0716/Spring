import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { getPostById } from '../service/PostService'; 
import { getChatRoomByPostId, createChatRoom } from '../service/ChatService';
import '../styles/Read.scss';

export default function Read() {
  return <ReadContent />;
}

function ReadContent() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [post, setPost] = useState(null);

  useEffect(() => {
    if (id) {
      getPostById(id)
        .then(response => {
          setPost(response.data);
        })
        .catch(error => console.error('Error fetching post:', error));
    } else {
      console.error('Error: ID is undefined');
    }
  }, [id]);

  function handleEdit() {
    navigate(`/Writing=${id}`);
  }

  function handleDelete() {
    if (window.confirm('삭제 하시겠습니까?')) {
      fetch(`https://elice-server.herokuapp.com/board/${id}`, {
        method: 'DELETE',
      })
      .then(() => {
        alert('삭제되었습니다.');
        navigate(`/page=1`);
      })
      .catch(error => {
        console.error('Error deleting post:', error);
        alert('삭제에 실패했습니다.');
      });
    } else {
      alert('삭제 취소');
    }
  }

  function handleJoinChat() {
    if (id) {
      getChatRoomByPostId(id)
        .then(response => {
          navigate(`/chat/${response.data.id}`);
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            createChatRoom(id)
              .then(response => {
                navigate(`/chat/${response.data.id}`);
              })
              .catch(createError => {
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

  if (!post) return <div>Loading...</div>;

  return (
    <section className="read_container">
      <div className="read_title">{post.title}</div>
      <div className="read_info">
        <span className="read_day">{post.createdAt ? post.createdAt.slice(0, 10) : 'Unknown Date'}</span>
      </div>
      <div className="read_content">
        {post.content.split('\n').map((line, idx) => (
          <span key={idx}>
            {line}
            <br />
          </span>
        ))}
      </div>
      <div className="button_box">
        <input type="submit" className="read_button" value="수정" onClick={handleEdit} />
        <input type="submit" className="read_button delete_button" value="삭제" onClick={handleDelete} />
        <input type="submit" className="read_button chat_button" value="채팅방으로 이동" onClick={handleJoinChat} />
      </div>
    </section>
  );
}
