package com.example.administrator.its.configuration;

import android.support.v4.widget.DrawerLayout;

import com.example.administrator.its.R;
import com.example.administrator.its.entity.ListViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class ListViewConfiguration {

    public void initListData( List fruitList) {
        ListViewData banana = new ListViewData("车辆账户管理", R.mipmap.ic_launcher);
        fruitList.add(banana);
        ListViewData orange = new ListViewData("公交查询",R.mipmap.ic_launcher);
        fruitList.add(orange);
        ListViewData watermelon = new ListViewData("红绿灯管理",R.mipmap.ic_launcher);
        fruitList.add(watermelon);
        ListViewData paer = new ListViewData("车辆违章",R.mipmap.ic_launcher);
        fruitList.add(paer);
        ListViewData grape = new ListViewData("路况查询",R.mipmap.ic_launcher);
        fruitList.add(grape);
        ListViewData pineapple = new ListViewData("生活助手",R.mipmap.ic_launcher);
        fruitList.add(pineapple);
        ListViewData strawberry = new ListViewData("数据分析",R.mipmap.ic_launcher);
        fruitList.add(strawberry);
        ListViewData cherry = new ListViewData("个人中心",R.mipmap.ic_launcher);
        fruitList.add(cherry);
        ListViewData aaa = new ListViewData("环境指标",R.mipmap.ic_launcher);
        fruitList.add(aaa);
    }
}
