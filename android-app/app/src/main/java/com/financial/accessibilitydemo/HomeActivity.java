package com.financial.accessibilitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStart = findViewById(R.id.btn_start);
        Button btnDemo = findViewById(R.id.btn_demo);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SelectDocumentActivity.class);
            startActivity(intent);
        });

        btnDemo.setOnClickListener(v -> {
            // 데모 모드: 카드 명세서 Mock 결과 화면으로 바로 이동
            Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
            intent.putExtra(SelectDocumentActivity.EXTRA_DOCUMENT_TYPE, SelectDocumentActivity.TYPE_CARD);
            startActivity(intent);
        });
    }
}
