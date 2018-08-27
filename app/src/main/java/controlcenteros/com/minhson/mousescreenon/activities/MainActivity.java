package controlcenteros.com.minhson.mousescreenon.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import controlcenteros.com.minhson.mousescreenon.R;
import controlcenteros.com.minhson.mousescreenon.util.Constants;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initViews();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.FINIS_MAIN_ACT);
        filter.addAction(Constants.FINIS_MAIN_ACTT);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case Constants.FINIS_MAIN_ACT:
                        finish();

                        break;

                    case Constants.FINIS_MAIN_ACTT:
                        finish();

                        break;
                }
            }
        };
        registerReceiver(receiver, filter);
    }

    private void initViews() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        }, 2500);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
