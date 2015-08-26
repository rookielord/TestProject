package com.zhd.waitnotify;

/**
 * Created by 2015032501 on 2015/8/24.
 */
public class Producter implements Runnable {
    private Info mInfo = null;

    public Producter(Info info) {
        this.mInfo = info;
    }


    @Override
    public void run() {
        //重复设置10次名字和内容，名字设置后需要睡几秒
//        for (int i = 0; i < 10; i++) {
//            mInfo.setmName("name:" + i);
//            try {
//                Thread.sleep(90);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            mInfo.setmContent("content:" + i);
//        }
        //这里调用其中的方法来设置内容
        for (int i = 0; i < 10; i++) {
            mInfo.set("name"+i,"content"+i);
        }
    }
}
