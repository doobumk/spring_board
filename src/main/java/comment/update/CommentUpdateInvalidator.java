package comment.update;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by User on 2017-06-01.
 */
public class CommentUpdateInvalidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommentUpdateCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CommentUpdateCommand commentUpdateCommand = (CommentUpdateCommand)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"content","required");

    }
}
