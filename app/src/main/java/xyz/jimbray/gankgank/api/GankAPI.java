package xyz.jimbray.gankgank.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.jimbray.gankgank.common.Constants;
import xyz.jimbray.gankgank.data.DataBean;
import xyz.jimbray.gankgank.data.HistoryBean;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class GankAPI {

    private static GankAPI mRef;

    private Retrofit mRetrofit;

    private Gank mGank;

    private static final long CONNECT_TIMEOUT = 30000;

    private GankAPI() {

    }

    public static GankAPI getInstance() {
        if(mRef == null) {
            mRef = new GankAPI();
        }

        return mRef;
    }

    private Retrofit getRetrofit() {
        if(mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();

            builder.baseUrl(Constants.BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

            builder.client(clientBuilder.build());

            mRetrofit = builder.build();
        }

        return mRetrofit;
    }

    private Gank getGank() {
        if(mGank == null) {
            mGank = getRetrofit().create(Gank.class);
        }
        return mGank;
    }

    public interface Gank {

        @GET("history/content/{count}/{page}")
        Observable<HistoryBean> getHistoryData(@Path("count") int cont, @Path("page") int page);

        @GET("data/{type}/{count}/{page}")
        Observable<DataBean> getData(@Path("type") String type, @Path("count") int count, @Path("page") int page);
    }

    public void getHistoryData(int count, int page, Subscriber<HistoryBean> subscriber) {
        getGank().getHistoryData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getData(String type, int count, int page, Subscriber<DataBean> subscriber) {
        getGank().getData(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
