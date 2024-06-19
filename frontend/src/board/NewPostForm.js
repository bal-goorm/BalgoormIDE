import React, { useState } from 'react';
import './NewPostForm.css';

const NewPostForm = ({ handleSavePost, handleCancel }) => {
  const [post, setPost] = useState({
    category: 'Python',
    title: '',
    content: '',
    author: '',
    password: '',
    order: 0 // 실제 게시글 순서는 서버 또는 게시글 상태에 따라 설정
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPost({ ...post, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleSavePost('discussion', post); // 게시글을 저장
  };

  return (
    <div className="post-form-container">
      <button className="close-button" onClick={handleCancel}>X</button> {/* 닫기 버튼 추가 */}
      <form onSubmit={handleSubmit} className="post-form">
        <h2 className="form-title">새 글 작성</h2>
        <label className="form-label">
          카테고리:
          <select name="category" value={post.category} onChange={handleChange} required className="form-select">
            <option value="Python">Python</option>
            <option value="C">C</option>
            <option value="C++">C++</option>
            <option value="JAVA">JAVA</option>
          </select>
        </label>
        <label className="form-label">
          제목:
          <input
            type="text"
            name="title"
            placeholder="제목을 입력하세요"
            value={post.title}
            onChange={handleChange}
            required
            className="form-input"
          />
        </label>
        <label className="form-label">
          내용:
          <textarea
            name="content"
            placeholder="내용을 입력하세요"
            value={post.content}
            onChange={handleChange}
            required
            className="form-textarea"
          />
        </label>
        <label className="form-label">
          작성자:
          <input
            type="text"
            name="author"
            placeholder="작성자를 입력하세요 (영어 1~19자 또는 한글 1~4자)"
            value={post.author}
            onChange={handleChange}
            required
            pattern="[A-Za-z]{1,19}|[가-힣]{1,4}"
            title="영어 1~19자 또는 한글 1~4자 입력 가능합니다."
            className="form-input"
          />
        </label>
        <label className="form-label">
          비밀번호:
          <input
            type="password"
            name="password"
            placeholder="비밀번호를 입력하세요 (숫자 4자)"
            value={post.password}
            onChange={handleChange}
            required
            pattern="\d{4}"
            title="숫자 4자만 입력 가능합니다."
            className="form-input"
          />
        </label>
        <input
          type="hidden"
          name="order"
          value={post.order}
        />
        <button type="submit" className="form-button">저장</button>
      </form>
    </div>
  );
};

export default NewPostForm;
