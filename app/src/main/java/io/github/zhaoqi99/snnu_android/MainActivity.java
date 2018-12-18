package io.github.zhaoqi99.snnu_android;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeHelper;

public class MainActivity extends AppCompatActivity {

    WelcomeHelper welcomeScreen;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar mToolBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeScreen = new WelcomeHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

        drawerLayout = this.findViewById(R.id.drawer_layout);
        mToolBar = this.findViewById(R.id.mToolBar);
        navigationView=this.findViewById(R.id.navigation_view);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                mToolBar, 0, 0);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();        // 添加此句，toolbar左上角显示开启侧边栏图标
        //侧栏菜单按钮事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_notice:
                        switchToNotice();
                        break;
                    case R.id.navigation_item_news:
                        switchToNews();
                        break;
                    case R.id.navigation_card:
                        switchToCard();
                        Toast.makeText(MainActivity.this, "x", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_lib:
                        break;
                    case R.id.navigation_item_jwc:
                        break;
                    case R.id.navigation_item_about:
                        switchToAbout();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        setSupportActionBar(mToolBar);
        switchToNotice();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    //ToolBar按钮事件
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

    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new AboutFragment()).commit();
//        mToolbar.setTitle(R.string.navigation_book);
    }
    private void switchToNews() {
        NewsFragment nf= new NewsFragment();
        nf.setType("新闻");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, nf).commit();
//        mToolbar.setTitle(R.string.navigation_book);
    }
    private void switchToNotice() {
       NewsFragment nf= new NewsFragment();
       nf.setType("通知");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,nf ).commit();
//        mToolbar.setTitle(R.string.navigation_book);
    }
    private void switchToCard() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new CardFragment() ).commit();
//        mToolbar.setTitle(R.string.navigation_book);
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

