package com.design.lld;

import java.util.ArrayList;
import java.util.List;

enum ProductType {
    ELECTRONICS,
    FURNITURE,
    DECORATIVE,
    GROCERY
}

abstract class Product {
    private String name;
    private double originalPrice;
    private ProductType type;

    public Product(String name, double originalPrice, ProductType type) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public ProductType getType() {
        return type;
    }

    public abstract double getPrice();
}

class ConcreteProduct extends Product {
    public ConcreteProduct(String name, double originalPrice, ProductType type) {
        super(name, originalPrice, type);
    }

    @Override
    public double getPrice() {
        return getOriginalPrice();
    }
}


abstract class CouponDecorator extends Product {
    protected Product product;

    public CouponDecorator(Product product) {
        super(product.getName(), product.getOriginalPrice(), product.getType());
        this.product = product;
    }
}

class PercentageCouponDecorator extends CouponDecorator {
    private double discountPercentage;

    public PercentageCouponDecorator(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
        return discountedPrice;
    }
}

class TypeCouponDecorator extends CouponDecorator {
    private double discountPercentage;
    private ProductType eligibleType;

    public TypeCouponDecorator(Product product, double discountPercentage, ProductType eligibleType) {
        super(product);
        this.discountPercentage = discountPercentage;
        this.eligibleType = eligibleType;
    }

    @Override
    public double getPrice() {
        if (product.getType() == eligibleType) {
            return product.getPrice() * (1 - discountPercentage / 100);
        }
        return product.getPrice();
    }
}


class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}

public class ShoppingCartWithCoupons {
    public static void main(String[] args) {
        // Create Products
        Product fan = new ConcreteProduct("Fan", 1000, ProductType.ELECTRONICS);
        Product sofa = new ConcreteProduct("Sofa", 2000, ProductType.FURNITURE);

        // Apply Coupons
        Product discountedFan = new PercentageCouponDecorator(fan, 10); // 10% off
        Product discountedSofa = new TypeCouponDecorator(
            new PercentageCouponDecorator(sofa, 5), // 5% off
            3, // Additional discount of 3% for Furniture
            ProductType.FURNITURE
        );

        // Add to Shopping Cart
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(discountedFan);
        cart.addProduct(discountedSofa);

        // Calculate Total Price
        System.out.println("Total Price: " + cart.getTotalPrice());
    }
}