import { useEffect, useState } from 'react';
import { getUserLevel, getTotalFocusTime } from '../api/focusApi';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

// ✅ 여기!
const formatTime = (minutes: number) => {
  const hours = Math.floor(minutes / 60);
  const mins = minutes % 60;
  return `${hours}시간 ${mins}분`;
};

export default function Home() {
  const [level, setLevel] = useState<number>(1); //초기값은 1레벨
  const [plantImage, setPlantImage] = useState<string>('stage-1.png');
  const [totalFocusTime, setTotalFocusTime] = useState<number>(0);

  useEffect(() => {
    const userId = Number(sessionStorage.getItem('id'));
    if (!userId) return;

    // 레벨 조회
    getUserLevel(userId).then((res) => {
      setLevel(res);

      if (res >= 6) setPlantImage('stage-6.png');
      else if (res >= 5) setPlantImage('stage-5.png');
      else if (res >= 4) setPlantImage('stage-4.png');
      else if (res >= 3) setPlantImage('stage-3.png');
      else if (res >= 2) setPlantImage('stage-2.png');
      else setPlantImage('stage-1.png');
    });

    //누적 집중 시간 조회
    getTotalFocusTime(userId).then((res) => {
      setTotalFocusTime(res); // res는 분 단위 숫자
    });
  }, []);

  return (
    <div>
      <h2 className="main-title">집중할수록, 식물이 자라요 🌱</h2>

      <div className="status-cards">
        <div className="card">
          <p className="card-title">새싹 단계</p>
          <p className="card-subtitle">누적 집중 시간</p>
          <p className="card-value">{formatTime(totalFocusTime)}</p>
        </div>
        <div className="card">
          <p className="card-title">현재 레벨</p>
          <p className="card-subtitle">집중한 시간에 따라 성장 중</p>
          <p className="card-value">🏆 레벨 {level}</p>
        </div>
      </div>

      <div className="plant-image">
        <img src={`/plants/${plantImage}`} alt="성장하는 식물" />
      </div>

      <div className="today-summary">
        <p className="today-time">
          오늘 집중 시간: <span className="highlight">00시간 25분</span>
        </p>
        <p className="week-goal">이번 주 목표: 5시간 중 2시간 달성 🎯</p>
      </div>

      <p className="home-ment">
        오늘도 타이머를 눌러 집중을 시작해보세요. <br />
        작은 씨앗이 자라고 있어요 🌿
      </p>

      <div className="start-button-wrapper">
        <Link to="/timer" className="start-button">
          타이머 시작하기
        </Link>
      </div>

      <div className="focus-history">
        <h3>최근 집중 기록</h3>
        <ul>
          <li>6/10(화) 오후 2:00 - 2:25 (25분)</li>
          <li>6/09(월) 오후 10:00 - 10:50 (50분)</li>
        </ul>
      </div>
    </div>
  );
}
