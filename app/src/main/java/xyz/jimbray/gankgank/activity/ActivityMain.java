package xyz.jimbray.gankgank.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import rx.Subscriber;
import xyz.jimbray.gankgank.R;
import xyz.jimbray.gankgank.api.GankAPIManager;
import xyz.jimbray.gankgank.common.Utils;
import xyz.jimbray.gankgank.data.DataBean;
import xyz.jimbray.gankgank.fragments.FragmentHomePage;
import xyz.jimbray.gankgank.view.ZoomoutPageTransformer;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class ActivityMain extends ActivityBase {

    private ViewPager viewpager;
    private PagerAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ac_main);

        initViews();

        getHistoryData();
    }

    @Override
    protected void initViews() {
        super.initViews();
        viewpager = getView(R.id.viewpager);

        mAdapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(5);//setOffscreenPageLimit表示设置缓存，这样左右拖动即可看见后面的Fragment
        viewpager.setPageMargin(Utils.dpToPx(15));
        viewpager.setPageTransformer(true, new ZoomoutPageTransformer());
    }

    private void getHistoryData() {
        GankAPIManager.getInstance().getFuli(new Subscriber<DataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DataBean dataBean) {
                mAdapter.setData(dataBean);
            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private List<DataBean.Data> data_list;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return data_list == null ? null : FragmentHomePage.newInstance(data_list.get(position));
        }

        @Override
        public int getCount() {
            return data_list == null ? 0 : data_list.size();
        }

        public void setData(DataBean dataBean) {
            if(dataBean != null) {
                this.data_list = dataBean.getData();
                notifyDataSetChanged();
            }
        }
    }
}
