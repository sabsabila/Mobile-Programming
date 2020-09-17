package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;

public class MainActivity extends Activity
{
    // Array of strings...
    ListView simpleList;
    String countryList[] = {"Membuat rangkuman Layout", "Mengerjakan Tugas Machine Learning", "Mengerjakan Tugas Keterampilan Komunikasi", "Bermain Game"};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }
}