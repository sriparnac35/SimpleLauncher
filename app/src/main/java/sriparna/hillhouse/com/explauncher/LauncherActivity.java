package sriparna.hillhouse.com.explauncher;

import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by sriparna on 07/08/16.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Button button = (Button)findViewById(R.id.all_apps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAllAppsButtonClicked();
            }
        });
    }

    private void onAllAppsButtonClicked(){
        Intent intent = new Intent();
        intent.setClass(this, AppListActivity.class);
        startActivity(intent);
    }
}
