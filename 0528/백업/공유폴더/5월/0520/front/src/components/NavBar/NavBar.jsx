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
	const [isLoggedIn, setIsLoggedIn] = useState(false);
	const [showMenu, setShowMenu] = useState(false);

	const toggleMenu = () => {
		setShowMenu((prevShowMenu) => !prevShowMenu);
	};

	useEffect(() => {
		const userId = localStorage.getItem('id');
		if (userId) {
			setIsLoggedIn(true);
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
		} else {
			setIsLoggedIn(false);
		}
	}, [UserStore]);

	const handleLogout = () => {
		Logout();
		setIsLoggedIn(false);
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
					{!isLoggedIn && (
						<>
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
						</>
					)}
					{isLoggedIn && (
						<li className="navItem__menu-container">
							<img
								src={UserStore.imgSrc}
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
					)}
				</ul>
			</nav>
			<Outlet />
		</>
	);
}

export default NavBar;
