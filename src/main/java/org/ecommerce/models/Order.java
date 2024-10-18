package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ecommerce.enums.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@AttributeOverride(name="id", column=@Column(name="pk_order_id"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Validated
public class Order extends Identity {
    @JoinColumn(name = "fk_customer_id", nullable = false)
    @ManyToOne
    private Customer customer;

    @Column(name = "date", nullable = false)
    @NotNull
    private Date orderDate;

    @Column(name = "total_usd", nullable = false)
    @NotNull
    private BigDecimal totalUsd;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_orders")
    @NotNull
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private OrderStatus status;

    @JoinColumn(name = "fk_shipping_information_id", nullable = false)
    @OneToOne
    @NotNull
    private ShippingInformation shippingInformation;

    @JoinColumn(name = "fk_billing_information_id", nullable = false)
    @OneToOne
    @NotNull
    private BillingInformation billingInformation;

    @JoinColumn(name = "fk_payment_details_id", nullable = false)
    @OneToOne
    @NotNull
    private PaymentDetails paymentDetails;
}
