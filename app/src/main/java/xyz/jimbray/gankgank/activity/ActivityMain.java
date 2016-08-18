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
import xyz.jimbray.gankgank.data.HistoryBean;
import xyz.jimbray.gankgank.fragments.FragmentHomePage;

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
    }

    private void getHistoryData() {
        GankAPIManager.getInstance().getHistoryData(5, 1, new Subscriber<HistoryBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HistoryBean historyBean) {
                mAdapter.setData(historyBean);
            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private List<HistoryBean.History> history_list;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return history_list == null ? null : FragmentHomePage.newInstance(history_list.get(position));
        }

        @Override
        public int getCount() {
            return history_list == null ? 0 : history_list.size();
        }

        public void setData(HistoryBean history) {
            if(history != null) {
                this.history_list = history.getHistoryList();
                notifyDataSetChanged();
            }
        }
    }
}
