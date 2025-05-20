import axios from 'axios';

// 로그인
export const login = async (email: string, password: string) => {
  return await axios.post(
    'http://localhost:8080/api/users/login',
    { email, password },
    { withCredentials: true }
  );
};

// 회원가입
export const register = async (
  username: string,
  email: string,
  password: string
) => {
  return await axios.post('http://localhost:8080/api/users', {
    username,
    email,
    password,
  });
};
