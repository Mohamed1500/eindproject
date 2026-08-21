package be.ehb.eindproject.service;

import be.ehb.eindproject.model.User;
import be.ehb.eindproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerNewUserStoresEncodedPasswordAndUserRole() {
        when(passwordEncoder.encode("veiligWachtwoord")).thenReturn("hashedPassword");
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        userService.registerNewUser("student", "veiligWachtwoord", "student@example.com");

        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals("hashedPassword", savedUser.getPassword());
        assertEquals("USER", savedUser.getRole());
    }
}