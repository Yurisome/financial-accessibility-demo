package com.financial.demo.dto;

public class DocumentResultDto {

    public String documentType;
    public String easyExplanation;
    public String ocrConfidence;
    public boolean needsReview;
    public String[] infoKeys;
    public String[] infoValues;

    public DocumentResultDto(String documentType, String easyExplanation, String ocrConfidence,
                              boolean needsReview, String[] infoKeys, String[] infoValues) {
        this.documentType = documentType;
        this.easyExplanation = easyExplanation;
        this.ocrConfidence = ocrConfidence;
        this.needsReview = needsReview;
        this.infoKeys = infoKeys;
        this.infoValues = infoValues;
    }
}
