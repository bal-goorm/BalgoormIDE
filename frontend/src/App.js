// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, BrowserRouter } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import EditorPage from './ide/EditorPage';
import TestEditorPage from './ide/TestEditorPage';
import Navbar from './components/Navbar';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './user/Login.js';
import Signup from './user/Signup.js';
import { AuthProvider } from './user/auth/AuthContext.js';
import ProtectedRoute from './user/auth/ProtectedRoute.js';
import MyPage from './user/MyPage.js';
import Admin from './user/admin/Admin.js';
import Delete from './user/Delete.js';
import QuizList from './quiz/QuizList.js';

function App() {
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
