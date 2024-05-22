package dofi.sge.util.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {
    OK("Respuesta exitosa.", 200),
    DELETE("Eliminación exitosa.", 200),
    UPDATE("Datos actualizados", 200),
    CREATE("Creación exitosa", 201),
    SYNTAX_JSON_INVALID("Error en la sintaxis del JSON enviado", 400),
    NOT_FOUND("No se encontraron resultados", 404),
    IS_EMPTY("No hay datos por el momento", 422),
    NO_Empty_fields("No se permite campos vacíos", 422),

    CORREO_NO_VALIDO("Ingrese un correo válido.", 403),
    USERNAME_UNICO("Ya existe ese usuario, ingrese otro", 403),
    CORREO_UNICO("Correo ya existe, intente otro", 403),
    UPPER_CASE("Ingrese el Nombre en mayúscula.", 403),
    NOT_ENCRYPT("Hubo un conflicto", 409),
    INTERNAL_ERROR("Problema en la transacción", 500);

    private String mensaje;
    private Integer code;

    MessageEnum(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
