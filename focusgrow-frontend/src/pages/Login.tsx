import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/authApi';
import styles from '../styles/Login.module.css';

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    if (!email || !password) {
      alert('이메일과 비밀번호를 모두 입력해주세요.');
      return;
    }

    try {
      const response = await login(email, password); // ⬅ 로그인 요청 및 응답 받기
      console.log(response.data);

      // 응답에서 유저 정보 꺼내기
      const { id, username, email: userEmail } = response.data;

      // 세션 스토리지에 저장 (로그인 상태 유지 목적)
      sessionStorage.setItem('id', id.toString());
      sessionStorage.setItem('username', username);
      sessionStorage.setItem('email', userEmail);
      console.log(id, username, userEmail); // 값이 다 잘 찍히는지 확인

      alert('로그인 성공!');
      navigate('/');
    } catch (error) {
      console.error(error);
      alert('로그인 실패');
    }
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.heading}>로그인</h2>
      <input
        type="email"
        name="email" // name 속성 추가!
        placeholder="이메일"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className={styles.input}
        autoComplete="email" // 브라우저 자동완성 유도
      />

      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className={styles.input}
      />
      <button onClick={handleLogin} className={styles.button}>
        로그인
      </button>
    </div>
  );
}
