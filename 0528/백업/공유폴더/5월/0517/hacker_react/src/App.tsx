import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import AuthForm from "./test/AuthForm";
import ModifyProfileForm from "./test/ModifyProfileForm";
import MyPageTest from "./test/MyPageTest";
import Home from "./test/home";

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <Routes>
            <Route path="/auth/login" element={<AuthForm />} />
            <Route path="/auth/join" element={<AuthForm />} />
            <Route path="/members/me" element={<ModifyProfileForm />} />
            <Route path="/mypage" element={<MyPageTest />} />
            <Route path="/" element={<Home />} />
          </Routes>
        </header>
      </div>
    </Router>
  );
}

export default App;
