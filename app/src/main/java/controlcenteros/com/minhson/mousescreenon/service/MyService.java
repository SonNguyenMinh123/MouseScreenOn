package controlcenteros.com.minhson.mousescreenon.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import controlcenteros.com.minhson.mousescreenon.R;
import controlcenteros.com.minhson.mousescreenon.util.Constants;
import controlcenteros.com.minhson.mousescreenon.util.PreferenceUtils;

/**
 * Created by Administrator on 6/9/2017.
 */

public class MyService extends Service {
    final int[] pictureIds = new int[]{
            R.drawable.low_mouse_0
            , R.drawable.low_mouse_1
            , R.drawable.low_mouse_2
            , R.drawable.low_mouse_3
            , R.drawable.low_mouse_4
            , R.drawable.low_mouse_5
            , R.drawable.low_mouse_6
            , R.drawable.low_mouse_7
            , R.drawable.low_mouse_8
            , R.drawable.low_mouse_9
            , R.drawable.low_mouse_10
            , R.drawable.low_mouse_11
            , R.drawable.low_mouse_12
            , R.drawable.low_mouse_13
            , R.drawable.low_mouse_14
            , R.drawable.low_mouse_15
            , R.drawable.low_mouse_16
            , R.drawable.low_mouse_17
            , R.drawable.low_mouse_18
            , R.drawable.low_mouse_19
            , R.drawable.low_mouse_20
            , R.drawable.low_mouse_21
            , R.drawable.low_mouse_22
            , R.drawable.low_mouse_23
            , R.drawable.low_mouse_24
            , R.drawable.low_mouse_25
            , R.drawable.low_mouse_26
            , R.drawable.low_mouse_27
            , R.drawable.low_mouse_28
            , R.drawable.low_mouse_29
            , R.drawable.low_mouse_30
            , R.drawable.low_mouse_31
            , R.drawable.low_mouse_32
            , R.drawable.low_mouse_33
            , R.drawable.low_mouse_34
            , R.drawable.low_mouse_35
            , R.drawable.low_mouse_36
            , R.drawable.low_mouse_37
            , R.drawable.low_mouse_38
            , R.drawable.low_mouse_39
            , R.drawable.low_mouse_40
            , R.drawable.low_mouse_41
            , R.drawable.low_mouse_42
            , R.drawable.low_mouse_43
            , R.drawable.low_mouse_44
            , R.drawable.low_mouse_45
            , R.drawable.low_mouse_46
            , R.drawable.low_mouse_47
            , R.drawable.low_mouse_48
            , R.drawable.low_mouse_49
            , R.drawable.low_mouse_50
            , R.drawable.low_mouse_51
            , R.drawable.low_mouse_52
            , R.drawable.low_mouse_53
            , R.drawable.low_mouse_54
            , R.drawable.low_mouse_55
            , R.drawable.low_mouse_56
            , R.drawable.low_mouse_57
            , R.drawable.low_mouse_58
            , R.drawable.low_mouse_59
            , R.drawable.low_mouse_60
            , R.drawable.low_mouse_61
            , R.drawable.low_mouse_62
            , R.drawable.low_mouse_63
            , R.drawable.low_mouse_64
            , R.drawable.low_mouse_65
            , R.drawable.low_mouse_66
            , R.drawable.low_mouse_67
            , R.drawable.low_mouse_68
            , R.drawable.low_mouse_69
            , R.drawable.low_mouse_70
            , R.drawable.low_mouse_71
            , R.drawable.low_mouse_72
            , R.drawable.low_mouse_73
            , R.drawable.low_mouse_74
            , R.drawable.low_mouse_75
            , R.drawable.low_mouse_76
            , R.drawable.low_mouse_77
            , R.drawable.low_mouse_78
            , R.drawable.low_mouse_79
            , R.drawable.low_mouse_80
            , R.drawable.low_mouse_81
            , R.drawable.low_mouse_82
            , R.drawable.low_mouse_83
            , R.drawable.low_mouse_84
            , R.drawable.low_mouse_85
            , R.drawable.low_mouse_86
            , R.drawable.low_mouse_87
            , R.drawable.low_mouse_88
            , R.drawable.low_mouse_89
            , R.drawable.low_mouse_90
            , R.drawable.low_mouse_91
            , R.drawable.low_mouse_92
            , R.drawable.low_mouse_93
            , R.drawable.low_mouse_94
            , R.drawable.low_mouse_95
            , R.drawable.low_mouse_96
            , R.drawable.low_mouse_97
            , R.drawable.low_mouse_98
            , R.drawable.low_mouse_99
            , R.drawable.low_mouse_100
            , R.drawable.low_mouse_111
            , R.drawable.low_mouse_112
            , R.drawable.low_mouse_113
            , R.drawable.low_mouse_114
            , R.drawable.low_mouse_115
            , R.drawable.low_mouse_116
            , R.drawable.low_mouse_117
            , R.drawable.low_mouse_118
            , R.drawable.low_mouse_119
            , R.drawable.low_mouse_120
            , R.drawable.low_mouse_121
            , R.drawable.low_mouse_122
            , R.drawable.low_mouse_123
            , R.drawable.low_mouse_124
            , R.drawable.low_mouse_125
            , R.drawable.low_mouse_126
            , R.drawable.low_mouse_127
            , R.drawable.low_mouse_128
            , R.drawable.low_mouse_129
            , R.drawable.low_mouse_130
            , R.drawable.low_mouse_131
            , R.drawable.low_mouse_132
            , R.drawable.low_mouse_133
            , R.drawable.low_mouse_134
            , R.drawable.low_mouse_135
            , R.drawable.low_mouse_136
            , R.drawable.low_mouse_137
            , R.drawable.low_mouse_138
            , R.drawable.low_mouse_139
            , R.drawable.low_mouse_140
            , R.drawable.low_mouse_141
            , R.drawable.low_mouse_142
            , R.drawable.low_mouse_143
            , R.drawable.low_mouse_144
            , R.drawable.low_mouse_145
            , R.drawable.low_mouse_146
            , R.drawable.low_mouse_147
            , R.drawable.low_mouse_148
            , R.drawable.low_mouse_149
            , R.drawable.low_mouse_150
            , R.drawable.low_mouse_151
            , R.drawable.low_mouse_152
            , R.drawable.low_mouse_153
            , R.drawable.low_mouse_154
            , R.drawable.low_mouse_155
            , R.drawable.low_mouse_156
            , R.drawable.low_mouse_157
            , R.drawable.low_mouse_158
            , R.drawable.low_mouse_159
            , R.drawable.low_mouse_160
            , R.drawable.low_mouse_161
            , R.drawable.low_mouse_162
            , R.drawable.low_mouse_163
            , R.drawable.low_mouse_164
            , R.drawable.low_mouse_165
            , R.drawable.low_mouse_166
            , R.drawable.low_mouse_167
            , R.drawable.low_mouse_168
            , R.drawable.low_mouse_169
            , R.drawable.low_mouse_170
            , R.drawable.low_mouse_171
            , R.drawable.low_mouse_172
            , R.drawable.low_mouse_173
            , R.drawable.low_mouse_174
            , R.drawable.low_mouse_175
            , R.drawable.low_mouse_176
            , R.drawable.low_mouse_177
            , R.drawable.low_mouse_178
            , R.drawable.low_mouse_179
            , R.drawable.low_mouse_180
            , R.drawable.low_mouse_181
            , R.drawable.low_mouse_182
            , R.drawable.low_mouse_183
            , R.drawable.low_mouse_184
            , R.drawable.low_mouse_185
            , R.drawable.low_mouse_186
            , R.drawable.low_mouse_187
            , R.drawable.low_mouse_188
            , R.drawable.low_mouse_189
            , R.drawable.low_mouse_190
            , R.drawable.low_mouse_191
            , R.drawable.low_mouse_192
            , R.drawable.low_mouse_193
            , R.drawable.low_mouse_194
            , R.drawable.low_mouse_195
            , R.drawable.low_mouse_196
            , R.drawable.low_mouse_197
            , R.drawable.low_mouse_198
            , R.drawable.low_mouse_199
            , R.drawable.low_mouse_200
            , R.drawable.low_mouse_201
            , R.drawable.low_mouse_202
            , R.drawable.low_mouse_203
            , R.drawable.low_mouse_204
            , R.drawable.low_mouse_205
            , R.drawable.low_mouse_206
            , R.drawable.low_mouse_207
            , R.drawable.low_mouse_208
            , R.drawable.low_mouse_209
            , R.drawable.low_mouse_210
            , R.drawable.low_mouse_211
            , R.drawable.low_mouse_212
            , R.drawable.low_mouse_213
            , R.drawable.low_mouse_214
            , R.drawable.low_mouse_215
            , R.drawable.low_mouse_216
            , R.drawable.low_mouse_217
            , R.drawable.low_mouse_218
            , R.drawable.low_mouse_219
            , R.drawable.low_mouse_220
            , R.drawable.low_mouse_221
            , R.drawable.low_mouse_222
            , R.drawable.low_mouse_223
            , R.drawable.low_mouse_224
            , R.drawable.low_mouse_225
            , R.drawable.low_mouse_226
            , R.drawable.low_mouse_227
            , R.drawable.low_mouse_228
            , R.drawable.low_mouse_229
            , R.drawable.low_mouse_230
            , R.drawable.low_mouse_231
            , R.drawable.low_mouse_232
            , R.drawable.low_mouse_233
            , R.drawable.low_mouse_234
            , R.drawable.low_mouse_235
            , R.drawable.low_mouse_236
            , R.drawable.low_mouse_237
            , R.drawable.low_mouse_238
            , R.drawable.low_mouse_239
            , R.drawable.low_mouse_240
            , R.drawable.low_mouse_241
            , R.drawable.low_mouse_242
            , R.drawable.low_mouse_243
            , R.drawable.low_mouse_244
            , R.drawable.low_mouse_245
            , R.drawable.low_mouse_246
            , R.drawable.low_mouse_247
            , R.drawable.low_mouse_248
            , R.drawable.low_mouse_249
            , R.drawable.low_mouse_250
            , R.drawable.low_mouse_251
            , R.drawable.low_mouse_252
            , R.drawable.low_mouse_253
            , R.drawable.low_mouse_254
            , R.drawable.low_mouse_255
            , R.drawable.low_mouse_256
            , R.drawable.low_mouse_257
            , R.drawable.low_mouse_258
            , R.drawable.low_mouse_259
            , R.drawable.low_mouse_260
            , R.drawable.low_mouse_261
            , R.drawable.low_mouse_262
            , R.drawable.low_mouse_263
            , R.drawable.low_mouse_264
            , R.drawable.low_mouse_265
            , R.drawable.low_mouse_266
            , R.drawable.low_mouse_267
            , R.drawable.low_mouse_268
            , R.drawable.low_mouse_269
            , R.drawable.low_mouse_270
            , R.drawable.low_mouse_271
            , R.drawable.low_mouse_272
            , R.drawable.low_mouse_273
            , R.drawable.low_mouse_274
            , R.drawable.low_mouse_275
            , R.drawable.low_mouse_276
            , R.drawable.low_mouse_277
            , R.drawable.low_mouse_278
            , R.drawable.low_mouse_279
            , R.drawable.low_mouse_280
            , R.drawable.low_mouse_281
            , R.drawable.low_mouse_282
            , R.drawable.low_mouse_283
            , R.drawable.low_mouse_284
            , R.drawable.low_mouse_285
            , R.drawable.low_mouse_286
            , R.drawable.low_mouse_287
            , R.drawable.low_mouse_288
            , R.drawable.low_mouse_289
            , R.drawable.low_mouse_290
            , R.drawable.low_mouse_291
            , R.drawable.low_mouse_292
            , R.drawable.low_mouse_293
            , R.drawable.low_mouse_294
            , R.drawable.low_mouse_295
            , R.drawable.low_mouse_296
            , R.drawable.low_mouse_297

            , R.drawable.mouse_0
            , R.drawable.mouse_1
            , R.drawable.mouse_2
            , R.drawable.mouse_3
            , R.drawable.mouse_4
            , R.drawable.mouse_5
            , R.drawable.mouse_6
            , R.drawable.mouse_7
            , R.drawable.mouse_8
            , R.drawable.mouse_9
            , R.drawable.mouse_10
            , R.drawable.mouse_11
            , R.drawable.mouse_12
            , R.drawable.mouse_13
            , R.drawable.mouse_14
            , R.drawable.mouse_15
            , R.drawable.mouse_16
            , R.drawable.mouse_17
            , R.drawable.mouse_18
            , R.drawable.mouse_19
            , R.drawable.mouse_20
            , R.drawable.mouse_21
            , R.drawable.mouse_22
            , R.drawable.mouse_23
            , R.drawable.mouse_24
            , R.drawable.mouse_25
            , R.drawable.mouse_26
            , R.drawable.mouse_27
            , R.drawable.mouse_28
            , R.drawable.mouse_29
            , R.drawable.mouse_30
            , R.drawable.mouse_31
            , R.drawable.mouse_32
            , R.drawable.mouse_33
            , R.drawable.mouse_34
            , R.drawable.mouse_35
            , R.drawable.mouse_36
            , R.drawable.mouse_37
            , R.drawable.mouse_38
            , R.drawable.mouse_39
            , R.drawable.mouse_40
            , R.drawable.mouse_41
            , R.drawable.mouse_42
            , R.drawable.mouse_43
            , R.drawable.mouse_44
            , R.drawable.mouse_45
            , R.drawable.mouse_46
            , R.drawable.mouse_47
            , R.drawable.mouse_48
            , R.drawable.mouse_49
            , R.drawable.mouse_50
            , R.drawable.mouse_51
            , R.drawable.mouse_52
            , R.drawable.mouse_53
            , R.drawable.mouse_54
            , R.drawable.mouse_55
            , R.drawable.mouse_56
            , R.drawable.mouse_57
            , R.drawable.mouse_58
            , R.drawable.mouse_59
            , R.drawable.mouse_60
            , R.drawable.mouse_61
            , R.drawable.mouse_62
            , R.drawable.mouse_63
            , R.drawable.mouse_64
            , R.drawable.mouse_65
            , R.drawable.mouse_66
            , R.drawable.mouse_67
            , R.drawable.mouse_68
            , R.drawable.mouse_69
            , R.drawable.mouse_70
            , R.drawable.mouse_71
            , R.drawable.mouse_72
            , R.drawable.mouse_73
            , R.drawable.mouse_74
            , R.drawable.mouse_75
            , R.drawable.mouse_76
            , R.drawable.mouse_77
            , R.drawable.mouse_78
            , R.drawable.mouse_79
            , R.drawable.mouse_80
            , R.drawable.mouse_81
            , R.drawable.mouse_82
            , R.drawable.mouse_83
            , R.drawable.mouse_84
            , R.drawable.mouse_85
            , R.drawable.mouse_86
            , R.drawable.mouse_87
            , R.drawable.mouse_88
            , R.drawable.mouse_89
            , R.drawable.mouse_90
            , R.drawable.mouse_91
            , R.drawable.mouse_92
            , R.drawable.mouse_93
            , R.drawable.mouse_94
            , R.drawable.mouse_95
            , R.drawable.mouse_96
            , R.drawable.mouse_97
            , R.drawable.mouse_98
            , R.drawable.mouse_99
            , R.drawable.mouse_100
            , R.drawable.mouse_111
            , R.drawable.mouse_112
            , R.drawable.mouse_113
            , R.drawable.mouse_114
            , R.drawable.mouse_115
            , R.drawable.mouse_116
            , R.drawable.mouse_117
            , R.drawable.mouse_118
            , R.drawable.mouse_119
            , R.drawable.mouse_120
            , R.drawable.mouse_121
            , R.drawable.mouse_122
            , R.drawable.mouse_123
            , R.drawable.mouse_124
            , R.drawable.mouse_125
            , R.drawable.mouse_126
            , R.drawable.mouse_127
            , R.drawable.mouse_128
            , R.drawable.mouse_129
            , R.drawable.mouse_130
            , R.drawable.mouse_131
            , R.drawable.mouse_132
            , R.drawable.mouse_133
            , R.drawable.mouse_134
            , R.drawable.mouse_135
            , R.drawable.mouse_136
            , R.drawable.mouse_137
            , R.drawable.mouse_138
            , R.drawable.mouse_139
            , R.drawable.mouse_140
            , R.drawable.mouse_141
            , R.drawable.mouse_142
            , R.drawable.mouse_143
            , R.drawable.mouse_144
            , R.drawable.mouse_145
            , R.drawable.mouse_146
            , R.drawable.mouse_147
            , R.drawable.mouse_148
            , R.drawable.mouse_149
            , R.drawable.mouse_150
            , R.drawable.mouse_151
            , R.drawable.mouse_152
            , R.drawable.mouse_153
            , R.drawable.mouse_154
            , R.drawable.mouse_155
            , R.drawable.mouse_156
            , R.drawable.mouse_157
            , R.drawable.mouse_158
            , R.drawable.mouse_159
            , R.drawable.mouse_160
            , R.drawable.mouse_161
            , R.drawable.mouse_162
            , R.drawable.mouse_163
            , R.drawable.mouse_164
            , R.drawable.mouse_165
            , R.drawable.mouse_166
            , R.drawable.mouse_167
            , R.drawable.mouse_168
            , R.drawable.mouse_169
            , R.drawable.mouse_170
            , R.drawable.mouse_171
            , R.drawable.mouse_172
            , R.drawable.mouse_173
            , R.drawable.mouse_174
            , R.drawable.mouse_175
            , R.drawable.mouse_176
            , R.drawable.mouse_177
            , R.drawable.mouse_178
            , R.drawable.mouse_179
            , R.drawable.mouse_180
            , R.drawable.mouse_181
            , R.drawable.mouse_182
            , R.drawable.mouse_183
            , R.drawable.mouse_184
            , R.drawable.mouse_185
            , R.drawable.mouse_186
            , R.drawable.mouse_187
            , R.drawable.mouse_188
            , R.drawable.mouse_189
            , R.drawable.mouse_190
            , R.drawable.mouse_191
            , R.drawable.mouse_192
            , R.drawable.mouse_193
            , R.drawable.mouse_194
            , R.drawable.mouse_195
            , R.drawable.mouse_196
            , R.drawable.mouse_197
            , R.drawable.mouse_198
            , R.drawable.mouse_199
            , R.drawable.mouse_200
            , R.drawable.mouse_201
            , R.drawable.mouse_202
            , R.drawable.mouse_203
            , R.drawable.mouse_204
            , R.drawable.mouse_205
            , R.drawable.mouse_206
            , R.drawable.mouse_207
            , R.drawable.mouse_208
            , R.drawable.mouse_209
            , R.drawable.mouse_210
            , R.drawable.mouse_211
            , R.drawable.mouse_212
            , R.drawable.mouse_213
            , R.drawable.mouse_214
            , R.drawable.mouse_215
            , R.drawable.mouse_216
            , R.drawable.mouse_217
            , R.drawable.mouse_218
            , R.drawable.mouse_219
            , R.drawable.mouse_220
            , R.drawable.mouse_221
            , R.drawable.mouse_222
            , R.drawable.mouse_223
            , R.drawable.mouse_224
            , R.drawable.mouse_225
            , R.drawable.mouse_226
            , R.drawable.mouse_227
            , R.drawable.mouse_228
            , R.drawable.mouse_229
            , R.drawable.mouse_230
            , R.drawable.mouse_231
            , R.drawable.mouse_232
            , R.drawable.mouse_233
            , R.drawable.mouse_234
            , R.drawable.mouse_235
            , R.drawable.mouse_236
            , R.drawable.mouse_237
            , R.drawable.mouse_238
            , R.drawable.mouse_239
            , R.drawable.mouse_240
            , R.drawable.mouse_241
            , R.drawable.mouse_242
            , R.drawable.mouse_243
            , R.drawable.mouse_244
            , R.drawable.mouse_245
            , R.drawable.mouse_246
            , R.drawable.mouse_247
            , R.drawable.mouse_248
            , R.drawable.mouse_249
            , R.drawable.mouse_250
            , R.drawable.mouse_251
            , R.drawable.mouse_252
            , R.drawable.mouse_253
            , R.drawable.mouse_254
            , R.drawable.mouse_255
            , R.drawable.mouse_256
            , R.drawable.mouse_257
            , R.drawable.mouse_258
            , R.drawable.mouse_259
            , R.drawable.mouse_260
            , R.drawable.mouse_261
            , R.drawable.mouse_262
            , R.drawable.mouse_263
            , R.drawable.mouse_264
            , R.drawable.mouse_265
            , R.drawable.mouse_266
            , R.drawable.mouse_267
            , R.drawable.mouse_268
            , R.drawable.mouse_269
            , R.drawable.mouse_270
            , R.drawable.mouse_271
            , R.drawable.mouse_272
            , R.drawable.mouse_273
            , R.drawable.mouse_274
            , R.drawable.mouse_275
            , R.drawable.mouse_276
            , R.drawable.mouse_277
            , R.drawable.mouse_278
            , R.drawable.mouse_279
            , R.drawable.mouse_280
            , R.drawable.mouse_281
            , R.drawable.mouse_282
            , R.drawable.mouse_283
            , R.drawable.mouse_284
            , R.drawable.mouse_285
            , R.drawable.mouse_286
            , R.drawable.mouse_287
            , R.drawable.mouse_288
            , R.drawable.mouse_289
            , R.drawable.mouse_290
            , R.drawable.mouse_291
            , R.drawable.mouse_292
            , R.drawable.mouse_293
            , R.drawable.mouse_294
            , R.drawable.mouse_295
            , R.drawable.mouse_296
            , R.drawable.mouse_297
    };
    //
    private Context context;
    private WindowManager windowManager;
    private boolean isClick = false;
    private boolean isClickRunMouse = false;
    private WindowManager.LayoutParams params;
    private View viewMain;
    private LayoutInflater layoutInflater;
    private ImageView ivMain;
    private Handler handler;
    private int i = 0;
    private MediaPlayer mediaPlayer;
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext().getApplicationContext();
        windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        layoutInflater = LayoutInflater.from(context);
        viewMain = layoutInflater.inflate(R.layout.layout_mouse, null);
        ivMain = (ImageView) viewMain.findViewById(R.id.iv_view_main);
        handler = new Handler(Looper.getMainLooper());
        mediaPlayer = MediaPlayer.create(context, R.raw.sound1);
        enableKeyGuard();
        // receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.SWITCH_SOUND_OFF);
        filter.addAction(Constants.SWITCH_SOUND_ON);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constants.SWITCH_SOUND_ON:
                        mediaPlayer.start();
                        break;

