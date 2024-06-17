/**
 * 채팅 페이지
 * STOMP 라이브러리로 연결
 * 화면 구현 해야됨
 */
import React, { useEffect, useRef, useState } from 'react'
import { Stomp } from '@stomp/stompjs';
import axios from 'axios';
import { Container, Nav, Navbar } from 'react-bootstrap';
import logo2 from '../img/Logo2.png';
import { useNavigate } from 'react-router-dom';

function Chat() {
  const [message, setMessage] = useState([]); // 채팅 내용 저장
  const [inputValue, setInputValue] = useState(''); // 사용자 입력 저장 변수
  const stompClient = useRef(null);
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await axios.post('http://localhost:8080/logout');
      localStorage.removeItem('token');
      navigate('/login')
    } catch (error) {
      console.error('로그아웃 실패', error); 
    }
  }

  const connect = () => {
    const socket = new WebSocket("ws://localhost:8080/chat");
    stompClient.current = Stomp.over(socket);
    stompClient.current.subscribe(`/sub/chat`, (message) => {
      const newMessage = JSON.parse(message.body);
      setMessage((prevMessage) => [...prevMessage, newMessage]);
    });
  }

  const disconnect = () => {
    if(stompClient.current) {
      stompClient.current.disconnect();
    }
  }

  const fetchMessage = async () => {
    return await axios.get('http://localhost:8080/chat/1')
    .then(response => {
      setMessage(response.data)
    });
  }

  useEffect(() => {
    connect();
    fetchMessage();
    return () => {
      disconnect();
    }
  }, []);
  
  const sendMessage = () => {
    if(stompClient.current && inputValue) {
      stompClient.current.send("/pub/chat", {}, JSON.stringify({
        nickname: 'lee99',
        message: inputValue
      }));
      setInputValue('');
    }
  }

  return (
    <Navbar bg="light" expand="lg">
        <Container fluid>
          <Navbar.Brand href="/">
            <img src={logo2} alt="BalGoorm Logo" style={{width: '240px'}} />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto">
              <Nav.Link href="quiz">코딩퀴즈</Nav.Link>
              <Nav.Link href="board">게시판</Nav.Link>
              <Nav.Link href="chat">채팅</Nav.Link>
              <Nav.Link onClick={handleLogout}>로그아웃</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      
  )
}

export default Chat