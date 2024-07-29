package com.converter;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("dateTimeValidator")
public class DateTimeValidator implements Validator {
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; 
        }

        String dateTimeString = value.toString();
        if (!DATE_TIME_PATTERN.matcher(dateTimeString).matches()) {
            FacesMessage msg = new FacesMessage("Date-time validation failed.", 
                                                "Invalid date-time format. Please use YYYY-MM-DDTHH:MM.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
