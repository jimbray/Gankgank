package xyz.jimbray.gankgank;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jimbray  .
 * on 2016/8/19
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class GankApplication extends Application {

    private static GankApplication mRef;

    @Override
    public void onCreate() {
        super.onCreate();

        mRef = this;
    }

    public static Context getAppContext() {
        return mRef.getApplicationContext();
    }
}
