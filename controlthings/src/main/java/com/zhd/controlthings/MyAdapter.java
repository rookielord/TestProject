package com.zhd.controlthings;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

/**
 * Created by 2015032501 on 2015/8/26.
 */
public class MyAdapter extends BaseAdapter{

    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.mylistitem, null);//得到布局文件的对象
        //找到其视图上的控件
        Button btn = (Button) v.findViewById(R.id.btn_show_position);
        //ImageView img= (ImageView) v.findViewById(R.id.img);
        TextView tv = (TextView) v.findViewById(R.id.tv_name);
        //对其添加事件和内容
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "当前item的位置是" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
