package com.imooc.openmind.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class BaseData<T> {

    @SerializedName("total_count")
    public int totalCount;

    public T data;

}
