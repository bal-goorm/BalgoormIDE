// /**
//  * 채팅 페이지
//  * STOMP 라이브러리로 연결
//  * 화면 구현 해야됨
//  */

// import React, { useEffect, useRef, useState } from 'react'
// import { Stomp } from '@stomp/stompjs';
// import axios from 'axios';
// import { Button, Container, Form, ListGroup, Nav, Navbar } from 'react-bootstrap';
// import logo2 from '../img/Logo2.png';
// import { useNavigate } from 'react-router-dom';
// import NavBar from '../user/components/Navbar.js';

// function Chat() {
//   const [message, setMessage] = useState([]); // 채팅 내용 저장
//   const [inputValue, setInputValue] = useState(''); // 사용자 입력 저장 변수
//   const stompClient = useRef(null);

//   const connect = () => {
//     const socket = new WebSocket("ws://localhost:8080/chat");
//     stompClient.current = Stomp.over(socket);
//     stompClient.current.subscribe(`/sub/chat`, (message) => {
//       const newMessage = JSON.parse(message.body);
//       setMessage((prevMessage) => [...prevMessage, newMessage]);
//     });
//   }

//   const disconnect = () => {
//     if(stompClient.current) {
//       stompClient.current.disconnect();
//     }
//   }

//   const fetchMessage = async () => {
//     return await axios.get('http://localhost:8080/chat/1')
//     .then(response => {
//       setMessage(response.data);
//     });
//   }

//   useEffect(() => {
//     connect();
//     fetchMessage();
//     return () => {
//       disconnect();
//     }
//   }, []);
  
//   const sendMessage = () => {
//     if(stompClient.current && inputValue) {
//       stompClient.current.send("/pub/chat", {}, JSON.stringify({
//         nickname: 'lee99',
//         message: inputValue
//       }));
//       setInputValue('');
//     }
//   }

//   const handleMessageChange = (e) => {
//     setInputValue(e.target.value);
//   }

//   const handleKeyPress = (e) => {
//     if(e.key === 'Enter') {
//       sendMessage();
//     }
//   }

//   return (
//     <div>
//       <NavBar />
//       <Container style={{ marginTop: '20px' }}>
//         <ListGroup>
//           {message.map((msg, index) => (
//             <ListGroup.Item key={index}>
//               <strong>{msg.nickname}:</strong> {msg.message}
//               </ListGroup.Item>
//           ))}
//         </ListGroup>
//         <Form>
//           <Form.Group className="mb-3" controlId="chatMessageInput">
//             <Form.Control 
//             type="text" 
//             placeholder="메시지 입력" 
//             value={inputValue} 
//             onChange={handleMessageChange} 
//             onKeyPress={handleKeyPress} 
//             />
//           </Form.Group>
//           <Button variant="primary" 
//           onClick={sendMessage}
//           >전송</Button>
//         </Form>
//       </Container> 
//     </div>
//   )
// }

// export default Chat;