package com.example.administrator.its.Fragment;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.its.Activity.BalanceActivity;
import com.example.administrator.its.Adapter.EnvirAdapter;
import com.example.administrator.its.DataBase.DataOperator;
import com.example.administrator.its.R;
import com.example.administrator.its.Util.CacheUntil;
import com.example.administrator.its.Util.NetUntil;
import com.example.administrator.its.entity.EnvironmentalBean;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/11/3.
 * 环境指标
 */

public class EnvironmentalFragment extends Fragment {
    private TextView temp;
    private TextView humidity;
    private TextView LightIntensity;
    private TextView co2;
    private TextView pm;
    private TextView status;
    private NetUntil netUntil;
    private DataOperator manager;
    EnvironmentalBean bean =new EnvironmentalBean();
    private final static int SEND_ENM=1;
    private final static int SEND_STATUS=2;
    private List<EnvironmentalBean> list=new ArrayList<>();
    private EnvirAdapter envirAdapter;
    private String ip;
    private String port;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.environmental,null);
        temp = view.findViewById(R.id.text_001);
        humidity = view.findViewById(R.id.text_002);
        LightIntensity = view.findViewById(R.id.text_003);
        co2 = view.findViewById(R.id.text_004);
        pm = view.findViewById(R.id.text_005);
        status = view.findViewById(R.id.text_006);
        envirAdapter=new EnvirAdapter(getContext(),bean);
        ip= CacheUntil.getString(getContext(),"url","192.168.1.109");
        port=CacheUntil.getString(getContext(),"port","8080");

        netUntil=new NetUntil();

        Message message=handler.obtainMessage();
        message.what=0;
        handler.sendMessage(message);


        return view;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    netUntil.getData("{}","http://192.168.1.109:8080/transportservice/type/jason/action/GetAllSense.do",handler);
                    Message message= handler.obtainMessage();
                    message.what=0;
                    handler.sendMessageDelayed(message,5000);
                    break;
                case 1:
                    LightIntensity.setText(bean.getLightIntensity()+"");
                    humidity.setText(bean.getHumidity()+"");
                    temp.setText(bean.getTemperature()+"");
                    co2.setText(bean.getCo2()+"");
                    pm.setText(bean.getPm()+"");
                    status.setText(bean.getStatus()+"");
                    list.add(bean);
                    saveAvg(getContext());
                    break;
                case NetUntil.NET_GETDATA:
                    setJson(msg.obj.toString());
                    break;
            }
        }
    };




    private void setJson(String result){
        Log.e("aaaaaa",result);
        try {
            String serverinfo= new JSONObject(result).getString("serverinfo");
            JSONObject info= new JSONObject(serverinfo);
            Message message=handler.obtainMessage();
            if(info.isNull("Status")){
                bean.setLightIntensity(info.getInt("LightIntensity"));
                bean.setHumidity(info.getInt("humidity"));
                bean.setTemperature(info.getInt("temperature"));
                bean.setCo2(info.getInt("co2"));
                bean.setPm(info.getInt("pm2.5"));
                netUntil.getData("{\"RoadId\" : 1}","http://192.168.1.109:8080/transportservice/type/jason/action/GetRoadStatus.do",handler);
            }else{
                bean.setStatus(info.getInt("Status"));
                message.what=1;
                message.obj=bean;
                handler.sendMessage(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  void saveAvg(Context context) {
        manager = manager.getInstance(context);
        if(list.size() == 12){
            int temperature = 0;
            int humidity = 0;
            int lightIntensity = 0;
            int co2 = 0;
            int pm = 0;
            int status = 0;
            SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
            String time = formatter.format(System.currentTimeMillis());
            for (int i = 0; i < list.size(); i++) {
                EnvironmentalBean bean1 = list.get(i);
                temperature+= bean1.getTemperature();
                humidity+= bean1.getHumidity();
                lightIntensity+= bean1.getLightIntensity();
                co2+= bean1.getCo2();
                pm+= bean1.getPm();
                status+= bean1.getStatus();
            }
            Long i = manager.insertData(time,Math.round(temperature/12),
                    Math.round(humidity/12), Math.round(lightIntensity/12),
                    Math.round(co2/12), Math.round(pm/12),Math.round(status/12));
            if(i > 0){
                Toast.makeText(getActivity(),"保存成功",Toast.LENGTH_SHORT).show();
            }
            list.clear();
            Log.e("IndexActivity", "saveAvg: " + temperature + " "
                    + humidity + " "
                    + lightIntensity + " "
                    + co2 + " "
                    + pm + " "
                    + status
            );
        }
    }
}
