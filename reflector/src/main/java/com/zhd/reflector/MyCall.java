package com.zhd.reflector;

/**
 * Created by 2015032501 on 2015/8/27.
 * 这里是向其中传入参数。
 */
public class MyCall {
    private static final String NAME="李家吉";
    private static final int NUM=117;
    private MyCallback mcallback;//建立接口字段

    public void setCallback(MyCallback callback) {//建立对外的赋值接口
        this.mcallback = callback;
    }

    public void showMyCallback(){
        mcallback.onclick(NUM);
        mcallback.showContent(NAME);
    }

}
