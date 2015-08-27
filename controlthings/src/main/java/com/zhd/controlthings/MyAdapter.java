package com.zhd.controlthings;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.jar.Attributes;

import javax.security.auth.callback.Callback;

/**
 * Created by 2015032501 on 2015/8/26.
 */
public class MyAdapter extends BaseAdapter{

    private Context mContext;
    private MyClickListener mylistener;
    private String[] mData;

    public MyAdapter(Context mContext, String[] mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setListener(MyClickListener listener) {
        this.mylistener = listener;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int index=position;
        View v = View.inflate(mContext, R.layout.mylistitem, null);//得到布局文件的对象
        //找到其视图上的控件
        Button btn = (Button) v.findViewById(R.id.btn_show_position);
        //ImageView img= (ImageView) v.findViewById(R.id.img);
        TextView tv = (TextView) v.findViewById(R.id.tv_name);
        tv.setText(mData[position]);
        //对其添加事件和内容
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mylistener!=null){//这里点击时会调用之前在Activity中创建的接口对象，并把值传给他
                    mylistener.onclick(index);
                }
            }
        });
        return v;
    }
}
