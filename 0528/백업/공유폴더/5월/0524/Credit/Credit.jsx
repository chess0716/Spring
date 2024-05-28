import React, { useState } from 'react';
import '../../styles/BtnStyle.scss';
import UserStore from '../../store/UserStore';
import CreditService from '../../service/CreditService'; // 서비스 임포트

function Credit() {
  return (
    <section className="Mystory_container">
      <CreditInfo />
      <CreditCount />
    </section>
  );
}

function CreditCount() {
  const [amount, setAmount] = useState('');
  const [balance, setBalance] = useState(0);
  const [showInput, setShowInput] = useState(false);

  const memberId = UserStore.currentUser.id; // 사용자 ID 가져오기

  const handleAmountChange = (e) => {
    setAmount(e.target.value);
  };

  const handleAddCredit = async () => {
    try {
      await CreditService.addCredit(memberId, amount);
      alert('Credit added successfully!');
      setAmount('');
      const updatedBalance = await CreditService.getBalance(memberId);
      setBalance(updatedBalance);
    } catch (error) {
      alert('Error adding credit');
    }
  };

  const toggleInput = async () => {
    setShowInput(!showInput);
    if (!showInput) {
      const updatedBalance = await CreditService.getBalance(memberId);
      setBalance(updatedBalance);
    }
  };

  return (
    <div>
      <button className="btnstlye" type="button" onClick={toggleInput}>
        +
      </button>
      {showInput && (
        <div>
          <div>
            <input
              type="number"
              value={amount}
              onChange={handleAmountChange}
              placeholder="Enter amount"
              className="amountInput"
            />
            <button className="btnstlye" type="button" onClick={handleAddCredit}>
              Add Credit
            </button>
          </div>
          <div>Your balance: {balance}₩</div>
        </div>
      )}
    </div>
  );
}

function CreditInfo() {
  return (
    <div className="story storyInfo">
      <span className="story_number story_child">충전하기</span>
    </div>
  );
}

export default Credit;
