package modelo_app_monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import vista_app_monitor.IVista;
import vista_app_monitor.Vista;

public class App_monitor {
	private IVista vista;
	
	//private Receptor receptor;
	
	public App_monitor(String puerto)
	{
		
	}
	
	public static void main(String[] args) throws IOException {
		
		Receptor receptor = new Receptor();
		receptor.setVista(new Vista());
		

	}

	
	
	

}
