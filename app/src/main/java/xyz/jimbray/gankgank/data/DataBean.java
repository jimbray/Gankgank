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
public class DataBean {

    private boolean error;

    private List<Data> results ;

    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setData(List<Data> results){
        this.results = results;
    }
    public List<Data> getData(){
        return this.results;
    }

    public static class Data implements Parcelable {
        private String _id;

        private String createdAt;

        private String desc;

        private String publishedAt;

        private String source;

        private String type;

        private String url;

        private boolean used;

        private String who;

        protected Data(Parcel in) {
            _id = in.readString();
            createdAt = in.readString();
            desc = in.readString();
            publishedAt = in.readString();
            source = in.readString();
            type = in.readString();
            url = in.readString();
            used = in.readByte() != 0;
            who = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(createdAt);
            dest.writeString(desc);
            dest.writeString(publishedAt);
            dest.writeString(source);
            dest.writeString(type);
            dest.writeString(url);
            dest.writeByte((byte) (used ? 1 : 0));
            dest.writeString(who);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        public void set_id(String _id){
            this._id = _id;
        }
        public String get_id(){
            return this._id;
        }
        public void setCreatedAt(String createdAt){
            this.createdAt = createdAt;
        }
        public String getCreatedAt(){
            return this.createdAt;
        }
        public void setDesc(String desc){
            this.desc = desc;
        }
        public String getDesc(){
            return this.desc;
        }
        public void setPublishedAt(String publishedAt){
            this.publishedAt = publishedAt;
        }
        public String getPublishedAt(){
            return this.publishedAt;
        }
        public void setSource(String source){
            this.source = source;
        }
        public String getSource(){
            return this.source;
        }
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        public void setUsed(boolean used){
            this.used = used;
        }
        public boolean getUsed(){
            return this.used;
        }
        public void setWho(String who){
            this.who = who;
        }
        public String getWho(){
            return this.who;
        }

    }

}
