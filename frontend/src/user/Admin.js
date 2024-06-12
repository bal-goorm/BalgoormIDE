/**
 * 관리자 페이지
 */

import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import logo1 from "../img/Logo1.png";
import logo2 from "../img/Logo2.png";

function Admin() {
  
  return (
    <div>
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
              <Nav.Link href="login">로그인</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  )
}

export default Admin;