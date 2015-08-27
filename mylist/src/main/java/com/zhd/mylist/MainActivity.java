package com.zhd.mylist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private String[] names=new String[20];
    private static final int NUM=17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //模拟数据
        for (int i=0;i<20;i++){
            if (i%2==0)
                names[i]="小红";
            else
                names[i]="小黑";
        }
        //实现类传递数据
        MyAdapter adapter=new MyAdapter(this,names);
        //给Adapter类中的接口字段赋值,并传入一个实现了接口的对象
        adapter.setCallback(new MyCallback() {
            @Override
            public void onclick(int position) {
                Toast.makeText(MainActivity.this, "当前的数字是" + (position + NUM), Toast.LENGTH_SHORT).show();
            }
        });
        ListView lv= (ListView) findViewById(R.id.lv);
        GridView gv= (GridView) findViewById(R.id.gv);
        Spinner sp= (Spinner) findViewById(R.id.sp);
        lv.setAdapter(adapter);
        gv.setAdapter(adapter);
        sp.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
