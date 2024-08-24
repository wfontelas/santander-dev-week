package me.dio.santander_dev_week.service.impl;

import me.dio.santander_dev_week.domain.model.User;
import me.dio.santander_dev_week.domain.repository.UserRepository;
import me.dio.santander_dev_week.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);

    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account number already exists;");
        }
        return userRepository.save(userToCreate);
    }
}
