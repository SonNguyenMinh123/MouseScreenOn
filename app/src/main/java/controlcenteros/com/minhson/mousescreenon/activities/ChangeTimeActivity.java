package controlcenteros.com.minhson.mousescreenon.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import controlcenteros.com.minhson.mousescreenon.R;
import controlcenteros.com.minhson.mousescreenon.util.Constants;
import controlcenteros.com.minhson.mousescreenon.util.PreferenceUtils;

/**
 * Created by Administrator on 7/9/2017.
 */

public class ChangeTimeActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private SeekBar sbChangeTime;
    private TextView tvTime;
    private RelativeLayout rlOffSound;
    private Switch swOffSound;
    private ImageView ivBack;
    private boolean isClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_time);

        initViews();
    }

    private void initViews() {
        sbChangeTime = (SeekBar) findViewById(R.id.sb_change_time);
        tvTime = (TextView) findViewById(R.id.tv_time);
        rlOffSound = (RelativeLayout) findViewById(R.id.rl_off_sound);
        swOffSound = (Switch) findViewById(R.id.sw_off_sound);
        ivBack = (ImageView) findViewById(R.id.iv_back);

        initEvent();
        initState();
    }

    private void initState() {
        if (PreferenceUtils.getBoolean(Constants.OFF_SOUND, this)){
            swOffSound.setChecked(true);
        } else {
            swOffSound.setChecked(false);
        }
    }

    private void initEvent() {
        sbChangeTime.setOnSeekBarChangeListener(this);
        rlOffSound.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        sbChangeTime.setMax(100);
        sbChangeTime.setProgress(sbChangeTime.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_off_sound:
                if (!isClick){
                    sendBroadcast(new Intent(Constants.SWITCH_SOUND_ON));
                    PreferenceUtils.save(Constants.OFF_SOUND, true, this);
                    swOffSound.setChecked(true);
                    isClick = true;
                } else {
                    sendBroadcast(new Intent(Constants.SWITCH_SOUND_OFF));
                    PreferenceUtils.save(Constants.OFF_SOUND, false, this);
                    swOffSound.setChecked(false);
                    isClick = false;
                }

                break;
            case R.id.iv_back:
                onBackPressed();

                break;

            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        swOffSound.setChecked(true);
        tvTime.setText(sbChangeTime.getProgress() + "");
        PreferenceUtils.save(Constants.OFF_SOUND, true, this);
    }
}
