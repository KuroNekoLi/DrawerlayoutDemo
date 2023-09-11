package com.example.drawerlayoutdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        androidx.appcompat.widget.Toolbar customToolbar = findViewById( R.id.custom_toolbar );
        setSupportActionBar(customToolbar);


        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_settings)
                .setOpenableLayout( drawerLayout )
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // 移除 ActionBar 的標題
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //设置左侧菜单
        NavigationView navigationView = findViewById(R.id.navigation_view);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged( @NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                // 不使用預設的箭頭
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                // 不使用預設的漢堡圖
                getSupportActionBar().setHomeButtonEnabled(false);

                ImageView customHamburgerIcon = customToolbar.findViewById(R.id.custom_hamburger_icon);
                TextView fragmentTitle = customToolbar.findViewById(R.id.fragment_title);

                if(destination.getId() == R.id.nav_home || destination.getId() == R.id.nav_settings || destination.getId()==R.id.nav_profile) {
                    customHamburgerIcon.setImageResource(R.drawable.icon_menu_n);  // 漢堡圖示
                } else {
                    customHamburgerIcon.setImageResource(R.drawable.icon_back_n);  // 返回圖示
                }

                // Set the title based on the current fragment
                CharSequence label = destination.getLabel();
                if (label != null) {
                    fragmentTitle.setText(label);
                }
            }
        });

        ImageView customHamburgerIcon = customToolbar.findViewById(R.id.custom_hamburger_icon);
        customHamburgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDestination currentDestination = navController.getCurrentDestination();
                if (currentDestination != null) {
                    if (currentDestination.getId() == R.id.nav_home || currentDestination.getId() == R.id.nav_settings || currentDestination.getId()==R.id.nav_profile) {
                        // 開啟或關閉 drawer
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);
                        }
                    } else {
                        // 返回上一個 Fragment
                        navController.navigateUp();
                    }
                }
            }
        });

    }

    /**
     * 左上角的菜单被点击时调用到
     */
    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}