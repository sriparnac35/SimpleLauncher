package sriparna.hillhouse.com.explauncher;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by sriparna on 07/08/16.
 */
public class AppsPresenter implements AppListAdapter.OnAppClickListener,
        LoaderManager.LoaderCallbacks<List<AppData>> {
    private FragmentActivity mContext = null;
    private PackageManager mPackageManager = null;

    private List<AppData> mAppsList= null;
    public OnDataAvailableListener dataAvailableListener = null;

    private static final int ALL_APPS_ASYNC_LOADER = 2;


    public AppsPresenter(FragmentActivity context){
        mContext = context;
        mPackageManager = mContext.getPackageManager();

        mAppsList = new ArrayList<>();
    }

    public void loadAppsData(){
        mContext.getSupportLoaderManager().initLoader(ALL_APPS_ASYNC_LOADER, null, this);
    }

    @Override
    public void onAppCellClicked(View view, AppData data) {
        Intent intent = mPackageManager.getLaunchIntentForPackage(data.packageName);
        mContext.startActivity(intent);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new AllAppsAsyncTaskLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<List<AppData>> loader, List<AppData> data) {
        mAppsList.clear();
        mAppsList.addAll(data);
        if(dataAvailableListener != null){
            dataAvailableListener.onNewDataAvailable(mAppsList);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAppsList.clear();
        if(dataAvailableListener != null){
            dataAvailableListener.onNewDataAvailable(mAppsList);
        }
    }

    public interface OnDataAvailableListener{
        void onNewDataAvailable(List<AppData> appsList);
    }
}
