package fr.eni.suividesrepas.dal;

import fr.eni.suividesrepas.bo.Repas;

public class DAOFactory {
	public static DAO<Repas> getRepasDAO() {
		return new RepasDaoJdbcImpl();
	}
}
