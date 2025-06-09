import { useState, useEffect } from 'react';
import axios from 'axios';
import { saveFocusRecord } from '../api/focusApi';
import PlantAnimation, { PlantStage } from './PlantAnimation'; // 타입도 함께 import
import '../styles/Timer.css';

export default function TimerComponent() {
  const [inputMinutes, setInputMinutes] = useState(25);
  const [seconds, setSeconds] = useState(1500);
  const [isRunning, setIsRunning] = useState(false);
  const [userLevel, setUserLevel] = useState<number | null>(null);
  const [plantStage, setPlantStage] = useState<PlantStage | null>(null);
  const [totalFocusTime, setTotalFocusTime] = useState<number>(0);

  useEffect(() => {
    let interval: ReturnType<typeof setInterval>;

    if (isRunning && seconds > 0) {
      interval = setInterval(() => {
        setSeconds((prev) => prev - 1);
      }, 1000);
    }

    if (isRunning && seconds === 0) {
      const userId = sessionStorage.getItem('id');
      const plantId = 1;

      if (userId) {
        saveFocusRecord(Number(userId), plantId, inputMinutes * 60).then(
          async () => {
            alert('집중 시간이 저장되었습니다.');

            const res = await axios.get(`/api/levels/user/${userId}`);
            const level = res.data;
            setUserLevel(level);
            setTotalFocusTime((prev) => prev + inputMinutes * 60);

            if (level === 1) setPlantStage('seed');
            else if (level === 2) setPlantStage('sprout');
            else if (level === 3) setPlantStage('grow');
            else setPlantStage('flower');
          }
        );
      }
    }

    return () => clearInterval(interval);
  }, [isRunning, seconds, inputMinutes]);

  const formatTime = (sec: number) => {
    const minutes = Math.floor(sec / 60)
      .toString()
      .padStart(2, '0');
    const seconds = (sec % 60).toString().padStart(2, '0');
    return `${minutes}:${seconds}`;
  };

  const formatTotalFocusTime = (totalSec: number) => {
    const h = Math.floor(totalSec / 3600);
    const m = Math.floor((totalSec % 3600) / 60);
    return `${h}h ${m}m`;
  };

  const progressPercent =
    ((inputMinutes * 60 - seconds) / (inputMinutes * 60)) * 100;

  return (
    <div className="timer-wrapper">
      <h1>FocusGrow</h1>

      <div>
        <div className="focus-stat-card">
          <div>Total Focus Time</div>
          <div>{formatTotalFocusTime(totalFocusTime)}</div>
        </div>
        <div className="focus-stat-card">
          <div>Level</div>
          <div>{userLevel ?? '-'}</div>
        </div>
      </div>

      {plantStage && (
        <div className="mb-2">
          <PlantAnimation stage={plantStage} />
          <p>growing...</p>
        </div>
      )}

      <div>{formatTime(seconds)}</div>

      <div className="progress-bar">
        <div
          className="progress-bar-inner"
          style={{ width: `${progressPercent}%` }}
        ></div>
      </div>

      {!isRunning && (
        <div>
          <label>Time (minutes):</label>
          <input
            type="number"
            min="1"
            value={inputMinutes}
            onChange={(e) => setInputMinutes(Number(e.target.value))}
          />
        </div>
      )}

      <div>
        {!isRunning ? (
          <button
            onClick={() => {
              setSeconds(inputMinutes * 60);
              setIsRunning(true);
            }}
            className="btn-start"
          >
            Start
          </button>
        ) : (
          <button onClick={() => setIsRunning(false)}>Pause</button>
        )}

        <button
          onClick={() => {
            setIsRunning(false);
            setSeconds(inputMinutes * 60);
          }}
          className="btn-reset"
        >
          Reset
        </button>
      </div>
    </div>
  );
}
