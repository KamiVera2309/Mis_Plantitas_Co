package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    FrameLayout fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Martin
        //fm = (FrameLayout)findViewById(R.id.fragment_container);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioNavFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.nav_inicio) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioNavFragment()).commit();

            //Martin
            //fm.setContent

        } else if (itemId == R.id.nav_info) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoNavFragment()).commit();

        } else if (itemId == R.id.nav_configuracion) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionNavFragment()).commit();

        } else if (itemId == R.id.nav_compartir) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompartirNavFragment()).commit();

        } else if (itemId == R.id.nav_salida) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioNavFragment()).commit();

            Intent intent = new Intent(this, LoginYRegistro.class);
            startActivity(intent);

            Toast.makeText(this, "Se cerro sesion", Toast.LENGTH_SHORT).show();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}