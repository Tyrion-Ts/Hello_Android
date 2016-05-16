package com.example.hello_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用activity_main.xml文件定义界面布局
        setContentView(R.layout.activity_main);
    }

    public void clickHandler(View view) {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(getString(R.string.hello, new Date().toString()));
    }
}
