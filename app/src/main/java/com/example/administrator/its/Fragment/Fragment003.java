package com.example.administrator.its.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.its.R;
import com.example.administrator.its.Util.ChartUtils;
import com.example.administrator.its.Util.NetUntil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */

public class Fragment003 extends Fragment{
    private LineChart lineChart;
    private LineData lineData;
    NetUntil netUntil;
    List<Entry> values = new ArrayList<>();
    List<Float> num = new ArrayList<>();
    List<Date> date=new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment003,null);
        netUntil=new NetUntil();
        lineChart = view.findViewById(R.id.chart003);
        ChartUtils.initChart(lineChart);
        ChartUtils.notifyDataSetChanged(lineChart, getData());
        handler.sendEmptyMessage(0);
        return view;
    }
    private List<Entry> getData() {
        handler.sendEmptyMessage(1);
        values.add(new Entry(1, 15));
        values.add(new Entry(2, 15));
        values.add(new Entry(3, 20));
        values.add(new Entry(4, 25));
        values.add(new Entry(5, 20));
        return values;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:

                    netUntil.getData("{}","http://192.168.1.109:8080/transportservice/type/jason/action/GetAllSense.do",handler);

                    sendEmptyMessageDelayed(0,5000);
                    break;
                case NetUntil.NET_GETDATA:
                    setJson(msg.obj.toString());
                    break;

            }


        }
    };
    private void setJson(String result){
        Log.e("ccccccc",result);
        try {
            String serverinfo= new JSONObject(result).getString("serverinfo");
            JSONObject info= new JSONObject(serverinfo);
            num.add((float) info.getInt("temperature"));
            if(num.size()==5){
                values.clear();
                for(int i=0;i<num.size();i++){
                    values.add(new Entry(i+1,num.get(i)));
                }
                num.clear();
                ChartUtils.notifyDataSetChanged(lineChart, values);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
