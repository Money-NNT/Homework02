package com.example.homework2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onClick(ShoppingItem item);
    }

    private final List<ShoppingItem> items;
    private final OnItemClickListener onEditClick;
    private final OnItemClickListener onDeleteClick;

    public ShoppingListAdapter(List<ShoppingItem> items, OnItemClickListener onEditClick, OnItemClickListener onDeleteClick) {
        this.items = items;
        this.onEditClick = onEditClick;
        this.onDeleteClick = onDeleteClick;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName, tvItemQuantity;
        public ImageView ivEdit, ivDelete;

        public ViewHolder(View view) {
            super(view);
            tvItemName = view.findViewById(R.id.tv_item_name);
            tvItemQuantity = view.findViewById(R.id.tv_item_quantity);
            ivEdit = view.findViewById(R.id.iv_edit);
            ivDelete = view.findViewById(R.id.iv_delete);
        }
    }

    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position) {
        ShoppingItem item = items.get(position);

        holder.tvItemName.setText(item.getName());
        holder.tvItemQuantity.setText("Số lượng: " + item.getQuantity());

        holder.ivEdit.setOnClickListener(v -> onEditClick.onClick(item));
        holder.ivDelete.setOnClickListener(v -> onDeleteClick.onClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
