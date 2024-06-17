import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, BrowserRouter, Navigate } from 'react-router-dom';
import Login from './user/Login.js'
import Signup from './user/Signup.js'
import { AuthProvider } from './user/auth/AuthContext.js';
import ProtectedRoute from './user/auth/ProtectedRoute.js';
import MyPage from './user/MyPage.js';
import Admin from './user/admin/Admin.js';
import Delete from './user/Delete.js';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Navigate to='/login' />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />
          <Route path='/mypage' element={<ProtectedRoute><MyPage /></ProtectedRoute>} />
          <Route path='/admin' element={<ProtectedRoute><Admin /></ProtectedRoute>}></Route>
          <Route path='/delete' element={<ProtectedRoute><Delete /></ProtectedRoute>}></Route>
          {/* <Route path='/edit' element={<ProtectedRoute><EditPage /></ProtectedRoute>}></Route> */}
          {/* <Route path='/chat' element={<Chat />}></Route> */}
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
