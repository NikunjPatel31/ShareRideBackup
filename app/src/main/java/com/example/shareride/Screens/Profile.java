package com.example.shareride.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareride.PageAdapter.MyPageAdapter;
import com.example.shareride.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private static final String TAG = "Profile";
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabItem profileTI, accountTI;
    private TextView tvUserName;
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeComponents();
        initializeFirebaseInstance();
        setViewPagerAdapter();
        TablayoutOnTabSeleteListener();
    }

    private void initializeComponents() {
        // this method will initialize all the components
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        profileTI = findViewById(R.id.profile_tab_item);
        accountTI = findViewById(R.id.account_tab_item);
        tvUserName = findViewById(R.id.new_user_text_view);
        imgProfile = findViewById(R.id.profile_image_view);

    }

    private void initializeFirebaseInstance() {
        // this method will initialize all firebase instance
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void setViewPagerAdapter()
    {
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),"Account");
        viewPager.setAdapter(pageAdapter);
    }

    private void TablayoutOnTabSeleteListener()
    {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}