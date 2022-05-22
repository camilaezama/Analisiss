package modelo_app_monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import modelo_app_local.SocketClient;
import shared.AllowNotification;
import shared.Notificacion;

public class EmisorPreferencias {

	private final static int TIMEOUT = 10000;
	private String host_server;
	private Integer port_server;
	private String host_monitor;
	private Integer port_monitor;
	private Boolean[] allowTipo;
	
	public EmisorPreferencias(String host_server, Integer port_server, String host_monitor, Integer port_monitor, Boolean[] allowTipo) throws IOException {
        this.host_server = host_server;
        this.host_monitor = host_monitor;
        this.port_server = port_server;
        this.port_monitor = port_monitor;
        this.allowTipo = allowTipo;
		
	}
	
	public  void sendMessage(AllowNotification mensaje) throws IOException {
        Socket socket = new Socket(this.host_server, this.port_server);
        socket.setSoTimeout(TIMEOUT);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        out.writeObject(mensaje);
        //JOptionPane.showMessageDialog(null, in.readLine(), "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, in.readLine(), "App Monitor: Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        out.close();
        in.close();
        socket.close();
    }
	
public void enviarMensaje(String serverHost, Integer serverPort, String monitorHost, Integer monitorPort, Boolean[] notif, boolean fromMonitor) {
		
		// Preparamos el mensaje para enviar
		String tipo = null;
		String ubicacion = null;
		String mensaje = null;
		Integer portServer = serverPort;
		String hostServer = serverHost;
		Integer localPort = monitorPort;
		String localHost = monitorHost;
		Boolean[] allowTipo = notif;
		
		
		AllowNotification notificacion = new AllowNotification(tipo, ubicacion, mensaje, LocalDateTime.now(), serverPort, serverHost, localPort,localHost, true, notif);
		
		System.out.println("ubicacion "+ubicacion);
		System.out.println("server host "+hostServer);
		System.out.println("server port "+portServer);
		
		try {
			this.sendMessage(notificacion);
			
		} catch (Exception e) {
            
			
			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje - Servidor Desconectado", "App Monitor Msg Error", JOptionPane.PLAIN_MESSAGE);
			
            e.printStackTrace();
        }
	
	}
	


}
