package com.example.hello_android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pojo.Person;

/**
 * Created by 健宇 on 2016/5/23.
 * 结果显示
 */
public class ResultActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView name = (TextView) findViewById(R.id.name);
        TextView passwd = (TextView) findViewById(R.id.passwd);
        TextView gendar = (TextView) findViewById(R.id.gendar);

        // 获取启动该Activity的Intent
        Intent intent = this.getIntent();
        Person p = (Person) intent.getSerializableExtra("person");
        name.setText(p.getName());
        passwd.setText(p.getPasswd());
        gendar.setText(p.getGendar());
    }

}
