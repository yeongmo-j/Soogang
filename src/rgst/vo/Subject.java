package rgst.vo;

public class Subject {
	protected String sub_code;
	protected String sub_name;
	protected String professor;
	protected String classroom;
	protected String pre;
	protected String time1;
	protected String time2;
	
	public String getSub_code() {
		return sub_code;
	}
	public Subject setSub_code(String sub_code) {
		this.sub_code = sub_code;
		return this;
	}
	public String getSub_name() {
		return sub_name;
	}
	public Subject setSub_name(String sub_name) {
		this.sub_name = sub_name;
		return this;
	}
	public String getProfessor() {
		return professor;
	}
	public Subject setProfessor(String professor) {
		this.professor = professor;
		return this;
	}
	public String getClassroom() {
		return classroom;
	}
	public Subject setClassroom(String classroom) {
		this.classroom = classroom;
		return this;
	}
	public String getPre() {
		return pre;
	}
	public Subject setPre(String pre) {
		this.pre = pre;
		return this;
	}
	public String getTime1() {
		return time1;
	}
	public Subject setTime1(String time1) {
		this.time1 = time1;
		return this;
	}
	public String getTime2() {
		return time2;
	}
	public Subject setTime2(String time2) {
		this.time2 = time2;
		return this;
	}
	
}
