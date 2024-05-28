import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
import UserService from "./userService";

const MyPageFormBlock = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f0f0f0;
`;

const LineBlock = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid #dcdcdc;
  padding: 0;
  margin-bottom: 20px;
`;

const TextBox = styled.div`
  font-size: 20px;
`;

const LogoutButton = styled.button`
  background-color: #ff6347;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-left: 10px;
`;

const MyPageForm = () => {
  const [username, setUsername] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    UserService.getCurrentUser()
      .then((userData) => {
        setUsername(userData.name || "");
      })
      .catch((error) => {
        console.error("Failed to fetch user data:", error);
      });
  }, []);

  const handleDeleteAccount = () => {
    if (window.confirm("정말 탈퇴하시겠습니까?")) {
      UserService.deleteAccount()
        .then(() => {
          alert("회원 탈퇴가 완료되었습니다.");
          navigate("/");
        })
        .catch((error) => {
          console.error("Failed to delete account:", error);
        });
    }
  };

  const handleLogout = () => {
    UserService.logout()
      .then(() => {
        navigate("/");
      })
      .catch((error) => {
        console.error("Failed to logout:", error);
      });
  };

  return (
    <MyPageFormBlock>
      <LineBlock>
        <TextBox>
          {username ? `안녕하세요 ${username} 님!` : "안녕하세요!"}
        </TextBox>
        <div>
          <Link to="/members/me">회원 정보 수정</Link>
          <button onClick={handleDeleteAccount}>회원 탈퇴</button>
          <LogoutButton onClick={handleLogout}>로그아웃</LogoutButton>
        </div>
      </LineBlock>
      <LineBlock>
        <TextBox>
          <Link to="#">내 게시글</Link>
        </TextBox>
        <TextBox>
          <Link to="#">채팅방 보기</Link>
        </TextBox>
      </LineBlock>
    </MyPageFormBlock>
  );
};

export default MyPageForm;
