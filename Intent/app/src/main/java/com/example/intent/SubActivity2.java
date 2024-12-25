package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity2 extends AppCompatActivity {

    private EditText editmsg;
    private Button backbtn;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
       editmsg=findViewById(R.id.editMsg);
        backbtn=findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendmsg=editmsg.getText().toString();
                Intent result=new Intent(SubActivity2.this,MainActivity.class);
                result.putExtra("msgstr",sendmsg);
                setResult(RESULT_OK,result);
                finish();
            }
        });

    }
}
