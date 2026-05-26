package com.financial.demo.controller;

import com.financial.demo.dto.AnalyzeRequestDto;
import com.financial.demo.dto.DocumentResultDto;
import com.financial.demo.service.MockDocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DocumentController {

    private final MockDocumentService service;

    public DocumentController(MockDocumentService service) {
        this.service = service;
    }

    @GetMapping("/demo/card-bill")
    public DocumentResultDto demoCardBill() {
        return service.getCardBill();
    }

    @GetMapping("/demo/id-card")
    public DocumentResultDto demoIdCard() {
        return service.getIdCard();
    }

    @PostMapping("/analyze")
    public DocumentResultDto analyze(@RequestBody AnalyzeRequestDto request) {
        return service.analyze(request.documentType);
    }
}
