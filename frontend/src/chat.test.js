import { WebSocket, Server } from 'mock-socket';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import Chat from './chat/Chat.js'; 

global.WebSocket = WebSocket;

describe('Chat Component', () => {
  let mockServer;

  beforeEach(() => {
    mockServer = new Server('ws://localhost:8080/chat');

    mockServer.on('connection', socket => {
      socket.on('message', data => {
        const message = JSON.parse(data);
        if (message.message) {
          socket.send(JSON.stringify({ nickname: 'TestUser', message: message.message }));
        }
      });
    });
  });

  afterEach(() => {
    mockServer.stop();
  });

  test('should send and receive messages', async () => {
    render(<Chat />);

    const input = screen.getByPlaceholderText('메시지 입력');
    const sendButton = screen.getByRole('button', { name: '전송' });

    fireEvent.change(input, { target: { value: 'Hello!' } });
    fireEvent.click(sendButton);

    await waitFor(() => {
      expect(screen.getByText('TestUser: Hello!')).toBeInTheDocument();
    });
  });
});