import { Link, Outlet, useNavigate } from 'react-router-dom';

export default function Layout() {
  const navigate = useNavigate();

  // 매 렌더마다 로그인 여부 체크
  const isLoggedIn = !!sessionStorage.getItem('id');

  const handleLogout = () => {
    sessionStorage.clear();
    alert('로그아웃 되었습니다.');
    navigate('/');
  };

  return (
    <div>
      <header>
        <Link to="/">홈</Link>
        {!isLoggedIn && <Link to="/login">로그인</Link>}
        {!isLoggedIn && <Link to="/register">회원가입</Link>}
        {isLoggedIn && <button onClick={handleLogout}>로그아웃</button>}
        {isLoggedIn && <Link to="/timer">타이머</Link>}
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
}
