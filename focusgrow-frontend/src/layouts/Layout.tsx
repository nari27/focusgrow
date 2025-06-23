import { Link, Outlet, useNavigate } from 'react-router-dom';
import '../styles/Layout.css';

export default function Layout() {
  const navigate = useNavigate();
  const isLoggedIn = !!sessionStorage.getItem('id');

  const handleLogout = () => {
    sessionStorage.clear();
    alert('로그아웃 되었습니다.');
    navigate('/');
  };

  return (
    <div className="layout-wrapper">
      <header className="layout-header">
        <Link to="/" className="logo">
          FocusGrow
        </Link>
        <div className="bar">
          {isLoggedIn && <Link to="/">홈</Link>}
          {isLoggedIn && <Link to="/timer">타이머</Link>}
          {isLoggedIn && <Link to="">통계</Link>}
          {!isLoggedIn && <Link to="/">홈</Link>}
          {!isLoggedIn && <Link to="/timer">타이머</Link>}
          {!isLoggedIn && <Link to="">통계</Link>}
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
