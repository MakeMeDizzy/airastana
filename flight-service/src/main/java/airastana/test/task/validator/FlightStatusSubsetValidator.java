package airastana.test.task.validator;

import airastana.test.task.model.FlightStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FlightStatusSubsetValidator implements ConstraintValidator<FlightStatusSubset, FlightStatus> {
    private List<FlightStatus> acceptedValues;
    @Override
    public void initialize(FlightStatusSubset constraintAnnotation) {
        acceptedValues = Arrays.asList(constraintAnnotation.anyOf());
    }

    @Override
    public boolean isValid(FlightStatus value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || acceptedValues.contains(value);
    }
}
