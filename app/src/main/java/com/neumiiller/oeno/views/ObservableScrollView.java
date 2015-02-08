package com.neumiiller.oeno.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * @author Quinn Neumiiller (quinnjn)
 * @since 15-02-08
 */
public class ObservableScrollView extends ScrollView {
    private Action action;
    private int totalHeight = 0;

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override protected void onScrollChanged(int x, int y, int oldx, int oldt) {
        super.onScrollChanged(x, y, oldx, oldt);
        if (action != null) {
            if (totalHeight == 0) {
                totalHeight = getHeight();
            }
            Log.d("change", y + " / " + totalHeight);
            float percentage = (float) y / totalHeight;
            if(percentage > 1){
                percentage = 1;
            }
            action.onScrollPercentageChanged(percentage);
        }
    }

    public interface Action {
        public void onScrollPercentageChanged(float percentage);
    }
}
