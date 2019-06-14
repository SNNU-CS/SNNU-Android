package io.github.zhaoqi99.snnu_android;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

public class MyWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorWelcomeBack)
                .page(new TitlePage(R.drawable.welcome_1,
                        "")
                )
                .page(new BasicPage(R.drawable.welcome_2,
                        "",
                        "")
                        .background(R.color.colorWelcomeBack)
                )
                .swipeToDismiss(true)
                .build();
    }
}
