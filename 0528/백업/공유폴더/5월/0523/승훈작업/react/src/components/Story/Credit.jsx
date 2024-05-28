import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/BtnStyle.scss';

function Credit({ userId }) {
	return (
		<section className="Mystory_container">
			<CreditInfo />
			<CreditCount userId={userId} />
		</section>
	);
}
function CreditCount({ userId }) {
	return (
		<div>
			<div>
				<button className="btnstlye" type="button">
					1000₩ + 3p
				</button>
				<button className="btnstlye" type="button">
					3000₩ + 10p
				</button>
			</div>
			<div>
				<button className="btnstlye" type="button">
					5000₩ + 20p
				</button>
				<button className="btnstlye" type="button">
					10000₩ + 50p
				</button>
			</div>
			<div>
				<button className="btnstlye" type="button">
					20000₩ + 110p
				</button>
				<button className="btnstlye" type="button">
					30000₩ + 200p
				</button>
			</div>
			<div>
				<button className="btnstlye" type="button">
					50000₩ + 500p
				</button>
				<button className="btnstlye" type="button">
					100000₩ + 1100p
				</button>
			</div>
		</div>
	);
}

function CreditInfo() {
	return (
		<div className="story storyInfo">
			<span className="story_number story_child">충전하기</span>
		</div>
	);
}
export default Credit;
