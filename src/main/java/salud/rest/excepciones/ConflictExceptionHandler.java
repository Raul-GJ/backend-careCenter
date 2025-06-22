package salud.rest.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ConflictExceptionHandler {

	@ExceptionHandler(ConflictException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public RespuestaError handleConflictException(ConflictException e) {
        return new RespuestaError("Conflict", e.getMessage());
    }

    public static class RespuestaError {
        private String estado;
        private String mensaje;

        public RespuestaError(String estado, String mensaje) {
            this.estado = estado;
            this.mensaje = mensaje;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

    }
}
