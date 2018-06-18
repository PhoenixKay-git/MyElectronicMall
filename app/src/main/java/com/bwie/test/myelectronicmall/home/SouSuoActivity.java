package com.bwie.test.myelectronicmall.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.test.bean.User;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.classify.ListsActivity;
import com.bwie.test.myelectronicmall.home.adapter.ListsAdapter;
import com.bwie.test.myelectronicmall.home.adapter.MyListAdapter;
import com.bwie.test.utils.SqlDao;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SouSuoActivity extends AppCompatActivity {
    private TextView btn_fan;
    private RecyclerView recycler_resou;
    private ListView listView;
    private EditText ed_sousuo;
    private SqlDao sqlDao;
    private ListsAdapter adapter;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        //隐藏原有标题
        getSupportActionBar().hide();
        //查找控件
        btn_fan = (TextView) findViewById(R.id.btn_fan);
        recycler_resou = (RecyclerView) findViewById(R.id.recycler_resou);
        listView = (ListView) findViewById(R.id.list_view);
        ed_sousuo = (EditText) findViewById(R.id.ed_sousuo1);
        //数据库
        sqlDao = new SqlDao(SouSuoActivity.this);

        select();
        //点击返回
        btn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取数据
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://120.27.23.105/product/getCatagory").build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String json = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            User user = gson.fromJson(json, User.class);
                            List<User.DataBean> list = user.getData();
                            recycler_resou.setLayoutManager(new GridLayoutManager(SouSuoActivity.this,4, OrientationHelper.VERTICAL,false));
                            recycler_resou.setAdapter(new MyListAdapter(SouSuoActivity.this,list));
                        }
                    });
                }
            }
        });
    }

    private void select() {
        //查询遍历
        if(adapter==null){
            list = sqlDao.select();
            if(list!=null){
                //    Toast.makeText(SouSuoActivity.this,list.toString(),Toast.LENGTH_SHORT).show();
                adapter = new ListsAdapter(list, SouSuoActivity.this);
                listView.setAdapter(adapter);
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    //搜索
    public void btn_Click(View view) {
        String s = ed_sousuo.getText().toString();
        sqlDao.add1(s);
        select();
        Intent intent = new Intent(SouSuoActivity.this,ListsActivity.class);
        intent.putExtra("keywords",s);
        startActivity(intent);
    }

    //清空历史记录
    public void btn_clone(View view) {
        sqlDao.delete();
        select();
    }
}
