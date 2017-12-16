package ua.spring.validator;

import ua.spring.model.User;
import ua.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{
    private static final int MIN_CHARS = 3;
    private static final int MAX_CHARS = 32;

    private UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
        if (user.getEmail().length() < MIN_CHARS || user.getEmail().length() > MAX_CHARS) {
            errors.rejectValue("username", "size.userform.username");
        }
        if (userService.findByUsername(user.getEmail()) != null) {
            errors.rejectValue("username", "duplicate.userform.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        if (user.getPassword().length() < MIN_CHARS || user.getPassword().length() > MAX_CHARS) {
            errors.rejectValue("password", "size.userform.password");
        }
        if (!user.getPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "different.userform.password");
        }
    }
}
