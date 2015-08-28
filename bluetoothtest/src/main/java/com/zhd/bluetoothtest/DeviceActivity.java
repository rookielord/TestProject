package com.zhd.bluetoothtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by 2015032501 on 2015/8/28.
 */
public class DeviceActivity extends Activity {

    //创建一个handler来实现线程间通信
    private Handler mhandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
    }

}
