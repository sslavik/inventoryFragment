package com.sslavik.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.sslavik.inventory.R;
import com.sslavik.inventory.ui.dependency.DependencyActivity;

public class DashActivity extends AppCompatActivity {

    // CAMPOS
    ImageButton btDependecias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        btDependecias = findViewById(R.id.btDependecias);

        // EVENTO ON CLICK
        btDependecias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDependency();
            }
        });

    }

    private void startDependency(){
        startActivity(new Intent(DashActivity.this, DependencyActivity.class));
    }
}
