#  🌱 FocusGrow (구현중)

집중 시간 관리와 식물 성장 게임 요소를 결합한 웹 애플리케이션입니다.

FocusGrow는 "Focus(집중)"와 "Grow(성장)"을 결합한 이름으로, 사용자가 집중하는 시간 동안 식물이 성장하는 컨셉을 기반으로 합니다.

집중 타이머 기능에 식물 성장이라는 게임적 요소를 더해, 사용자가 몰입한 시간을 시각적으로 확인하고 몰입과 재미를 동시에 경험할 수 있도록 설계했습니다.

---
## FocusGrow 메인 화면
![스크린샷 2025-06-23 오후 5 28 19](https://github.com/user-attachments/assets/89cffbc0-e309-49c3-93e2-ae4642349552)
---

## 주요 기능

- 집중 타이머: 사용자가 설정한 시간 동안 타이머를 작동시켜 집중 상태를 유지할 수 있습니다.

- 집중 기록 저장: 집중 완료 후 해당 기록이 저장되어 히스토리를 확인할 수 있습니다.

- 식물 성장 시각화: 누적 집중 시간에 따라 식물이 성장하는 애니메이션 효과를 제공합니다.

- 레벨 및 배지 시스템: 사용자의 누적 집중 시간에 따라 레벨이 오르고, 특정 조건을 달성하면 배지를 획득할 수 있습니다.

- 통계 대시보드: 일/주/월 단위로 집중 시간 통계를 확인할 수 있습니다.

- JWT 로그인/회원가입: 사용자의 데이터를 보호하고, 개인화된 경험을 제공합니다.

<br>

## 기술 스택

### Frontend

- React (Vite 기반)

- TypeScript

- React Query (서버 상태 관리)

- Axios (API 통신)

- CSS / CSS Modules (스타일링)


### Backend

- Spring Boot (Kotlin 기반)

- JPA (Hibernate) (ORM)

- MySQL (RDBMS)

- JWT (로그인 인증)

<br>

## 프로젝트 구조

FocusGrow

├── focusgrow-frontend (React + Vite)

└── focusgrow-backend (Spring Boot + Kotlin + JPA)

 - 프론트엔드와 백엔드를 분리하여 관리하는 멀티 레포지토리 구조입니다.

 - 프론트엔드는 Axios를 사용해 백엔드의 RESTful API와 통신합니다.
