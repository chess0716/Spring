import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import '../../styles/Story/Story.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowRotateRight } from '@fortawesome/free-solid-svg-icons';
import { getPosts } from '../../service/PostService';

function Story({ searchKeyword, searchWritings, setSearchWritings }) {
	return <Storys searchKeyword={searchKeyword} searchWritings={searchWritings} setSearchWritings={setSearchWritings} />;
}

function Storys({ searchKeyword, searchWritings, setSearchWritings }) {
	const [board, setBoard] = useState([]);
	const [page, setPage] = useState([]);
	const [boardCount, setBoardCount] = useState(0);
	const navigate = useNavigate();
	const { pageNum } = useParams();

	useEffect(() => {
		getPosts()
			.then((res) => {
				const result = res.data;
				console.log('게시판리스트: ', result);
				setBoard(result.data || []);

				if (result.pageCount && result.pageCount.length > 0) {
					setBoardCount(result.pageCount[0].count);
					const totalPage = Math.ceil(result.pageCount[0].count / 10); // 마지막 페이지 수
					const pageNumArray = [];
					for (let i = 1; i <= totalPage; i += 1) {
						pageNumArray.push(i);
					}
					setPage(pageNumArray);
				}
			})
			.catch((error) => {
				console.error('게시글을 불러오는 중 오류 발생:', error);
			});
	}, [pageNum]);

	function storyPagination(e) {
		const pageNumber = Number(e.target.innerHTML);
		getPosts(pageNumber)
			.then((res) => {
				const result = res.data;
				setBoard(result.data || []);
			})
			.catch((error) => {
				console.error('페이지네이션 중 오류 발생:', error);
			});
		navigate(`/page=${pageNumber}`);
	}

	return searchWritings === undefined ? (
		<section className="story_parent">
			<div className="story_container">
				<div className="story_length">
					<span>{boardCount}개의 게시글</span>
					<FontAwesomeIcon
						icon={faArrowRotateRight}
						className="refresh__icon"
						onClick={async () => {
							getPosts(1)
								.then((res) => {
									const result = res.data;
									setBoard(result.data || []);
								})
								.catch((error) => {
									console.error('게시글을 새로고침하는 중 오류 발생:', error);
								});
						}}
					/>
				</div>
				<StoryInfo />
				{board.map((el, idx) => (
					<div className="story" key={idx}>
						<span className="story_number story_child">{el.post_idx}</span>
						<span className="story_name story_child">{el.nickname}</span>
						<Link to={`/page=${pageNum}/Read=${el.post_idx}`}>
							<span className="story_title story_child">
								{el.title.length < 25 ? el.title : `${el.title.substr(0, 25)}...`}
							</span>
						</Link>
						<span className="story_time story_child">{el.date.substr(0, 10)}</span>
					</div>
				))}
			</div>
			<Link className="writeBtn" to="/Writing">
				글쓰기
			</Link>
			<div className="pageNav">
				&lt;
				{page.length > 0
					? page.map((el) => (
							<button type="submit" className="pageNav_btn" onClick={storyPagination} value={el} key={el}>
								{el}
							</button>
					  ))
					: null}
				&gt;
			</div>
		</section>
	) : (
		<section className="story_parent">
			<div className="story_container">
				<div className="story_length">
					<span>
						&apos;{searchKeyword}&apos; - {searchWritings.length}개의 게시글
					</span>
					<FontAwesomeIcon
						icon={faArrowRotateRight}
						className="refresh__icon"
						onClick={async () => {
							getPosts(1)
								.then((res) => {
									const result = res.data;
									setSearchWritings(result.data || []);
									setSearchWritings(undefined);
								})
								.catch((error) => {
									console.error('게시글을 새로고침하는 중 오류 발생:', error);
								});
							navigate('/page=1');
						}}
					/>
				</div>
				<StoryInfo />
				{searchWritings.map((el) => (
					<div className="story" key={el.post_idx}>
						<span className="story_number story_child">{el.post_idx}</span>
						<span className="story_name story_child asdf">{el.nickname}</span>
						<Link to={`Read=${el.post_idx}`}>
							<span className="story_title story_child">{el.title}</span>
						</Link>
						<span className="story_time story_child">{el.date.substr(0, 10)}</span>
					</div>
				))}
			</div>
			<Link className="writeBtn" to="/Writing">
				글쓰기
			</Link>
		</section>
	);
}

function StoryInfo() {
	return (
		<div className="story storyInfo">
			<span className="story_number story_child">번호</span>
			<span className="story_title story_child">제목</span>
			<span className="story_name story_child">작성자</span>
			<span className="story_time story_child">작성일</span>
		</div>
	);
}

export default Story;
