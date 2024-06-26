import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // 추가
import AuthService from 'components/Profile/AuthService';
import '../styles/Pages/AuthForm.scss';

function AuthForm() {
	const [isRightPanelActive, setIsRightPanelActive] = useState(false);
	const [id, setId] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [name, setName] = useState('');
	const [mobile, setMobile] = useState('');
	const [error, setError] = useState('');
	const [loading, setLoading] = useState(false);
	const [isSignUp, setIsSignUp] = useState(false);
	const navigate = useNavigate(); // 추가

	const handleSignUpSuccess = () => {
		setIsSignUp(false);
		setError('');
		setIsRightPanelActive(false);
	};

	const handleSignUp = async (e) => {
		e.preventDefault();
		setLoading(true);
		try {
			await AuthService.signup({ id, email, password, name, mobile });
			setError('');
			setLoading(false);
			handleSignUpSuccess();
		} catch (err) {
			setError('Signup failed');
			setLoading(false);
		}
	};

	const handleSignIn = async (e) => {
		e.preventDefault();
		setLoading(true);
		try {
			await AuthService.login({ email, password });
			setError('');
			setLoading(false);
			setEmail('');
			setPassword('');
			setId('');
			setName('');
			setMobile('');
			navigate('/main'); // 로그인 성공 시 /main으로 이동
		} catch (err) {
			setError('Login failed');
			setLoading(false);
		}
	};

	const toggleForm = (isSignUp) => {
		setIsSignUp(isSignUp);
		setError('');
		setIsRightPanelActive(isSignUp);
	};

	return (
		<div className="App">
			<div className={`container ${isRightPanelActive ? 'right-panel-active' : ''}`} id="container">
				{isSignUp ? (
					<div className="form-container sign-up-container">
						<form onSubmit={handleSignUp}>
							<h1>회원가입</h1>
							<span>or use your email for registration</span>
							<input type="text" placeholder="Id" value={id} onChange={(e) => setId(e.target.value)} />
							<input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
							<input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
							<input
								type="password"
								placeholder="Password"
								value={password}
								onChange={(e) => setPassword(e.target.value)}
							/>
							<input type="text" placeholder="Mobile" value={mobile} onChange={(e) => setMobile(e.target.value)} />
							<button type="submit" disabled={loading}>
								Sign Up
							</button>
							{error && <p>{error}</p>}
						</form>
					</div>
				) : (
					<div className="form-container sign-in-container">
						<form onSubmit={handleSignIn}>
							<h1>로그인</h1>
							<span>or use your account</span>
							<input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
							<input
								type="password"
								placeholder="Password"
								value={password}
								onChange={(e) => setPassword(e.target.value)}
							/>
							<button type="button">Forgot your password?</button>
							<button type="submit" disabled={loading}>
								Sign In
							</button>
							{error && <p>{error}</p>}
						</form>
					</div>
				)}
				<div className="overlay-container">
					<div className="overlay">
						<div className="overlay-panel overlay-left">
							<h1>Welcome Back!</h1>
							<p>To keep connected with us please login with your personal info</p>
							<button className="ghost" id="signIn" onClick={() => toggleForm(false)} type="button">
								Sign In
							</button>
						</div>
						<div className="overlay-panel overlay-right">
							<h1>Hello, Friend!</h1>
							<p>Enter your personal details and start journey with us</p>
							<button className="ghost" id="signUp" onClick={() => toggleForm(true)} type="button">
								Sign Up
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default AuthForm;
