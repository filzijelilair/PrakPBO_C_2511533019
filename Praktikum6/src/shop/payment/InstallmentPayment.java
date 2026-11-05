package shop.payment;

public interface InstallmentPayment extends PaymentMethod{
    int maxInstallmentMethods();

    default long monthlyInstallment(long amount, int months){
        if (months < 1 || months > maxInstallmentMethods()) {             
            throw new IllegalArgumentException(
                "Months must be between 1 and " + maxInstallmentMethods());        
        }         
        return (amount + months - 1) / months; 
    }
}
