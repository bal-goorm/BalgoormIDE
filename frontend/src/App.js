import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, BrowserRouter, Navigate, Router } from 'react-router-dom';
import Login from './user/Login.js'
import Signup from './user/Signup.js'
import { AuthProvider } from './user/auth/AuthContext.js';
import ProtectedRoute from './user/auth/ProtectedRoute.js';
import MyPage from './user/MyPage.js';
import Admin from './user/admin/Admin.js';
import Delete from './user/Delete.js';
import Chat from './chat/Chat.js';

function App() {
  return (
    <AuthProvider>
        <Routes>
          <Route path='/' element={<Navigate to='/login' />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />
          <Route path='/mypage' element={<MyPage />} />
          <Route path='/admin' element={<Admin />}></Route>
          <Route path='/delete' element={<Delete />}></Route>
          {/* <Route path='/edit' element={<ProtectedRoute><EditPage /></ProtectedRoute>}></Route> */}
          <Route path='/chat' element={<Chat />}></Route>
        </Routes>
    </AuthProvider>
  );
}

export default App;
