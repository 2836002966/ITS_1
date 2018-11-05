package com.example.administrator.its.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.its.R;
import com.example.administrator.its.entity.EnvironmentalBean;


import java.util.List;

public class EnvirAdapter extends BaseAdapter {

    private EnvironmentalBean bean;
    private Context context;
    public EnvirAdapter(Context context, EnvironmentalBean bean) {
        this.context=context;
        this.bean=bean;
    }

    @Override
    public int getCount() {
        return 6;
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
        if(convertView==null){
            convertView=View.inflate(context, R.layout.environmental,null);
        }
            TextView data_1=convertView.findViewById(R.id.text_001);
        TextView data_2=convertView.findViewById(R.id.text_002);
        TextView data_3=convertView.findViewById(R.id.text_003);
        TextView data_4=convertView.findViewById(R.id.text_004);
        TextView data_5=convertView.findViewById(R.id.text_005);
        TextView data_6=convertView.findViewById(R.id.text_006);
            switch (position){
                case 0:

                    data_1.setText(""+bean.getTemperature());
                    break;
                case 1:

                    data_2.setText(""+bean.getHumidity());
                    break;
                case 2:

                    data_3.setText(""+bean.getLightIntensity());
                    break;
                case 3:

                    data_4.setText(""+bean.getCo2());
                    break;
                case 4:

                    data_5.setText(""+bean.getPm());
                    break;
                case 5:

                    data_6.setText(""+bean.getStatus());
                    break;
            }

        return convertView;
    }
}
