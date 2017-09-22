package com.cai.yi.myapplication0;

import com.cai.yi.myapplication0.bean.DetailBean;
import com.cai.yi.myapplication0.bean.EventBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by qf on 2017/9/14.
 */

public class NetUtil {

    public interface GitHubService {
        @GET("toh?key=9990733a68a463a481cad1fe9dae656a")
        Call<EventBean> listRepos(@Query("v") String appid, @Query("month") int month, @Query("day") int day);

        @GET("tohdet?key=9990733a68a463a481cad1fe9dae656a")
        Call<DetailBean> getDetail(@Query("v") String appid, @Query("id") int id);
    }
}
