package controlcenteros.com.minhson.mousescreenon.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by binhnk on 6/14/2017.
 */

@SuppressLint("AppCompatCustomView")
public class TextViewCherryBold extends TextView{
    public TextViewCherryBold(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextViewCherryBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextViewCherryBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("CherrySwashBold.otf", context);
        setTypeface(customFont);
    }
}
