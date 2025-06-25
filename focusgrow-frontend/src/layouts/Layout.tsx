import { Link, Outlet, useNavigate } from 'react-router-dom';
import '../styles/Layout.css';
import { useState, useEffect } from 'react';

export default function Layout() {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(!!sessionStorage.getItem('id'));

  // 로그인 상태를 매번 최신으로 유지하기 위해 sessionStorage 감시
  useEffect(() => {
    const interval = setInterval(() => {
      setIsLoggedIn(!!sessionStorage.getItem('id'));
    }, 500); // 0.5초마다 확인

    return () => clearInterval(interval); //cleanup
  }, []);

  const handleProtectedRoute = (path: string) => {
    if (!isLoggedIn) {
      alert('로그인이 필요합니다.');
      navigate('/login');
    } else {
      navigate(path);
    }
  };

  const handleLogout = () => {
    sessionStorage.clear();
    alert('로그아웃 되었습니다.');
    setIsLoggedIn(false); // 상태업데이트
    navigate(0); // 브라우저 새로고침
  };

  return (
    <div className="layout-wrapper">
      <header className="layout-header">
        <Link to="/" className="logo">
          FocusGrow
        </Link>
        <div className="bar">
          <button onClick={() => navigate('/')}>홈</button>
          <button onClick={() => handleProtectedRoute('/timer')}>타이머</button>
          <button onClick={() => handleProtectedRoute('/stats')}>통계</button>
        </div>
        <div className="login">
          {!isLoggedIn && <Link to="/login">로그인</Link>}
          {!isLoggedIn && <Link to="/register">회원가입</Link>}
          {isLoggedIn && <button onClick={handleLogout}>로그아웃</button>}
        </div>
      </header>

      <main className="layout-main">
        <div className="layout-main-inner">
          <Outlet />
        </div>
      </main>
    </div>
  );
}
