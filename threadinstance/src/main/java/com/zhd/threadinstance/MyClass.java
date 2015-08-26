package com.zhd.threadinstance;

import android.util.Log;

import org.apache.http.impl.conn.tsccm.WaitingThread;

/**
 * Created by 2015032501 on 2015/8/21.
 */
public class MyClass {
    /**
     * 懒汉模式，线程不安全
     */
//    private int i=20;
//    public void reduceNum(){
//        for (;i>=0;i--){
//            Log.i("减少的数量",String.valueOf(i));
//        }
//    }
    private static MyClass SingleInstance = null;

    public static synchronized MyClass getSingleTon() {
        if (SingleInstance == null) {
            try {
                    MyClass.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                Log.i("thread", Thread.currentThread().getId() + "");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            SingleInstance = new MyClass();
        }
        return SingleInstance;
    }

    private MyClass() {
    }
    /**
     * 饿汉模式线程安全
     */
//    private static final MyClass  SingleTon=new MyClass();
//    //静态工场方法
//    public static MyClass getSingleTon(){
//        return  SingleTon;
//    }

}
