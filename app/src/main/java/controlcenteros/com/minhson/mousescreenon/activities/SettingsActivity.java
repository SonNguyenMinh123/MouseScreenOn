package controlcenteros.com.minhson.mousescreenon.activities;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import controlcenteros.com.minhson.mousescreenon.R;
import controlcenteros.com.minhson.mousescreenon.dialog.InformationDialog;
import controlcenteros.com.minhson.mousescreenon.service.MyService;
import controlcenteros.com.minhson.mousescreenon.util.Constants;
import controlcenteros.com.minhson.mousescreenon.util.PreferenceUtils;

/**
 * Created by Administrator on 6/9/2017.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Switch swShowMouse;
    private RelativeLayout rlSettings;
    private RelativeLayout rlInformation;
    private RelativeLayout rlShareFacebook;
    private RelativeLayout rlRate;
    private RelativeLayout rlUnstall;
    private boolean isClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
    }

    private void initViews() {
        swShowMouse = (Switch) findViewById(R.id.sw_show_mouse);
        rlSettings = (RelativeLayout) findViewById(R.id.rl_settings);
        rlInformation = (RelativeLayout) findViewById(R.id.rl_information);
        rlShareFacebook = (RelativeLayout) findViewById(R.id.rl_share_facebook);
        rlUnstall = (RelativeLayout) findViewById(R.id.rl_uninstall);
        rlRate = (RelativeLayout) findViewById(R.id.rl_rate);

        initEvent();
        receiver();
        initState();
        canOverlayPermission();
    }

    private void initEvent() {
        swShowMouse.setOnCheckedChangeListener(this);
        rlSettings.setOnClickListener(this);
        rlInformation.setOnClickListener(this);
        rlShareFacebook.setOnClickListener(this);
        rlRate.setOnClickListener(this);
        rlUnstall.setOnClickListener(this);
    }

    public void receiver() {
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        }, new IntentFilter(Constants.FINIS_MAIN_ACTT));
    }

    public void initState() {
        if (PreferenceUtils.getBoolean(Constants.STATE_IMAGE_SHOW_MOUSE, this)) {
            swShowMouse.setChecked(true);
        } else {
            swShowMouse.setChecked(false);
        }
    }

    public void canOverlayPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1000);//dung de nhan ket qua cua ng dung tra ve
            }
//            else {
//                startService(new Intent(SettingsActivity.this, MyService.class));
//                sendBroadcast(new Intent(Constants.FINIS_MAIN_ACTT));
//                finish();
//            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_settings:
                startActivity(new Intent(this, ChangeTimeActivity.class));

                break;

            case R.id.rl_information:
                InformationDialog informationDialog = new InformationDialog(this);
                informationDialog.show();

                break;

            case R.id.rl_share_facebook:
                final String appPackageName = getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out Aap Ka Sewak App at: https://play.google.com/store/apps/details?id=" + appPackageName);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                break;

            case R.id.rl_rate:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }

                break;

            case R.id.rl_uninstall:
                String packageName = getPackageName();
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (!Settings.canDrawOverlays(this)) {
                    finish();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        sendBroadcast(new Intent(Constants.FINIS_MAIN_ACT));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isClick) {
        Intent intent = new Intent(SettingsActivity.this, MyService.class);
        switch (compoundButton.getId()) {
            case R.id.sw_show_mouse:
                if (isClick) {
                    startService(intent);
                    PreferenceUtils.save(Constants.STATE_IMAGE_SHOW_MOUSE, true, this);
                    Log.e("click", "oke");

                } else {
                    PreferenceUtils.save(Constants.STATE_IMAGE_SHOW_MOUSE, false, this);
                    stopService(intent);
                }

                break;
            default:
                break;
        }
    }
}
