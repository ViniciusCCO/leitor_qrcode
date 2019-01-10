package model;
import java.io.Serializable;

import com.mysql.jdbc.Blob;

public class Cnh extends Documento implements Serializable {
	
	private String register, category, due_date_at;
	
	public Cnh() {
		super();
		this.register = null;
		this.category = null;
		this.due_date_at = null;
	}
	
	public Cnh(char type, int user_id, String fullname, String cpf, String birth_date, int document_id, 
			String emission_at, String city, String uf, String register, String category, String due_date_at, String validate_code) {
		super(type, user_id, fullname, cpf, birth_date, document_id, emission_at, city, uf, validate_code);
		this.register = register;
		this.category = category;
		this.due_date_at = due_date_at;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date_at() {
		return due_date_at;
	}

	public void setDue_date_at(String due_date_at) {
		this.due_date_at = due_date_at;
	}	
	
	@Override
	public String toString() {
		return super.toString() + " Cnh [register=" + register + ", category=" + category + ", due_date_at=" + due_date_at + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((register == null) ? 0 : register.hashCode());
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
		Cnh other = (Cnh) obj;
		if (register == null) {
			if (other.register != null)
				return false;
		} else if (!register.equals(other.register))
			return false;
		return true;
	}
}
