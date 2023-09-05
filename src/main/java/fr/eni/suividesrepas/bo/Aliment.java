package fr.eni.suividesrepas.bo;

public class Aliment {
	private int id;
	private String aliment;
	private int idRepas;

	public Aliment(String aliment) {
		this.aliment = aliment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAliment() {
		return aliment;
	}

	public void setAliment(String aliment) {
		this.aliment = aliment;
	}

	public int getIdRepas() {
		return idRepas;
	}

	public void setIdRepas(int idRepas) {
		this.idRepas = idRepas;
	}
}
