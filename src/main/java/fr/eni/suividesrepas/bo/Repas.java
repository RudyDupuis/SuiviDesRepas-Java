package fr.eni.suividesrepas.bo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Repas {
	private int id;
	private Date rDate;
	private Time rTime;
	private List<Aliment> aliments = new ArrayList<>(); 

	public Repas(int id, Date rDate, Time rTime) {
		this.id = id;
		this.rDate = rDate;
		this.rTime = rTime;
	}

	public Repas(Date rDate, Time rTime) {
		this.rDate = rDate;
		this.rTime = rTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public Time getrTime() {
		return rTime;
	}

	public void setrTime(Time rTime) {
		this.rTime = rTime;
	}
	
	public List<Aliment> getAliments() {
		return aliments;
	}
	
	public void addAliments(Aliment aliment) {
		this.aliments.add(aliment);
	}
	
}
