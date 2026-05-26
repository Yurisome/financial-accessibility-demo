package com.financial.accessibilitydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.financial.accessibilitydemo.model.DocumentResult;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String docType = getIntent().getStringExtra(SelectDocumentActivity.EXTRA_DOCUMENT_TYPE);

        // 나중에 Spring Boot API를 연결할 때 이 부분을 Retrofit/OkHttp 호출로 교체
        // API 엔드포인트 예시: POST /api/analyze { documentType, imageBase64 }
        DocumentResult result = SelectDocumentActivity.TYPE_ID.equals(docType)
                ? DocumentResult.getMockIdCard()
                : DocumentResult.getMockCardStatement();

        TextView tvDocType = findViewById(R.id.tv_doc_type);
        TextView tvExplanation = findViewById(R.id.tv_explanation);
        TextView tvConfidence = findViewById(R.id.tv_confidence);
        TextView tvNeedsReview = findViewById(R.id.tv_needs_review);
        LinearLayout infoContainer = findViewById(R.id.info_container);
        Button btnTts = findViewById(R.id.btn_tts);
        Button btnRetry = findViewById(R.id.btn_retry);

        tvDocType.setText(result.documentType + " 분석 완료");
        tvExplanation.setText(result.easyExplanation);
        tvConfidence.setText("OCR 신뢰도: " + result.ocrConfidence);
        tvNeedsReview.setText("확인 필요: " + (result.needsReview ? "예" : "아니오"));

        buildInfoRows(infoContainer, result);

        // 나중에 실제 TTS를 추가할 때 TextToSpeech 클래스로 교체
        btnTts.setOnClickListener(v ->
                Toast.makeText(this, result.easyExplanation, Toast.LENGTH_LONG).show());

        btnRetry.setOnClickListener(v -> finish());
    }

    private void buildInfoRows(LinearLayout container, DocumentResult result) {
        for (int i = 0; i < result.infoKeys.length; i++) {
            if (i > 0) {
                View divider = new View(this);
                LinearLayout.LayoutParams dp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 1);
                dp.setMargins(0, 4, 0, 4);
                divider.setLayoutParams(dp);
                divider.setBackgroundColor(0xFFE0E0E0);
                container.addView(divider);
            }

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, 16, 0, 16);

            TextView key = new TextView(this);
            key.setText(result.infoKeys[i]);
            key.setTextSize(18f);
            key.setTextColor(0xFF616161);
            key.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView value = new TextView(this);
            value.setText(result.infoValues[i]);
            value.setTextSize(18f);
            value.setTextColor(0xFF212121);
            value.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            value.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.5f));

            row.addView(key);
            row.addView(value);
            container.addView(row);
        }
    }
}