                    case Constants.SWITCH_SOUND_OFF:
                        mediaPlayer.stop();
                        break;
                    default:
                        break;
                }
            }
        };
        registerReceiver(receiver, filter);

    }

    private void enableKeyGuard() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock("IN");
        lock.reenableKeyguard();
    }

    private void disableKeyGuard() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock("IN");
        lock.disableKeyguard();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (PreferenceUtils.getBoolean(Constants.STATE_IMAGE_SHOW_MOUSE, context)) {
            showMouseScreenOn();
        }
        return START_STICKY;
    }

    private void showMouseScreenOn() {
        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.format = PixelFormat.TRANSPARENT;
        params.flags = WindowManager.LayoutParams.FIRST_SUB_WINDOW
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

        if (!isClick) {
            if (viewMain == null || viewMain.getWindowToken() == null) {
                windowManager.addView(viewMain, params);
            }
            isClick = true;
        }
        Runnable runTime = new Runnable() {
            @Override
            public void run() {
                if (i < pictureIds.length) {
                    i++;
                    loadImage();
                    Log.e("threadddd", "" + i);
                    handler.postDelayed(this, 60);
                }
            }
        };
        handler.postDelayed(runTime, 60);
        //
//        CountDownTimer timer = new CountDownTimer(100, 100) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
    }

    private void loadImage() {
        Runnable runTime = new Runnable() {
            @Override
            public void run() {
                if (i < pictureIds.length) {
                    ivMain.setImageResource(pictureIds[i]);
                    mediaPlayer.start();
                    Log.e("threads", "" + i);
                } else {
                    if (isClick) {
                        windowManager.removeView(viewMain);
                        isClick = false;
                    }
                }
            }
        };
        handler.postDelayed(runTime, 60);

        //
//        new CountDownTimer(100, 100){
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (i < pictureIds.length) {
//                    ivMain.setImageResource(pictureIds[i]);
//                    mediaPlayer.start();
//                    Log.e("threads", "" + i);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        disableKeyGuard();
        super.onDestroy();
    }
}
