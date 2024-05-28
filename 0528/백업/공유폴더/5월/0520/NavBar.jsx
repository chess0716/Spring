import React, { useEffect, useState } from 'react';
import 'styles/NavBar/NavBar.scss';

// FontAwesome Icon
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faRightFromBracket, faRightToBracket, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { Link, Outlet } from 'react-router-dom';
import Logout from 'utils/Logout';
import store from 'store';

function NavBar() {
	const { UserStore } = store();
	const [showMenu, setShowMenu] = useState(false);

	const toggleMenu = () => {
		setShowMenu((prevShowMenu) => !prevShowMenu);
	};

	useEffect(() => {
		const userId = localStorage.getItem('id');
		if (userId) {
			(async function fetchUserId() {
				await fetch(`https://elice-server.herokuapp.com/mypage/${userId}`, {
					method: 'GET',
				})
					.then((res) => res.json())
					.then((result) => {
						UserStore.setNickname(result.data.nickname);
						UserStore.setEmail(result.data.id);
						UserStore.setDescription(result.data.intro);
						if (result.data.profile !== null) {
							UserStore.setImgSrc(result.data.profile);
						}
					});
			})();
		}
	}, []); // 종속성 배열을 빈 배열로 설정하여 한 번만 실행되도록 함

	const handleLogout = () => {
		Logout();
		setShowMenu(false); // 로그아웃 시 메뉴를 숨깁니다.
	};

	return (
		<>
			<nav className="navBar navBar1">
				<div className="navLogo">
					<Link to="/page=1" style={{ textDecoration: 'none' }}>
						GRaM
					</Link>
				</div>
				<ul className="navItems">
					<li className="navItem">
						<Link to="/auth/login" style={{ textDecoration: 'none' }}>
							<FontAwesomeIcon icon={faRightToBracket} className="menu__icon" />
							로그인
						</Link>
					</li>
					<li className="navItem">
						<Link to="/auth/join" style={{ textDecoration: 'none' }}>
							<FontAwesomeIcon icon={faUserPlus} className="menu__icon" />
							회원가입
						</Link>
					</li>
					<li className="navItem__menu-container">
						<img
							src={UserStore.imgSrc || '/default-profile.png'} // 기본 프로필 이미지 설정
							alt="profile"
							onClick={toggleMenu}
							aria-hidden="true"
							className="navItem__trigger"
						/>
						{showMenu && (
							<nav className="menu">
								<div className="menu__square" />
								<div className="menu__lists">
									<li className="menu__mypage">
										<FontAwesomeIcon icon={faCircleUser} className="menu__icon" />
										<Link to={`/user/${localStorage.getItem('id')}`} style={{ textDecoration: 'none' }}>
											마이 페이지
										</Link>
									</li>
									<li>
										<FontAwesomeIcon icon={faRightFromBracket} className="menu__icon" />
										<Link to="/auth/login" onClick={handleLogout} style={{ textDecoration: 'none' }}>
											로그아웃
										</Link>
									</li>
								</div>
							</nav>
						)}
					</li>
				</ul>
			</nav>
			<Outlet />
		</>
	);
}

export default NavBar;
