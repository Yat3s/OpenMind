package com.imooc.openmind.topic;

import com.google.gson.annotations.SerializedName;
import com.imooc.openmind.user.UserModel;

import java.util.List;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicModel {

    public String id;

    public String title;

    @SerializedName("abstract")
    public String abstractStr;

    public int status;

    private String img;

    public int color;

    public UserModel host;

    public List<String> tags;

    public String getTag() {
        if (null == tags || tags.size() <= 0) {
            return null;
        }
        return "#" + tags.get(0) + "#";
    }

    public String getImageURL() {
        return img + "?imageView2/3/w/1080/h/800/format/webp";
    }

}
