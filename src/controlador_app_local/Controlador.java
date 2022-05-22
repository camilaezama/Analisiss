package controlador_app_local;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo_app_local.SocketClient;

import java.time.LocalDateTime;


import shared.Notificacion;
import shared.AllowNotification;
import vista_app_local.IVista;
import shared.AllowNotification;

public class Controlador implements ActionListener{
	
	private static Controlador instance;
	private Properties properties;
	private IVista vista;
	
	private Controlador() {
		
	}
	
	public static Controlador getInstance() {
		if (Controlador.instance == null) {
			Controlador.instance = new Controlador();
		}
		return instance;
		
	}
	
	public void setVista(IVista vista) {
		this.vista = vista;
	}
	
//	public void enviarMensaje(String mensaje) {
//		Notificacion notificacion = new Notificacion(this.vista.getTipoNotificacion(), this.properties.getProperty("ubicacion"), mensaje, LocalDateTime.now());
//		
//		Boolean[] auxiliar = new Boolean[3];
//		auxiliar[0]= true;
//		auxiliar[1]= false;
//		auxiliar[2]= true;
//		
//		// alowed_notif = (tipo_notificacion, lugar_notificacion, mensaje_adicional, fecha, notificaciones_permitidas, port_monitor, host_monitor, port_app_local,host_app_local, from_monitor
// 		AllowNotification allowed_notif = new AllowNotification(this.vista.getTipoNotificacion(), "Oficina 1", mensaje, LocalDateTime.now(), auxiliar, Integer.parseInt(this.properties.getProperty("port")), this.properties.getProperty("host"), 1111, "localhost",false);
//		System.out.println("properties.ubicacion "+this.properties.getProperty("ubicacion"));
//		System.out.println("properties.host "+this.properties.getProperty("host"));
//		System.out.println("properties.port "+this.properties.getProperty("port"));
//		
//		try {
//			SocketClient.sendMessage("localhost", 9999, allowed_notif);
//			this.vista.setMessage("");
//            this.vista.setTipoNotificacion("");
//            this.vista.enableAllButtons();
//		} catch (Exception e) {
//            
//			
//			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje - Servidor Desconectado", "App Local Msg Error", JOptionPane.PLAIN_MESSAGE);
//			
//            e.printStackTrace();
//        }
//		
////		try {
////			SocketClient.sendMessage(this.properties.getProperty("host"), Integer.parseInt(this.properties.getProperty("port")), notificacion);
////			this.vista.setMessage("");
////            this.vista.setTipoNotificacion("");
////            this.vista.enableAllButtons();
////		} catch (Exception e) {
////            
////			
////			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje - Servidor Desconectado", "App Local Msg Error", JOptionPane.PLAIN_MESSAGE);
////			
////            e.printStackTrace();
////        }
//	}
	
	public void enviarMensaje(String mensaje) {
		
		// Preparamos el mensaje para enviar
		String tipo = this.vista.getTipoNotificacion();
		String ubicacion = this.vista.getUbicacion();
		String hostServer = this.vista.getHost();
		Integer portServer = Integer.parseInt(this.vista.getPort());
		String localHost = "localhost";
		Integer localPort = 1122;
		
		AllowNotification notificacion = new AllowNotification(tipo, ubicacion, mensaje, LocalDateTime.now(), portServer, hostServer, localPort,localHost, false, null);
		
		System.out.println("ubicacion "+ubicacion);
		System.out.println("server host "+hostServer);
		System.out.println("server port "+portServer);
		
		try {
			SocketClient.sendMessage(hostServer, portServer, notificacion);
			this.vista.setMessage("");
            this.vista.setTipoNotificacion("");
            this.vista.enableAllButtons();
		} catch (Exception e) {
            
			
			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje - Servidor Desconectado", "App Local Msg Error", JOptionPane.PLAIN_MESSAGE);
			
            e.printStackTrace();
        }
		
//		try {
//			SocketClient.sendMessage(this.properties.getProperty("host"), Integer.parseInt(this.properties.getProperty("port")), notificacion);
//			this.vista.setMessage("");
//            this.vista.setTipoNotificacion("");
//            this.vista.enableAllButtons();
//		} catch (Exception e) {
//            
//			
//			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje - Servidor Desconectado", "App Local Msg Error", JOptionPane.PLAIN_MESSAGE);
//			
//            e.printStackTrace();
//        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Seguridad") || actionCommand.equals("Ambulancia") || actionCommand.equals("Incendio")) {
            this.vista.setTipoNotificacion(actionCommand);
            JButton btn = (JButton) e.getSource();
            this.vista.disableButtonsBut(btn);
        } else if (actionCommand.equals("setPort")) {
        	properties.setProperty("port", vista.getPort());
        	properties.setProperty("host", vista.getHost());
        	properties.setProperty("ubicacion", vista.getUbicacion());
        	this.vista.enableAllButtons();
        	this.vista.disableSetPortButton();
        	this.vista.disableTextield();
        } else {        	
            this.enviarMensaje(this.vista.getMessage());
        }		
	}
	
	 public void readConfig() throws IOException {
	       // String path = "emisor" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";
	        this.properties = new Properties();
	       // FileInputStream fileInputStream = new FileInputStream(path);
	        //this.properties.load(fileInputStream);
	        properties.put("port", this.vista.getPort());
	        properties.put("host",this.vista.getHost());
	        properties.put("ubicacion", this.vista.getUbicacion());
	    }

}
