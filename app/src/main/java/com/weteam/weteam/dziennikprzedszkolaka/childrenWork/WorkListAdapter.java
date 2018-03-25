package com.weteam.weteam.dziennikprzedszkolaka.childrenWork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weteam.weteam.dziennikprzedszkolaka.R;

import java.util.ArrayList;

/**
 * Created by User on 2018-01-14.
 */

public class WorkListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Work> worksList;

    public WorkListAdapter(Context context, int layout, ArrayList<Work> worksList) {
        this.context = context;
        this.layout = layout;
        this.worksList = worksList;
    }


    @Override
    public int getCount() {
        return worksList.size();
    }

    @Override
    public Object getItem(int position) {
        return worksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder holder=new ViewHolder();

        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.txtName=(TextView) row.findViewById(R.id.txtName);
            holder.imageView=(ImageView) row.findViewById(R.id.imgWork);

            row.setTag(holder);
        }
        else{
            holder=(ViewHolder) row.getTag();
        }

        Work work= worksList.get(position);

        holder.txtName.setText(work.getName());

        byte[] workImage=work.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(workImage, 0, workImage.length);
        holder.imageView.setImageBitmap(bitmap);


        return row;
    }
}
