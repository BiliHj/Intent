package com.example.intent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intent.R;
import com.example.intent.SubActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView dispalyView;

    private EditText webEdit;
    private Button webButton;

    private EditText entryEdit;
    private  Button broadbtn;

   ActivityResultLauncher<Intent>mStartForResult=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
           new ActivityResultCallback<ActivityResult>() {
       @Override
       public void onActivityResult(ActivityResult result) {
           // 初始化控件
           textView = findViewById(R.id.textView);
           dispalyView = findViewById(R.id.display);

           if (result.getResultCode() == Activity.RESULT_OK) {
               Intent intent = result.getData();

               if (intent != null) {
                   if (intent.hasExtra("userstr")) {
                       String username = intent.getStringExtra("userstr");
                       if (username != null) {
                           textView.setText("用户名：" + username);
                       } else {
                           textView.setText("用户名未提供");
                       }
                   } else if (intent.hasExtra("msgstr")) {
                       String msg1 = intent.getStringExtra("msgstr");
                       dispalyView.setText("消息展示区：" + msg1);
                   }
               }
           }
       }
   });

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        Button loginButton=findViewById(R.id.loginButton);
        Button sendmsgButton=findViewById(R.id.btn2);
        webEdit=findViewById(R.id.webEdit);
        webButton=findViewById(R.id.webbtn);
        entryEdit=findViewById(R.id.entryEdit);
        broadbtn=findViewById(R.id.broadbtn);

        loginButton.setOnClickListener(view ->{
            Intent intent=new Intent(MainActivity.this, SubActivity.class);
                }
        );

        sendmsgButton.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,SubActivity2.class);
        mStartForResult.launch(intent);
        });

        webButton.setOnClickListener(v -> {
            String uriString=webEdit.getText().toString();
            if (!uriString.startsWith("http://") && !uriString.startsWith("https://")) {
                uriString = "http://" + uriString;
            }
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
            startActivity(intent);
        });

        BroadcastReceiver br=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter("com.example.intent.MyBroadcastReceiver");
        this.registerReceiver(br,intentFilter, Context.RECEIVER_NOT_EXPORTED);
        broadbtn.setOnClickListener(v -> {
        Intent intent=new Intent(MainActivity.this,MyBroadcastReceiver.class);
        intent.putExtra("message",entryEdit.getText().toString());
        sendBroadcast(intent);
        });

    }
}
