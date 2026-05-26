# 금융 문서 도우미 - Android 데모 앱

금융 약자를 위한 AI 금융 문서 설명 서비스의 Android 데모 앱입니다.
Mock 데이터 기반 시연용 앱으로, 실제 OCR·AI·서버 연동 없이 전체 사용자 흐름을 확인할 수 있습니다.

---

## 스크린샷

| 홈 화면 | 문서 선택 | 분석 결과 |
|--------|----------|----------|
| ![홈](../docs/screenshots/01_home.png) | ![선택](../docs/screenshots/02_select.png) | ![결과](../docs/screenshots/03_result_card.png) |

> 스크린샷은 `docs/screenshots/` 디렉터리에 저장합니다.

---

## 데모 흐름

```
홈 화면 (HomeActivity)
  ├── "문서 분석 시작하기" → 문서 종류 선택
  └── "데모 보기"         → 카드 명세서 결과 화면 (바로 이동)

문서 종류 선택 (SelectDocumentActivity)
  ├── 카드 명세서
  ├── 신분증
  └── "분석 시작" → 분석 결과 화면

분석 결과 (ResultActivity)
  ├── AI 쉬운 설명 (파란 카드)
  ├── 주요 정보 (카드사, 금액, 날짜 등)
  ├── OCR 신뢰도 / 확인 필요 여부
  ├── "음성으로 듣기" (현재 Toast 미리보기)
  └── "다시 분석하기" → 이전 화면으로
```

---

## Android Studio에서 실행

1. Android Studio 실행
2. **File → Open** → 이 `android-app` 폴더 선택
3. Gradle Sync 완료 대기
4. 에뮬레이터 또는 실기기 선택 후 **Run** 버튼

> 권장 에뮬레이터: Pixel 6, API 34 (Android 14)

---

## 기술 스택

| 항목 | 내용 |
|------|------|
| 언어 | Java |
| 최소 SDK | 24 (Android 7.0) |
| 타겟 SDK | 34 (Android 14) |
| UI | XML Layout |
| 구조 | Activity 기반 |
| 외부 의존성 | AndroidX AppCompat, Material Components |

---

## 파일 구조

```
android-app/
├── app/src/main/
│   ├── AndroidManifest.xml
│   ├── java/com/financial/accessibilitydemo/
│   │   ├── HomeActivity.java
│   │   ├── SelectDocumentActivity.java
│   │   ├── ResultActivity.java
│   │   └── model/DocumentResult.java       <- Mock 데이터
│   └── res/
│       ├── layout/                         <- 화면 3개
│       ├── values/                         <- 색상, 문자열, 테마
│       └── drawable/                       <- 배경, 아이콘
├── build.gradle
└── settings.gradle
```

---

## Mock 데이터

### 카드 명세서
- 카드사: 국민카드 / 결제금액: 1,230,000원 / 결제일: 2026-05-25
- AI 설명: "이번 달 카드값은 123만원입니다. 5월 25일에 자동 결제될 예정입니다."

### 신분증
- 이름: 홍길동 / 생년월일: 1992-10-29 / 주민번호: 921029-1\*\*\*\*\*\*
- AI 설명: "홍길동님의 신분증 정보가 확인되었습니다. 생년월일은 1992년 10월 29일입니다."

---

## 향후 연동 계획

| 기능 | 연동 위치 | 비고 |
|------|----------|------|
| OCR + AI 분석 | `ResultActivity.java` — `DocumentResult result = ...` 부분 | Spring Boot API 또는 FastAPI |
| 카메라 촬영 | `SelectDocumentActivity.java` — `btnAnalyze` 클릭 핸들러 | `MediaStore.ACTION_IMAGE_CAPTURE` |
| 음성 안내 TTS | `ResultActivity.java` — `btnTts` 클릭 핸들러 | `TextToSpeech` 클래스 |
