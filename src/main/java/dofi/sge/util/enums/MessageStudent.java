package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageStudent {
    STUDENT_CODE_UNIQUE("Ya existe un registro con ese cogigo para un estudiante, ingrese otro", 403);


    private String mensaje;
    private Integer code;

    MessageStudent(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
