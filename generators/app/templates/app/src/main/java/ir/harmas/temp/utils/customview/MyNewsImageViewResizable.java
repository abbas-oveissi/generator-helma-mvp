package <%= appPackage %>.utils.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by Abbas on 5/28/2015.
 */
public class MyNewsImageViewResizable extends AppCompatImageView{
    public MyNewsImageViewResizable(Context context) {
        super(context);
    }

    public MyNewsImageViewResizable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {


        int widthMode = MeasureSpec.getMode(widthSpec);
        int widthSize = MeasureSpec.getSize(widthSpec);
        int heightMode = MeasureSpec.getMode(heightSpec);
        int heightSize = MeasureSpec.getSize(heightSpec);

        int width=widthSize;
        int height=(widthSize*3)/4;


        setMeasuredDimension(width, height);
        heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }
}
