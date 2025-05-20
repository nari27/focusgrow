import { useState, useEffect } from 'react';
import { saveFocusRecord } from '../api/focusApi';

export default function Timer() {
  const [inputMinutes, setInputMinutes] = useState(30); // 사용자 입력용 (기본 30분)
  const [seconds, setSeconds] = useState(1800);
  const [isRunning, setIsRunning] = useState(false);

  useEffect(() => {
    let interval: ReturnType<typeof setInterval>;

    if (isRunning && seconds > 0) {
      interval = setInterval(() => {
        setSeconds((prev) => prev - 1);
      }, 1000);
    }

    // 타이머가 0이 되는 시점에 저장
    if (isRunning && seconds === 0) {
      const userId = sessionStorage.getItem('id');
      const plantId = 1; // ⚠️ 임시 값, 추후 식물 선택 구현 후 동적으로 설정

      if (userId) {
        saveFocusRecord(Number(userId), plantId, inputMinutes * 60)
          .then(() => alert('집중 시간이 저장되었습니다.'))
          .catch((err) => {
            console.error(err);
            alert('집중 시간 저장 실패');
          });
      }
    }

    return () => clearInterval(interval);
  }, [isRunning, seconds]);

  const formatTime = (sec: number) => {
    const minutes = Math.floor(sec / 60)
      .toString()
      .padStart(2, '0');
    const seconds = (sec % 60).toString().padStart(2, '0');
    return `${minutes}:${seconds}`;
  };

  return (
    <div className="p-8 text-center">
      <h2 className="text-3xl mb-6">⏱ 집중 타이머</h2>

      {/* ⏱ 시간 입력 */}
      {!isRunning && (
        <div className="mb-4">
          <label className="mr-2 text-lg">시간(분):</label>
          <input
            type="number"
            min="1"
            value={inputMinutes}
            onChange={(e) => setInputMinutes(Number(e.target.value))}
            className="border px-2 py-1 rounded w-20 text-center"
          />
        </div>
      )}

      <div className="text-5xl font-mono mb-4">{formatTime(seconds)}</div>

      <div className="space-x-4">
        {!isRunning ? (
          <button
            onClick={() => {
              setSeconds(inputMinutes * 60); // 입력한 시간으로 설정
              setIsRunning(true);
            }}
            className="bg-green-500 text-white px-4 py-2 rounded"
          >
            시작
          </button>
        ) : (
          <button
            onClick={() => setIsRunning(false)}
            className="bg-red-500 text-white px-4 py-2 rounded"
          >
            일시정지
          </button>
        )}

        <button
          onClick={() => {
            setIsRunning(false);
            setSeconds(inputMinutes * 60);
          }}
          className="bg-gray-300 px-4 py-2 rounded"
        >
          리셋
        </button>
      </div>
    </div>
  );
}
