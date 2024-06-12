import React, { useState } from 'react'
import io from 'socket.io-client';
import { useEffect } from 'react';

// const socket = io("http://localhost:8080");

// function Chat() {
//     useEffect(() => {
//         socket.on('connect', () => {
//           console.log('connected');
//         });
      
//         return () => {
//           socket.off("connect");
//           socket.close();
//         }
//       }, []);

//     const [message, setMessage] = useState("");
//     const [content, setContent] = useState([]);

//     const sendMessage = () => {
//         if(message) {
//             socket.emit('message', message);
//         }
//     }
    
//     return (
//     <div>Chat</div>
//   )
//}

// export default Chat