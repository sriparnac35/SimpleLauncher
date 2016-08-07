package sriparna.hillhouse.com.explauncher;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
public class LauncherActivity extends AppCompatActivity
        implements HomeScreenFragment.OnAllAppsClickedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_screen);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable currentWallpaper = wallpaperManager.getDrawable();

        View rootView = findViewById(R.id.mainscreen_rootview);
        rootView.setBackground(currentWallpaper);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Fragment fragment = new HomeScreenFragment();
        getSupportFragmentManager().beginTransaction().
                replace(android.R.id.content, fragment).commit();
    }

    @Override
    public void onAllAppsClicked() {
        Fragment fragment = new AppListFragment();
        getSupportFragmentManager().beginTransaction().
                replace(android.R.id.content, fragment).addToBackStack("").commit();
    }
}
