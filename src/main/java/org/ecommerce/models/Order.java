package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Currency;
import java.util.List;

@Setter
@Getter
public class Order extends Identity {
    private Long customerId;
    private Date orderDate;
    private String status;
    private Currency totalAmount;
    private String shippingAddress;
    private List<Product> products;

    Order(Long id, Long customerId, Date orderDate, String status, Currency totalAmount, String shippingAddress, List<Product> products) {
        super(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.products = products;
    }

}
