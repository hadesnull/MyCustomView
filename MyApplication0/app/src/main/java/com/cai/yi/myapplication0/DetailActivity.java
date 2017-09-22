package com.cai.yi.myapplication0;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cai.yi.myapplication0.bean.DetailBean;
import com.cai.yi.myapplication0.bean.EventBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qf on 2017/9/14.
 */

public class DetailActivity extends Activity {

    private ImageView mImg;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mImg = (ImageView) findViewById(R.id.img_detail);
        mTv = (TextView) findViewById(R.id.tv_detail);

        String id = getIntent().getStringExtra("id");

        getData(Integer.parseInt(id));
    }

    private void getData(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/japi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetUtil.GitHubService service = retrofit.create(NetUtil.GitHubService.class);


        Toast.makeText(this, "id=="+id, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "id=="+id, Toast.LENGTH_SHORT).show();
        Call<DetailBean> eventBeanCall = service.getDetail("1.0", id);

        eventBeanCall.enqueue(new Callback<DetailBean>() {

            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {

                DetailBean body = response.body();
                DetailBean.ResultBean resultBean = body.getResult().get(0);
                Glide.with(DetailActivity.this)
                        .load(resultBean.getPic())
                        .into(mImg);
                mTv.setText(resultBean.getContent());

             //   mTv.setText(response.body().toString());

            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
