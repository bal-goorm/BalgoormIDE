import { render, screen, fireEvent } from '@testing-library/react';
import LoginPage from './LoginPage';
import { BrowserRouter } from 'react-router-dom';

describe('LoginPage', () => {
  test('allows the user to log in successfully', () => {
    render(
      <BrowserRouter>
        <LoginPage />
      </BrowserRouter>
    );

    // 사용자 이름과 비밀번호 입력 필드 찾기
    const usernameInput = screen.getByLabelText(/username/i);
    const passwordInput = screen.getByLabelText(/password/i);

    // 입력 필드에 값 입력
    fireEvent.change(usernameInput, { target: { value: 'user1' } });
    fireEvent.change(passwordInput, { target: { value: 'password123' } });

    // 로그인 버튼을 찾아 클릭 이벤트 발생
    fireEvent.click(screen.getByRole('button', { name: /login/i }));

    // 로그인 함수 호출 검증 (이 부분은 추가적인 목(mock) 설정이 필요할 수 있음)
    // 예: expect(mockedLoginFunction).toHaveBeenCalledWith('user1', 'password123');
  });
});
