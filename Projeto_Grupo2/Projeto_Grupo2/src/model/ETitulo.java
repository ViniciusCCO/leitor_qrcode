package model;
import java.io.Serializable;

public class ETitulo extends Documento implements Serializable {
	private int    zone;
	private String register, section;

	public ETitulo() {
		super();
		this.zone = -1;
		this.register = null;
		this.section = null;
	}
	
	public ETitulo(char type, int user_id, String fullname, String cpf, String birth_date, int document_id,
			String emission_at, String city, String uf, int zone, String register, String section, String validate_code) {
		super(type, user_id, fullname, cpf, birth_date, document_id, emission_at, city, uf, validate_code);
		this.zone = zone;
		this.register = register;
		this.section = section;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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
		ETitulo other = (ETitulo) obj;
		if (register == null) {
			if (other.register != null)
				return false;
		} else if (!register.equals(other.register))
			return false;
		return true;
	}
}
