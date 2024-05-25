package dofi.sge.util;

import dofi.sge.util.helper.RangoNotaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RangoNotaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RangoNota {
    String message() default "Solo acepta valores en el rango de 0 a 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
