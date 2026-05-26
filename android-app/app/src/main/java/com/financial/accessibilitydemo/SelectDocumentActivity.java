package com.financial.accessibilitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SelectDocumentActivity extends AppCompatActivity {

    public static final String EXTRA_DEMO_MODE = "demo_mode";
    public static final String EXTRA_DOCUMENT_TYPE = "document_type";
    public static final String TYPE_CARD = "card";
    public static final String TYPE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_document);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton radioCard = findViewById(R.id.radio_card);
        Button btnAnalyze = findViewById(R.id.btn_analyze);

        // 데모 모드이면 카드 명세서를 기본 선택
        boolean demoMode = getIntent().getBooleanExtra(EXTRA_DEMO_MODE, false);
        if (demoMode) {
            radioCard.setChecked(true);
        }

        btnAnalyze.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "문서 종류를 선택해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            String docType = (selectedId == R.id.radio_card) ? TYPE_CARD : TYPE_ID;

            // 나중에 실제 카메라 촬영을 추가할 때 여기에 Intent(MediaStore.ACTION_IMAGE_CAPTURE) 추가
            // 현재는 바로 결과 화면으로 이동 (Mock 데이터 사용)
            Intent intent = new Intent(SelectDocumentActivity.this, ResultActivity.class);
            intent.putExtra(EXTRA_DOCUMENT_TYPE, docType);
            startActivity(intent);
        });
    }
}
