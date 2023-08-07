package xrzqx.contactmanagementresfulapi.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xrzqx.contactmanagementresfulapi.entity.User;
import xrzqx.contactmanagementresfulapi.model.RegisterUserRequest;
import xrzqx.contactmanagementresfulapi.repository.UserRepository;
import xrzqx.contactmanagementresfulapi.security.BCrypt;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;
    public void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolationSet = validator.validate(request);
        if(!constraintViolationSet.isEmpty()){
            //eror
            throw new ConstraintViolationException(constraintViolationSet);
        }

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }
}
