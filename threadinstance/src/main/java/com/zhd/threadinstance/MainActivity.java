package com.zhd.threadinstance;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.locks.Lock;


public class MainActivity extends ActionBarActivity {

    MyClass class1, class2;

    public static Object ob1 = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Class<String> content;
//        class1 = MyClass.getSingleTon();
//        class2 = MyClass.getSingleTon();
//        if (class1.equals(class2)) {
//            Toast.makeText(this, "两个是同样的实例", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "两个不同的实例", Toast.LENGTH_LONG).show();
//        }
//        ShowThreadInstance();
//        开启线程
//        new Thread(new MyThread1()).start();
//        new Thread(new MyThread2()).start();
//        ShowThreadInstance();
//        if (class1.equals(class2)) {
//            Toast.makeText(this, "两个是同样的实例", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "两个不同的实例", Toast.LENGTH_LONG).show();
//        }


        ShowThreadInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //获得使用进程来获得两个单例


    public void ShowThreadInstance() {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                class1 = MyClass.getSingleTon();
//                Log.i("class", "1" + class1.toString());
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                class2 = MyClass.getSingleTon();
//                Log.i("class", "2" + class2.toString());
//            }
//        }).start();
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyClass mine=MyClass.getSingleTon();
                    Log.i("class",mine.toString());
                }
            }).start();
        }

        synchronized (MyClass.class){
            MyClass.class.notifyAll();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        if (class1.equals(class2)) {
//            Log.i("time", "3");
//            Log.i("class", "两个class得到的实例一样");
//            Toast.makeText(this, "两个是同样的实例", Toast.LENGTH_LONG).show();
//
//        } else {
//            Log.i("time", "3");
//            Log.i("class", "两个class得到的实例不一样");
//            Toast.makeText(this, "两个不同的实例", Toast.LENGTH_LONG).show();
//        }
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

    class MyThread1 implements Runnable {

        @Override
        public void run() {
            //这里封装message
            Bundle bundle = new Bundle();
            bundle.putString("data", "这是第一个子线程中的内容，通过message传递过来");
            Message message1 = Message.obtain();
            message1.what = 1;
            MyClass myObj1 = MyClass.getSingleTon();//获得单例
            message1.obj = myObj1;
            message1.setData(bundle);
            mHandler.sendMessage(message1);
            Log.i("thread", "这是在第一个子线程中");
            Log.i("class1", myObj1.toString());
        }
    }

    class MyThread2 implements Runnable {
        @Override
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString("data", "这是第二个子线程中的内容，通过message传递过来");
            Message message2 = Message.obtain();
            message2.what = 2;
            MyClass myObj2 = MyClass.getSingleTon();
            message2.obj = myObj2;
            message2.setData(bundle);
            mHandler.sendMessage(message2);
            Log.i("thread", "这是在第二个子线程中");
            Log.i("class2", myObj2.toString());
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = null;
            switch (msg.what) {
                case 1:
                    class1 = (MyClass) msg.obj;
                    bundle = msg.getData();
                    String data = bundle.getString("data");
                    break;
                case 2:
                    class2 = (MyClass) msg.obj;
//                    Bundle bundle=msg.getData();
//                    String data=bundle.getString("data");
//                    tv.setText(data);
                    break;
            }
        }
    };
}
