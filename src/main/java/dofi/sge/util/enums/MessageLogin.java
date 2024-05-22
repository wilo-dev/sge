package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageLogin {
    LOGIN_ERROR("Usuario o clave incorrecto", 404),
    NOT_STRONG_PASS("Su contraseña no es segura", 403);

    private String mensaje;
    private Integer code;

    MessageLogin(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
