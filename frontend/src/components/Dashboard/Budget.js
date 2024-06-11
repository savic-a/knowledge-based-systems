import React, { useEffect, useState } from 'react';

const Budget = () => {
  const [creditCards, setCreditCards] = useState([]);

//   useEffect(() => {
//     const fetchCreditCards = async () => {
//       try {
//         const cards = await clientService.getBudget();
//         setCreditCards(cards);
//       } catch (error) {
//         console.error('Failed to fetch credit cards:', error);
//       }
//     };

//     fetchCreditCards();
//   }, []);

  
  return (
    <div className="framed-container">
      <div className='component-title'>Budget</div>
      <span>$</span>
      <span>5000</span>
    </div>
  );
};

export default Budget;
