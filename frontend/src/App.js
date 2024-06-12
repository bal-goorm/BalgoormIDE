import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Login from './user/Login.js'
import Signup from './user/Signup.js'
import Chat from './chat/Chat.js';
import { AuthProvider } from './user/auth/AuthContext.js';
import ProtectedRoute from './user/auth/ProtectedRoute.js';
import MyPage from './user/MyPage.js';

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/mypage' element={<MyPage />} />
          <Route path='/signup' element={<Signup />}/>
          {/* <Route path='/edit' element={<EditPage />}></Route> */}
          {/* <Route path='/chat' element={<Chat />}></Route> */}
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
