package com.example.administrator.its.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.its.R;
import com.example.administrator.its.entity.ListViewData;

import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class ListViewAdapter extends ArrayAdapter<ListViewData> {
    private int resourceId;
    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<ListViewData> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListViewData data = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.listImage = view.findViewById(R.id.fruit_image);
            viewHolder.listName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.listImage .setImageResource(data.getImageId());
        viewHolder.listName.setText(data.getName());
        return view;
    }
    class ViewHolder{
        ImageView listImage;
        TextView listName;
    }
}
