import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import moment from 'moment';
import 'moment/locale/ko'; // 한글 로케일을 import
import './Board.css';
import NewPostForm from './NewPostForm';

moment.locale('ko'); // moment에 한글 로케일 설정

const Board = () => {
  const navigate = useNavigate();

  // 초기 게시글 데이터를 정의합니다.
  const initialPosts = {
    discussion: [
      {
        id: 1,
        title: '첫 번째 게시글',
        content: '이것은 첫 번째 게시글의 내용입니다.',
        author: '작성자1',
        order: 3, // 게시글 넘버링을 3부터 시작
        password: 'password1',
        category: 'Python',
        date: new Date(2023, 5, 18, 12, 34, 56) // Date 객체로 설정
      },
      {
        id: 2,
        title: '두 번째 게시글',
        content: '이것은 두 번째 게시글의 내용입니다.',
        author: '작성자2',
        order: 4, // 게시글 넘버링을 4로 설정
        password: 'password2',
        category: 'C',
        date: new Date(2023, 5, 17, 12, 34, 56) // Date 객체로 설정
      }
      // 더 많은 게시글 추가 가능
    ]
  };

  // 게시글 상태를 관리합니다.
  const [posts, setPosts] = useState(initialPosts);
  const [isWriting, setIsWriting] = useState(false); // 새 글 작성 상태를 관리합니다.
  const [visiblePost, setVisiblePost] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedCategory, setSelectedCategory] = useState('all'); // 카테고리 필터 상태 추가
  const [showModal, setShowModal] = useState(false); // 모달 표시 상태
  const [modalPost, setModalPost] = useState(null); // 모달에 표시할 게시글

  const postsPerPage = 10;

  // 게시글 저장 함수
  const handleSavePost = (board, newPost) => {
    newPost.date = new Date(); // 새 게시글의 날짜를 현재 날짜로 설정
    newPost.order = posts[board].length + 3; // 게시글 넘버링을 현재 게시글 수 + 3으로 설정
    setPosts(prevPosts => {
      const updatedPosts = { ...prevPosts };
      updatedPosts[board].push(newPost);
      
      // 게시글 순서를 다시 설정
      for (let i = 0; i < updatedPosts[board].length; i++) {
        updatedPosts[board][i].order = i + 3;
      }
      return updatedPosts;
    });
    setIsWriting(false); // 저장 후 글 작성 화면 종료
    navigate('/discussion'); // 저장 후 /discussion 경로로 리디렉션
  };

  const currentBoard = 'discussion';
  const filteredPosts = selectedCategory === 'all'
    ? posts[currentBoard]
    : posts[currentBoard].filter(post => post.category === selectedCategory);
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = filteredPosts.slice(indexOfFirstPost, indexOfLastPost);

  const handleEdit = (post) => {
    const password = prompt("게시글을 수정하려면 비밀번호를 입력하세요:");
    if (password === post.password) {
      navigate(`/edit/${post.id}`);
    } else {
      alert("비밀번호가 틀렸습니다!");
    }
  };

  const handleDelete = (postId) => {
    const post = posts[currentBoard].find(post => post.id === postId);
    const password = prompt("게시글을 삭제하려면 비밀번호를 입력하세요:");
    if (password === post.password) {
      setPosts(prevPosts => {
        const updatedPosts = { ...prevPosts };
        updatedPosts[currentBoard] = updatedPosts[currentBoard].filter(post => post.id !== postId);
        
        // 게시글 순서를 다시 설정
        for (let i = 0; i < updatedPosts[currentBoard].length; i++) {
          updatedPosts[currentBoard][i].order = i + 3;
        }
        return updatedPosts;
      });
    } else {
      alert("비밀번호가 틀렸습니다!");
    }
  };

  const obfuscateAuthor = (author) => {
    return author.charAt(0) + '*'.repeat(author.length - 1);
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleCommentSubmit = (postId, comment) => {
    setPosts(prevPosts => {
      const updatedPosts = { ...prevPosts };
      const postIndex = updatedPosts[currentBoard].findIndex(post => post.id === postId);
      if (!updatedPosts[currentBoard][postIndex].comments) {
        updatedPosts[currentBoard][postIndex].comments = [];
      }
      updatedPosts[currentBoard][postIndex].comments.push(comment);
      return updatedPosts;
    });
  };

  const openModal = (post) => {
    setModalPost(post);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setModalPost(null);
  };

  return (
    <div className="board-container">
      {isWriting ? (
        <NewPostForm handleSavePost={handleSavePost} handleCancel={() => setIsWriting(false)} />
      ) : (
        <>
          <div className="board-header">
            <h2 className="board-title">토론</h2>
            <button onClick={() => setIsWriting(true)} className="new-post-button">새 글 작성</button> {/* 오른쪽에 위치 */}
          </div>
          <div className="category-filter">
            <select onChange={(e) => setSelectedCategory(e.target.value)} value={selectedCategory} className="category-select">
              <option value="all">전체</option>
              <option value="Python">Python</option>
              <option value="C">C</option>
              <option value="C++">C++</option>
              <option value="JAVA">JAVA</option>
            </select>
          </div>
          <ul className="post-list">
            {currentPosts.length > 0 ? (
              currentPosts.map(post => (
                <li key={post.id} className="post-item">
                  <strong onClick={() => openModal(post)}>{post.order}. {post.title}</strong>
                  <div className="post-actions">
                    <span className="date">{moment(post.date).fromNow()}</span> {/* 한글로 날짜 표시 */}
                    <span className="author">{obfuscateAuthor(post.author)}</span>
                    <button onClick={() => handleEdit(post)} className="button">수정</button>
                    <button onClick={() => handleDelete(post.id)} className="button">삭제</button>
                  </div>
                </li>
              ))
            ) : (
              <li>게시글이 없습니다.</li>
            )}
          </ul>
          <footer className="footer">
            <div className="pagination">
              {[...Array(Math.ceil(filteredPosts.length / postsPerPage)).keys()].map(number => (
                <button
                  key={number + 1}
                  onClick={() => paginate(number + 1)}
                  className={`page-button ${currentPage === number + 1 ? 'active' : ''}`}
                >
                  {number + 1}
                </button>
              ))}
            </div>
          </footer>
          {showModal && (
            <Modal post={modalPost} handleCommentSubmit={handleCommentSubmit} closeModal={closeModal} />
          )}
        </>
      )}
    </div>
  );
};

const Modal = ({ post, handleCommentSubmit, closeModal }) => {
  const [comment, setComment] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    handleCommentSubmit(post.id, comment);
    setComment('');
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <span className="close" onClick={closeModal}>&times;</span>
        <h2>{post.title}</h2>
        <p>{post.content}</p>
        {post.comments && post.comments.length > 0 && <p className="comment-title">댓글</p>}
        <div className="comments">
          {post.comments && post.comments.map((comment, index) => (
            <p key={index} className="comment">{comment}</p>
          ))}
          <form onSubmit={handleSubmit} className="comment-form">
            <input
              type="text"
              placeholder="댓글을 입력하세요"
              value={comment}
              onChange={(e) => setComment(e.target.value)}
              className="comment-input"
            />
            <button type="submit" className="comment-button">댓글 달기</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Board;
