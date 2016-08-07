package sriparna.hillhouse.com.explauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by sriparna on 07/08/16.
 */
public class AppListActivity extends AppCompatActivity implements AppsPresenter.OnDataAvailableListener{
    private RecyclerView mRecyclerView = null;
    private AppListAdapter mRecyclerViewAdapter = null;
    private AppsPresenter mAppsPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_apps);
        mRecyclerView = (RecyclerView)findViewById(R.id.apps_list);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAppsPresenter = new AppsPresenter(this);
        mRecyclerViewAdapter = new AppListAdapter(this);

        mAppsPresenter.dataAvailableListener = this;
        mRecyclerViewAdapter.mListener = mAppsPresenter;

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mAppsPresenter.loadAppsData();
    }

    @Override
    public void onNewDataAvailable(List<AppData> appsList) {
        mRecyclerViewAdapter.onNewDataAvailable(appsList);
    }
}


