package sriparna.hillhouse.com.explauncher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sriparna on 07/08/16.
 */
public class AppsPresenter implements AppListAdapter.OnAppClickListener{
    private Context mContext = null;
    private PackageManager mPackageManager = null;
    private Intent mAllAppsIntent = null;
    private List<AppData> mAppsList= null;
    public OnDataAvailableListener dataAvailableListener = null;


    public AppsPresenter(Context context){
        mContext = context;
        mPackageManager = mContext.getPackageManager();
        mAllAppsIntent = new Intent();
        mAllAppsIntent.setAction(Intent.ACTION_MAIN);
        mAllAppsIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mAppsList = new ArrayList<>();
    }

    public void loadAppsData(){
        List<ResolveInfo> intentList = mPackageManager.
                queryIntentActivities(mAllAppsIntent,  PackageManager.MATCH_ALL  );

        for(ResolveInfo info : intentList){
            AppData data = new AppData();
            data.icon = info.loadIcon(mPackageManager);
            data.label = (String) info.loadLabel(mPackageManager);
            data.packageName = info.activityInfo.packageName;
            mAppsList.add(data);
        }
        if(dataAvailableListener != null){
            dataAvailableListener.onNewDataAvailable(mAppsList);
        }
    }

    @Override
    public void onAppCellClicked(View view, AppData data) {
        Intent intent = mPackageManager.getLaunchIntentForPackage(data.packageName);
        mContext.startActivity(intent);
    }

    public interface OnDataAvailableListener{
        void onNewDataAvailable(List<AppData> appsList);
    }
}
