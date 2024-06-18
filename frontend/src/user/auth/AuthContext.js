import axios from 'axios';
import React, { useState, createContext, useContext } from 'react'

const AuthContext = createContext(null);

export function useAuth() {
  return useContext(AuthContext);
}

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const setAuthToken = (token) => {
        localStorage.setItem('token', token);
    }

    const setUserRole = (role) => {
        localStorage.setItem('role', role);
    }

    const login = async (userData) => {
        const { id, password } = userData;
        try {
            const response = await axios.post("http://localhost:8080/login", {
                id,
                password
            });
            const { token, role } = response.data;
            setAuthToken(token);
            setUserRole(role);
            setUser({ id, role });
        } catch(error) {
            alert(error.response ? error.response.data : "로그인 실패");
        }
    }

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        setUser(null);
    }

    const value = { user, login, logout, setAuthToken, setUserRole };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export default AuthContext;