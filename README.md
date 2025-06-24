# 🌱 FocusGrow (구현중)
**“몰입 시간 = 성장”을 시각적으로 보여주는 집중 습관 강화 앱**  
> React + Spring Boot · 시각화 기반 집중 피드백 · 레벨 & 배지 시스템

### 🎯 FocusGrow란?

**FocusGrow**는 집중 시간 동안 식물이 자라는 **게이미피케이션 기반 웹 앱**입니다.  
사용자가 설정한 시간 동안 집중하면, 식물이 성장하고 레벨이 오르며  
**몰입 피드백 + 배지 시스템 + 집중 통계 시각화**까지 제공합니다.

- 집중 = 성장!  
- 시각적 피드백으로 몰입 유도  
- 꾸준한 집중 습관 형성에 도움
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

- React Query 

- Axios 

- CSS / CSS Modules


### Backend

- Spring Boot (Kotlin 기반)

- JPA (Hibernate) 

- MySQL 

- Session 기반 로그인 인증

<br>

## 프로젝트 구조

FocusGrow

├── focusgrow-frontend (React + Vite)

└── focusgrow-backend (Spring Boot + Kotlin + JPA)

- 프론트엔드와 백엔드를 디렉토리 단위로 분리한 **멀티 레포지토리 구조**입니다.
- 
- 프론트엔드는 Axios를 통해 백엔드의 **RESTful API**와 통신합니다.
