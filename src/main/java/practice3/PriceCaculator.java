package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private BigDecimal subTotal;
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts, BigDecimal tax) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = tax;
        subTotal = new BigDecimal(0);
    }

    public BigDecimal calculate() {
        calculateSubtotal();
        subtractDiscounts();
        calculateTax();
        return subTotal;
    }

    private void calculateTax() {
        // calculate tax
        BigDecimal tax = subTotal.multiply(this.tax);
        // calculate GrandTotal
        subTotal = subTotal.add(tax);
    }

    private void subtractDiscounts() {
        // Subtract discounts
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void calculateSubtotal() {
        // Total up line items
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }
}
