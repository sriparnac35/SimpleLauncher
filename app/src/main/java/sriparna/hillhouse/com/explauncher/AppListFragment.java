package sriparna.hillhouse.com.explauncher;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import java.util.List;

/**
 * Created by sriparna on 07/08/16.
 */
public class AppListFragment extends Fragment
        implements AppsPresenter.OnDataAvailableListener{
    private RecyclerView mRecyclerView = null;
    private ProgressBar mProgressBar = null;
    private AppListAdapter mRecyclerViewAdapter = null;
    private AppsPresenter mAppsPresenter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.all_apps, parent, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.apps_list);
        mProgressBar = (ProgressBar)view.findViewById(R.id.loading_indicator);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        mAppsPresenter = new AppsPresenter(getActivity());
        mRecyclerViewAdapter = new AppListAdapter(getActivity());

        mAppsPresenter.dataAvailableListener = this;
        mRecyclerViewAdapter.mListener = mAppsPresenter;

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mAppsPresenter.loadAppsData();
    }

    @Override
    public void onNewDataAvailable(List<AppData> appsList) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerViewAdapter.onNewDataAvailable(appsList);
    }


}


