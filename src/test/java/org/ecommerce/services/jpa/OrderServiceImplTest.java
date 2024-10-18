package org.ecommerce.services.jpa;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.ecommerce.models.Order;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ActiveProfiles("local")
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Mock
    private Order order;

    @Mock
    private Customer customer;

    @Mock
    private ShippingInformation shippingInformation;

    @Mock
    private Price price;

    @Mock
    private BillingInformation billingInformation;

    @Mock
    private PaymentDetails paymentDetails;

    @DisplayName("This BeforeAll method creates a web server in the 8082 port so we can connect to the h2 database")
    @BeforeAll
    public static void initTest() throws SQLException {
//        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
//                .start();
    }

    @BeforeEach
    void setUp() {
        BigDecimal amount = new BigDecimal("100.00");

        lenient().when(order.getOrderDate()).thenReturn(new Date());

        lenient().when(order.getTotalUsd()).thenReturn(amount);

        lenient().when(order.getCustomer()).thenReturn(customer);
        lenient().when(order.getCustomer().getId()).thenReturn(1L);

        lenient().when(order.getShippingInformation()).thenReturn(shippingInformation);
        lenient().when(order.getShippingInformation().getId()).thenReturn(1L);
        lenient().when(order.getShippingInformation().getShippingCost()).thenReturn(price);
        lenient().when(order.getShippingInformation().getShippingCost().getAmount()).thenReturn(amount);

        lenient().when(order.getBillingInformation()).thenReturn(billingInformation);
        lenient().when(order.getBillingInformation().getId()).thenReturn(1L);
        lenient().when(order.getBillingInformation().getAmount()).thenReturn(price);
        lenient().when(order.getBillingInformation().getAmount().getAmount()).thenReturn(amount);

        lenient().when(order.getPaymentDetails()).thenReturn(paymentDetails);
        lenient().when(order.getPaymentDetails().getId()).thenReturn(1L);

        lenient().when(order.getStatus()).thenReturn(OrderStatus.PLACED);
    }


    @Test
    @Disabled
    void create() {
        orderService.create(order);
        assertNotNull(order.getId());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
       Order order = orderService.findById(1L);
        Log.info(order.toString());
    }

    @Test
    @Disabled("This test prevent tests to finish, used for debug purposes")
    void infiniteLoop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}


