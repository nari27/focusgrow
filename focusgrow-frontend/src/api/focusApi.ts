// src/api/focusApi.ts
import axios from 'axios';

export const saveFocusRecord = async (
  userId: number,
  plantId: number,
  focusTime: number
) => {
  const today = new Date().toISOString().split('T')[0]; // yyyy-mm-dd 형식
  const response = await axios.post('http://localhost:8080/api/focus-records', {
    userId,
    plantId,
    focusTime,
    date: today,
  });

  return response.data;
};
