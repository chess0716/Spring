import React, { useState, useEffect, useContext } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faRightFromBracket, faRightToBracket, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import store from '../../store';
import defaultImage from '../../assets/images/default-profile.png';
import AuthService from '../../service/AuthService';
import UserService from '../../service/UserService';
import SearchContext from '../../utils/SearchContext';

function NavBar() {
	const { setSearchKeyword } = useContext(SearchContext); // SearchContext 사용
	const { UserStore } = store();
	const [showMenu, setShowMenu] = useState(false);
	const navigate = useNavigate();

	const toggleMenu = () => {
		setShowMenu((prevShowMenu) => !prevShowMenu);
	};

	const fetchUserById = async (userId) => {
		try {
			const response = await UserService.getUser(userId);
			return response;
		} catch (error) {
			console.error('Failed to fetch user data:', error);
			throw error;
		}
	};

	const updateUserStore = (userData) => {
		UserStore.setId(userData.id);
		UserStore.setName(userData.name);
		UserStore.setEmail(userData.email);
		UserStore.setDescription(userData.description);
		if (userData.profile !== null) {
			UserStore.setImgSrc(userData.profile);
		}
		console.log('UserStore.id:', UserStore.id);
	};

	useEffect(() => {
		const userId = localStorage.getItem('id');
		console.log('userId from localStorage:', userId);

		if (userId) {
			(async function fetchUserData() {
				try {
					const response = await fetchUserById(userId);
					if (!response) {
						throw new Error('User data not found');
					}
					const userData = response;
					updateUserStore(userData);
				} catch (error) {
					console.error('Failed to fetch user data:', error);
				}
			})();
		}
	}, []);

	const handleLogout = async () => {
		try {
			await AuthService.logout();
			setShowMenu(false);
			navigate('/main');
			console.log('logout successfully');
		} catch (error) {
			console.error('Failed to logout:', error);
		}
	};

	const handleLogoClick = () => {
		setSearchKeyword(''); // 검색 키워드 초기화
		navigate('/posts/page/1'); // 첫 페이지로 이동
		// window.location.reload(); // 페이지 새로고침
	};

	return (
		<>
			<nav className="navBar navBar1">
				<div className="navItem">
					<Link to="/main" style={{ textDecoration: 'none' }}>
						Home
					</Link>
				</div>
				<div className="navLogo">
					{/* handleLogoClick 추가*/}
					<Link to="/posts/page/1" style={{ textDecoration: 'none' }} onClick={handleLogoClick}>
						ERaM
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
							src={UserStore.imgSrc || defaultImage}
							alt="profile"
							onClick={toggleMenu}
							aria-hidden="true"
							className="navItem__trigger"
						/>
						{showMenu && (
							<nav className="menu">
								<div className="menu__square" />
								<div className="menu__lists">
									<li className="menu__mypage" onClick={() => setShowMenu(false)}>
										<FontAwesomeIcon icon={faCircleUser} className="menu__icon" />
										<Link to={`/members/get/${UserStore.id}`} style={{ textDecoration: 'none' }}>
											마이 페이지
										</Link>
									</li>
									<li onClick={handleLogout}>
										<FontAwesomeIcon icon={faRightFromBracket} className="menu__icon" />
										<span style={{ textDecoration: 'none', cursor: 'pointer' }}>로그아웃</span>
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
