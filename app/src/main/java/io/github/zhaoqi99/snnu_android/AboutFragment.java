package io.github.zhaoqi99.snnu_android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.about_logo)//图片
                .setDescription("道理我都懂，可我就是不听啊")//介绍
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("与我联系")
                .addEmail("zhaoqi99@outlook.com")//邮箱
                .addWebsite("https://zhaoqi.vip")//网站
                .addGitHub("zhaoqi99")//github
                .addItem(getCopyRightsElement())
                .create();
        return aboutPage;
    }
    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        String copyrights = getString(R.string.copy_right)+ Calendar.getInstance().get(Calendar.YEAR);
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.copyright);
//        copyRightsElement.setIconDrawable(R.drawable.about_logo);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AboutFragment.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

}
