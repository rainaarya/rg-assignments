class TaxUtil {
    public double calculateTax(double amount, double rate) {
        return amount * rate;
    }
}

public class Q1 {
    public static void main(String[] args) {
        TaxUtil taxUtil = new TaxUtil();
        System.out.println(taxUtil.calculateTax(100, 0.1));
    }
}

