package sriparna.hillhouse.com.explauncher;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by sriparna on 07/08/16.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_screen);

        Button button = (Button)findViewById(R.id.all_apps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAllAppsButtonClicked();
            }
        });

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable currentWallpaper = wallpaperManager.getDrawable();


        View rootView = findViewById(R.id.mainscreen_rootview);
        rootView.setBackground(currentWallpaper);
    }

    private void onAllAppsButtonClicked(){
        Intent intent = new Intent();
        intent.setClass(this, AppListActivity.class);
        startActivity(intent);
    }
}
