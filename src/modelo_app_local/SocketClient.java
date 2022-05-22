package modelo_app_local;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Notificacion;

public class SocketClient {
	private final static int TIMEOUT = 10000;
	
	public static void sendMessage(String host, Integer port, Notificacion notificacion) throws IOException {
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(TIMEOUT);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.writeObject(notificacion);
        JOptionPane.showMessageDialog(null, in.readLine(), "App Local: Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);

        out.close();
        in.close();
        socket.close();
    }
	

}
