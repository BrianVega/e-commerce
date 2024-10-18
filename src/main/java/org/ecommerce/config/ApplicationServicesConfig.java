package org.ecommerce.config;

import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.models.Manager;
import org.ecommerce.models.Order;
import org.ecommerce.models.User;
import org.ecommerce.repositories.*;
import org.ecommerce.services.*;
import org.ecommerce.services.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApplicationRepositoriesConfig.class)
public class ApplicationServicesConfig {

    @Bean
    PasswordService passwordService(UserRepository userRepository) {
        return new PasswordServiceImpl(userRepository);
    }

    @Bean
    UserService<User> customerService(UserRepository userRepository, PasswordService passwordService) {
        return new CustomerServiceImpl(userRepository, passwordService);
    }

    @Bean
    UserService<Manager> managerService(ManagerRepository managerRepository) {
        return new ManagerServiceImpl(managerRepository);
    }

    @Bean
    OrderService orderService(OrderRepository orderRepository, MessageQueue<Order> messageQueue) {
        return new OrderServiceImpl(orderRepository, messageQueue);
    }

    @Bean
    ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl();
    }

    @Bean
    StockService stockService(StockRepository stockRepository) {
        return new StockServiceImpl(stockRepository);
    }

}
