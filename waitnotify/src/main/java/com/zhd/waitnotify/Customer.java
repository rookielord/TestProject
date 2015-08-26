package com.zhd.waitnotify;

import android.util.Log;

/**
 * Created by 2015032501 on 2015/8/24.
 */
public class Customer implements Runnable {
    private Info mInfo = null;

    public Customer(Info mInfo) {
        this.mInfo = mInfo;
    }

    @Override
    public void run() {
        //使用for循环，每次循环开始先暂停10秒
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("data", mInfo.getmName() + "-->" + mInfo.getmContent());
//        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mInfo.get();
        }
    }
}
