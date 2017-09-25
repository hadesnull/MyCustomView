package com.cai.yi.myapplication0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cai.yi.myapplication0.factory.FactoryBuild;
import com.cai.yi.myapplication0.view.ColorProActivity;
import com.cai.yi.myapplication0.view.HistoryDayActivity;
import com.cai.yi.myapplication0.wight.turntest;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        mList = (RecyclerView) findViewById(R.id.list);
        MyAdapter myAdapter = new MyAdapter();
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        mList.setAdapter(myAdapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main, null);
            MyViewHolder myViewHolder = new MyViewHolder(inflate);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    switch (position) {
                        case 0:
                            intent.setClass(MainActivity.this, HistoryDayActivity.class);
                            startActivity(intent);
                            break;

                        case 1:
                            intent.setClass(MainActivity.this, turntest.class);
                            startActivity(intent);
                            break;

                        case 2:
                            intent.setClass(MainActivity.this, ViewAnimationActivity.class);
                            startActivity(intent);
                            break;

                        case 3:
                            intent.setClass(MainActivity.this, ColorProActivity.class);
                            startActivity(intent);
                            break;

                        case 4:
                            FactoryBuild.getEnstance(FactoryBuild.type_1).save();
                            break;

                        case 5:
                            FactoryBuild.getEnstance(FactoryBuild.type_2).save();
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            private ImageView img;
            public MyViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.item_img);
            }
        }
    }

}
