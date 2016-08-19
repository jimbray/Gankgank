package xyz.jimbray.gankgank.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.jimbray.gankgank.R;
import xyz.jimbray.gankgank.data.DataBean;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class FragmentHomePage extends FragmentBase {

    private static final String KEY_DATA = "key_history";

    private DataBean.Data mCurData;

    private TextView tv_title;
    private ImageView iv_pic;

    public static Fragment newInstance(DataBean.Data data) {
        Fragment fg = new FragmentHomePage();

        Bundle extras = new Bundle();
        extras.putParcelable(KEY_DATA, data);
        fg.setArguments(extras);

        return fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCurData = getArguments().getParcelable(KEY_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fg_home_page, container, false);

        initViews(view);
        initData();

        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        tv_title = getView(view, R.id.tv_title);
        iv_pic = getView(view, R.id.iv_pic);
    }

    @Override
    protected void initData() {
        super.initData();

//        tv_title.setText(mCurData.getTitle());
        if(mCurData != null) {
            Glide.with(this)
                    .load(mCurData.getUrl())
                    .into(iv_pic);
        }
    }
}
