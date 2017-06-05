package member.register;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 2017-04-29.
 */
public class RegisterRequestValidator implements Validator {

    private static final String emailRegExp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    public RegisterRequestValidator() {
        pattern = Pattern.compile(emailRegExp);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest registerRequest = (RegisterRequest)target;
        if(registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()){
            errors.rejectValue("email","required"); // email폼에 아무런 값이 없을떄, email프로퍼티의 에러코드로 required 추가
        }else{
            Matcher matcher = pattern.matcher(registerRequest.getEmail());
            if(!matcher.matches()){
                errors.rejectValue("email","bad"); //email폼에 값이 있지만 규칙에 맞지 않을때 bad
            }
        }
        if(registerRequest.getLevel() == 0){
            errors.rejectValue("level","required");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"groupcode","required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"level","required");


        if(registerRequest.getPassword().isEmpty() == false){
            if(registerRequest.isPasswordEqualToConfirmPassword() == false){
                errors.rejectValue("confirmPassword","notMatch");
            }
        }
    }
}
