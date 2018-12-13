package io.github.zhaoqi99.snnu_android;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    WelcomeHelper welcomeScreen;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar mToolBar;

    TabLayout tablayout;
    private mViewPagerFragmentAdapter mAdapter;
    private List<String> mTabTitleList;
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeScreen = new WelcomeHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

        drawerLayout = this.findViewById(R.id.drawer_layout);
        mToolBar = this.findViewById(R.id.mToolBar);
        navigationView=this.findViewById(R.id.navigation_view);
        tablayout=this.findViewById(R.id.tablayout);
        mViewPager=this.findViewById(R.id.mViewPager);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                mToolBar, 0, 0);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();        // 添加此句，toolbar左上角显示开启侧边栏图标

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_home:
                        Toast.makeText(MainActivity.this, "x", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        initFragmentList();
        initTabTitleList();
        mAdapter = new mViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList,mTabTitleList);
        mViewPager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(mViewPager);

        setSupportActionBar(mToolBar);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    public void initFragmentList(){
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new Tab());
        mFragmentList.add(new Tab());
    }

    public void initTabTitleList() {
        mTabTitleList = new ArrayList<String>();
        mTabTitleList.add("新闻");
        mTabTitleList.add("通知");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Toast.makeText(this, " clicked  search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh:
                Toast.makeText(this, "clicked refresh", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

//    @Override
//    public void onBackPressed()
//    {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
}

