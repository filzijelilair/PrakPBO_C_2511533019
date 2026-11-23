package shop.Tantangan;

import shop.discount.NoDiscount;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.service.Bill;
import shop.service.OrderCalculator;
import shop.shipping.SameDayRate;
import shop.util.Rupiah;

public class TantanganA {
    public static void main(String[] args) {
        OrderCalculator sameDayCalc = new OrderCalculator(new SameDayRate());

        // Uji berat <= 3 kg (2.5 kg -> 3 kg = Rp25.000)
        PhysicalProduct kopi = new PhysicalProduct("P01", "Kopi Bubuk 2.5kg", 150_000, 2500);
        Order order1 = new Order("ORD-SD1", new Address("Budi", "Padang"));
        order1.add(kopi, 1);
        Bill bill1 = sameDayCalc.calculate(order1, new NoDiscount());
        System.out.println("Order 1 (2.5kg) Ongkir SameDay: " + Rupiah.format(bill1.shipping()));

        // Uji berat > 3 kg (4.2 kg -> 5 kg = 25.000 + (2 * 10.000) = Rp45.000)
        PhysicalProduct rendang = new PhysicalProduct("P02", "Rendang 2.1kg", 200_000, 2100);
        Order order2 = new Order("ORD-SD2", new Address("Ani", "Padang"));
        order2.add(rendang, 2);
        Bill bill2 = sameDayCalc.calculate(order2, new NoDiscount());
        System.out.println("Order 2 (4.2kg) Ongkir SameDay: " + Rupiah.format(bill2.shipping()));
    }
}