package shop.shipping;

public class SameDayRate implements ShippingRate{
    @Override
    public long costFor(int grams) {
        if (grams <= 0) return 0;
        long kg = (grams + 999) / 1000;
        if (kg <= 3) {
            return 25_000;
        }
        return 25_000 + (kg-3)*10_000;
    }
}
