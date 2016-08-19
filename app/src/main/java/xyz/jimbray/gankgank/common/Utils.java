package xyz.jimbray.gankgank.common;

import android.util.TypedValue;

import xyz.jimbray.gankgank.GankApplication;

/**
 * Created by Jimbray  .
 * on 2016/8/19
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class Utils {
    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, GankApplication.getAppContext().getResources().getDisplayMetrics());
    }

    public static int pxTodp(float pxValue) {
        final float scale = GankApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
