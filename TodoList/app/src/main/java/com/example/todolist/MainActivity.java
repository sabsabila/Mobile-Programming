package com.example.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    ListView listview;
    Button addButton, clearButton;
    EditText GetValue;
    String[] ListElements = new String[] {"Belajar Android", "Mengerjakan Tugas", "Membuat Rangkuman", "Mentoring WPPL"};
    ArrayAdapter<String> adapter;
    ArrayList<String> ListElementsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView1);
        addButton = findViewById(R.id.btnAdd);
        clearButton = findViewById(R.id.btnClear);
        GetValue = findViewById(R.id.editText);

        ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show input box
                showInputBox(ListElementsArrayList.get(position),position);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return true;
            }
        });

        /*addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList.add(GetValue.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });*/

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    public void showAlertDialog(final int position){
        final int index = position;

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item ?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ListElementsArrayList.remove(index);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showInputBox(String oldItem, final int index) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);

        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Update item");
        txtMessage.setTextColor(Color.parseColor("#000000"));

        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button bt = (Button) dialog.findViewById(R.id.btdone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList.set(index, editText.getText().toString());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}