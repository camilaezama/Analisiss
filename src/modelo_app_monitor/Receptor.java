package modelo_app_monitor;

import shared.Notificacion;
import vista_app_monitor.IVista;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Receptor {

	private IVista vista;
	private Integer puerto;
	private final Logger log = Logger.getLogger("servidor.Servidor");
	
	public Receptor() {
		
	}
	
	
	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}
	
	
	public void run() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(this.puerto)) {
            	System.out.println("puerto de escucha= "+this.puerto);
                while (true) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    Notificacion notificacion = (Notificacion) in.readObject();
                    
                    

                    Toolkit.getDefaultToolkit().beep();
                    
                    this.vista.showNewNotification(notificacion, true);
                    /*
                    int res = JOptionPane.showConfirmDialog(null, notificacion.getMensaje(), notificacion.getTipo(), JOptionPane.YES_NO_OPTION);
                    if (res == 0) {
                        this.vista.showNewNotification(notificacion, true);
                        out.println("aceptado");
                    } else {
                        this.vista.showNewNotification(notificacion, false);
                        out.println("rechazado");
                    }*/
                    out.close();
                    in.close();
                }
            } catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
        
	}   
	public void setVista(IVista vista) {
		this.vista = vista;
		vista.addReceptor(this);
	}
}
