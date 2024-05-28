package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageParalelo {
    PARALELO_UNIQUE("Ya existe ese paralelo, escriba otro", 403);

    private String mensaje;
    private Integer code;

    MessageParalelo(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
