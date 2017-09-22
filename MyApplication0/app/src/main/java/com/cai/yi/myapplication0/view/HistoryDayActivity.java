package com.cai.yi.myapplication0.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.cai.yi.myapplication0.NetUtil;
import com.cai.yi.myapplication0.R;
import com.cai.yi.myapplication0.adapter.EventAdapter;
import com.cai.yi.myapplication0.bean.EventBean;
import com.cai.yi.myapplication0.wight.RecycleViewDivider;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qf on 2017/9/15.
 */

public class HistoryDayActivity extends Activity {
    private TextView mText;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.tv_name);
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.addItemDecoration(new RecycleViewDivider(this, DividerItemDecoration.HORIZONTAL));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(mList);
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/japi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetUtil.GitHubService service = retrofit.create(NetUtil.GitHubService.class);


        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        Call<EventBean> eventBeanCall = service.listRepos("1.0", month + 1, day);

        eventBeanCall.enqueue(new Callback<EventBean>() {

            @Override
            public void onResponse(Call<EventBean> call, Response<EventBean> response) {

                EventBean body = response.body();
                //Toast.makeText(MainActivity.this, "body=="+body.toString(), Toast.LENGTH_SHORT).show();
                mText.setText(body.toString());
                dealData(body);
            }

            @Override
            public void onFailure(Call<EventBean> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dealData(EventBean body) {

        EventAdapter adapter = new EventAdapter(this);
        adapter.setData(body.getResult());
        mList.setAdapter(adapter);
    }

}
