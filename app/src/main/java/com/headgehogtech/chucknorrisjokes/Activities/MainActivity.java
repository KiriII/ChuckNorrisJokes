package com.headgehogtech.chucknorrisjokes.Activities;

import android.os.Bundle;
import android.os.PowerManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.headgehogtech.chucknorrisjokes.R;
import com.headgehogtech.chucknorrisjokes.Views.JokesView;
import com.headgehogtech.chucknorrisjokes.Views.WebFragmentView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

//    @Override
//    public void onBackPressed() {
//        tellFragments();
//        super.onBackPressed();
//    }
//
//    private void tellFragments(){
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for(Fragment f : fragments){
//            if(f != null && f instanceof WebFragmentView)
//                ((WebFragmentView)f).onBackPressed();
//        }
//    }
}
