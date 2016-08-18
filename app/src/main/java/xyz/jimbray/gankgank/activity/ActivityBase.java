package xyz.jimbray.gankgank.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class ActivityBase extends AppCompatActivity {

    protected <T extends View> T getView(int id) {
        return (T) super.findViewById(id);
    }

    protected <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected void initViews() {}

}
