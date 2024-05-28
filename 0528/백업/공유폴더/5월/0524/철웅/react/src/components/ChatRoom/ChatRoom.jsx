import React, { useEffect, useState, useRef } from 'react';
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
  ConversationList,
  Conversation,
  InputToolbox,
} from '@chatscope/chat-ui-kit-react';
import { getChatRoomByPostId, getMessagesByRoomId, getChatRoomMembers } from '../../service/ChatService';
import CreditService from '../../service/CreditService';
import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import styles from './CustomChatStyle.module.css';

function ChatRoom() {
  const { postId } = useParams();
  const [messages, setMessages] = useState([]);
  const [members, setMembers] = useState([]);
  const [chatRoomId, setChatRoomId] = useState(null);
  const clientRef = useRef(null);
  const [currentUser, setCurrentUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [balance, setBalance] = useState(0);
  const [amount, setAmount] = useState('');
  const [showAmountInput, setShowAmountInput] = useState(false);

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
          setMessages((prev) => [
            ...prev,
            {
              ...receivedMessage,
              direction: receivedMessage.senderId === currentUser.mno ? 'outgoing' : 'incoming',
              position: 'normal',
            },
          ]);
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
        const { data: roomData } = await getChatRoomByPostId(postId);
        if (roomData.chatRoomId) {
          setChatRoomId(roomData.chatRoomId);

          const { data: membersData } = await getChatRoomMembers(roomData.chatRoomId);
          setMembers(membersData);

          const token = localStorage.getItem('accessToken');
          if (token && membersData) {
            const decodedToken = jwtDecode(token);
            const email = decodedToken.sub;
            const user = membersData.find((member) => member.email === email);
            if (user) {
              setCurrentUser(user);
              const balanceData = await CreditService.getBalance(user.mno); // 보유 금액 불러오기
              setBalance(balanceData);
            } else {
              console.warn('User not found in chat room members, setting as unknown');
              setCurrentUser({ name: 'unknown', mno: -1 });
            }
          }
        } else {
          setError('Chat room not found for the provided post ID');
        }
      } catch (error) {
        setError('Error fetching chat room');
        setCurrentUser({ name: 'unknown', mno: -1 });
      } finally {
        setLoading(false);
      }
    };

    loadChatRoom();

    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate();
      }
    };
  }, [postId]);

  useEffect(() => {
    if (chatRoomId && currentUser) {
      const loadMessages = async () => {
        try {
          const { data: messagesData } = await getMessagesByRoomId(chatRoomId);
          setMessages(
            messagesData.map((msg) => ({
              ...msg,
              direction: msg.senderId === currentUser.mno ? 'outgoing' : 'incoming',
              position: 'normal',
            }))
          );
        } catch (error) {
          setError('Error fetching messages');
        }
      };

      loadMessages();
      setupWebSocket(chatRoomId);
    }

    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate();
      }
    };
  }, [chatRoomId, currentUser]);

  const sendMessage = async (input) => {
    if (input.trim() && clientRef.current && clientRef.current.connected && chatRoomId && currentUser) {
      const token = localStorage.getItem('accessToken') || '';
      const messageData = { message: input, token, senderName: currentUser.name, senderId: currentUser.mno };
      try {
        clientRef.current.publish({
          destination: `/app/chat/${chatRoomId}/send`,
          body: JSON.stringify(messageData),
        });
      } catch (error) {
        console.error('Error sending message:', error);
      }
    }
  };

  const handleAmountChange = (e) => {
    setAmount(e.target.value);
  };

  const handleSendAmount = async () => {
    if (amount && chatRoomId && currentUser) {
      try {
        // 금액 송금 로직 추가
        alert('Amount sent successfully!');
        setAmount('');
        const updatedBalance = await CreditService.getBalance(currentUser.mno);
        setBalance(updatedBalance);
      } catch (error) {
        console.error('Error sending amount:', error);
      }
    }
  };

  const toggleAmountInput = async () => {
    setShowAmountInput(!showAmountInput);
    if (!showAmountInput) {
      const updatedBalance = await CreditService.getBalance(currentUser.mno);
      setBalance(updatedBalance);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error && !currentUser) {
    return <div>{error}</div>;
  }

  if (!chatRoomId || !currentUser) {
    return <div>Chat room or user not found</div>;
  }

  return (
    <div className={styles.container}>
      <MainContainer className={styles.mainContainer}>
        <Sidebar position="left" scrollable className={styles.customSidebar}>
          <div className={styles.sidebarHeader}>사용자 목록</div>
          <div className={styles.sidebarContent}>
            <ConversationList>
              {members && members.length > 0 ? (
                members.map((member, index) => (
                  <Conversation key={index} name={member.name}>
                    {/* <Avatar name={member.name} /> */}
                  </Conversation>
                ))
              ) : (
                <Conversation name="No members found" />
              )}
            </ConversationList>
          </div>
        </Sidebar>
        <ChatContainer className={styles.customChatContainer}>
          <MessageList className={styles.messageList}>
            {messages && messages.length > 0 ? (
              messages.map((msg, index) => (
                <Message
                  key={index}
                  model={{
                    message: `${msg.senderName}: ${msg.message}`,
                    direction: msg.senderId === currentUser.mno ? 'outgoing' : 'incoming',
                    sender: msg.senderName,
                    position: 'normal',
                  }}
                  className={styles.boldText} // 텍스트 두껍게 설정
                />
              ))
            ) : (
              <Message
                model={{
                  message: 'No messages found',
                  direction: 'incoming',
                  sender: 'System',
                  position: 'normal',
                }}
                className={styles.boldText} // 텍스트 두껍게 설정
              />
            )}
          </MessageList>
          <MessageInput
            placeholder="Type message here"
            onSend={sendMessage}
            attachButton={false}
            className={styles.messageInputContainer}
          />
          <InputToolbox>
            <button className={styles.addAmountButton} type="button" onClick={toggleAmountInput}>
              송금
            </button>
            {showAmountInput && (
              <div className={styles.amountContainer}>
                <input
                  type="number"
                  value={amount}
                  onChange={handleAmountChange}
                  placeholder="Enter amount"
                  className={styles.amountInput}
                />
                <button type="button" onClick={handleSendAmount} className={styles.sendAmountButton}>
                  Send Amount
                </button>
                <div className={styles.balanceContainer}>Your balance: {balance}₩</div>
              </div>
            )}
          </InputToolbox>
        </ChatContainer>
      </MainContainer>
    </div>
  );
}

export default ChatRoom;
