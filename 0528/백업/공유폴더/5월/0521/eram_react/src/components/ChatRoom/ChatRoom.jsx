// src/components/ChatRoom.jsx
import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import jwtDecode from 'jwt-decode';
import {
  MainContainer,
  ChatContainer,
  MessageList,
  Message,
  MessageInput,
  Sidebar,
  ConversationHeader,
  Avatar,
  ConversationList,
  Conversation,
} from '@chatscope/chat-ui-kit-react';
import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';

import { getChatRoomByPostId, getMessagesByRoomId, getMembersByRoomId } from '../../service/ChatService';

function ChatRoom() {
  const { postId } = useParams();
  const [messages, setMessages] = useState([]);
  const [members, setMembers] = useState([]);
  const [chatRoomId, setChatRoomId] = useState(null);
  const clientRef = useRef(null);
  const [currentUser, setCurrentUser] = useState(null);

  const setupWebSocket = (roomId) => {
    if (clientRef.current) {
      clientRef.current.deactivate();
    }

    const socket = new SockJS('http://localhost:8005/ws');
    const stompClient = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 10000,
      onConnect: () => {
        stompClient.subscribe(`/topic/chat/${roomId}`, (message) => {
          const receivedMessage = JSON.parse(message.body);
          if (receivedMessage.senderId !== currentUser?.mno) {
            setMessages((prev) => [...prev, { ...receivedMessage, direction: 'incoming', position: 'normal' }]);
          }
        });

        stompClient.subscribe(`/topic/chat/${roomId}/members`, (message) => {
          const updatedMembers = JSON.parse(message.body);
          setMembers(updatedMembers);
        });

        const token = localStorage.getItem('accessToken');
        if (token) {
          stompClient.publish({
            destination: `/app/chat/${roomId}/join`,
            body: JSON.stringify({ token }),
          });
        }
      },
    });

    stompClient.activate();
    clientRef.current = stompClient;
  };

  useEffect(() => {
    const loadChatRoom = async () => {
      try {
        const roomResponse = await getChatRoomByPostId(postId);
        const roomData = roomResponse.data;
        if (roomData.chatRoomId) {
          setChatRoomId(roomData.chatRoomId);

          const messagesResponse = await getMessagesByRoomId(roomData.chatRoomId);
          const messagesData = messagesResponse.data;
          setMessages(
            messagesData.map((msg) => ({
              ...msg,
              direction: msg.senderId === currentUser?.mno ? 'outgoing' : 'incoming',
              position: 'normal',
            }))
          );

          const membersResponse = await getMembersByRoomId(roomData.chatRoomId);
          const membersData = membersResponse.data;
          setMembers(membersData);

          const token = localStorage.getItem('accessToken');
          if (token) {
            const decodedToken = jwtDecode(token);
            const email = decodedToken.sub;
            const currentUser = membersData.find((member) => member.email === email);
            if (currentUser) {
              setCurrentUser(currentUser);
            }
          }
          setupWebSocket(roomData.chatRoomId);
        } else {
          console.error('Chat room not found for the provided post ID');
        }
      } catch (error) {
        console.error('Error fetching chat room:', error);
      }
    };

    loadChatRoom();

    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate();
      }
    };
  }, [postId, currentUser?.mno]);

  useEffect(() => {
    if (chatRoomId) {
      setupWebSocket(chatRoomId);
    }
    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate();
      }
    };
  }, [chatRoomId, currentUser?.mno]);

  const sendMessage = async (input) => {
    if (input.trim() && clientRef.current && clientRef.current.connected && chatRoomId && currentUser) {
      const token = localStorage.getItem('accessToken') || '';
      const messageData = { message: input, token };
      try {
        clientRef.current.publish({
          destination: `/app/chat/${chatRoomId}/send`,
          body: JSON.stringify(messageData),
        });
        setMessages((prev) => [...prev, { message: input, direction: 'outgoing', senderName: currentUser.name, position: 'normal' }]);
      } catch (error) {
        console.error('Error sending message:', error);
      }
    }
  };

  return (
    <div className="container">
      <MainContainer className="main-container">
        <Sidebar position="left" scrollable className="custom-sidebar">
          <ConversationList>
            <ConversationHeader>
              <ConversationHeader.Content userName="User list" />
            </ConversationHeader>
            {members.map((member) => (
              <Conversation key={member.mno} name={member.name}>
                <Avatar name={member.name} />
              </Conversation>
            ))}
          </ConversationList>
        </Sidebar>
        <ChatContainer className="custom-chat-container">
          <MessageList>
            {messages.map((msg, index) => (
              <Message
                key={index}
                model={{
                  message: msg.message,
                  direction: msg.direction,
                  sender: msg.senderName,
                  position: 'normal',
                }}
              />
            ))}
          </MessageList>
          <MessageInput placeholder="Type message here" onSend={sendMessage} />
        </ChatContainer>
      </MainContainer>
    </div>
  );
}

export default ChatRoom;
