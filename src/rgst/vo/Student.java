package rgst.vo;

public class Student {
	protected String stu_code;
	protected String stu_name;
	protected int credit;
	protected String password;
	
	public String getStu_code() {
		return stu_code;
	}
	public Student setStu_code(String stu_code) {
		this.stu_code = stu_code;
		return this;
	}
	public String getStu_name() {
		return stu_name;
	}
	public Student setStu_name(String stu_name) {
		this.stu_name = stu_name;
		return this;
	}
	public int getCredit() {
		return credit;
	}
	public Student setCredit(int credit) {
		this.credit = credit;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Student setPassword(String password) {
		this.password = password;
		return this;
	}
}
