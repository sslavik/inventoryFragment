package com.sslavik.inventory.ui.base;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sslavik.inventory.R;

public class BaseActivity extends AppCompatActivity {

    // Instancia del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_dependency, menu);

        return super.onCreateOptionsMenu(menu);
    }


    // Callback de las opcions pulsadas en el menu del Toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this,"Se ha pulsado buscar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"Se ha pulsado ajustes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_aboutus:
                Toast.makeText(this,"Se ha pulsado sobre nosotros", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_help:
                Toast.makeText(this,"Se ha pulsado ayuda", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
