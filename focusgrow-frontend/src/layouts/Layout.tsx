import { Link, Outlet, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function Layout() {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  //로그인 여부 체크
  useEffect(() => {
    const userId = sessionStorage.getItem('id');
    setIsLoggedIn(!!userId); //존재 여부를 불리언으로 변환
  }, []);

  // 로그아웃 기능
  const handleLogout = () => {
    sessionStorage.clear(); // 세션 정보 삭제
    alert('로그아웃 되었습니다.');
    setIsLoggedIn(false); // 상태 반영
    navigate('/'); // 홈으로 이동
  };

  return (
    <div>
      <header className="p-4 bg-green-200 flex gap-4">
        <Link to="/">홈</Link>
        {!isLoggedIn && <Link to="/login">로그인</Link>}
        {!isLoggedIn && <Link to="/register">회원가입</Link>}
        {isLoggedIn && (
          <button onClick={handleLogout} className="text-blue-800 uderLine">
            로그아웃
          </button>
        )}
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
}
