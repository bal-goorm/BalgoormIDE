/**
 * 로그인
 * react-bootstrap, react-hook-form 라이브러리 사용
 * axios로 post를 이용해 id, password 전송
 */

import axios from 'axios';
import React from 'react';
import { useForm } from 'react-hook-form';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo1 from "../img/Logo1.png";
import logo2 from "../img/Logo2.png";

function Login() {
    const { register, handleSubmit, formState: {errors} } = useForm();

    const submitForm = async (data) => {
        console.log(data);
        const { id, password }  = data;
        // try {
        //     const response = await axios.post('localhost:8080/login', {
        //         id,
        //         password
        //     });
        // }
        // catch(error) {
        //     alert(error.response.data);
        // }  
    }

    return (
        <div>
        <Navbar bg="light" expand="lg">
          <Container>
            <Navbar.Brand href="">
              <img src={logo2} alt="BalGoorm Logo" style={{width: '240px'}} />
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className='ms-auto'>
                <Nav.Link href="quiz">코딩퀴즈</Nav.Link>
                <Nav.Link href="board">게시판</Nav.Link>
                <Nav.Link href="chat">채팅</Nav.Link>
                <Button variant="primary" href="#login">로그인</Button>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        <Container className="d-flex flex-column align-items-center justify-content-center min-vh-100">
            <div className="text-center mb-4">
                <img src={logo1} alt="BalGoorm Logo" style={{ width: '300px' }} />
                <h1 className="mt-2" style={{ color: '#3498db' }}>BalGoorm</h1>
            </div>

            <Form onSubmit={handleSubmit(submitForm)} className="w-100" style={{ maxWidth: '300px' }}>
                <Form.Group controlId="id">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control type="text" placeholder="id 입력" {...register("id", {required: true})} />
                </Form.Group>
                <br></br>
                <Form.Group controlId="password">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" placeholder="비밀번호 입력" {...register("password", {required: true})}/>
                </Form.Group>
                {errors.id?.type === 'required' && (
                    <div className='login-error'>아이디를 입력해주세요.</div>
                )}
                <Button variant="primary" type="submit" className="w-100 mt-4">
                    로그인
                </Button>
            </Form>

            <div className="mt-3">
                <p>회원이 아니신가요? <a href="signup">회원가입</a></p>
            </div>    
        </Container>
        </div>
  );
}

export default Login;