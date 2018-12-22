package io.github.zhaoqi99.snnu_android;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.forusers.heinsinputdialogs.HeinsDatePickerDialog;
import br.com.forusers.heinsinputdialogs.interfaces.OnSelectDateListener;

public class MainActivity extends AppCompatActivity {

    WelcomeHelper welcomeScreen;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar mToolBar;

    HeinsDatePickerDialog dialog;

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
                        switchToBookInfo();
                        break;
                    case R.id.navigation_item_jwc:
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(intent,0);
                        //switchToJwc();
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
        dialog = new HeinsDatePickerDialog();
        dialog.setListener(new OnSelectDateListener() {
            @Override
            public void onSelectDate(Date date) throws Exception {
                Toast.makeText(getApplicationContext(), "clicked refresh", Toast.LENGTH_SHORT).show();
                SearchNoticeFragment nf= new SearchNoticeFragment();
                nf.setType(mToolBar.getTitle().toString());
                SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
                nf.setDate(sft.format(date));
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,nf ).commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {// 取出Intent里的数据
            String result = data.getStringExtra("success");
            if(result.equals("true"))
                switchToJwc();
            else
                Toast.makeText(MainActivity.this,"登录失败，请重试!",Toast.LENGTH_LONG).show();
        }
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
                dialog.show(getSupportFragmentManager(), getClass().getSimpleName());
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
        mToolBar.setTitle(R.string.navigation_about);
    }
    private void switchToNews() {
        NewsFragment nf= new NewsFragment();
        nf.setType("新闻");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, nf).commit();
        mToolBar.setTitle(R.string.navigation_news);
    }
    private void switchToNotice() {
       NewsFragment nf= new NewsFragment();
       nf.setType("通知");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,nf ).commit();
        mToolBar.setTitle(R.string.navigation_notice);
    }
    private void switchToCard() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new CardFragment() ).commit();
        mToolBar.setTitle(R.string.navigation_card);
    }
    private void switchToJwc() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new JwcFragment() ).commitAllowingStateLoss();
        mToolBar.setTitle(R.string.navigation_jwc);
    }
    private void switchToBookInfo() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new BookInfoFragment() ).commit();
        mToolBar.setTitle(R.string.navigation_borrow);
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

