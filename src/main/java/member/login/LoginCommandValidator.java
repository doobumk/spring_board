package member.login;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by User on 2017-04-29.
 */
public class LoginCommandValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return LoginCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","required");
    }
}
