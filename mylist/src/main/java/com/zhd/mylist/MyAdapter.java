package com.zhd.mylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 2015032501 on 2015/8/27.
 * 这里的是具体的内容，然后里面触发
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private ImageView view;
    private TextView tv_content;
    private Button btn;
    private String[] mContent;

    //回调接口字段
    private MyCallback callback;
    //布设在外面可以回调的接口
    public void setCallback(MyCallback callback) {
        this.callback = callback;
    }

    public MyAdapter(Context mContext, String[] mContent) {
        this.mContext = mContext;
        this.mContent = mContent;
    }

    @Override
    public int getCount() {
        return mContent.length;
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
        View v=View.inflate(mContext,R.layout.listitem,null);
        //找到上面的控件
        btn = (Button) v.findViewById(R.id.btn_click);
        tv_content = (TextView) v.findViewById(R.id.tv_content);
        view = (ImageView) v.findViewById(R.id.iv_img);
        view.setImageResource(R.drawable.wordpress);
        if (position==2)
        {
            view.setImageResource(R.drawable.qq
            );
        }
        tv_content.setText(mContent[position]);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onclick(index);
            }
        });
        //添加信息
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callback.onclick(index);
//            }
//        });
        return v;
    }
}
