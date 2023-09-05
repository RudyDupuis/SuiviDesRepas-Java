package fr.eni.suividesrepas.message;

public abstract class CodeErreur {
	//DAL
	
	//Error Connection
	public static final int ERROR_CONNECTION = 10000;
	//Error insert
	public static final int ERROR_INSERT= 10001;
	//Error select
	public static final int ERROR_SELECT = 10002;
	
	//BLL
	
	//Error Empty field 
	public static int ERROR_EMPTY_FIELD = 20000;
	//Error Empty aliment
	public static int ERROR_EMPTY_ALIMENT = 20001;
	//Error Only letter
	public static int ERROR_ONLY_LETTER = 20002;

}
