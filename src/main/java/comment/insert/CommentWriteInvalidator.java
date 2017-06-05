package comment.insert;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by User on 2017-06-01.
 */
public class CommentWriteInvalidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommentWriteCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CommentWriteCommand commentWriteCommand = (CommentWriteCommand)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"content","required");


    }
}
