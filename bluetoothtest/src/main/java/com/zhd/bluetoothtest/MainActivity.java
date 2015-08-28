package com.zhd.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    //请求打开蓝牙
    private static final int REQUEST_ENABLE = 0x1;

    //请求能够被搜索
    private static final int REQUEST_DISCOVERABLE=0x2;
    //蓝牙适配器
    private BluetoothAdapter adapter;
    //按钮
    Button btn_open,btn_search,btn_discover,btn_close,btn_client,btn_server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得蓝牙适配器
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            Toast.makeText(this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }
        //对按钮添加监听
        btn_open= (Button) findViewById(R.id.open);
        btn_close= (Button) findViewById(R.id.close);
        btn_discover= (Button) findViewById(R.id.discover);

        btn_open.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        btn_discover.setOnClickListener(this);
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

    /**
     * 以下两种都可以打开蓝牙，但是通过intent打开的用户体验更好
     * 直接使用适配器对象打开体验不好
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.open:
                intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE);
                //adapter.enable();
                intent=null;
                break;
            case R.id.discover:
                intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //这里设置蓝牙设备搜索的时间
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
                startActivityForResult(intent, REQUEST_DISCOVERABLE);
                //adapter.cancelDiscovery();
                intent=null;
                break;
            case R.id.close:
                adapter.disable();
                break;
            case R.id.search:
                intent=new Intent(this,DeviceActivity.class);
                startActivity(intent);
                break;
        }

    }
}
