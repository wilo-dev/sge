package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageParcial {
    PARCIAL_UNIQUE("Ya existe ese parcial, intente otro nombre", 403);

    private String mensaje;
    private Integer code;

    MessageParcial(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
