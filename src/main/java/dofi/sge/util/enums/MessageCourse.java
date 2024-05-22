package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageCourse {
    COURSE_UNIQUE("Ya existe este curso", 403);

    private String mensaje;
    private Integer code;

    MessageCourse(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
