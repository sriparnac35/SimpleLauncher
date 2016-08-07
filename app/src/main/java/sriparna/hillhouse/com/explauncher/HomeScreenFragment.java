package sriparna.hillhouse.com.explauncher;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sriparna on 07/08/16.
 */
public class HomeScreenFragment extends Fragment {
    private View mAllAppsButton = null;
    private TextView mTimeView = null;
    private TextView mDateView = null;

    private Timer mTimer = null;
    private TimerTask mTimerTask = null;

    private Handler mTimeHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen, parent, false);

        mTimeView = (TextView)view.findViewById(R.id.time_view);
        mDateView = (TextView)view.findViewById(R.id.date_view);
        mAllAppsButton = view.findViewById(R.id.all_apps);

        mAllAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAllAppsButtonClicked();
            }
        });

        return view;
    }


    private void onAllAppsButtonClicked(){
        ((OnAllAppsClickedListener)getActivity()).onAllAppsClicked();
    }

    @Override
    public void onStart(){
        super.onStart();
        mTimer = new Timer();

        mTimeHandler = new Handler(Looper.getMainLooper()){

            @Override
            public void handleMessage(Message msg) {
                updateTime();
            }
        };

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message m =  Message.obtain();
                mTimeHandler.sendMessage(m);
            }
        };
        mTimer.schedule(mTimerTask,0,1000);
    }

    @Override
    public void onStop(){
        super.onStop();
        mTimerTask.cancel();
        mTimer.cancel();
    }

    private void updateTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        String timeString = "" + hour + ":" + minute;
        String dayString = "" + day + "-" + month + "-" + year;

        mTimeView.setText(timeString);
        mDateView.setText(dayString);
    }

    public interface OnAllAppsClickedListener{
        void onAllAppsClicked();
    }
}
