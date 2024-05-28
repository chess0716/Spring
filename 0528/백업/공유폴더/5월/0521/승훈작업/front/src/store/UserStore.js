import { observable } from 'mobx';
import profile from '../public/profile.jpg';

const UserStore = observable({
	id: '',
	nickname: '',
	email: '',
	description: '',
	mobile: '', // 새로운 필드 추가
	imgSrc: profile,

	setId(id) {
		this.id = id;
	},
	setNickname(nickname) {
		this.nickname = nickname;
	},
	setEmail(email) {
		this.email = email;
	},
	setDescription(description) {
		this.description = description;
	},
	setMobile(mobile) {
		// setMobile 함수 추가
		this.mobile = mobile;
	},
	setImgSrc(imgSrc) {
		this.imgSrc = imgSrc;
	},
	reset() {
		this.id = '';
		this.nickname = '';
		this.email = '';
		this.description = '';
		this.mobile = ''; // mobile도 리셋되어야 함
		this.imgSrc = profile;
	},
});

export default UserStore;
