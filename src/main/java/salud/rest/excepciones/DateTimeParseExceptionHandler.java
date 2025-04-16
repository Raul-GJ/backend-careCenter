package salud.rest.excepciones;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DateTimeParseExceptionHandler {

	@ExceptionHandler(DateTimeParseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespuestaError handleDateTimeParseException(DateTimeParseException e) {
        return new RespuestaError("Bad Request", 
        		"La fecha o fechas introdicidas están mal formateadas "
        		+ "(Formato correcto: AAAA-MM-DD'T'HH:MM:SS)");
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
