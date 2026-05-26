package com.financial.accessibilitydemo.model;

public class DocumentResult {

    public String documentType;
    public String easyExplanation;
    public String ocrConfidence;
    public boolean needsReview;
    public String[] infoKeys;
    public String[] infoValues;

    // 나중에 Spring Boot API를 연결할 때 이 두 메서드를 API 호출로 교체하면 됩니다.
    // 교체 위치: ResultActivity.java onCreate() 내 DocumentResult result = ... 부분

    public static DocumentResult getMockCardStatement() {
        DocumentResult result = new DocumentResult();
        result.documentType = "카드 명세서";
        result.easyExplanation =
                "이번 달 카드값은 123만원입니다.\n5월 25일에 자동 결제될 예정입니다.";
        result.ocrConfidence = "94%";
        result.needsReview = false;
        result.infoKeys = new String[]{"카드사", "결제금액", "결제일", "계좌번호"};
        result.infoValues = new String[]{"국민카드", "1,230,000원", "2026-05-25", "123-***-789"};
        return result;
    }

    public static DocumentResult getMockIdCard() {
        DocumentResult result = new DocumentResult();
        result.documentType = "신분증";
        result.easyExplanation =
                "홍길동님의 신분증 정보가 확인되었습니다.\n생년월일은 1992년 10월 29일이며,\n주민등록번호 뒷자리는 안전하게 가렸습니다.";
        result.ocrConfidence = "91%";
        result.needsReview = false;
        result.infoKeys = new String[]{"이름", "생년월일", "주민등록번호", "발급일자"};
        result.infoValues = new String[]{"홍길동", "1992-10-29", "921029-1******", "2020-05-10"};
        return result;
    }
}
