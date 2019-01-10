package model;
import java.io.Serializable;

public class Ctps extends Documento implements Serializable {
	
	private String number, serie;
	
	public Ctps() {
		super();
		this.number = null;
		this.serie = null;
	}
	
	
	
	public Ctps(char type, int user_id, String fullname, String cpf, String birth_date, int document_id,
			String emission_at, String city, String uf, String number, String serie, String validate_code) {
		super(type, user_id, fullname, cpf, birth_date, document_id, emission_at, city, uf, validate_code);
		this.number = number;
		this.serie = serie;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ctps other = (Ctps) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		return true;
	}
}
