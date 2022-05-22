package vista_app_monitor;

import modelo_app_monitor.Receptor;
import shared.Notificacion;

public interface IVista {

	String getHost();
    String getPort();
    void showNewNotification(Notificacion notificacion, Boolean accepted);
	void addReceptor(Receptor receptor);
	Boolean[] getAceptados();
	
}
