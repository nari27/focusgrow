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
        <div>
          <Link to="/">홈</Link>
          {isLoggedIn && <Link to="/timer">타이머</Link>}
        </div>
        <div>
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
