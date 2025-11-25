package com.example.myapplication.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import java.io.IOException;
import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_GALLERY = 101;
    private static final int REQUEST_PERMISSION = 102;
    private static final int MAX_IMAGES = 5;

    private Toolbar toolbar;
    private CardView cvAddImage;
    private LinearLayout llImagesContainer;
    private TextInputEditText etProductName, etPrice, etQuantity, etDescription;
    private Spinner spinnerCategory, spinnerUnit;
    private Button btnPublishProduct;

    private ArrayList<Uri> productImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initViews();
        setupToolbar();
        setupSpinners();
        setupListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        cvAddImage = findViewById(R.id.cv_add_image);
        llImagesContainer = findViewById(R.id.ll_images_container);
        etProductName = findViewById(R.id.et_product_name);
        etPrice = findViewById(R.id.et_price);
        etQuantity = findViewById(R.id.et_quantity);
        etDescription = findViewById(R.id.et_description);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerUnit = findViewById(R.id.spinner_unit);
        btnPublishProduct = findViewById(R.id.btn_publish_product);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupSpinners() {
        // Catégories
        String[] categories = {"Légumes", "Fruits", "Céréales", "Tubercules", "Élevage"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Unités
        String[] units = {"kg", "litre", "pièce", "sac", "tonne"};
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(unitAdapter);
    }

    private void setupListeners() {
        cvAddImage.setOnClickListener(v -> {
            if (productImages.size() >= MAX_IMAGES) {
                Toast.makeText(this, "Maximum " + MAX_IMAGES + " photos", Toast.LENGTH_SHORT).show();
                return;
            }
            showImageSourceDialog();
        });

        btnPublishProduct.setOnClickListener(v -> publishProduct());
    }

    private void showImageSourceDialog() {
        String[] options = {"Prendre une photo", "Choisir de la galerie"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajouter une photo");
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                if (checkPermissions()) {
                    openCamera();
                }
            } else {
                if (checkPermissions()) {
                    openGallery();
                }
            }
        });
        builder.show();
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    REQUEST_PERMISSION);
            return false;
        }
        return true;
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri imageUri = null;

            if (requestCode == REQUEST_CAMERA) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    // TODO: Convertir le bitmap en Uri et le sauvegarder
                    // Pour l'instant, on affiche juste un message
                    Toast.makeText(this, "Photo prise", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_GALLERY) {
                imageUri = data.getData();
                if (imageUri != null) {
                    addImageToList(imageUri);
                }
            }
        }
    }

    private void addImageToList(Uri imageUri) {
        productImages.add(imageUri);

        // Créer une nouvelle vue pour l'image
        View imageItemView = LayoutInflater.from(this)
                .inflate(R.layout.item_product_image, llImagesContainer, false);

        ImageView ivProductImage = imageItemView.findViewById(R.id.iv_product_image);
        ImageButton btnDeleteImage = imageItemView.findViewById(R.id.btn_delete_image);

        // Afficher l'image
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            ivProductImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gérer la suppression
        final int imageIndex = productImages.size() - 1;
        btnDeleteImage.setOnClickListener(v -> {
            productImages.remove(imageIndex);
            llImagesContainer.removeView(imageItemView);
            Toast.makeText(this, "Photo supprimée", Toast.LENGTH_SHORT).show();
        });

        // Ajouter la vue avant le bouton "Ajouter photo"
        llImagesContainer.addView(imageItemView, llImagesContainer.getChildCount() - 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions accordées", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions refusées", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void publishProduct() {
        String name = etProductName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (productImages.isEmpty()) {
            Toast.makeText(this, "Veuillez ajouter au moins une photo", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Uploader les images et enregistrer le produit dans votre backend
        Toast.makeText(this, "Produit publié avec succès !", Toast.LENGTH_LONG).show();
        finish();
    }
}