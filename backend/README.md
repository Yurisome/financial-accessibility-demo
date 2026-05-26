# 금융 문서 도우미 - Mock API 서버

Spring Boot 기반 Mock REST API 서버입니다.
실제 OCR / AI 없이, Android 앱 연동 테스트용 고정 응답을 반환합니다.

---

## 실행 방법

```bash
# backend 폴더에서 실행
cd backend

# macOS / Linux
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

서버가 뜨면 `http://localhost:8080` 에서 응답합니다.

---

## API 목록

| 메서드 | 경로 | 설명 |
|--------|------|------|
| GET | `/api/demo/card-bill` | 카드 명세서 Mock 결과 반환 |
| GET | `/api/demo/id-card` | 신분증 Mock 결과 반환 |
| POST | `/api/analyze` | documentType으로 분기하여 Mock 결과 반환 |

---

## curl 테스트 예시

### GET /api/demo/card-bill

```bash
curl http://localhost:8080/api/demo/card-bill
```

응답:
```json
{
  "documentType": "카드 명세서",
  "easyExplanation": "이번 달 카드값은 123만원입니다.\n5월 25일에 자동 결제될 예정입니다.",
  "ocrConfidence": "94%",
  "needsReview": false,
  "infoKeys": ["카드사", "결제금액", "결제일", "계좌번호"],
  "infoValues": ["국민카드", "1,230,000원", "2026-05-25", "123-***-789"]
}
```

### GET /api/demo/id-card

```bash
curl http://localhost:8080/api/demo/id-card
```

응답:
```json
{
  "documentType": "신분증",
  "easyExplanation": "홍길동님의 신분증 정보가 확인되었습니다.\n생년월일은 1992년 10월 29일이며,\n주민등록번호 뒷자리는 안전하게 가렸습니다.",
  "ocrConfidence": "91%",
  "needsReview": false,
  "infoKeys": ["이름", "생년월일", "주민등록번호", "발급일자"],
  "infoValues": ["홍길동", "1992-10-29", "921029-1******", "2020-05-10"]
}
```

### POST /api/analyze

```bash
# 카드 명세서
curl -X POST http://localhost:8080/api/analyze \
  -H "Content-Type: application/json" \
  -d '{"documentType": "card"}'

# 신분증
curl -X POST http://localhost:8080/api/analyze \
  -H "Content-Type: application/json" \
  -d '{"documentType": "id"}'
```

---

## 프로젝트 구조

```
backend/
└── src/main/java/com/financial/demo/
    ├── DemoApplication.java          <- Spring Boot 진입점
    ├── controller/
    │   └── DocumentController.java   <- REST 엔드포인트 3개
    ├── dto/
    │   ├── DocumentResultDto.java    <- 응답 JSON 구조
    │   └── AnalyzeRequestDto.java    <- POST 요청 바디
    └── service/
        └── MockDocumentService.java  <- Mock 데이터 반환 로직
```

---

## 기술 스택

| 항목 | 내용 |
|------|------|
| 언어 | Java 21 |
| 프레임워크 | Spring Boot 3.5.0 |
| 빌드 | Gradle |
| DB | 없음 (Mock 데이터) |
| 포트 | 8080 |

---

## Android 앱 연동 시 수정 위치

Android `ResultActivity.java`의 `DocumentResult result = ...` 부분을 아래로 교체:

```java
// Retrofit 인터페이스 예시
@POST("/api/analyze")
Call<DocumentResultDto> analyze(@Body AnalyzeRequestDto request);
```

실제 연동은 추후 단계에서 진행합니다.
