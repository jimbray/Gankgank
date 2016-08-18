package xyz.jimbray.gankgank.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class FragmentBase extends Fragment {


    protected <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected void initViews(View view) {}

    protected void initData() {}

}
