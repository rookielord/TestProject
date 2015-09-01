package com.zhd.mysocket;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends ActionBarActivity {

    private Button btn_send, btn_login, btn_close;
    private EditText et_content;
    private TextView tv_content;
    private Socket mSocket;
    //输入流==>阅读流和输出流
    BufferedReader br;
    //读取出来的数据
    private String mStrMSG="";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                tv_content.setText(mStrMSG);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这是发送消息，通过线程启动
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage();
                    }
                }).start();
            }
        });
        //在登录的时候建立连接，并给各种流赋值
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loginServer();
                    }
                }).start();
                new Thread(mRunnable).start();
            }
        });
        btn_close= (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        et_content = (EditText) findViewById(R.id.et_content);
        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    private void loginServer() {
        try {
            //连接服务器获得各种流
            mSocket = new Socket("172.16.23.163", 11060);
            //输入读取流
            br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        Socket socket = mSocket;
        String sendresult = et_content.getText().toString();
        try {
            OutputStream out=mSocket.getOutputStream();
            //获取输出流
            out.write(sendresult.getBytes("UTF-8"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessage() {
        Socket socket = mSocket;
        String content;
        //创建socket连接
        try {
            //获取输入流(变成了读入的内容)和输出流
            //读取其信息
            String msg = br.readLine();
            if (msg != null) {
                content = msg;
            } else {
                content = "数据错误";
            }
            //线程通信
//            Message message = Message.obtain();
//            message.obj = content;
//            message.what = 1;
//            handler.sendMessage(message);
            //发送信息

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //不断监听服务端传过来内容的线程
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if ((mStrMSG=br.readLine())!=null){
                        mStrMSG+="\n";
                    handler.sendMessage(Message.obtain());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

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
