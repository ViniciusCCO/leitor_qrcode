package model;
import java.io.Serializable;

import com.mysql.jdbc.Blob;

public abstract class Documento implements Serializable {
	
	private int    user_id, document_id;
	private char   type;
	private String fullname, cpf, birth_date, emission_at, city, uf, validate_code;
	private Blob   biometry;

	public Documento() {
		setType(' ');
		setUser_id(-1);
		setFullname(null);
		setCpf(null);
		setBirth_date(null);
		setDocument_id(-1);
		setEmission_at(null);
		setCity(null);
		setUf(null);
		setValidate_code(null);
	}

	public Documento(char type, int user_id, String fullname, String cpf, String birth_date, 
			int document_id, String emission_at, String city, String uf, String validate_code) {
		this.type = type;
		this.user_id = user_id;
		this.fullname = fullname;
		this.cpf = cpf;
		this.birth_date = birth_date;
		this.document_id = document_id;
		this.emission_at = emission_at;
		this.city = city;
		this.uf = uf;
		this.validate_code = validate_code;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	
	public int getDocument_id() {
		return document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}

	public String getEmission_at() {
		return emission_at;
	}

	public void setEmission_at(String emission_at) {
		this.emission_at = emission_at;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Blob getBiometry() {
		return biometry;
	}

	public void setBiometry(Blob biometry) {
		this.biometry = biometry;
	}
	
	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	@Override
	public String toString() {
		return "Documento [user_id=" + user_id + ", document_id=" + document_id + ", type=" + type + ", fullname="
				+ fullname + ", cpf=" + cpf + ", birth_date=" + birth_date + ", emission_at=" + emission_at + ", city="
				+ city + ", uf=" + uf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + document_id;
		result = prime * result + type;
		result = prime * result + user_id;
		result = prime * result + ((validate_code == null) ? 0 : validate_code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (document_id != other.document_id)
			return false;
		if (type != other.type)
			return false;
		if (user_id != other.user_id)
			return false;
		if (validate_code == null) {
			if (other.validate_code != null)
				return false;
		} else if (!validate_code.equals(other.validate_code))
			return false;
		return true;
	}
}
