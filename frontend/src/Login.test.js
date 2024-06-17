import React from 'react';
import { server } from './mocks/server';
import { rest } from 'msw'
import { render, screen, fireEvent, waitFor } from '@testing-library/react'
import Login from './user/Login';
import '@testing-library/jest-dom/extend-expect'

// 테스트 설정 구성
beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('successful login', async () => {
  render(<Login />)

  fireEvent.change(screen.getByLabelText(/아이디/i), {target: {value: 'test'}});
  fireEvent.change(screen.getByLabelText(/비밀번호/i), {target: {value: '1234'}});

  fireEvent.click(screen.getByText(/로그인/i));

  const successMessage = await screen.findByText(/login success/i);
  expect(successMessage).toBeInTheDocument();
 });

 test('failed login', async () => {
  render(<Login />);
  
  fireEvent.change(screen.getByLabelText(/아이디/i), { target: { value: 'wrong' } });
  fireEvent.change(screen.getByLabelText(/비밀번호/i), { target: { value: 'wrong' } });
  
  fireEvent.click(screen.getByText(/로그인/i));
  
  const errorMessage = await screen.findByText(/login failed/i);
  expect(errorMessage).toBeInTheDocument();
});