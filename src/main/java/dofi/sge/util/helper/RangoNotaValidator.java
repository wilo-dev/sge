package dofi.sge.util.helper;

import dofi.sge.util.RangoNota;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangoNotaValidator implements ConstraintValidator<RangoNota, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Dejar que @NotNull maneje esto si es necesario
        }
        return value >= 0 && value <= 10;
    }
}
