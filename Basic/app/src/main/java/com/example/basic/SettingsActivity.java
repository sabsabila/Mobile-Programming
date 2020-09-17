package com.example.basic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.basic.utils.ThemeUtils;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> spinnerArray;
    ArrayAdapter<String> spinnerArrayAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });
        spinnerArray = new ArrayList<String>();
        spinnerArray.add("Whatsapp_Dark_Green_Theme");
        spinnerArray.add("Soft_Blue");
        spinnerArray.add("Blue");
        spinner = findViewById(R.id.spinner);
        spinnerArrayAdapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
    }
    // ThemeUtils.changeToTheme(SettingsActivity.this, position);
    @Override
    public void onBackPressed() {
        ThemeUtils.changeToTheme(SettingsActivity.this,
                spinner.getSelectedItemPosition());
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}