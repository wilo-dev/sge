package dofi.sge.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
public class MyException extends Exception {

    private static final long serialVersionUID = 1L;
    private ArrayList<String> messages = new ArrayList<>();
    private Integer code;
    private HttpStatus codeHttp;

    public MyException(Integer code, String message) {
        super();
        this.code = code;
        this.messages.add(message);
    }
}
