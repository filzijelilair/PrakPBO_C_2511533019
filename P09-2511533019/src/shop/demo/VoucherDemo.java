package shop.demo;

import shop.model.Voucher;

public class VoucherDemo {
    public static void main(String[] args) {
        // voucher sah
        Voucher validVoucher = new Voucher.Builder("JELY01", 10)
                .minimumPurchase(50_000)
                .description("Diskon 10% minimal belanja Rp50.000 di toko Jely")
                .build();
        System.out.println("Voucher Diskon berhasil dibuat: " + validVoucher);

        // tidak sah (persentase diskon > 50)
        try {
            Voucher invalidVoucher = new Voucher.Builder("JELY02", 60)
                    .minimumPurchase(100_000)
                    .build();
            System.out.println("Berhasil dibuat: " + invalidVoucher);
        } catch (IllegalStateException e) {
            System.out.println("Gagal membuat voucher: " + e.getMessage());
        }
    }
}

