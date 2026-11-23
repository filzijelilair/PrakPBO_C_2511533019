package shop.shipping;

public class RegularRate implements ShippingRate{
    private final long ratePerKg;

    public RegularRate(long ratePerKg){
        this.ratePerKg = ratePerKg;
    }
    @Override 
    public long costFor(int grams){
        if (grams <= 0) return 0;
        long kg = (grams + 999)/1000;
        return kg*ratePerKg;
    }
}
