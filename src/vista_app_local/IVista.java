package vista_app_local;

import javax.swing.*;

public interface IVista {
	
    String getMessage();
    void setMessage(String msg);
    String getTipoNotificacion();
    void setTipoNotificacion(String tipoNotificacion);
    String getHost();
    String getPort();
    void disableButtonsBut(JButton btn);
    void enableAllButtons();
	void enableSendButton();
	void disableSendButton();
	void disableSetPortButton();
	void disableTextield();
	String getUbicacion();
}

