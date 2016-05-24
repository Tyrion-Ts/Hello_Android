package com.example.hello_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    // 图片封装为一个数组
    private int[] icon = {R.drawable.calculator,
            R.drawable.superpong,
            R.drawable.messenger,
            R.drawable.maps};
    private String[] iconName = {"计算器", "弹球", "聊天", "地图"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //使用grid.xml文件定义界面布局
        setContentView(R.layout.grid);

        gridView = (GridView) findViewById(R.id.gview);
        //新建List
        dataList = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        adapter = new SimpleAdapter(this, dataList, R.layout.item, from, to);
        //配置适配器
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.i("MainActivity", "Position:" + position);
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, CalcActivity.class);
                    startActivity(intent);
                }
            }
        });

        //实现数据对象传递
        /*Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.name);
                EditText passwd = (EditText) findViewById(R.id.passwd);
                RadioButton male = (RadioButton) findViewById(R.id.male);
                String gendar = male.isChecked() ? "男" : "女";
                Person p = new Person(name.getText().toString(), passwd.getText().toString(), gendar);

                Bundle data = new Bundle();
                data.putSerializable("person", p);
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }

        return dataList;
    }
}

