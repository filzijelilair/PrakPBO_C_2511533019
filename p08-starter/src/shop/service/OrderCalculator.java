package shop.service;

import shop.discount.DiscountStrategy;
import shop.model.Order;
import shop.model.Shippable;
import shop.shipping.RegularRate;
import shop.shipping.ShippingRate;

public class OrderCalculator {
    private final ShippingRate shippingRate;
    
    public OrderCalculator(long ratePerKg){
        this(new RegularRate(ratePerKg));
    }
    public OrderCalculator(ShippingRate shippingRate){
        this.shippingRate = shippingRate;
    }

    public Bill calculate(Order order, DiscountStrategy discountStrategy){
        // [R1] menghitung total subtotal dari seluruh item pesanan//
        long subtotal = 0;
        int grams = 0;

        for (Order.Line line : order.lines()) {
            subtotal += line.total();
            if (line.product() instanceof Shippable item){
                grams += item.getWeightGram()*line.qty();
            }
        }
        // [R2] menghitung diskon berdasarkan memberLevel//
        long discount = discountStrategy.discountFor(subtotal); 
        // [R4] menghitung biaya pengiriman //
        long shipping = shippingRate.costFor(grams);
        // [R5]menghitung total pembayaran setelah diskon dan +biaya pengiriman//
        long total = subtotal - discount + shipping;

        return new Bill(subtotal, discount, shipping, total);
    }
}
