package com.example.intent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        Button submitButton=findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> {
            String username=usernameEditText.getText().toString();
            Intent intent=new Intent(SubActivity.this,MainActivity.class);
            intent.putExtra("userstr",username);
            setResult(RESULT_OK,intent);
            finish();
        });

    }
}