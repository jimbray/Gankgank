package xyz.jimbray.gankgank.api;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.jimbray.gankgank.common.Constants;
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
        Observable<DataBean> fuli = GankAPI.getInstance().getGank().getData("福利", 5, 1);
        final Observable<HistoryBean> history = GankAPI.getInstance().getGank().getHistoryData(5, 1);

        fuli.flatMap(new Func1<DataBean, Observable<DataBean.Data>>() {
            @Override
            public Observable<DataBean.Data> call(DataBean dataBean) {
                return Observable.from(dataBean.getData());
            }
        });

        history.flatMap(new Func1<HistoryBean, Observable<HistoryBean.History>>() {
            @Override
            public Observable<HistoryBean.History> call(HistoryBean historyBean) {
                return Observable.from(historyBean.getHistoryList());
            }
        });

        Observable.merge(fuli, history)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof DataBean.Data) {
                            Log.d(Constants.TAG, ((DataBean.Data) o).getUrl());
                        } else if(o instanceof HistoryBean.History) {
                            Log.d(Constants.TAG, ((HistoryBean.History) o).getTitle());
                        }
                    }
                });
    }

}
