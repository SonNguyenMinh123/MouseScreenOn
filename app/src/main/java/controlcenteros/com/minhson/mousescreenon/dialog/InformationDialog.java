package controlcenteros.com.minhson.mousescreenon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import controlcenteros.com.minhson.mousescreenon.R;

/**
 * Created by Administrator on 7/9/2017.
 */

public class InformationDialog extends Dialog {
    private Button btnOke;

    public InformationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_information);

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initDialog();
    }

    private void initDialog() {
        btnOke = (Button) findViewById(R.id.btn_oke);
        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
