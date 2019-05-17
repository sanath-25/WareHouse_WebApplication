package sample.com.trial1;

public class Customer {
	private int customerID;
	private String firstName;
	private String lastName;
	private String contactName;
	private int personalID;


public Customer() {
	customerID = 00;
	firstName = "default";
	lastName = "default";
	contactName = "default";

	}
public Customer(int id, String fn, String ln) {
	customerID = id;
	firstName = fn;
	lastName = ln;
}

public int getID() {
	return customerID;
}
public String getfirstName() {
	return firstName;
}
public String getlastName() {
	return lastName;
}
public String contactName() {
	return contactName;
}

public void setCustomerID(int cid) {
	customerID = cid;
}
public void setFirstname(String FN) {
	firstName = FN;
}
public void setLastName(String LN) {
	lastName = LN;
}
public void setContactName (String CN) {
	contactName = CN;
}
}

