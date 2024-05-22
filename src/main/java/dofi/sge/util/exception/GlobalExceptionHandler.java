package dofi.sge.util.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<OutputEntity<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        OutputEntity<String> out = new OutputEntity<>();
        // Verifica si la causa es un problema de sintaxis JSON
        if (ex.getCause() instanceof JsonParseException || ex.getCause() instanceof JsonMappingException) {
            out.error(MessageEnum.SYNTAX_JSON_INVALID.getCode(),
                    new ArrayList<>(Arrays.asList(MessageEnum.SYNTAX_JSON_INVALID.getMensaje())),
                    null);
        } else {
            // Otro tipo de error en la solicitud
            out.error(400, new ArrayList<>(Arrays.asList("Solicitud incorrecta")), null);
        }
        return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
    }
}
