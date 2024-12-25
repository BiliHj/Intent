package com.example.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText_inputName;
    EditText editText_inputAge;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_inputName = findViewById(R.id.editText_inputName);
        editText_inputAge = findViewById(R.id.editText_inputAge);
        button=findViewById(R.id.Button1);
        button.setOnClickListener(v -> transferData());
    }

    public void transferData() {
        String name = editText_inputName.getText().toString().trim();
        String age = editText_inputAge.getText().toString();
        Intent intent = new Intent(this, receiveData.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        startActivity(intent);
    }
}