package org.ecommerce.repositories;

import org.ecommerce.models.Customer;
import org.ecommerce.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private User user;

    @Test
    void save() {
        userRepository.save(user);

        when(user.getId()).thenReturn(1L);

        assertThat(userRepository.findById(user.getId()))
                .isEqualTo(Optional.of(user));
    }

    @Test
    void findById() {
        userRepository.save(user);

        assertThat(userRepository.findById(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void update() {
        User customer = new Customer(0L, "Brian", "Vega", "bvega@gd.com"
                , "@Pass123" ,"3414204938", "Providencia #33"
                , new ArrayList<>());
        userRepository.save(customer);

        var updatedUser = userRepository.findById(customer.getId()).orElseThrow();

        updatedUser.setFirstName("updatedFirstName");

        userRepository.update(0L, updatedUser);

        var res = userRepository.findById(user.getId()).orElseThrow();
        assertThat(res.getFirstName()).isEqualTo("updatedFirstName");
    }

    @Test
    void deleteByIdSuccessfully() {
        userRepository.save(user);

        userRepository.deleteById(0L);

        assertThat(userRepository.findById(0L)).isEqualTo(Optional.empty());
    }

    @Test
    void deleteByIdNotSuccessfully() {
        assertThrows(NoSuchElementException.class,
                () -> userRepository.deleteById(1L));
    }

    @Test
    void findAll() {
        userRepository.save(user);
        userRepository.save(user);
        userRepository.save(user);

        assertThat(userRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void getDb() {
        var db = userRepository.getDb();

        assertThat(db).isNotNull();
        assertThat(db).isInstanceOf(HashMap.class);
    }

    @Test
    void findPasswordByIdSuccessfully() {
        User customer = new Customer(0L, "Brian", "Vega", "bvega@gd.com"
                , "@Pass123" ,"3414204938", "Providencia #33"
                , new ArrayList<>());

        userRepository.save(customer);

        var password = userRepository.findPasswordById(customer.getId());
        assertThat(password).isEqualToIgnoringCase("@Pass123");
    }

    @Test
    void findPasswordByIdNotSuccessfully() {
        assertThrows(RuntimeException.class,
                () -> userRepository.findPasswordById(user.getId()));
    }

    @Test
    void updatePasswordById() {
        User customer = new Customer(0L, "Brian", "Vega", "bvega@gd.com"
                , "@Pass123" ,"3414204938", "Providencia #33"
                , new ArrayList<>());
        userRepository.save(customer);

        String newPassword = "@newPassword123";

        userRepository.updatePasswordById(0L, newPassword);
        var updatedPassword = userRepository.findPasswordById(customer.getId());

        assertThat(updatedPassword).isEqualToIgnoringCase(newPassword);
    }

    @Test
    void updatePasswordByIdNotSuccessfully() {
        assertThrows(RuntimeException.class,
                () -> userRepository.updatePasswordById(1L, "newPassword123"));
    }
}