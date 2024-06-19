// src/App.js
import React, { useRef } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Sidebar from './components/sidebar/Sidebar.js';
import Navbar from './components/navbar/Navbar.js';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './user/login/Login.js';
import Signup from './user/signup/Signup.js';
import { AuthProvider } from './user/auth/AuthContext.js';
import ProtectedRoute from './user/auth/ProtectedRoute.js';
import MyPage from './user/mypage/MyPage.js';
import Admin from './user/admin/Admin.js';
import Delete from './user/Delete.js';
import QuizList from './quiz/QuizList.js';
import TestEditorPage from './ide/EditorPage.js';

function App() {

  const { sendMessage, fetchMessage } = UseWebSocket();

  return (
    <AuthProvider>
      <BrowserRouter>
        <div className="App">
          <Sidebar />
          <div className="main">
            <Navbar />
            <div className="content">
              <Routes>
                <Route path="/" />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/mypage" element={<MyPage />} />
                <Route path="/admin" element={<Admin />} />
                <Route path="/delete" element={<Delete />} />
                {/* 기존 경로들 추가 */}
                {/* <Route path="/main" element={<MainPage />} /> */}
                {/* <Route path="/editor" element={<EditorPage />} />
                <Route path="/editortest" element={<TestEditorPage />} /> */}
                <Route path="/editor" element={<EditorPage />} />
                <Route path="/editortest" element={<TestEditorPage />} />

                <Route path="/quizlist" element={<QuizList />} />
                <Route path="/quiz/detail/:id" element={<EditorPage />} />
                {/* 주석 처리된 경로 추가 가능 */}
                {/* <Route path="/edit" element={<ProtectedRoute><EditPage /></ProtectedRoute>} /> */}
                {/* <Route path="/chat" element={<Chat />} /> */}
              </Routes>
            </div>
          </div>
        </div>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
