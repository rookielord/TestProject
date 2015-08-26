package com.zhd.waitnotify;

import android.util.Log;

/**
 * Created by 2015032501 on 2015/8/24.
 */
public class Info {
    private String mContent = "content";
    private String mName = "name";
    private boolean mFlag = true;

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public synchronized void set(String name, String content) {
        if (!mFlag) {//如果标签为false，可以不生产
            try {
                wait();//停止当前进程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setmName(name);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setmContent(content);
        mFlag = false;//生产结束，唤醒消费
        this.notify();
    }

    public synchronized void get() {
        if (mFlag) {//如果为true，则不需要消费，需要生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("data", this.mName + ":-->" + this.mContent);
        //消费后，然后把标签设置为true(表示需要生产),并暂停该线程,唤醒生产线程
        mFlag = true;
        this.notify();
    }
}
