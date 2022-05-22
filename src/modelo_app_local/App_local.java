package modelo_app_local;

import java.io.IOException;

import controlador_app_local.Controlador;
import vista_app_local.Vista;


public class App_local {

	public static void main(String[] args) throws IOException {
        Vista vista = new Vista();        
        Controlador.getInstance().setVista(vista);
        Controlador.getInstance().readConfig();
        vista.setVisible(true);
    }
}
