package fr.eni.suividesrepas.dal;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	public void insert(T t) throws SQLException;
	public List<T> selectAll() throws SQLException;
}
