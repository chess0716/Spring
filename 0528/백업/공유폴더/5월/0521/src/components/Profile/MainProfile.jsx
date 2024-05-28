import React from 'react';

import { Link } from 'react-router-dom';
import store from '../../store';
import '../../styles/Profile/MainProfile.scss';
import '../ImgModal'

function MainProfile({ imgSrc, email }) {
	const { UserStore } = store();
	return (
		<div className="MainProfile">
			<Link to={`/user/${localStorage.getItem('id')}`}>
				<img src={imgSrc} alt="profile" />
			</Link>
			<h2>{UserStore.nickname}</h2>
			<span>{email}</span>
		</div>
	);
}

export default MainProfile;
