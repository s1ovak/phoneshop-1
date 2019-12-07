package com.es.core.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> cartItems;

    private BigDecimal totalPrice;
    private Integer totalQuantity;

    public Cart() {
        this.cartItems = new ArrayList<>();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    public void addCartItem(CartItem item) {
        Optional<CartItem> optionalCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getPhone().getId().equals(item.getPhone().getId())).findAny();

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        } else {
            cartItems.add(item);
        }

        recalculateCart();
    }

    public void setCartItemQuantity(Long phoneId, Integer newQuantity) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getPhone().getId().equals(phoneId)).findAny()
                .ifPresent(cartItem -> cartItem.setQuantity(newQuantity));
        recalculateCart();
    }

    public void recalculateCart() {
        totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getPhone().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

        totalQuantity = 0;
        for (CartItem cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
    }

    public void removeItem(Long phoneId) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getPhone().getId().equals(phoneId))
                .findAny().ifPresent(cartItems::remove);
        recalculateCart();
    }


    public void clearCart() {
        cartItems.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }
}
