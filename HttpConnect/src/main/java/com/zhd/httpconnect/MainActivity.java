package com.zhd.httpconnect;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 第一种使用HttpURLConnection
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_send, btn_request;
    private TextView tv_content;
    private EditText et_content;

    private static final int SHOW_RESPONSE = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_RESPONSE) {
                String content = (String) msg.obj;
                tv_content.setText(content);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        btn_request = (Button) findViewById(R.id.btn_request);
        btn_send = (Button) findViewById(R.id.btn_send);
        tv_content = (TextView) findViewById(R.id.tv_content);
        et_content = (EditText) findViewById(R.id.et_content);

        //添加监听
        btn_request.setOnClickListener(this);
        btn_send.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request:
                sendRequest();
                break;
            case R.id.btn_send:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        String content = et_content.getText().toString();
        HttpURLConnection conn=null;
        try {
            URL url=new URL("http://www.baidu.com");
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
            out.writeBytes("我的信息是");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启线程来进行URL通信
     */
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    //这里写访问的地址，遵守http协议不能打开端口
                    URL url = new URL("http://59.41.252.66:6610");
                    conn = (HttpURLConnection) url.openConnection();
                    //设置超时和访问方式，使用GET方式返回的是HTML代码，如果想要传输数据用POST方式
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    //获得输入流
                    InputStream in = conn.getInputStream();
                    //读取输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    //通过Message封装
                    Message message = Message.obtain();
                    message.obj = sb.toString();
                    message.what = SHOW_RESPONSE;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null)
                        conn.disconnect();
                }
            }
        }).start();
    }
}
