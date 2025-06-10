package com.example.homework2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View modalOverlay;
    private CardView modalAddItem;
    private Button btnCancel, btnAdd;
    private TextView tvAddItem;
    private TextInputEditText etItemName, etQuantity;
    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;

    private final List<ShoppingItem> shoppingItems = new ArrayList<>();
    private boolean isEditing = false;
    private ShoppingItem editingItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initViews() {
        modalOverlay = findViewById(R.id.modal_overlay);
        modalAddItem = findViewById(R.id.modal_add_item);
        btnCancel = findViewById(R.id.btn_cancel);
        btnAdd = findViewById(R.id.btn_add);
        tvAddItem = findViewById(R.id.tv_add_item);
        etItemName = findViewById(R.id.et_item_name);
        etQuantity = findViewById(R.id.et_quantity);
        recyclerView = findViewById(R.id.recycler_view_shopping_list);
    }

    private void setupRecyclerView() {
        adapter = new ShoppingListAdapter(shoppingItems,
                item -> editItem(item),
                item -> deleteItem(item)
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupClickListeners() {
        tvAddItem.setOnClickListener(v -> showModal());
        btnCancel.setOnClickListener(v -> hideModal());
        modalOverlay.setOnClickListener(v -> hideModal());
        btnAdd.setOnClickListener(v -> addItem());
    }

    private void showModal() {
        modalOverlay.setVisibility(View.VISIBLE);
        modalAddItem.setVisibility(View.VISIBLE);
        clearInputFields();
    }

    private void hideModal() {
        modalOverlay.setVisibility(View.GONE);
        modalAddItem.setVisibility(View.GONE);
        clearInputFields();
        isEditing = false;
        editingItem = null;
        btnAdd.setText("Thêm");
        btnAdd.setOnClickListener(v -> addItem());
    }

    private void clearInputFields() {
        etItemName.setText("");
        etQuantity.setText("");
    }

    private void addItem() {
        String itemName = etItemName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();

        if (itemName.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        try {
            quantity = quantityStr.isEmpty() ? 1 : Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity <= 0) {
            Toast.makeText(this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            return;
        }

        ShoppingItem newItem = new ShoppingItem(System.currentTimeMillis(), itemName, quantity);
        shoppingItems.add(newItem);
        adapter.notifyItemInserted(shoppingItems.size() - 1);

        hideModal();
        Toast.makeText(this, "Đã thêm " + itemName, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void editItem(ShoppingItem item) {
        editingItem = item;
        isEditing = true;

        etItemName.setText(item.getName());
        etQuantity.setText(String.valueOf(item.getQuantity()));

        showModal();
        btnAdd.setText("Cập nhật");
        btnAdd.setOnClickListener(v -> updateItem(item));
    }

    private void updateItem(ShoppingItem item) {
        String itemName = etItemName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();

        if (itemName.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        try {
            quantity = quantityStr.isEmpty() ? 1 : Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity <= 0) {
            Toast.makeText(this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            return;
        }

        int index = -1;
        for (int i = 0; i < shoppingItems.size(); i++) {
            if (shoppingItems.get(i).getId() == item.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            shoppingItems.set(index, new ShoppingItem(item.getId(), itemName, quantity));
            adapter.notifyItemChanged(index);
            Toast.makeText(this, "Đã cập nhật " + itemName, Toast.LENGTH_SHORT).show();
        }

        hideModal();
    }

    private void deleteItem(ShoppingItem item) {
        int index = -1;
        for (int i = 0; i < shoppingItems.size(); i++) {
            if (shoppingItems.get(i).getId() == item.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            shoppingItems.remove(index);
            adapter.notifyItemRemoved(index);
            Toast.makeText(this, "Đã xóa " + item.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
