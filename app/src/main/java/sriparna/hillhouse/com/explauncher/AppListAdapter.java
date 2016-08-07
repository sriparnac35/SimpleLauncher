package sriparna.hillhouse.com.explauncher;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriparna on 07/08/16.
 */
public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppViewHolder>{
    private Context mContext = null;
    private List<AppData> mAppList = null;
    private LayoutInflater mLayoutInflater = null;
    public OnAppClickListener mListener = null;

    public AppListAdapter(Context context){
        mContext = context;
        mAppList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount(){
        return mAppList.size();
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int type){
        View view = mLayoutInflater.inflate(R.layout.app_cell,null,false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder viewHolder, final int position){
        AppData data = mAppList.get(position);
        viewHolder.populate(data.icon, data.label);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onAppCellClicked(view, mAppList.get(position));
                }
            }
        });
    }

    public void onNewDataAvailable(List<AppData> appsList) {
        mAppList.clear();
        mAppList.addAll(appsList);
        notifyDataSetChanged();
    }


    public static class AppViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView label;

        public AppViewHolder(View view){
            super(view);
            icon = (ImageView)view.findViewById(R.id.app_icon);
            label = (TextView)view.findViewById(R.id.app_name);
        }

        public void populate(Drawable icon, String label){
            this.icon.setImageDrawable(icon);
            this.label.setText(label);
        }
    }

    public interface OnAppClickListener{
        void onAppCellClicked(View view, AppData data);
    }

}
