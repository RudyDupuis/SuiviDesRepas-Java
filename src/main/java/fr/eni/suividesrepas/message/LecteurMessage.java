package fr.eni.suividesrepas.message;

import java.util.ResourceBundle;

public abstract class LecteurMessage {
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("fr.eni.suividesrepas.message.messages_erreurs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageErreur(int Code ) {
		String message = "";
		
		try {
			if(rb!=null) {
				message = rb.getString(String.valueOf(Code));
			} else {
				message = "Problème à la lecture du fichier contenant les messages";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message= "Une erreur inconnue s'est produite";
		}
		
		return message;
	}

}
