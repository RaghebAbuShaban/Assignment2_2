package com.example.assignment2_2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.assignment2_2.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.bottomNavigationView.setSelectedItemId(R.id.homeFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {

                case R.id.homeFragment:
                    selectedFragment = new HomeFragment();

                    break;
                case R.id.chatFragment:
                    selectedFragment = new ChatFragment();

                    break;
                case R.id.settingsFragment:
                    selectedFragment = new SettingsFragment();

                    break;
                case R.id.profileFragment:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);

            if (selectedFragment != null && currentFragment.getClass() != selectedFragment.getClass()) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFragment, selectedFragment)
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        });




        setSupportActionBar(binding.toolbar);
        binding.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.open_nav,R.string.close_nav);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();
            binding.navigationView.setCheckedItem(R.id.nav_home);
        }




        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    MainActivity.super.onBackPressed();
                }
            }
        });


        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment current = getSupportFragmentManager().findFragmentById(R.id.flFragment);
            if (current instanceof HomeFragment){
                binding.bottomNavigationView.setSelectedItemId(R.id.homeFragment);
                binding.navigationView.setCheckedItem(R.id.nav_home);
            }else if (current instanceof ChatFragment){
                binding.bottomNavigationView.setSelectedItemId(R.id.chatFragment);
                binding.navigationView.setCheckedItem(R.id.nav_chat);
            }else if (current instanceof ProfileFragment){
                binding.bottomNavigationView.setSelectedItemId(R.id.profileFragment);
                binding.navigationView.setCheckedItem(R.id.nav_profile);
            }else if (current instanceof SettingsFragment){
                binding.bottomNavigationView.setSelectedItemId(R.id.settingsFragment);
                binding.navigationView.setCheckedItem(R.id.nav_settings);
            }
        });


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ChatFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}