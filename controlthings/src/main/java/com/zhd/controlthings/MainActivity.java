package com.zhd.controlthings;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button btn_normal1,btn_normal2,btn_custom,btn_clear,btn_adapter;
    private static final int NOTIFICATION_FLAG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_normal1= (Button) findViewById(R.id.btn_normal1);
        btn_normal2= (Button) findViewById(R.id.btn_normal2);
        btn_custom= (Button) findViewById(R.id.custom);
        btn_clear= (Button) findViewById(R.id.clear);
        btn_adapter= (Button) findViewById(R.id.adapter);
        //监听控件
        btn_normal1.setOnClickListener(this);
        btn_normal2.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_adapter.setOnClickListener(this);
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
        //获取通知服务，只有获得了服务才可以添加进去
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        switch (v.getId()){
            case R.id.btn_normal1:
                //创建现在用的普通通知
                Intent intent=new Intent(this,MainActivity.class);
                PendingIntent pi1=PendingIntent.getActivity(this,0,intent,0);
                Notification notify1=new Notification();
                notify1.icon= R.drawable.notification_template_icon_bg;
                notify1.tickerText="你有内容注意查收";//设置内容
                notify1.when=System.currentTimeMillis();
                notify1.setLatestEventInfo(this,"通知标题","通知的主要内容",pi1);
                notify1.number=1;
                notify1.flags=Notification.FLAG_NO_CLEAR;
                manager.notify(NOTIFICATION_FLAG,notify1);
                break;
            case R.id.btn_normal2:
//                PendingIntent pi2=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
//                //在API16后才支持
//                Notification notify2=new Notification.Builder(this)
//                        .setTicker("一闪而逝的内容")
//                        .setContentTitle("下拉通知的标题")
//                        .setContentText("下拉通知的内容")
//                        .setContentIntent(pi2).setNumber(1).build();
//                notify2.flags=Notification.FLAG_AUTO_CANCEL;
//                manager.notify(NOTIFICATION_FLAG,notify2);
                break;
            case R.id.custom:
                Notification myNotify = new Notification();
                myNotify.icon = R.drawable.notification_template_icon_bg;
                myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
                myNotify.when = System.currentTimeMillis();//立刻显示
                myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除,而且必须删除应用才会被清除
                RemoteViews rv = new RemoteViews(getPackageName(),
                        R.layout.mynotifycation);
                rv.setTextViewText(R.id.content, "hello wrold!");
                myNotify.contentView = rv;
                Intent intent3 = new Intent(Intent.ACTION_MAIN);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                        intent3, 0);
                myNotify.contentIntent = contentIntent;
                manager.notify(NOTIFICATION_FLAG, myNotify);
                break;
            case R.id.clear:
                manager.cancel(NOTIFICATION_FLAG);
                break;
            case R.id.adapter:
                Intent intent4=new Intent("com.zhd.adapter");
                startActivity(intent4);
                break;
        }
    }
}
