package member.edit;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by User on 2017-04-29.
 */
public class ChangePwdCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePwdCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"oldPassword","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"newPassword","required");
    }
}
