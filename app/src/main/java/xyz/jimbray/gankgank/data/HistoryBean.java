package xyz.jimbray.gankgank.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Jimbray  .
 * on 2016/8/18
 * Email: jimbray16@gmail.com
 * Description: TODO
 */
public class HistoryBean {

    private boolean error;

    private List<History> results ;

    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setHistoryList(List<History> results){
        this.results = results;
    }
    public List<History> getHistoryList(){
        return this.results;
    }

    public static class History implements Parcelable {
        private String _id;

        private String content;

        private String publishedAt;

        private String title;

        protected History(Parcel in) {
            _id = in.readString();
            content = in.readString();
            publishedAt = in.readString();
            title = in.readString();
        }

        public static final Creator<History> CREATOR = new Creator<History>() {
            @Override
            public History createFromParcel(Parcel in) {
                return new History(in);
            }

            @Override
            public History[] newArray(int size) {
                return new History[size];
            }
        };

        public void set_id(String _id){
            this._id = _id;
        }
        public String get_id(){
            return this._id;
        }
        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setPublishedAt(String publishedAt){
            this.publishedAt = publishedAt;
        }
        public String getPublishedAt(){
            return this.publishedAt;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(content);
            dest.writeString(publishedAt);
            dest.writeString(title);
        }
    }
}
