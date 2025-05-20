import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register } from '../api/authApi';
import styles from '../styles/Login.module.css';

export default function Register() {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const validateEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  const handleRegister = async () => {
    if (!username || !email || !password) {
      setErrorMessage('모든 필드를 입력해주세요.');
      return;
    }

    if (!validateEmail(email)) {
      setErrorMessage('유효한 이메일을 입력해주세요.');
      return;
    }

    if (password.length < 6) {
      setErrorMessage('비밀번호는 최소 6자 이상이어야 합니다.');
      return;
    }

    setIsLoading(true);
    setErrorMessage('');

    try {
      await register(username, email, password);
      alert('회원가입 성공! 로그인 페이지로 이동합니다.');
      navigate('/login');
    } catch (error: any) {
      if (error?.response?.status === 409) {
        setErrorMessage('이미 사용 중인 이메일입니다.');
      } else if (error?.response?.data?.message) {
        setErrorMessage(error.response.data.message);
      } else {
        console.error(error);
        setErrorMessage('회원가입 실패');
      }
    } finally {
      setIsLoading(false); // ✅ 요청 끝나면 로딩 해제
    }
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.heading}>회원가입</h2>
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
      <input
        type="text"
        placeholder="사용자 이름"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        className={styles.input}
      />
      <input
        type="email"
        placeholder="이메일"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className={styles.input}
      />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className={styles.input}
      />
      <button
        onClick={handleRegister}
        className={styles.button}
        disabled={isLoading}
      >
        {isLoading ? '처리 중...' : '회원가입'}
      </button>
    </div>
  );
}
