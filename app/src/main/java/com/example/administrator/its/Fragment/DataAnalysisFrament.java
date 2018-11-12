package com.example.administrator.its.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.its.Adapter.MyViewPager;
import com.example.administrator.its.Adapter.ViewPagerAdatper;
import com.example.administrator.its.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 * 数据分析
 */

public class DataAnalysisFrament extends Fragment {
     ViewPager mViewPager;
    List<Fragment>list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dataanalysis,null);
        mViewPager = view.findViewById(R.id.my_viewpager);
        initData();
        mViewPager.setAdapter(new ViewPagerAdatper(getChildFragmentManager(),list));
        return view;
    }
    private void initData() {
        list.add(new Fragment001());
        list.add(new Fragment002());
        list.add(new Fragment003());
        list.add(new Fragment004());
        list.add(new Fragment005());
        list.add(new Fragment006());

    }


}
