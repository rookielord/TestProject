package com.zhd.reflector;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button btn_click;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        tv_content = (TextView) findViewById(R.id.tv_content);



        //这里来实例化类
        final MyCall call = new MyCall();
        //调用里面的setter方法，把实现了接口的类传给这个方法
        call.setCallback(new MyCallback() {
            @Override
            public void onclick(int position) {
                Toast.makeText(MainActivity.this, "当前的位置是:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showContent(String msg) {
                tv_content.setText("这里是我的内容" + msg);
            }
        });

        //这个也是个回调函数
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.showMyCallback();
            }
        });

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
