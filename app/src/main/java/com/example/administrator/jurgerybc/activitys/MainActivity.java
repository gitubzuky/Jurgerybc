package com.example.administrator.jurgerybc.activitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.jurgerybc.R;
import com.example.administrator.jurgerybc.adapters.RvAdapter;
import com.example.administrator.jurgerybc.beans.ContentBean;
import com.example.administrator.jurgerybc.utils.GsonRequest;
import com.example.administrator.jurgerybc.utils.QueueSingleton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = "MainActivty";
    RecyclerView rv_newest;
    RecyclerView.Adapter rvadapter_newest;
    LinearLayoutManager layoutManager_newest;
    SwipeRefreshLayout srl_newest;
    ProgressBar pb_newest;
    TabLayout tablayout;
    ViewPager viewpager;
    final static String url = "http://ftp490528.host551.zhujiwu.cn/";

    ContentBean contentBean;

    String api;

    int termnum = 4, page = 0, pagesize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_newest = (RecyclerView) findViewById(R.id.rv_newest);
        srl_newest = (SwipeRefreshLayout) findViewById(R.id.srl_newest);
        pb_newest = (ProgressBar) findViewById(R.id.pb_neweset);
        tablayout = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for(int i = 0; i < termnum; i++){
            tablayout.addTab(tablayout.newTab().setText("Tab "+(i+1)));
        }

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        api = url+"getjsondata.php?page="+page+"&pagesize="+pagesize;
//        initData(api);

        srl_newest.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                api = url + "getjsondata.php?page=" + page + "&pagesize=" + pagesize;
                initData(api);
                srl_newest.setRefreshing(false);
            }
        });


        InitDataTask task = new InitDataTask();
        task.execute();

    }

    private Map<String, String> getloginheader(){
        Map<String, String> loginheader = new HashMap<String, String>();
        loginheader.put("Accept", "*/*");
        loginheader.put("Accept-Encoding", "zh-CN,zh;q=0.8");
        loginheader.put("Connection", "keep-alive");
        loginheader.put("Content-Length", "0");
        loginheader.put("Content-Type", "application/x-www-form-urlencoded");
        loginheader.put("Host", "break.zhujiwu.com");
        loginheader.put("Origin", url);
        loginheader.put("Referer", url);
        return loginheader;
    }

    private void Login() {
        //只需要进行一次请求来生成cookie即可，所以都显示登录成功
        String loginurl = "http://break.zhujiwu.com/cmd.asp?host_type=vip&password=ZJWftp888";

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, loginurl, ContentBean.class,
                getloginheader(), null , new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
        QueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }


    private Map<String, String> getheader(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("Cookie", getCookie());
        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "zh-CN,zh;q=0.8");
        header.put("Connection", "keep-alive");
        header.put("Host", "ftp490528.host551.zhujiwu.cn");
        header.put("Referer", api);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)" +
                " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
        return header;
    }

    private void initData(String api) {

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, api, ContentBean.class, getheader(), null , new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                ContentBean contentBean = (ContentBean)response;
                rvadapter_newest = new RvAdapter(MainActivity.this, contentBean);
                layoutManager_newest = new LinearLayoutManager(MainActivity.this);
                rv_newest.setLayoutManager(layoutManager_newest);
                rv_newest.setAdapter(rvadapter_newest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });
        QueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }

    private String getCookie() {
        String cookiestr = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH");
        Date curDate = new Date(System.currentTimeMillis());
        String datestr = formatter.format(curDate);
        cookiestr = "zhujiwusysdomain=ftp490528.host551.zhujiwu.cn;CookieZJWFANGDAOLIAN=118.193.244.209#"
                + datestr
                + "#ftp490528.host551.zhujiwu.cn";
        return cookiestr;
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            initData(api);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class InitDataTask extends AsyncTask<String, Integer, Objects> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Objects doInBackground(String... params) {
            //进行一遍登录请求，主要用于让服务器生成cookie
            Login();
            //初始化数据
            initData(api);
            return null;
        }


        @Override
        protected void onPostExecute(Objects objects) {
            super.onPostExecute(objects);
            pb_newest.setVisibility(View.GONE);
        }
    }
}
