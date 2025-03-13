package com.example.vzw_transaction_ledger;

import com.example.vzw_transaction_ledger.model.User;
import com.example.vzw_transaction_ledger.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    // Inject the UserRepository bean from the application context.
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndFindUser() {
        // Create a new User instance with a name and email.
        User user = new User("John Doe", "john.doe@example.com");

        // Save the user to the database.
        userRepository.save(user);

        // Retrieve the user using the generated id.
        User found = userRepository.findById(user.getId()).orElse(null);

        // Assert that the user was found (i.e., not null).
        assertThat(found).isNotNull();

        // Verify that the user's details match what was saved.
        assertThat(found.getName()).isEqualTo("John Doe");
        assertThat(found.getEmail()).isEqualTo("john.doe@example.com");
    }
}
