package <%= appPackage %>.utils.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

import <%= appPackage %>.R;
import <%= appPackage %>.utils.FontHelper;


/**
 * Created by USER on 12/1/2015.
 */
public class ButtonTypeFace extends AppCompatButton {
    public Context mContext;
    private String fontName="";

    public ButtonTypeFace(Context context) {
        super(context);
        initializeViews(context,null);
    }

    public ButtonTypeFace(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context,attrs);
    }


    public ButtonTypeFace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context,attrs);
    }




    private void initializeViews(Context context, AttributeSet attrs) {

        this.mContext=context;
        if(attrs!=null)
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewTypeFace, 0, 0);
            fontName = a.getString(R.styleable.TextViewTypeFace_fontName);
            a.recycle();
        }
        if(isInEditMode()==false)
        {
            setTypeface(FontHelper.get(context, fontName + ".ttf"));
        }
    }



}
