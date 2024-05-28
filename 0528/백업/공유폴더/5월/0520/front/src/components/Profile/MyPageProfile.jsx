import React, { useEffect, useState } from 'react';
import 'styles/Profile/MyPageProfile.scss';
import ImgModal from 'components/ImgModal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import store from 'store';
import defaultProfileImage from 'assets/images/default-profile.png'; // 기본 이미지 경로

function MyPageProfile({ userId }) {
	const { UserStore } = store();

	const [edit, setEdit] = useState(false);
	const [modalOpen, setModalOpen] = useState(false);
	const [currentNickname, setCurrentNickname] = useState('');
	const [isAdmin, setIsAdmin] = useState(false);
	const [mouseEnter, setMouseEnter] = useState(false);

	const [otherUserProfile, setOtherUserProfile] = useState('');
	const [otherUserNickname, setOtherUserNickname] = useState('');
	const [otherUserIntro, setOtherUserIntro] = useState('');

	useEffect(() => {
		if (userId === localStorage.getItem('id')) {
			setIsAdmin(true);
		} else {
			(async function fetchUserData() {
				await fetch(`https://elice-server.herokuapp.com/mypage/${userId}`, {
					method: 'GET',
				})
					.then((res) => res.json())
					.then((result) => {
						setOtherUserProfile(result.data.profile || defaultProfileImage); // 기본 이미지 사용
						setOtherUserNickname(result.data.nickname);
						setOtherUserIntro(result.data.intro);
					});
			})();
		}
	}, [userId]);

	const openModal = () => {
		setModalOpen(true);
	};
	const closeModal = () => {
		setModalOpen(false);
	};

	const HandleNickname = (e) => {
		UserStore.setNickname(e.target.value);
	};

	const HandleDescription = (e) => {
		UserStore.setDescription(e.target.value);
	};

	const toggleEdit = () => {
		setEdit((prevEdit) => !prevEdit);
	};

	const onMouseEnter = () => {
		setMouseEnter(true);
	};

	const onMouseLeave = () => {
		setMouseEnter(false);
	};

	const profileImage = UserStore.imgSrc || defaultProfileImage; // 기본 이미지 사용

	return (
		<div className="MyPageProfile__profile">
			<div className="MyPageProfile__container__left" onMouseEnter={onMouseEnter} onMouseLeave={onMouseLeave}>
				{isAdmin ? (
					<>
						<img src={profileImage} alt="profile" onClick={openModal} className="AdminProfile" />
						{mouseEnter && <FontAwesomeIcon icon={faPen} className="Profile__icon" />}
					</>
				) : (
					<img src={otherUserProfile} alt="profile" />
				)}
			</div>
			<ImgModal
				open={modalOpen}
				close={closeModal}
				imgSrc={UserStore.imgSrc}
				setImgSrc={UserStore.setImgSrc}
				nickname={UserStore.nickname}
				description={UserStore.description}
			/>
			<div className="MyPageProfile__container__right">
				<div className="description">
					<span>[닉네임] 회원</span>
					{isAdmin ? (
						edit ? (
							<textarea
								onChange={HandleDescription}
								placeholder={UserStore.description}
								value={UserStore.description}
							/>
						) : (
							<p>{UserStore.description}</p>
						)
					) : (
						<p>{otherUserIntro}</p>
					)}
					<div>
						<p>
							<button type="button">충전하기</button>
						</p>
						<p>
							<button type="button">회원수정/탈퇴</button>
						</p>
					</div>
				</div>
			</div>
			<div className="MyPageProfile__container__right2">
				<div>
					<span>
						<img src="#" alt="mylist" />
						<button type="button">내가 쓴 글 보기</button>
					</span>
				</div>
				<div />
			</div>
			<div className="MyPageProfile__container__right2">
				<div>
					<span>
						<img src="#" alt="mychat" />
						<button type="button">참여중인 채팅방 보기</button>
					</span>
				</div>
			</div>
		</div>
	);
}

export default MyPageProfile;
