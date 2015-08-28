package com.zhd.reflector;

/**
 * Created by 2015032501 on 2015/8/27.
 * 这里规定调用函数的传入的形参类型
 */
public interface MyCallback {
    void onclick(int position);
    void showContent(String msg);
}
