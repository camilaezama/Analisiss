package shared;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Notificacion implements Serializable{
	private final String tipo;
    private final String ubicacion;
    private final String mensaje;
    private final LocalDateTime time;
    
    public Notificacion(String tipo, String ubicacion, String mensaje, LocalDateTime time) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.mensaje = mensaje;
        this.time = time;
    }
	
    // ESTOS GETTERS SE UTILIZAN DESDE LA VISTA
    public String getTipo() {
    	return this.tipo;
    }
    
    public String getUbicacion() {
        return this.ubicacion;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public LocalDateTime getTime() {
        return this.time;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Notificacion that = (Notificacion) obj;
        return Objects.equals(this.tipo, that.tipo) &&
                Objects.equals(this.ubicacion, that.ubicacion) &&
                Objects.equals(this.mensaje, that.mensaje) &&
                Objects.equals(this.time, that.time);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(tipo, ubicacion, mensaje, time);
    }
    
    @Override
    public String toString() {
        return "Notificacion[" +
                "tipo=" + this.tipo + ", " +
                "ubicacion=" + this.ubicacion + ", " +
                "mensaje=" + this.mensaje + ", " +
                "time=" + this.time + ']';
    }


}
