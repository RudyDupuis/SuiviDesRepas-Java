package fr.eni.suividesrepas.bll;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.suividesrepas.bo.Aliment;
import fr.eni.suividesrepas.bo.Repas;
import fr.eni.suividesrepas.dal.DAO;
import fr.eni.suividesrepas.dal.DAOFactory;
import fr.eni.suividesrepas.message.CodeErreur;
import fr.eni.suividesrepas.message.LecteurMessage;

public class RepasManager {
	DAO<Repas> dao = DAOFactory.getRepasDAO();
	
	private void checkUserData(Repas r) throws Exception {
		StringBuilder errorMessage = new StringBuilder();
		boolean hasError = false;
		
		// check if attribute is null
		if(r.getrDate() == null || r.getrTime() == null || r.getAliments() == null) {
			errorMessage.append(LecteurMessage.getMessageErreur(CodeErreur.ERROR_EMPTY_FIELD));
			hasError = true;
		}
		
		// check aliments
		for(Aliment a : r.getAliments()) {
			if(a.getAliment().isEmpty() || a.getAliment().isBlank()) {
				errorMessage.append(LecteurMessage.getMessageErreur(CodeErreur.ERROR_EMPTY_ALIMENT));
				hasError = true;
				break;
			}
		}
		
		Pattern pattern = Pattern.compile("^[\\p{L}]+$");
		
		for (Aliment a : r.getAliments()) {
		    String alimentName = a.getAliment();
		    Matcher matcher = pattern.matcher(alimentName);
		    
		    if (!matcher.matches()) {
		        errorMessage.append(LecteurMessage.getMessageErreur(CodeErreur.ERROR_ONLY_LETTER));
		        hasError = true;
		        break;
		    }
		}
		
		if(hasError) {
			throw new Exception(errorMessage.toString());
		}
	}
		
		
		public List<Repas> getRepas() throws Exception{
			return dao.selectAll();
		}
		
		public void addRepas(Repas r) throws Exception{
			checkUserData(r);
			
			dao.insert(r);
		}
		
				

}
