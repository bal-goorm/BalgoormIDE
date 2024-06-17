import React from 'react';
import { Link } from 'react-router-dom';
import './Sidebar.css';

function Sidebar() {
  return (
    <div className="sidebar">
      <div className="sidebar-container">
        <div className="dashboard">
          사이트 관리 
          {/* 이미지? 수정필요 */}
        </div>
        <br />
        <ul>
          <li><Link to="/">유저 관리</Link></li>
          <li><Link to="/">게시판 정보</Link></li>
          <li><Link to="/">채팅 관리</Link></li>
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
