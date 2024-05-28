import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/Story/MyStory.scss';
import UserService from '../../service/UserService'; // UserService를 임포트

function MyStory() {
    const [board, setBoard] = useState([]);

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const data = await UserService.getCurrentUserPosts();
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
        <div className="MyStory" key={el.post_idx || 'Unknown'}>
            <span className="story_number story_child">{el.post_idx || 'Unknown'}</span>
            <span className="story_name story_child">{el.name || 'Unknown'}</span>
            <Link to={`/Read=${el.post_idx || 'Unknown'}`}>
                <span className="story_title story_child">
                    {el.title ? (el.title.length < 25 ? el.title : `${el.title.substr(0, 25)}...`) : 'Unknown'}
                </span>
            </Link>
            <span className="story_time story_child">{el.date ? el.date.substr(0, 10) : 'Unknown'}</span>
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
