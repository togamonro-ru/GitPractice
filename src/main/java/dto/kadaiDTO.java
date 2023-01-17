package dto;

public class kadaiDTO {
	private int id;
	private String name;
	private String gender;
	private int age;
	private String tell;
	private String mail;
	private String salt;
	private String password;
	private String hashedPassword;
	
	public kadaiDTO(int id, String name, String gender, int age, String tell, String mail, String salt, String password,
			String hashedPassword) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.tell = tell;
		this.mail = mail;
		this.salt = salt;
		this.password = password;
		this.hashedPassword = hashedPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	
}
