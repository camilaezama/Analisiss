package vista_app_monitor;
import shared.Notificacion;
import modelo_app_monitor.EmisorPreferencias;

import javax.swing.*;

import modelo_app_monitor.Receptor;

import java.awt.*;
import java.net.InetAddress;
import java.time.format.DateTimeFormatter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Vista extends JFrame implements IVista{
	
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private JTextArea textAreaMensajes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textFieldHost;
    private javax.swing.JTextField textFieldPort;
	private Receptor receptor;
	private JButton btnSetPref;
	private JCheckBox[] checkboxes = new JCheckBox[3];
	private Boolean[] aceptados = new Boolean[3];
	
    public Vista() {
    	setTitle("App Monitor");
    	setAlwaysOnTop(true);
        initComponents();
    }
    
    private void initComponents() {
    
    	jPanel1 = new javax.swing.JPanel();
        textFieldHost = new javax.swing.JTextField();
        textFieldPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton1.setEnabled(true);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        try {
            textFieldHost.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // JPANEL PRINCIPAL
        jPanel1.setSize(new Dimension(500, 500));
        jPanel1.setPreferredSize(new Dimension(500, 500));
        jPanel1.setBounds(new Rectangle(0, 0, 500, 500));
        jPanel1.setMinimumSize(new Dimension(500, 500));
        jPanel1.setLayout(null);

        // JTEXTAREA PARA RECIBIR MENSAJES
        textAreaMensajes = new JTextArea();
        textAreaMensajes.setBounds(50, 100, 400, 300);
        textAreaMensajes.setRows(30);
        textAreaMensajes.setColumns(50);
        jPanel1.add(textAreaMensajes);
        
        // JTEXTFIELD DIRECCION IP DE RECEPTOR
        // textFieldHost.setText("fromConfig");
        // textFieldHost.setEnabled(false);
        textFieldHost.setText("localhost");
        textFieldHost.setEnabled(true);

        // JTEXT FIELD DEL PUERTO DE ESCUCHA
        textFieldPort.setText("1234");
        textFieldPort.setEnabled(true);
        
        // JLABEL PARA JTEXTFIELD1
        jLabel1.setText("IP:");

        // JLABEL PARA JTEXTFIELD2
        jLabel2.setText("Puerto:");
        
        // JBUTTON PARA INICIAR ESCUCHA
        jButton1.setText("Escuchar");
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        JLabel lblNewLabel = new JLabel("Tipo de Mensajes que acepta:");
        
        JCheckBox chkSeguridad = new JCheckBox("Seguridad");
        chkSeguridad.setSelected(true);
        checkboxes[0] = chkSeguridad;
        
        JCheckBox chkAmbulancia = new JCheckBox("Ambulancia");
        chkAmbulancia.setSelected(true);
        checkboxes[1] = chkAmbulancia;
        
        JCheckBox chkIncendio = new JCheckBox("Incendio");
        chkIncendio.setSelected(true);
        checkboxes[2] = chkIncendio;
        
        btnSetPref = new JButton("Setear Preferencias");
        btnSetPref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnSetPrefActionPerformed(evt);
            }});
        
        
        //LAYOUT
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(43)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(textFieldHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(19)
        							.addComponent(jLabel2)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(btnSetPref))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addComponent(chkSeguridad, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(chkAmbulancia, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(chkIncendio, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
        								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
        							.addGap(77))))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(53)
        					.addComponent(jButton1)))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(textFieldHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel2)
        				.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSetPref))
        			.addGap(14)
        			.addComponent(lblNewLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(chkSeguridad)
        				.addComponent(chkAmbulancia)
        				.addComponent(chkIncendio))
        			.addPreferredGap(ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
        			.addComponent(jButton1)
        			.addGap(51))
        );
        jPanel1.setLayout(jPanel1Layout);
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);
        pack();
        this.setVisible(true);
    }
    
    // ACCION DEL BOTON
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.receptor.setPuerto(Integer.parseInt(this.getPort()));
    	this.receptor.run();
    	jButton1.setEnabled(false);
    	textAreaMensajes.append("Escuchando mensajes en el puerto "+this.getPort()+"\n");
    	// enviar opciones elegidas al server - TODO
    }
    
    private void btnSetPrefActionPerformed(java.awt.event.ActionEvent evt) {
    	Boolean[] auxiliar = new Boolean[3];
    	auxiliar[0] = checkboxes[0].isSelected();
    	auxiliar[1] = checkboxes[1].isSelected();
    	auxiliar[2] = checkboxes[2].isSelected();
    	this.aceptados = auxiliar;
    	System.out.print("acepta seguridad? --> ");
    	System.out.println(auxiliar[0]);
    	System.out.print("acepta ambulancia? --> ");
    	System.out.println(auxiliar[1]);
    	System.out.print("acepta incendio? --> ");
    	System.out.println(auxiliar[2]);
    	String serverHost = "localhost";
    	Integer serverPort = 9999;
    	String monitorHost = this.getHost();
    	Integer monitorPort = Integer.parseInt(this.getPort());
    	EmisorPreferencias preferencias;
		try {
			preferencias = new EmisorPreferencias(serverHost, serverPort, monitorHost, monitorPort ,auxiliar);
			preferencias.enviarMensaje(serverHost,serverPort, monitorHost, monitorPort, auxiliar, true);
			this.disableTextField();
			this.disableCheckBoxes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void disableCheckBoxes() {
    	for (int i=0; i<3; i++) {
    		checkboxes[i].setEnabled(false);
    	} 
    }
    
    public void disableTextField() {
    	this.textFieldHost.setEnabled(false);
    	this.textFieldPort.setEnabled(false);
    		
    }
    
    
    @Override
    public Boolean[] getAceptados( ) {
    	return this.aceptados;
    }
    
    
	@Override
	public String getHost() {		
		return this.textFieldHost.getText();		
	}

	@Override
	public String getPort() {
		return this.textFieldPort.getText();
	}

	@Override
	public void showNewNotification(Notificacion notificacion, Boolean accepted) {
		StringBuilder sb = new StringBuilder();

        textAreaMensajes.append("----------------------------------------------------------------\n");
        sb.append("Time: ").append(notificacion.getTime().format(fmt)).append("\n");
        sb.append("Tipo: ").append(notificacion.getTipo()).append("\n");
        sb.append("Ubicacion: ").append(notificacion.getUbicacion()).append("\n");
        sb.append("Msg: ").append(notificacion.getMensaje()).append("\n");
        sb.append("Status: ").append(accepted ? "Aceptado" : "Rechazado").append("\n");
        textAreaMensajes.append(sb.toString());
        textAreaMensajes.append("----------------------------------------------------------------\n");    
		
	}
	
	public void addReceptor(Receptor receptor) {
		this.receptor = receptor;
	}
}
