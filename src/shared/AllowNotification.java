package shared;

import java.time.LocalDateTime;
import shared.Notificacion;

public class AllowNotification extends Notificacion{
	private final Boolean[] allowTipo;
	private final Integer port_monitor;
	private final String host_monitor;
	private final Integer port_app_local;
	private final String host_app_local;
	private final boolean fromMonitor;
	//private final String tipo;
    //private final String ubicacion;
    //private final String mensaje;
    //private final LocalDateTime time;
	
	
	// constructor completo --> no deberia utilizarse
	public AllowNotification(String tipo, String ubicacion, String mensaje, LocalDateTime time, Boolean[] allowTipo, 
			Integer port_monitor, String host_monitor, Integer port_app_local, String host_app_local,boolean fromMonitor) {
		super(tipo, ubicacion, mensaje, time);
		this.allowTipo = allowTipo;
		this.port_monitor = port_monitor;
		this.host_monitor = host_monitor;
		this.port_app_local = port_app_local;
		this.host_app_local = host_app_local;
		this.fromMonitor = fromMonitor;
	}
	
//	
//	// constructor para seteo de mensajes permitidos desde Monitor
//	public AllowNotification(Boolean[] allowTipo, Integer port_monitor, String host_monitor, boolean fromMonitor) {
//		super(null, null, null, null);
//		this.allowTipo = allowTipo;
//		this.port_monitor = port_monitor;
//		this.host_monitor = host_monitor;
//		this.fromMonitor = true;
//		this.port_app_local = null;
//		this.host_app_local = null;
//	}
	
	// constructor para envio de mensajes desde app local hacia app_Monitor
	public AllowNotification(String tipo, String ubicacion, String mensaje, LocalDateTime time, Integer server_port, String server_host, Integer app_local_port, String app_local_host, boolean fromMonitor, Boolean[] allowNotif) {
		super(tipo, ubicacion, mensaje, time);
		if (fromMonitor==true) {
			this.fromMonitor = true;
			this.allowTipo = allowNotif;
			this.port_monitor = app_local_port;
			this.host_monitor = app_local_host;
			this.port_app_local = app_local_port;
			this.host_app_local = app_local_host;
		}
		else {
			this.fromMonitor = false;
			this.allowTipo = null;
			this.port_monitor = server_port;
			this.host_monitor = server_host;
			this.port_app_local = app_local_port;
			this.host_app_local = app_local_host;
		}
		
		
		
	}
	
	public String getHostMonitor()	{
		return this.host_monitor;
	}
	
	public Integer getPortMonitor( ) {
		return this.port_monitor;
	}
	
	public String getHostAppLocal()	{
		return this.host_app_local;
	}
	
	public Integer getPortAppLocal( ) {
		return this.port_app_local;
	}
	
	public Boolean[] getAllowTipo() {
		return this.allowTipo;
	}
	
	public boolean getFromMonitor() {
		return this.fromMonitor;
	}
	
    
}
