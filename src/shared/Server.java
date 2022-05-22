package shared;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import controlador_app_local.Controlador;
import modelo_app_local.SocketClient;
import vista_app_local.Vista;
import shared.Vista_server;

public class Server {
	private HashMap<Integer, Properties> preferencias = new HashMap<>();
	private ArrayList<HashMap<String,String>> monitoresConectados = new ArrayList<HashMap<String,String>>();
	private Integer puertoServer;
	public static Server instance;
	private final static int TIMEOUT = 10000;
	private Vista_server vista;
	
	
	// CONSTRUCTOR
	private Server()
	{
		this.puertoServer = 9999;
		vista = new Vista_server();
		
	}
	
	// SINGLETON
	public static Server getInstance() {
		if (Server.instance==null) {
			Server.instance = new Server();
		}
		return instance;
	}
	
	
	// MAIN FUNCTION PARA HACERLO CORRER
	public static void main(String[] args) throws IOException {
        //Vista_server vista = new Vista_server();        
		
        
//        vista.setVisible(true);
        
        Server server = new Server();
        server.vista.setVisible(true);
        server.run();
    }
	
	// --------------------------------------------------------------------------------------------------------
	
	// EL SERVER SE CONECTA A LOS MONITORES CUANDO DEBE ENTREGARLES LAS NOTIFICACIONES                                
	public static void sendMessage(String host, Integer port, Notificacion notificacion) throws IOException {
        //Socket socket = new Socket("localhost", 1234);
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(TIMEOUT);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.writeObject(notificacion);
        //JOptionPane.showMessageDialog(null, in.readLine(), "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

        out.close();
        in.close();
        socket.close();
    }
	
	
	
	// --------------------------------------------------------------------------------------------------------
	// EL SERVER ESCUCHA A TODAS LAS APLICACIONES LOCALES
	public void run() {
		
		System.out.println("Server corriendo!!");
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(this.puertoServer)) {
            	System.out.println("puerto de escucha de server= "+this.puertoServer);
                while (true) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    AllowNotification notificacion = (AllowNotification) in.readObject();

                    Toolkit.getDefaultToolkit().beep();
                    
                    boolean origen = notificacion.getFromMonitor();
                    
                    System.out.println("tipo notificacion" + notificacion.getTipo());
                    System.out.println("Mensaje notificacion" + notificacion.getMensaje());
                    System.out.println("Direccion IP de destino (monitor)"+notificacion.getHostMonitor());
                    System.out.println("Puerto de destino (monitor)" + notificacion.getPortMonitor());
                    
                    if (origen) {
                    	out.println("Configuracion de notificaciones permitidas exitosa");
                    }
                    else {
                    	out.println("Solicitud recibida por el server");	
                    }
                    
                    
                    if (origen) {
                    	vista.writeOnTxtAreaMonitores("Host del monitor: "+notificacion.getHostMonitor()+"\nPuerto del monitor: "+notificacion.getPortMonitor()+"\nSeguridad: "+notificacion.getAllowTipo()[0]+"\nAmbulancia: "+notificacion.getAllowTipo()[1]+"\nIncendio: "+notificacion.getAllowTipo()[2]+"\n");
                    	HashMap<String,String> mapa = new HashMap<String,String>();
                    	mapa.put("Host", notificacion.getHostMonitor().toString());
                    	mapa.put("Puerto", notificacion.getPortMonitor().toString());
                    	mapa.put("Seguridad", notificacion.getAllowTipo()[0].toString());
                    	mapa.put("Ambulancia", notificacion.getAllowTipo()[1].toString());
                    	mapa.put("Incendio", notificacion.getAllowTipo()[2].toString());
                    	monitoresConectados.add(mapa);
                    	System.out.println(mapa.toString());
                    } else {
                    	vista.writeOnTxtAreaAppLocal("Tipo de notificacion: "+notificacion.getTipo()+"\nUbicacion del Pedido: "+notificacion.getUbicacion()+"\n");
                    	System.out.println("envia a monitor");
                    	for(HashMap<String,String> monitor: monitoresConectados){
                    		if (monitor.get(notificacion.getTipo().toString()) == "true") {
                    			System.out.println("Tiene la notificacion");
                    			sendMessage(monitor.get("Host"),Integer.parseInt(monitor.get("Puerto")), notificacion);
                    		}
                    	};
                    	//sendMessage(notificacion.getHostMonitor(),notificacion.getPortMonitor(), notificacion);
                    }
                    
//                    if (notificacion.getFromMonitor()) {
//                    	// nos escribe el monitor para setear sus preferencias
//                    	Boolean[] allowTipo = notificacion.getAllowTipo();
//                    	String monitorHost = notificacion.getHostMonitor();
//                    	Integer monitorPort = notificacion.getPortMonitor();
//                    }
//                    int res = JOptionPane.showConfirmDialog(null, notificacion.getMensaje(), notificacion.getTipo(), JOptionPane.YES_NO_OPTION);
//                    if (res == 0) {
//                        this.vista.showNewNotific\nation(notificacion, true);
//                        out.println("aceptado");
//                    } else {
//                        this.vista.showNewNotification(notificacion, false);
//                        out.println("rechazado");
//                    }
                    
                    out.println("checkpoint");
                    out.close();
                    in.close();
                }
            } catch (IOException e) {
                //log.log(Level.SEVERE, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
        
	} 

}


