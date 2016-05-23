package com.example.hello_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by 健宇 on 2016/5/23.
 * 计算器Activity
 */
public class CalcActivity extends Activity {
    GridLayout gridLayout;

    String[] chars = new String[] {
            "√", "^", "清除", "回退",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //使用grid.xml文件定义界面布局
        setContentView(R.layout.calc);

        gridLayout = (GridLayout) findViewById(R.id.root);
        for (int i = 0; i < chars.length; i++) {
            Button bn = new Button(this);
            bn.setText(chars[i]);
            bn.setTextSize(40);
            bn.setPadding(5, 35, 5, 35);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.FILL);

            if(i == 4) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView tv = (TextView) findViewById(R.id.process);
                        tv.append("7");
                    }
                });
            }

            gridLayout.addView(bn, params);
        }
    }
}
