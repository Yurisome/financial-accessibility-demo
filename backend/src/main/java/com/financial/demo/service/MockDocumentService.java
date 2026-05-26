package com.financial.demo.service;

import com.financial.demo.dto.DocumentResultDto;
import org.springframework.stereotype.Service;

@Service
public class MockDocumentService {

    public DocumentResultDto getCardBill() {
        return new DocumentResultDto(
                "카드 명세서",
                "이번 달 카드값은 123만원입니다.\n5월 25일에 자동 결제될 예정입니다.",
                "94%",
                false,
                new String[]{"카드사", "결제금액", "결제일", "계좌번호"},
                new String[]{"국민카드", "1,230,000원", "2026-05-25", "123-***-789"}
        );
    }

    public DocumentResultDto getIdCard() {
        return new DocumentResultDto(
                "신분증",
                "홍길동님의 신분증 정보가 확인되었습니다.\n생년월일은 1992년 10월 29일이며,\n주민등록번호 뒷자리는 안전하게 가렸습니다.",
                "91%",
                false,
                new String[]{"이름", "생년월일", "주민등록번호", "발급일자"},
                new String[]{"홍길동", "1992-10-29", "921029-1******", "2020-05-10"}
        );
    }

    public DocumentResultDto analyze(String documentType) {
        if ("id".equalsIgnoreCase(documentType)) {
            return getIdCard();
        }
        return getCardBill();
    }
}
