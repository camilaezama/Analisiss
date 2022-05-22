package vista_app_local;

import javax.swing.*;

import controlador_app_local.Controlador;

import java.awt.*;
import java.io.File;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame implements IVista{

	private final JButton[] btnPress = new JButton[3];
    private String tipoNotificacion = "";
    private javax.swing.JButton jButton1;
    private javax.swing.JButton btnSetPortIP;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textFieldHost;
    private javax.swing.JTextField textFieldPort;
    private JPanel panel;
    private JTextArea textAreaMensaje;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField txtOficina;

    public Vista() {
    	setBackground(Color.LIGHT_GRAY);
    	setTitle("App Local");
        initComponents();
    }

    private void initComponents() {
    	jButton1 = new javax.swing.JButton();
    	btnSetPortIP = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        jPanel1.setBackground(Color.PINK);
        textFieldHost = new javax.swing.JTextField();
        textFieldPort = new javax.swing.JTextField();
        jLabelHost = new javax.swing.JLabel();
        jLabelPort = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Enviar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(Controlador.getInstance());
        
        panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBackground(Color.PINK);
        panel.setSize(new Dimension(100, 600));
        panel.setPreferredSize(new Dimension(650, 450));
        panel.setBounds(new Rectangle(0, 0, 1000, 450));
        panel.setMinimumSize(new Dimension(1000, 450));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        textAreaMensaje = new JTextArea();
        textAreaMensaje.setBounds(50, 300, 550, 130);
        textAreaMensaje.setRows(30);
        textAreaMensaje.setColumns(90);
        panel.add(textAreaMensaje);
        
     // BOTON SOLICITUD DE SEGURIDAD
        JButton btnSeguridad = new JButton("");
        btnSeguridad.setIcon(new ImageIcon("src" + File.separator+"images"+File.separator+"policiaCuadroMini.png"));
        btnSeguridad.setMaximumSize(new Dimension(28, 7));
        btnSeguridad.setBounds(250, 40, 150, 200);
        panel.add(btnSeguridad);
        btnSeguridad.setActionCommand("Seguridad");
        btnSeguridad.addActionListener(Controlador.getInstance());

        // BOTON SOLICITUD DE AMBULANCIA
        JButton btnAmbulancia = new JButton("");
        btnAmbulancia.setIcon(new ImageIcon("src" + File.separator+"images"+File.separator+"firstAidCuadroMini.png"));
        btnAmbulancia.setBounds(50, 40, 150, 200);
        panel.add(btnAmbulancia);
        btnAmbulancia.setActionCommand("Ambulancia");
        btnAmbulancia.addActionListener(Controlador.getInstance());

        // LABEL DE MENSAJE ADICIONAL
        JLabel lblNewLabel = new JLabel("Mensaje adicional:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(55, 270, 139, 27);
        panel.add(lblNewLabel);

        // BOTON AVISO DE INCENDIO
        JButton btnIncendio = new JButton("");
        btnIncendio.setIcon(new ImageIcon("src" + File.separator+"images"+File.separator+"fuegoCuadroMini.png"));
        btnIncendio.setBounds(450, 40, 150, 200);
        panel.add(btnIncendio);
        btnIncendio.setActionCommand("Incendio");
        btnIncendio.addActionListener(Controlador.getInstance());
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);
        
        // ARRAY DE LOS BOTONES PARA HABILITARLOS O DESHABILITARLOS A GUSTO
        btnPress[0] = btnIncendio;
        btnPress[1] = btnAmbulancia;
        btnPress[2] = btnSeguridad;
        
        this.disableAllButtons();
        
        // TEXTFIELD QUE CONTIENE LA DIRECCION IP -- > BLOQUEAR CUANDO SE LEA DESDE ARCHIVO DE CONFIG.
        textFieldHost.setText("localhost");
        textFieldHost.setEnabled(true);
        
        // TEXTFIELD PARA INDICAR EL PUERTO HACIA DONDE SE ENVIA LA SOLICITUD
        textFieldPort.setText("9999");
        textFieldPort.setEnabled(true);
        
        // LABEL PARA JTEXTFIELDHOST, QUE CONTIENE LA DIRECCION IP
        jLabelHost.setText("IP Server:");

        // LABEL PARA JTEXTFIELDPORT, QUE CONTIENE EL PUERTO
        jLabelPort.setText("Puerto server:");
        
        
        // BOTON PARA SETEAR PUERTO Y DIRECION IP
        //JButton btnSetPortIP = new JButton("Setear puerto & direccion IP");
        btnSetPortIP.setText("Setear puerto & direccion IP");
        btnSetPortIP.setActionCommand("setPort");
        btnSetPortIP.addActionListener(Controlador.getInstance());
        
        lblNewLabel_1 = new JLabel("Panel de Configuracion");
        
        lblNewLabel_2 = new JLabel("Ubicaci\u00F3n Equipo:");
        
        txtOficina = new JTextField();
        txtOficina.setText("Oficina 1 - PB");
        txtOficina.setColumns(10);
        
        // LAYOUT
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabelHost)
        					.addGap(2)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(textFieldHost, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
        							.addGap(37)
        							.addComponent(jLabelPort)
        							.addGap(18)
        							.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        							.addGap(51)
        							.addComponent(btnSetPortIP)))
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtOficina, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(6)
        			.addComponent(lblNewLabel_1)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabelHost)
        				.addComponent(jLabelPort)
        				.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSetPortIP))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_2)
        				.addComponent(txtOficina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);
        pack();


    }
    
    @Override
    public void disableButtonsBut(JButton btn) {
        for (int i = 0; i < 3; i++) {
            if (!this.btnPress[i].equals(btn)) {
                btnPress[i].setEnabled(false);
            }
        }
    }
    
    public void disableAllButtons() {
    	for (int i=0; i<3; i++) {
    		btnPress[i].setEnabled(false);
    	}
    	jButton1.setEnabled(false);
    }

    @Override
    public void enableAllButtons() {
        for (int i = 0; i < 3; i++) {
            btnPress[i].setEnabled(true);
        }
        jButton1.setEnabled(true);
    }


    @Override
    public String getMessage() {
        return textAreaMensaje.getText();
    }

    @Override
    public void setMessage(String msg) {
        this.textAreaMensaje.setText(msg);
    }

    @Override
    public String getTipoNotificacion() {
        return this.tipoNotificacion;
    }

    @Override
    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
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
    public String getUbicacion() {
    	return this.txtOficina.getText();
    }
    
    
    public void enableSendButton() {
    	jButton1.setEnabled(true);
    }
    
    public void disableSendButton() {
    	jButton1.setEnabled(false);
    }
    
    public void disableSetPortButton() {
    	btnSetPortIP.setEnabled(false);
    }
    
    public void disableTextield( ) {
    	textFieldHost.setEnabled(false);
    	textFieldPort.setEnabled(false);
    	txtOficina.setEnabled(false);
    }
    
    public void enableTextield( ) {
    	textFieldHost.setEnabled(true);
    	textFieldPort.setEnabled(true);
    }
}
