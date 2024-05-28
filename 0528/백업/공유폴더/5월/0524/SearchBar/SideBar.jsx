import React, { useContext } from 'react';
import '../styles/SideBar.scss';
import { useNavigate } from 'react-router-dom';
import SearchContext from '../utils/SearchContext'; // SearchContext 임포트

function SideBar() {
	const { setSearchKeyword } = useContext(SearchContext);
	const navigate = useNavigate();

	const handleLinkClick = (keyword) => {
		setSearchKeyword(keyword);
		navigate(`/posts/page/1?search=${keyword}`);
	};

	return (
		<div className="Sidebar__container">
			<h2>바로가기</h2>
			<div className="link">
				<p id="link1">
					<a href="http://localhost:3000/eram/chat">채팅방가기</a>
				</p>
				<p id="link2">
					<button
						onClick={() => handleLinkClick('제목')}
						type="button"
						style={{
							background: 'none',
							border: 'none',
							padding: 0,
							cursor: 'pointer',
							color: 'blue',
							textDecoration: 'underline',
							fontSize: 'inherit',
							fontFamily: 'inherit',
						}}
					>
						아이스링크
					</button>
				</p>
				<p id="link3">
					<a href="http://localhost:3000/eram/posts/page/1?search=아이스링크">SideBar1 &rarr;</a>
				</p>
				<div className="git">
					<a href="http://localhost:3000/eram">Main</a>
				</div>
				<div className="footer">
					<p>© 2024 ERaM</p>
				</div>
			</div>
		</div>
	);
}

export default SideBar;
