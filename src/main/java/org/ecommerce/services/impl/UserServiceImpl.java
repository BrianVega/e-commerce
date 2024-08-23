package org.ecommerce.services.impl;

import org.ecommerce.models.Customer;
import org.ecommerce.models.User;
import org.ecommerce.repositories.UserRepository;
import org.ecommerce.services.EmailService;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;
import org.ecommerce.Enums.Error;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService<User> {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
    }

    public Map<String, String> generateCustomer(Customer customer) {
        Map<String, String> result = new HashMap<>();
        if (!emailService.meetsBusinessRules(customer.getEmail()))
            result.put("error", Error.INVALID_EMAIL.getDescription());
        if (!passwordService.meetsBusinessRules(customer.getPassword()))
            result.put("error", Error.PASSWORD_FORMAT.getDescription());
        if (result.isEmpty())
            result.put("generatedCustomer", String.valueOf(userRepository.save(customer)));
        return result;
    }

    @Override
    public boolean meetsBusinessRules(User obj) {
        return false;
    }
}
