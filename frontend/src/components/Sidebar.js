import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../user/auth/AuthContext';
import './Sidebar.css';

function Sidebar() {
  const { user } = useAuth();
  const location = useLocation();
  const isAdminPage = location.pathname === '/admin';

  return (
    <div className="sidebar">
      <div className="sidebar-container">
        <div className="dashboard">
          Dashboard 
          {/* 이미지? 수정필요 */}
        </div>
        <br />
        <ul>
          <li><Link to="/">메인 화면</Link></li>
          <li><Link to="/quizlist">문제 풀기</Link></li>
          <li><Link to="/editortest">테스트용 IDE</Link></li>
          <li><Link to="/editor">질의응답 게시판</Link></li>
          <li><Link to="/editor">Test Menu</Link></li>
          <li><Link to="/editor">Test Menu</Link></li>
          {isAdminPage && (
            <div className="admin-menu">
              <li><Link to="/admin">관리자 메뉴</Link></li>
              <li><Link to="/delete">삭제 메뉴</Link></li>
            </div>
          )}
        </ul>

        <div className="chat-wrapper">
          <div className="chat-container">
            <div className="chat-header">
              채팅헤더
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Sidebar;
