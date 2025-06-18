import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

// 집중 기록 저장
export const saveFocusRecord = async (
  userId: number,
  plantId: number,
  focusTime: number
) => {
  const today = new Date().toISOString().split('T')[0]; // yyyy-mm-dd 형식
  const response = await axios.post(`${API_URL}/focus-records`, {
    userId,
    plantId,
    focusTime,
    date: today,
  });

  return response.data;
};

// 유저 레벨 조회
export const getUserLevel = async (userId: number) => {
  const response = await axios.get(`${API_URL}/levels/user/${userId}`);
  return response.data;
};

// 누적 집중 시간 조회
export const getTotalFocusTime = async (userId: number) => {
  const response = await axios.get(
    `${API_URL}/focus-records/total-time/${userId}`
  );
  return response.data; // 분 단위 숫자
};
