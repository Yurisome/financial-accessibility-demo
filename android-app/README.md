# 금융 문서 도우미 - Android 데모 앱

금융 약자를 위한 AI 금융 문서 설명 서비스의 Android 데모 앱입니다.
모두의창업 2기 제출 및 숏폼 시연용으로 제작된 Mock 데이터 기반 껍데기 앱입니다.

---

## 화면 구성

```
HomeActivity (홈)
  ├── "문서 분석 시작하기" → SelectDocumentActivity
  └── "데모 보기"         → SelectDocumentActivity (카드 명세서 자동 선택)

SelectDocumentActivity (문서 종류 선택)
  ├── 카드 명세서 선택
  ├── 신분증 선택
  └── "분석 시작" → ResultActivity (Mock 데이터 표시)

ResultActivity (분석 결과)
  ├── AI 쉬운 설명
  ├── 주요 정보 카드
  ├── OCR 신뢰도 / 확인 필요 여부
  ├── "음성으로 듣기" (Toast로 미리보기)
  └── "다시 분석하기" → SelectDocumentActivity로 돌아가기
```

---

## Android Studio에서 열기

1. Android Studio를 실행합니다.
2. **File → Open** 선택 후 이 `android-app` 폴더를 선택합니다.
3. Gradle Sync가 자동으로 시작됩니다. 완료까지 기다립니다.
4. 상단 툴바에서 실행할 기기(에뮬레이터 또는 실제 기기)를 선택합니다.
5. **Run ▶** 버튼을 누릅니다.

> **권장 Android Studio 버전:** Hedgehog (2023.1.1) 이상  
> **권장 에뮬레이터:** Pixel 6, API 34 (Google Play)

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
├── app/
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/financial/accessibilitydemo/
│       │   ├── HomeActivity.java
│       │   ├── SelectDocumentActivity.java
│       │   ├── ResultActivity.java
│       │   └── model/
│       │       └── DocumentResult.java          ← Mock 데이터 모델
│       └── res/
│           ├── layout/
│           │   ├── activity_home.xml
│           │   ├── activity_select_document.xml
│           │   └── activity_result.xml
│           ├── values/
│           │   ├── colors.xml
│           │   ├── strings.xml
│           │   └── themes.xml
│           └── drawable/
│               ├── card_background.xml
│               ├── card_background_primary.xml
│               ├── radio_item_background.xml
│               ├── badge_background.xml
│               └── ic_launcher.xml
├── build.gradle
├── settings.gradle
├── gradle.properties
└── gradle/wrapper/gradle-wrapper.properties
```

---

## Spring Boot API 연결 시 수정 위치

실제 OCR + AI 분석 백엔드를 붙일 때 수정할 파일과 위치입니다.

### 1. Mock 데이터 → API 호출로 교체
**파일:** `ResultActivity.java`  
**위치:** `onCreate()` 상단의 `DocumentResult result = ...` 부분

```java
// 현재 (Mock)
DocumentResult result = DocumentResult.getMockCardStatement();

// 교체 후 (Retrofit 예시)
ApiService api = RetrofitClient.getInstance().create(ApiService.class);
api.analyze(new AnalyzeRequest(docType, imageBase64))
   .enqueue(new Callback<DocumentResult>() { ... });
```

**API 엔드포인트 예시:**
```
POST /api/analyze
Body: { "documentType": "card", "imageBase64": "..." }
Response: { "documentType": "카드 명세서", "easyExplanation": "...", ... }
```

### 2. 카메라 촬영 추가
**파일:** `SelectDocumentActivity.java`  
**위치:** `btnAnalyze.setOnClickListener()` 내부 주석 위치

```java
// 촬영 Intent 추가
Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
```

### 3. 음성 안내(TTS) 연결
**파일:** `ResultActivity.java`  
**위치:** `btnTts.setOnClickListener()` 내부

```java
// Toast를 TextToSpeech로 교체
TextToSpeech tts = new TextToSpeech(this, status -> {
    if (status == TextToSpeech.SUCCESS) {
        tts.setLanguage(Locale.KOREAN);
        tts.speak(result.easyExplanation, TextToSpeech.QUEUE_FLUSH, null, null);
    }
});
```

### 4. 네트워크 권한 추가 (API 연결 시 필수)
**파일:** `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CAMERA" />
```

### 5. Retrofit 의존성 추가
**파일:** `app/build.gradle`

```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```

---

## Mock 데이터 내용

### 카드 명세서
- 카드사: 국민카드
- 결제금액: 1,230,000원
- 결제일: 2026-05-25
- 계좌번호: 123-\*\*\*-789
- OCR 신뢰도: 94%

### 신분증
- 이름: 홍길동
- 생년월일: 1992-10-29
- 주민등록번호: 921029-1\*\*\*\*\*\*
- 발급일자: 2020-05-10
- OCR 신뢰도: 91%
