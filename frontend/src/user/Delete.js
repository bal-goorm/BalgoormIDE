/**
 * 회원 탈퇴 페이지
 * 제대로 동작하는지 확인해야됨
 */

import React, { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Nav, Navbar, Row } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo2 from "../img/Logo2.png";
import logo1 from '../img/Logo1.png';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useAuth } from './auth/AuthContext';
import NavBar from './components/Navbar.js';


function Delete({userId}) {
  const [pwd, setPwd] = useState('');
  const [message, setMessage] = useState('');
  const { user, logout} = useAuth();
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

  const handleDelete = async () => {
    if(pwd) {
      try {
        await axios.delete(`http://localhost:8080/deleteUser/${user.id}`, {data: {pwd}});
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        alert("계정이 삭제되었습니다. 그동안 저희 서비스를 이용해주셔서 감사합니다.");
        handleLogout();
      } catch (error) {
        alert('계정 삭제 실패', error);
      }
    }
  }

  return (
    <div>
      <NavBar showExtraLinks={true}/>

      <Container className='flex-grow-1'>
        <div className="text-center mb-4 mt-4">
          <img src={logo1} alt="BalGoorm Logo" style={{ width: '300px' }} />
          <h1 className="mt-2" style={{ color: '#3498db' }}>BalGoorm</h1>
        </div>

        <div className='border border-2 border-secondary rounded'>
          <Row className='justify-content-center'>
            <Col md={6} className='main-content'>
              <h1 className='text-center fw-bold'>회원탈퇴</h1>
              <br />
              <p className='fs-4'>회원 탈퇴하시면 발구름 관련 모든 서비스에 대한 정보가 사라집니다.</p>
              <p className='fs-4'>위 내용을 확인하셨으면, 비밀번호를 입력하고 회원탈퇴 버튼을 눌러주세요.</p>
              <br />
              <Form>
                <Form.Group as={Row} className="d-flex mb-3">
                  <Form.Label className='fw-bolder' column sm={3}>비밀번호</Form.Label>
                  <Col sm={5}>
                    <Form.Control 
                    type="password" 
                    value={pwd}
                    onChange={(e) => setPwd(e.target.value)}
                    placeholder='비밀번호를 입력하세요.' />
                  </Col>
                </Form.Group>

                <Button variant="primary" onClick={handleDelete} className="w-100 mt-4">
                  회원 탈퇴
                </Button>
              </Form>
            </Col>
          </Row>
        </div>
      </Container>
    </div>
  )
}

export default Delete