package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;


public class AcheteurDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navView;
    private EditText etSearch;
    private ImageButton btnFilter;
    private RecyclerView rvFeaturedProducts, rvAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheteur_dashboard);

        initViews();
        setupToolbar();
        setupRecyclerViews();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);
        etSearch = findViewById(R.id.et_search);
        btnFilter = findViewById(R.id.btn_filter);
        rvFeaturedProducts = findViewById(R.id.rv_featured_products);
        rvAllProducts = findViewById(R.id.rv_all_products);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        }

        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(navView));
    }

    private void setupRecyclerViews() {
        // Configuration pour afficher 2 produits par ligne
        rvFeaturedProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvAllProducts.setLayoutManager(new GridLayoutManager(this, 2));

        // TODO: Créer et attacher les adapters
    }
}
