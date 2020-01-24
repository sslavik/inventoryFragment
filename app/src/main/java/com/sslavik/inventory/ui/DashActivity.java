package com.sslavik.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.ui.base.BaseActivity;
import com.sslavik.inventory.ui.dependency.DependencyActivity;
import com.sslavik.inventory.ui.section.SectionActivity;

public class DashActivity extends BaseActivity {

    // CAMPOS
    ImageButton btDependecias;
    ImageButton btSections;

    // LISTENERS
    View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        btDependecias = findViewById(R.id.btDependecias);
        btSections = findViewById(R.id.btSector);

        handleOnClickListener();

        btDependecias.setOnClickListener(onClickListener);
        btSections.setOnClickListener(onClickListener);


    }

    private void handleOnClickListener() {

        onClickListener = v -> {
            switch (v.getId()){
                case R.id.btDependecias:
                    startDependency();
                    break;
                case R.id.btSector:
                    startActivity(new Intent(this, SectionActivity.class));
                    break;
            }
        };
    }

    private void startDependency(){
        startActivity(new Intent(DashActivity.this, DependencyActivity.class));
    }
}
