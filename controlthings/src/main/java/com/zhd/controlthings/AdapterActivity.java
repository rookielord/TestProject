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
import android.widget.Toast;

/**
 * Created by 2015032501 on 2015/8/26.
 */
public class AdapterActivity extends Activity {

    private ListView lv;
    String[] names = new String[20];

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
    }

    class MyAdapter extends BaseAdapter {

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
            View v = View.inflate(AdapterActivity.this, R.layout.mylistitem, null);//得到布局文件的对象
            //找到其视图上的控件
            Button btn= (Button) v.findViewById(R.id.btn_show_position);
            ImageView img= (ImageView) v.findViewById(R.id.img);

            //对其添加事件和内容
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AdapterActivity.this,"当前item的位置是"+position,Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }
}
