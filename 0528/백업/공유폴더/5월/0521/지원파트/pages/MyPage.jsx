import React, { useState, useEffect } from 'react';
import MyPageProfile from 'components/Profile/MyPageProfile';
import MyStory from 'components/Story/MyStory';
import { useParams } from 'react-router-dom';

function MyPage() {
	const { id } = useParams();
	const [userId, setUserId] = useState(id || '');

	useEffect(() => {
		const fetchUserId = async () => {
			const user = JSON.parse(localStorage.getItem('user'));
			if (user && user.id) {
				setUserId(user.id);
			}
		};

		if (!userId) {
			fetchUserId();
		}
	}, [userId]);

	return (
		<>
			<MyPageProfile userId={userId} />
			<MyStory userId={userId} />
		</>
	);
}

export default MyPage;
