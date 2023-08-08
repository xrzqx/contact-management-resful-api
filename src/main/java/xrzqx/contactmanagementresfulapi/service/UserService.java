package xrzqx.contactmanagementresfulapi.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import xrzqx.contactmanagementresfulapi.entity.User;
import xrzqx.contactmanagementresfulapi.model.RegisterUserRequest;
import xrzqx.contactmanagementresfulapi.model.UpdateUserRequest;
import xrzqx.contactmanagementresfulapi.model.UserResponse;
import xrzqx.contactmanagementresfulapi.repository.UserRepository;
import xrzqx.contactmanagementresfulapi.security.BCrypt;

import java.util.Objects;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request) {

        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Transactional
    public UserResponse update(UpdateUserRequest request, User user) {
        validationService.validate(request);
        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }
        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }


}
