package sriparna.hillhouse.com.explauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriparna on 07/08/16.
 */
public class AllAppsAsyncTaskLoader extends AsyncTaskLoader<List<AppData>> {
    private List<AppData> mAppDataList = null;
    private Intent mAllAppsIntent = null;
    private PackageManager mPackageManager = null;

    public AllAppsAsyncTaskLoader(Context context) {
        super(context);

        mAllAppsIntent = new Intent();
        mAllAppsIntent.setAction(Intent.ACTION_MAIN);
        mAllAppsIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mPackageManager = context.getPackageManager();
    }

    @Override
    public List<AppData> loadInBackground() {
        mAppDataList = new ArrayList<>();

        List<ResolveInfo> applicationInfos =
                mPackageManager.queryIntentActivities(mAllAppsIntent, PackageManager.MATCH_ALL);

        for(ResolveInfo info : applicationInfos) {
            AppData appData = new AppData();
            appData.packageName = info.activityInfo.packageName;
            appData.label = info.loadLabel(mPackageManager).toString();
            appData.icon = info.loadIcon(mPackageManager);
            mAppDataList.add(appData);
        }

        return mAppDataList;
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        if(mAppDataList != null){
            deliverResult(mAppDataList);
        }
        else {
            forceLoad();
        }
    }
}
