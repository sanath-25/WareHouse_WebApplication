package sample.com.trial1;

public class User {
	int userID;
	int role;
	private String username;
	private String password;
	//private int personalID;
	private String FirstName;
	private String LastName;
	private String Phone;
	private int RegionID; 
	
	public int getRegionID() {
		return RegionID;
	}
	public void setRegionID(int regionID) {
		RegionID = regionID;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	private String Email;

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

public User() {
	userID = 0;
	role = 0;
	username = "default";
	password = "default";
	//personalID = 0;

	}
public User(int u, int r, String un, String pw, int per) {
	userID = u;
	role = r;
	username = un;
	password = pw;
	//personalID = per;
}
public int getuserID() {
	return userID;
}
public int getrole() {
	return role;
}
public String getusername(){
	return username;
}
public String getpassword() {
	return password;
}


public void setuserID(int uid) {
	userID = uid;
}
public void setrole(int rol) {
	role = rol;
}
public void setUsername(String un) {
	username = un;
}
public void setPassword (String pw) {
	password = pw;
}

}