package shared;

import javax.swing.*;



import java.awt.*;
import java.net.InetAddress;
import java.time.format.DateTimeFormatter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;

public class Vista_server extends JFrame{
	private javax.swing.JPanel jPanel1;
	JTextArea textAreaMonitores = new JTextArea();
	JTextArea textAreaAppLocal = new JTextArea();;
	
	
	public Vista_server( ) {
		setTitle("Server");
		initComponents();
	}
	
	private void initComponents( ) {
		jPanel1 = new javax.swing.JPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// JPANEL PRINCIPAL
        jPanel1.setSize(new Dimension(700, 500));
        jPanel1.setPreferredSize(new Dimension(700, 500));
        jPanel1.setBounds(new Rectangle(0, 0, 700, 500));
        jPanel1.setMinimumSize(new Dimension(700, 500));
        jPanel1.setLayout(null);

        
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);
        
        JLabel lblMonitores = new JLabel("Monitores Conectados");
        lblMonitores.setBounds(91, 51, 188, 13);
        jPanel1.add(lblMonitores);
        
        JLabel lblAppLocal = new JLabel("Aplicaciones Locales esperando atenci\u00F3n");
        lblAppLocal.setBounds(413, 45, 361, 25);
        jPanel1.add(lblAppLocal);
        
//        JTextArea textAreaMonitores = new JTextArea();
        textAreaMonitores.setBounds(30, 80, 300, 300);
        jPanel1.add(textAreaMonitores);
        
//        JTextArea textAreaAppLocal = new JTextArea();
        textAreaAppLocal.setBounds(370, 80, 300, 300);
        jPanel1.add(textAreaAppLocal);
        pack();
        this.setVisible(true);
	}
	
	public void writeOnTxtAreaMonitores(String txt) {
		textAreaMonitores.append(txt);
		textAreaMonitores.append("--------------------------------------------------------------\n");
	}
	
	public void writeOnTxtAreaAppLocal(String txt) {
		textAreaAppLocal.append(txt);
		textAreaAppLocal.append("--------------------------------------------------------------\n");
	}
}
