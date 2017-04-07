package <%= appPackage %>.utils.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import android.support.v7.widget.AppCompatTextView;

import <%= appPackage %>.R;
import <%= appPackage %>.utils.FontHelper;


/**
 * Created by USER on 12/1/2015.
 */
public class TextViewTypeFace extends AppCompatTextView {
    public Context mContext;
    private String fontName="";

    public TextViewTypeFace(Context context) {
        super(context);
        initializeViews(context,null);
    }

    public TextViewTypeFace(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context,attrs);
    }


    public TextViewTypeFace(Context context, AttributeSet attrs, int defStyle) {
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
