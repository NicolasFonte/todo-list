package com.yordex.test.dl.service;

import static org.junit.Assert.*;

import com.yordex.test.dl.domain.User;
import com.yordex.test.dl.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserService userService;

    private UserRepository userRepository;


    @Before
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testUserCanBeLoadedByUserName() {
        User userInDb = new User();
        userInDb.setUsername("MyUser");
        Mockito.when(userRepository.findByUsername("MyUser")).thenReturn(userInDb);

        UserDetails myUser = userService.loadUserByUsername("MyUser");

        Assertions.assertThat(myUser)
                .isNotNull()
                .extracting("username").containsOnly("MyUser");
    }

    @Test
    public void testUserNotFoundThrowsException() {
        Mockito.when(userRepository.findByUsername("MyUser")).thenReturn(null);

        expectedException.expectMessage("User not found");
        expectedException.expect(UsernameNotFoundException.class);

        userService.loadUserByUsername("MyUser");
    }
}