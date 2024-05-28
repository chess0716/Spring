// src/service/ChatService.js
import axios from 'axios';

// Backend server URL
const BASE_URL = 'http://localhost:8005';

// Create Axios instance
const axiosInstance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

// Function to get chat room by post ID
export const getChatRoomByPostId = (postId) => 
  axiosInstance.get(`/chat/room/by-post/${postId}`);

// Function to get messages by chat room ID
export const getMessagesByRoomId = (chatRoomId) => 
  axiosInstance.get(`/chat/messages/${chatRoomId}`);

// Function to send message to chat room
export const sendMessageToRoom = (chatRoomId, message) => 
  axiosInstance.post(`/chat/${chatRoomId}/send`, message);

// Function to get members by chat room ID
export const getMembersByRoomId = (chatRoomId) => 
  axiosInstance.get(`/chat/chatroom/${chatRoomId}/user`);
