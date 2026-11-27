package shop.model;

import java.lang.module.ModuleDescriptor.Builder;

public class Voucher {
    private final String code;
    private final int percent;
    private final long minimumPurchase;
    private final String description;

    private Voucher(Builder b){
        this.code = b.code;
        this.percent = b.percent;
        this.minimumPurchase = b.minimumPurchase;
        this.description = b.description;
    }

    public String getCode(){
        return code;
    }
    public int getPercent(){
        return percent;
    }
    public long getMinimumPurchase(){
        return minimumPurchase;
    }
    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Voucher " + code + ", diskon " + percent + "% min belanja : Rp" + minimumPurchase
                + (description.isEmpty() ? "" : ", ket : " + description);
    }

    public static class Builder {
        private final String code;
        private final int percent;
        private long minimumPurchase = 0;
        private String description = "";

        public Builder(String code, int percent) {
            this.code = code;
            this.percent = percent;
        }

        public Builder minimumPurchase(long minimumPurchase) {
            this.minimumPurchase = minimumPurchase;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Voucher build() {
            if (code == null || code.isBlank()) {
                throw new IllegalStateException("Kode voucher wajib diisi!");
            }
            if (percent < 1 || percent > 50) {
                throw new IllegalStateException("Persentase diskon harus dalam rentang 1-50");
            }
            return new Voucher(this);
        }
    }
}