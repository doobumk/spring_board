package document.write;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by User on 2017-05-05.
 */
public class DocumentWriteValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return DocumentWriteCommand.class.isAssignableFrom(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {
        DocumentWriteCommand documentWriteCommand = (DocumentWriteCommand)target;
        if(documentWriteCommand.getType() == 0 ){
            errors.rejectValue("type","required");
        } //DB에 0들어갈떄 오류발생

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"content","required");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors,"type","required");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors,"multipartFile","required");
    }
}
