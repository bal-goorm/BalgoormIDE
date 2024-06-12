import axios from 'axios';
import React, { useState, createContext, useContext } from 'react'

const AuthContext = createContext(null);

export function useAuth() {
  return useContext(AuthContext);
}

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const login = async (userData) => {
        const { id, password } = userData;
        try {
            const response = await axios.get("http://localhost:8080/login", {
                id,
                password
            })
        }
        catch(error) {
            alert(error.response.userData);
        }
    }

    const logout = () => {
        setUser(null); // 로그아웃 로직 들어가야됨
    }

    const value = { user, login, logout };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export default AuthContext;