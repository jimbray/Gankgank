package xyz.jimbray.gankgank.api;

import rx.Subscriber;
import xyz.jimbray.gankgank.data.DataBean;
import xyz.jimbray.gankgank.data.HistoryBean;
import xyz.jimbray.gankgank.data.HomePageBean;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class GankAPIManager {

    private static GankAPIManager mRef;

    private GankAPIManager() {

    }

    public static GankAPIManager getInstance() {
        if(mRef == null) {
            mRef = new GankAPIManager();
        }

        return mRef;
    }

    public void getHistoryData(int count, int page, Subscriber<HistoryBean> subscribers) {
        GankAPI.getInstance().getHistoryData(count, page, subscribers);
    }

    public void getFuli(Subscriber<DataBean> subscriber) {
        GankAPI.getInstance().getData("福利", 5, 1, subscriber);
    }

    public void getHomeData(Subscriber<HomePageBean> subscriber) {

    }

}
