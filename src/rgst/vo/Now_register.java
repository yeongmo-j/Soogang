package rgst.vo;

public class Now_register {
	protected String stu_code;
	protected String sub_code;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stu_code == null) ? 0 : stu_code.hashCode());
		result = prime * result + ((sub_code == null) ? 0 : sub_code.hashCode());
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
		Now_register other = (Now_register) obj;
		if (stu_code == null) {
			if (other.stu_code != null)
				return false;
		} else if (!stu_code.equals(other.stu_code))
			return false;
		if (sub_code == null) {
			if (other.sub_code != null)
				return false;
		} else if (!sub_code.equals(other.sub_code))
			return false;
		return true;
	}
	
	public String getStu_code() {
		return stu_code;
	}
	public Now_register setStu_code(String stu_code) {
		this.stu_code = stu_code;
		return this;
	}
	public String getSub_code() {
		return sub_code;
	}
	public Now_register setSub_code(String sub_code) {
		this.sub_code = sub_code;
		return this;
	}	
}
