# CLAUDE.md

## Project
Financial Accessibility Demo

This project is a demo app for the "AI financial document explanation service for financially vulnerable users" idea.

The current goal is not to build a perfect OCR system.
The current goal is to create a visible, working demo flow for:
- Android Java app
- Mock financial document analysis
- Easy Korean explanation
- Future Spring Boot API integration
- Future OCR/AI integration

## Current Tech Stack
- Android Native Java
- XML Layout
- Android Studio
- Gradle
- Future backend: Java Spring Boot
- Future OCR/AI: external OCR API or Python/FastAPI service

## Current Project Structure
- `android-app/`
  - Android Java demo app
  - HomeActivity
  - SelectDocumentActivity
  - ResultActivity
  - DocumentResult mock model
- `backend/`
  - Reserved for future Spring Boot API
- `docs/`
  - Business proposal notes
  - Short-form video script
  - Screenshots and planning notes

## Product Concept
Service name: 금융 문서 도우미

One-line description:
금융 문서를 쉽게 설명해주는 AI 안내 서비스

Target users:
- 시각장애인
- 고령층
- 디지털 취약계층
- 금융 문서를 혼자 이해하기 어려운 사용자

Core value:
Do not simply read text with OCR.
Explain the meaning of financial documents in easy Korean.

Example:
- Original: 청구금액 1,230,000원 / 결제일 2026-05-25
- Easy explanation: 이번 달 카드값은 123만원입니다. 5월 25일에 자동 결제될 예정입니다.

## Operating Mode

The user may use the following control words.

### 계속
When the user says "계속", do this first:
1. Read this CLAUDE.md.
2. Check current project files.
3. Summarize current project state.
4. Create today's task tracker.
5. Separate tasks into:
   - AI가 할 일
   - 사용자가 할 일
6. Pick the first smallest task.
7. Do not expand scope unless asked.

Output format for "계속":

```md
## 현재 상태
- ...

## 오늘의 목표
- ...

## AI가 할 일
1. ...

## 사용자가 할 일
1. ...

## 첫 번째 작업
- ...

## 진행 전 확인
- 수정할 파일:
- 위험도:
- 되돌리는 방법:
```

### 멈춤
When the user says "멈춤", stop coding and create a closing report.

Output format for "멈춤":

```md
## 작업 종료 보고

### 오늘 한 일
- ...

### 변경된 파일
- ...

### 발생한 문제
- ...

### 해결한 방법
- ...

### 아직 남은 문제
- ...

### 다음에 이어서 할 첫 작업
- ...

### Git 상태
- 커밋 여부:
- 추천 커밋 메시지:
```

### 상태
When the user says "상태", do not modify files.
Only inspect and summarize.

### 수정
When the user says "수정", explain which files will be changed before editing.

## Safety Rules
- Do not delete files without explicit confirmation.
- Do not run destructive commands such as `rm -rf` unless the user explicitly confirms.
- Do not modify Gradle versions, AGP versions, or SDK versions unless needed for a specific error.
- Do not press or recommend AGP Upgrade Assistant unless the user asks.
- Do not add real OCR, camera, server API, database, or TTS unless explicitly requested.
- Prefer the smallest working change.
- Before editing, say which files will be changed.
- After editing, explain exactly what changed.

## Git Rules
- Before major edits, check:
  - `git status`
- After a stable working state, recommend:
  - `git add ...`
  - `git commit -m "..."`
- Do not commit automatically unless the user asks.
- Always recommend committing immediately after a successful build/run milestone.

Recommended first commit:
```bash
git add .gitignore android-app
git commit -m "Create Android Java demo shell app"
```

## Android Development Rules
- Use Android Studio as the source of truth for Android build/run.
- VS Code may show false red errors for Android imports.
- If Android Studio builds successfully, trust Android Studio over VS Code.
- Use Java, not Kotlin, unless the user explicitly changes direction.
- Use XML Layout, not Jetpack Compose, unless explicitly requested.
- Keep UI simple and accessible:
  - large font
  - large buttons
  - high contrast
  - minimal screens
  - no unnecessary animations

## Current App Flow
Expected flow:
1. HomeActivity
   - "문서 분석 시작하기" -> SelectDocumentActivity
   - "데모 보기" -> ideally ResultActivity with CARD_BILL mock result
2. SelectDocumentActivity
   - choose CARD_BILL or ID_CARD
   - "분석 시작" -> ResultActivity
3. ResultActivity
   - show easy explanation
   - show key fields
   - show confidence
   - show review status
   - "음성으로 듣기" may be Toast only for now
   - "다시 분석하기" returns to selection or home

## Mock Data
Card bill:
```json
{
  "documentType": "CARD_BILL",
  "company": "국민카드",
  "amount": 1230000,
  "paymentDate": "2026-05-25",
  "maskedAccount": "123-***-789",
  "easyExplanation": "이번 달 카드값은 123만원입니다. 5월 25일에 자동 결제될 예정입니다.",
  "confidence": 0.94,
  "needsReview": false
}
```

ID card:
```json
{
  "documentType": "ID_CARD",
  "name": "홍길동",
  "birthDate": "1992-10-29",
  "maskedIdNumber": "921029-1******",
  "issuedDate": "2020-05-10",
  "easyExplanation": "홍길동님의 신분증 정보가 확인되었습니다. 생년월일은 1992년 10월 29일이며, 주민등록번호 뒷자리는 안전하게 가렸습니다.",
  "confidence": 0.91,
  "needsReview": false
}
```

## Scope Control
Until TOEFL and the Information Processing Engineer exam are over, keep tasks very small.

Allowed now:
- Fix navigation
- Improve visible UI text
- Capture demo screenshots
- Write docs
- Prepare business proposal material
- Commit stable app state

Not allowed now unless explicitly requested:
- Real OCR
- Camera integration
- Spring Boot backend
- Database
- Login
- Cloud deployment
- Handwriting OCR
- LayoutLMv3 training
- Complex refactoring

## Debugging Rules
When there is a red error:
1. Ask for the exact error message if not visible.
2. Identify the file and line.
3. Prefer minimal fix.
4. Do not change unrelated files.
5. After fix, run or ask user to run Gradle build.
6. If Android Studio succeeds but VS Code shows red lines, treat VS Code as likely misconfigured.

## Response Style
- Use Korean.
- Be direct and practical.
- Explain only what is needed.
- Give the next command or next click clearly.
- Do not overwhelm the user.
- Keep scope small.
- When the user is stressed, stabilize first, then proceed.

## Current Study Constraints
The user is also preparing:
- TOEFL through offline academy
- Information Processing Engineer exam around July 18
- TypeScript and Java study

Therefore, prioritize:
1. Exam routine
2. Small visible demo progress
3. Safe Git commits
4. No large technical rabbit holes
