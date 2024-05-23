package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageQuimestre {
    QUIMESTRE_UNIQUE("Ya existe este quimestre, intente otro nombre", 403);

    private String mensaje;
    private Integer code;

    MessageQuimestre(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
