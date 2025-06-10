package com.example.homework2;

public class ShoppingItem {
    private final long id;
    private final String name;
    private final int quantity;

    public ShoppingItem(long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }
}
