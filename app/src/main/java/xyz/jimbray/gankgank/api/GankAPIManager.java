package xyz.jimbray.gankgank.api;

import rx.Subscriber;
import xyz.jimbray.gankgank.data.HistoryBean;

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

}
