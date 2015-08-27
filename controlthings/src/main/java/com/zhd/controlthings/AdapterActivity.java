package com.zhd.controlthings;

import android.app.Activity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 2015032501 on 2015/8/26.
 */
public class AdapterActivity extends Activity {

    private final int i=3;//需要添加的内容
    private ListView lv;
    String[] names = new String[20];//数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        lv = (ListView) findViewById(R.id.lv);
        //设置需要填充的数据
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                names[i] = "小黄" + i;
            else
                names[i] = "小红" + i;
        }
        MyAdapter adapter=new MyAdapter(this,names);//得到adapter对象，里面有填充的方法
        adapter.setListener(new MyClickListener() {//这里是创建了接口的对象，并赋值给它的方法
            @Override
            public void onclick(int position) {//具体接口方法的实现位置，positon的值是在之前那里MyAdapter中将值传过来的
                Toast.makeText(AdapterActivity.this,"当前位置是"+position+i,Toast.LENGTH_SHORT).show();
            }
        });
        lv.setAdapter(adapter);
    }
    public void showContent(int i){
        Toast.makeText(this,String.valueOf(i),Toast.LENGTH_SHORT).show();
    }

//    class MyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return names.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        //返回的是每个item的数量
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            View v = View.inflate(AdapterActivity.this, R.layout.mylistitem, null);//得到布局文件的对象
//            //找到其视图上的控件
//            Button btn= (Button) v.findViewById(R.id.btn_show_position);
//            //ImageView img= (ImageView) v.findViewById(R.id.img);
//            TextView tv= (TextView) v.findViewById(R.id.tv_name);
//            //对其添加事件和内容
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(AdapterActivity.this,"当前item的位置是"+position,Toast.LENGTH_SHORT).show();
//                }
//            });
//            tv.setText(names[position]);
//            return v;
//        }
//    }
}
