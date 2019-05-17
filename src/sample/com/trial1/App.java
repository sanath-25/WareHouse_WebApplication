package sample.com.trial1;

import java.util.*;
import java.sql.*;
import java.sql.ResultSet;
public class App {

	public static void main(String[] args) {
		
		
		System.out.println("Welcome!");
		initialPrompt();	// Text prompt showing the user commands.
		HashMap<String, List<String>> hmap = null;
		User user = new User();
		Scanner in = new Scanner (System.in); 
		int usr_count =0;
		String cmd = "";	// Command character for knowing the user's choice.
		Boolean registered = false;
		Boolean signed = false;
		
		do {
			//Login & Register process
			cmd = in.nextLine();
			try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			Statement stmt = Conn.createStatement();
			if (cmd.equals("1")) {	
				in.close();
				Scanner addin =new Scanner (System.in);
				// User wishes to register. Ask for new email and a name.
				System.out.println("Please enter your username:");
				user.setUsername(addin.nextLine());
				System.out.println("Please enter your password:");
				user.setPassword(addin.nextLine());
				System.out.println("Please enter the type of business you wish to do:");
				System.out.println("type in yes for customer ");
				String input=addin.nextLine();
				if(input.equals("yes")) {
					user.setrole(2);	
				}
				//getting the user count to initiate the user id for the new customer
				try {
				ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) AS total FROM User");
			    while(rs3.next()){
				    usr_count = rs3.getInt("total")+1;
				}
			    user.setuserID(usr_count);
			    
			//	signed = registered = registerCustomer(user,usr_count,Conn);	// Handle the registration. Method returns true if the registration succeeded, meaning customer is signed in.
				//usr_count++;
				break;
				}
				catch (Exception e) {
					System.err.println("An error occurred: " + e.toString());
				}
				addin.close();
				break;
			} 
			else if (cmd.equals("2")) {	
				// User wishes to sign in. Ask for existing email.
				System.out.println("Please enter your username to sign in:");
				user.setUsername(in.nextLine());
				System.out.println("Please enter your password:");
				user.setPassword(in.nextLine());
				//signed  = signIn(user,Conn);	// Handle the sign in. Method returns true if sign in succeeded, meaning customer's email was found in the database.
				System.out.print(signed);
			} else if (cmd.equals("q")) {	// Handle the exit command q. Prints an exit message and breaks the loop.
				System.out.println("Command 'q' entered. Quitting the Application.");
				System.exit(0);
			} else {	// Handle incorrect commands. Complain that the command entered is not valid and loop.
				System.out.println("'"+ cmd + "'" + " is not a command.");
				initialPrompt();
			}
			
			}
			catch (Exception e) {
				System.err.println("An error occurred: " + e.toString());
			}
			
		} while (!cmd.equals("q") && !signed);
		//This loop is for prompting the user an option to change their email phone etc.
		boolean modified = false;
		System.out.println("Welcome! Thank you for signing in.");
		//Scanner in = new Scanner (System.in); 
		while (signed && !modified) {
			String d =" ";
			if(user.getrole()==1) {
				//Role =1 means that the user is warehouse
				System.out.println("Warehouse should never be signed in to the system");
				System.exit(0);
			}
			System.out.println("would you like to modify your personal info (Y/N)");
			d=in.nextLine();
			if (d.equals("Y")) {
				System.out.println(user.getpassword());
				ModifyPersonalInfo(user);
				modified=true;
				break;
			}
			else if (d.equals("N")) {
				modified=true;
				break;
			}
		}
		int warId =0;
		while (signed && modified) {
			//Successfully login and decided on whether to change the personal information or not
				System.out.println("You have signed in to the management interface");
				int rol = user.getrole();
				System.out.println("The role of the user is :"+rol);
				if(rol ==3||(rol==4&&warId!=0)) {
					//Warehouse manager or Admin who chose to act as a warehouse manager
					System.out.println("Welcome! Warehouse manager.");
					String inp=" ";
					System.out.println("Select an action by entering a displayed number, or enter 'q' to quit:\n1. Warehouse management.");
					inp=in.nextLine();
					if(inp.equals("1")) {
						//List all the product stored in the warehouse which the manager is responsible for
						ListProduct(user,warId);
					//	WarehouseReport(user,warId);
					}
					else if (inp.equals("q")) {	// Handle the exit command q. Prints an exit message and breaks the loop.
						System.out.println("Command 'q' entered. Quitting the Application.");
						System.exit(0);
					}
					if(rol==4) {
						//Admin logged in
						System.out.print("if you wish to return to previous interface, press q");
						String q=in.nextLine();
						if(q.equals("q")) {
							warId=0;
						}
					}
					
					
				}
				else if(rol == 2) {
					//Normal customer logged in
					//Scanner r2in = new Scanner(System.in);
					System.out.println("Welcome! Customer.");
					//String inp1=" ";
					System.out.println("Select an action by entering a displayed number, or enter 'q' to quit:\n1. Modify/Add your address.\n2. Submit product\n3. Show submitted product" );
						//boolean vending= true; &&vending
						String inp1 = in.nextLine();
						//if(inp1!=null) {
						if (inp1.equals("1")) {
							System.out.println("1.Add   2.Modify");
							String inp2=in.nextLine();
							
							if(inp2.equals("1")) {
							String cou=" ", Address=" ", City=" ", Zip=" ";
							System.out.println("Please enter your Country");
							cou = in.nextLine();
							System.out.println(cou);
							System.out.println("Please enter your Address:");
							Address = in.nextLine();
							System.out.println(Address);
							System.out.println("Please enter City:");
							City= in.nextLine();
							System.out.println("Please enter State:");
							String state= in.nextLine();
							System.out.println("Please enter your zip code:");
							Zip= in.nextLine();
							ModifyAddress(user,cou,Address,City,state,Zip);
							//vending=false;
							//break;
							}
							else if(inp2.equals("2")) {
							try {
								Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
								PreparedStatement sta = myConn.prepareStatement("SELECT * FROM Address WHERE UserID = ?");
								sta.setInt(1, user.getuserID());
								ResultSet rs= sta.executeQuery();
								ResultSetMetaData rsmd = rs.getMetaData();
								int index=0;
								while(rs.next()) {
									System.out.println("Would you like to change: ");
									for(int i =2; i<=6; i++) {
										System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
									}
									String input = in.nextLine();
									if(input.equals("yes")) {
										index = rs.getInt("AddressID");
										ChangeAddress(user, in, rsmd, rs,index);	
									}
								}
							}
							catch (Exception e) {
								System.err.println(e.toString());
							}
							}
						}
						else if (inp1.equals("2")) {
							// For the customer to submit the product 
							//ShowCategory();
							//Add Category?
							Scanner in2 = new Scanner (System.in);
							int c_id;
							//System.out.println(user.getuserID());
							System.out.println("Would you like to add a category? if not put in the category id");
							String a = in2.nextLine();
							if(a.equals("yes")) {
								//AddCategory
								System.out.println("Please name the category you wish to add: ");
								String b = in.nextLine();
								System.out.println("Please give a brief description ");
								String c = in.nextLine();
								//c_id = AddCategory(b,c);
							}
							else {
								c_id = Integer.parseInt(a);
							}
							
							//if--> new Category added
							//ShowWarehouses(user);
							modified=true;
							Product product = new Product();
						//	product.setCategoryID(c_id);
							System.out.println("Please enter your product name:");
							product.setProductName(in2.nextLine());
							System.out.println("Please enter the measurement in liters:");
							product.setSizeMeasurement(in2.nextLine());
							System.out.println("Please enter the weight:");
							product.setWeight(in2.nextLine());
							System.out.println("Please enter the Color:");
							product.setProductColor(in2.nextLine());
							System.out.println("Please enter the Image Link:");
							product.setWebLink(in2.nextLine());
							System.out.println("Please enter the unit price:");
							product.setUnitPrice(in2.nextDouble());
							System.out.println("Please enter the Quantity:");
							product.setQuantity(in2.nextInt());
						
						
						//	SubmitProduct(user,product);
							
						}
						else if(inp1.equals("3") ) {
							//ShowProducts(user);
						}
						
						//else if (inp1.equals("q")) {	// Handle the exit command q. Prints an exit message and breaks the loop.
						//	System.out.println("Command 'q' entered. Quitting the Application.");
						//	signed = false;
						
						else {	// Handle incorrect commands. Complain that the command entered is not valid and loop.
							System.out.println("'"+ inp1 + "'" + " is not a command.");
							VendersignedPrompt();
						}
						//r2in.close();
					}
					
				else if(rol==4) {
					System.out.print("All Rights Granted\n");
					System.out.println("1.Access User Informations\n2.Access Warehouse Informations");
					String opt =in.nextLine();		
					if(opt.equals("1"))
					{
						ListUsers();
					}
					if(opt.equals("2")) {
						ListWarehouses();
						System.out.println("Would you like to proceed as warehouse manager:\n"+"if so input the WarehouseID "+"or enter q to exit");
						String opt1= in.nextLine();
						if(opt1.equals("q")) {
							System.exit(0);
						}
						else if(opt1.equals(null)) {
							System.out.println("invalid input");
						}
						else {
							warId =Integer.parseInt(opt1);
						}
					}
				}
				else {
					System.exit(0);
				}
		}
	
	}
	public Map<String,String> WarehouseReport(User newUser, int warID) {
		boolean flag = false;
		Map<String,String> map = new HashMap<String,String>();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			if(warID ==0) {
				PreparedStatement wid = myConn.prepareStatement("SELECT WarehouseID FROM Warehouse WHERE Manager_UserID=?");
				wid.setInt(1, newUser.getuserID());
				//System.out.println(user.getuserID());
				ResultSet rs=wid.executeQuery();
				rs.next();
				warID=rs.getInt("WarehouseID");
				rs.close();
			}
			int war_id=warID;
			PreparedStatement list = myConn.prepareStatement("SELECT SUM(SizeMeasurement*Quantity) AS Volume FROM Product WHERE WarehouseID = ? ");
			//SUM(SizeMeasurement) AS Volume 
			list.setInt(1, war_id);
			ResultSet rs1=list.executeQuery();
			rs1.next();
			int occ_vol = rs1.getInt("Volume");
			rs1.close();
			PreparedStatement xizt = myConn.prepareStatement("SELECT Volume FROM Warehouse WHERE WarehouseID = ? ");
			xizt.setInt(1, war_id);
			ResultSet rs2=xizt.executeQuery();
			rs2.next();
			int war_vol = rs2.getInt("Volume");
			int emp_vol =war_vol - occ_vol;
			
			System.out.println("The capacity of the warehouse is: "+ war_vol+" L");
			map.put("wCapacity", Integer.toString(war_vol));
			System.out.println("The occ capacity of the warehouse is: "+ occ_vol+" L");
			map.put("cVolume", Integer.toString(occ_vol));
			System.out.println("The remaining capacity of the warehouse is: "+ emp_vol+" L");
			map.put("rVolume", Integer.toString(emp_vol));
			rs2.close();
			
		}
		catch (Exception e) {
			System.err.println(" "+e.toString());
			return null;
		}
		//rs2.close();
		return map;
	}
	public List<Product>  ShowProducts(User newUser) {
		//boolean flag = false;
		
		List<Product> pr = new ArrayList<Product>();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement list = myConn.prepareStatement("SELECT ProductID,ProductName,Quantity FROM Product WHERE UserID =?");
			list.setInt(1, newUser.getuserID());
			ResultSet rs1=list.executeQuery();
			ResultSetMetaData rs1md = list.getMetaData();
			int count=1;
						
			while(rs1.next()) {
				System.out.println("Information of product "+count);
				/*for(int i =1; i<=5; i++) {
					System.out.println(rs1md.getColumnName(i)+": "+rs1.getString(i));
					}
				System.out.println("-----------------------------------------------");
				count++;
				*/
				Product p = new Product();
				
				p.setProductID(Integer.parseInt(rs1.getString(1)));
				p.setProductName(rs1.getString(2));
				p.setQuantity(Integer.parseInt(rs1.getString(3)));
				pr.add(p);
				
			}
		}
		catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
		return pr;
	}
	
	//List all the warehouses and its description, being call from the main function by the admin
	//Allowing the admin to pick a warehouse that he needs to get permission
	public static boolean ListWarehouses() {
		boolean flag = false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement list = myConn.prepareStatement("SELECT * FROM Warehouse ");
			ResultSet rs1=list.executeQuery();
			ResultSetMetaData rs1md = list.getMetaData();
			while(rs1.next()) {
				int count=1;
				System.out.println("Information of Warehouse "+count);
				for(int i =1; i<=5; i++) {
					System.out.println(rs1md.getColumnName(i)+": "+rs1.getString(i));
					}
				System.out.println("-----------------------------------------------");
				count++;
				
			}
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		return flag;
	}
	//Takes an user object of a warehouse manager and a warehouseID showing all the product in
	//his or her administration
	public static boolean ListProduct(User user,int w_id) {
		boolean flag = false;
		try {
			System.out.println(w_id);
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			if(w_id ==0) {
				PreparedStatement wid = myConn.prepareStatement("SELECT WarehouseID FROM Warehouse WHERE Manager_UserID=?");
				wid.setInt(1, user.getuserID());
				//System.out.println(user.getuserID());
				ResultSet rs=wid.executeQuery();
				rs.next();
				while(rs.next()) {
				System.out.println(rs.getInt("WarehouseID"));
				w_id=rs.getInt("WarehouseID");
				}
			}
			PreparedStatement list = myConn.prepareStatement("SELECT * FROM Product WHERE WarehouseID = ?");
			list.setInt(1, 1);
			ResultSet rs1=list.executeQuery();
			ResultSetMetaData rs1md = list.getMetaData();
			int count=1;
			while(rs1.next()) {
				//int count=1;
				System.out.println("Information of Product "+count);
				for(int i =2; i<=10; i++) {
					System.out.println(rs1md.getColumnName(i)+": "+rs1.getString(i));
					}
				System.out.println("-----------------------------------------------");
				count++;
				
			}
		
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		return flag;
	}
	//Function called by the admin, to show all the registered user
	//**** Currently,no further action other than showing*****
	public static boolean ListUsers() {
		boolean flag = false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			PreparedStatement list = myConn.prepareStatement("SELECT * FROM User ");
			ResultSet rs1=list.executeQuery();
			ResultSetMetaData rs1md = list.getMetaData();
			while(rs1.next()) {
				int count=1;
				System.out.println("Information of User "+count);
				for(int i =1; i<=8; i++) {
					//System.out.println("Information of Product "+count);
					System.out.println(rs1md.getColumnName(i)+": "+rs1.getString(i));
					}
				System.out.println("-----------------------------------------------");
				count++;
				
			}
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		return flag;
	}
	//being called by the customer allowing the user to modify his or her address
	public static boolean ChangeAddress(User user, Scanner in,ResultSetMetaData rsmd, ResultSet rs, int index) {
		boolean change = false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
				System.out.println("Please input your changes: ");
				PreparedStatement chane = myConn.prepareStatement("UPDATE Address set Country=?,Address=?,City=?,State=?,ZipCode=? WHERE AddressID = ? ");
				for(int i =2; i<=6; i++) {
					System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
					String a = " ";
					a=in.nextLine();
					chane.setString(i-1, a);
					}
				chane.setInt(6, index);
				chane.executeUpdate();
				}
			
		
		catch (Exception e) {
			System.err.println(e.toString());
		}
		return change;
	}
	//Allowing the customer to add a new category
	public int AddCategory(String name,String des) {
		int flag=0;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stats = myConn.prepareStatement("SELECT COUNT(*) AS total FROM Category");
			ResultSet rrr = stats.executeQuery();
			int count1=0;
			while(rrr.next()){
			    count1 = rrr.getInt("total")+1;
			}
			PreparedStatement myStat1 = myConn.prepareStatement("INSERT INTO Category (CategoryID,CategoryName,CategoryDescription) VALUES (?,?,?)");
			myStat1.setInt(1, count1);
			myStat1.setString(2, name);
			myStat1.setString(3, des);
			myStat1.executeUpdate();
			myStat1.close();
			flag = count1;
			return flag;
		}
		catch(Exception e) {
			System.err.println("An error occured: "+e.toString());
			return 0;
		}
	}
	//Showing all the category in existence
	public List<String>  ShowCategory() {
		//boolean flag = false;
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stats = myConn.prepareStatement("SELECT * FROM Category");
			ResultSet cat = stats.executeQuery();
			List<String> clist = new ArrayList<String>();
			while(cat.next()){
			    System.out.print("category name and ids"+ cat.getInt("CategoryID")+" "+cat.getString("CategoryName")+"\n");
			    clist.add(cat.getString("CategoryName"));
			}
			
			return clist;
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return null;
		}
	}
	//Showing all the warehouse in existence
	public static boolean  ShowWarehouses(User newUser) {
		boolean flag = false;
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			PreparedStatement stats = myConn.prepareStatement("SELECT * FROM Warehouse");
			PreparedStatement stats1 = myConn.prepareStatement("SELECT * FROM Address WHERE AddressID=?");
			//stats1.setString(1, );
			ResultSet cat = stats.executeQuery();
			//cat.next();
			while(cat.next()){
			    System.out.print(cat.getInt("WarehouseID")+" "+cat.getString("WarehouseDescription")+"\n");
			    stats1.setInt(1, cat.getInt("AddressID"));
			    ResultSet add = stats1.executeQuery();
			    System.out.print(add.getString("Address")+" "+add.getString("City")+" "+add.getString("State")+" "+add.getString("Country")+" "+add.getString("ZipCode")+"\n");
			}
			flag=true;
			return flag;
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return false;
		}
	}
	//Allowing the user to add address into their address book
	public static boolean ModifyAddress(User newUser,String cou, String Address, String City, String state, String Zip) {
		boolean flag= false;
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			PreparedStatement stats = myConn.prepareStatement("SELECT COUNT(*) AS total FROM  Address");
			ResultSet rrr = stats.executeQuery();
			int count1=0;
			while(rrr.next()){
			    count1 = rrr.getInt("total")+1;
			}
			PreparedStatement myStatShi = myConn.prepareStatement("INSERT INTO Address (UserID,AddressID,Country,Address,City,State,ZipCode) VALUES (?,?,?,?,?,?,?)");
			myStatShi.setString(3, cou);
			myStatShi.setString(4, Address);
			myStatShi.setString(5, City);
			myStatShi.setString(6, state);
			myStatShi.setString(7, Zip);
			myStatShi.setInt(1, newUser.getuserID());
			myStatShi.setInt(2, count1);
			myStatShi.executeUpdate();
			myStatShi.close();
		}
	
		//PreparedStatement list = myConn.prepareStatement("SELECT )
	
	//scan.close();*/
		
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return false;
		}
	
		return flag;
		
	}
	
	public int getCategoryID(String cname) {
		int u_id =0;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stat = myConn.prepareStatement("SELECT CategoryID FROM category WHERE CategoryName = ?" );
			stat.setString(1, cname);
			ResultSet r= stat.executeQuery();
			//r.next();
			while (r.next()) {
				u_id = r.getInt("CategoryID");
			}
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return u_id;

	}
	
	public int getUserID(String username) {
		int u_id =0;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stat = myConn.prepareStatement("SELECT UserID FROM User WHERE Username = ?" );
			stat.setString(1, username);
			ResultSet r= stat.executeQuery();
			//r.next();
			while (r.next()) {
				u_id = r.getInt("UserID");
			}
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return u_id;

	}


	
	
	//Submit product, updates product table and its corresponding warehouse ID 
	public boolean SubmitProduct(User newUser, Product product) {
		boolean flag =false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			int pro_count =0;
		//	ShowRegion();
			/*Scanner intScan = new Scanner(System.in);
			System.out.println("Please select the region you wish to place you product:");
			int r_id = intScan.nextInt();*/
			/*PreparedStatement rsat = myConn.prepareStatement("SELECT WarehouseID FROM Region WHERE RegionID = ?");
			rsat.setInt(1, r_id);
			ResultSet r= rsat.executeQuery();
			r.next();
			int w_id = r.getInt("WarehouseID");
			if (w_id==0) {
				System.out.println("Unfortunately, warehouse for this region has not been set up yet");
			}
			intScan.close();*/
			/*PreparedStatement stmt = myConn.prepareStatement("SELECT COUNT(*) AS total FROM Product;");
			ResultSet rs3 = stmt.executeQuery();
			while(rs3.next()){
				  pro_count = rs3.getInt("total")+1;
			}
			product.setProductID(pro_count);
			*///System.out.println(product.getProductID());
			
			/*PreparedStatement myStat2 = myConn.prepareStatement("SELECT ProductID, ProductName FROM Product WHERE ProductID = ?;");
			myStat2.setInt(1, product.getProductID());
			ResultSet rs = myStat2.executeQuery();*/
			
			
				PreparedStatement myStat = myConn.prepareStatement("INSERT INTO Product (ProductName,UserID,Weight,UnitPrice,Quantity,SizeMeasurement,ProductID,CategoryID,WebLink,ProductColor,WarehouseID) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
				myStat.setString(1, product.getProductName());
				myStat.setInt(2, newUser.getuserID());
				myStat.setString(3, product.getWeight());
				myStat.setDouble(4, product.getUnitPrice());
				myStat.setInt(5, product.getQuantity());
				myStat.setString(6, product.getSizeMeasurement());
				myStat.setInt(7, pro_count);
				//myStat.setInt(8,product.getWarehouseID());
				myStat.setInt(8, product.getCategoryID());
				myStat.setString(9, product.getWebLink());
				myStat.setString(10, product.getProductColor());
				myStat.setInt(11, product.getWarehouseID());
				myStat.executeUpdate();
				flag=true;
			
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return false;
		}
		return flag;	
	}
	//public static boolean updateWarehouse (int vol, wa)
	//Allowing user to modify First name Lastname phone email 
	public static boolean ModifyPersonalInfo(User newUser) {
		try {
			boolean modify = false;
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","0530");
			PreparedStatement myStat1 = myConn.prepareStatement("SELECT UserID FROM User WHERE UserID = ?;");
			myStat1.setInt(1, newUser.getuserID());
			System.out.println(newUser.getuserID());
			ResultSet r = myStat1.executeQuery();
			Scanner in = new Scanner (System.in); 
			String Fn=" ",Ln = "",phone=" ", email=" ";
			if (r.next()) {
				// Register the customer using an INSERT statement.
				PreparedStatement myStat = myConn.prepareStatement("UPDATE User set FirstName = ? ,LastName = ?, Phone = ?,Email = ?,RegionID =? where UserID = ?");
				System.out.println("Please input your First Name:");
				Fn=in.nextLine();
				myStat.setString(1, Fn);
				System.out.println("Please input your Last Name:");
				Ln=in.nextLine();
				myStat.setString(2, Ln);
				System.out.println("Please input your phone number:");
				phone=in.nextLine();
				myStat.setString(3, phone);
				System.out.println("Please input your email:");
				email=in.nextLine();
				myStat.setString(4, email);
				email=in.nextLine();
				myStat.setInt(6, newUser.getuserID()); 
				myStat.executeUpdate();
				modify = true;
			} else {	// Result set was not empty, meaning the email was found, customer registration failed.
				System.out.println("The user already exists. Please sign in using it or register with another username");
				//initialPrompt();	// Print initial prompt again to continue asking the customer to sign in or register.
			}
			//in.close();
			return modify;
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return false;
		}
	}
	//Register a new user
	public int registerCustomer(User newUser, Connection myConn) throws Exception{
		try {
			boolean sigin = false;
			boolean warehouse = false;
			// create a connection
			PreparedStatement myStat1 = myConn.prepareStatement("SELECT Username FROM User WHERE Username = ?;");
			myStat1.setString(1, newUser.getusername());
			ResultSet r = myStat1.executeQuery();
			Scanner in = new Scanner(System.in);
			
			
			// If the result set is empty, meaning the email does not exist, we can add the customer.
			if (!r.next() || warehouse) {
				// Register the customer using an INSERT statement.
				PreparedStatement myStat = myConn.prepareStatement("INSERT INTO User (Role,Username,Password,FirstName,LastName,Phone,Email,RegionID) VALUES (?,?,?,?,?,?,?,?)");
				myStat.setInt(1, newUser.getrole());
				myStat.setString(2, newUser.getusername());
				myStat.setString(3, newUser.getpassword());
				myStat.setString(4, newUser.getFirstName());
				myStat.setString(5, newUser.getLastName());
				myStat.setString(6, newUser.getPhone());
				myStat.setString(7, newUser.getEmail());
				myStat.setInt(8, newUser.getRegionID());
				myStat.executeUpdate();
				
				/*if (newUser.getrole()==2 || newUser.getrole()==1||newUser.getrole()==3) {
				
				String country=" ", Address=" ", City=" ", Zip=" ";
				int warID =0;
					PreparedStatement stats = myConn.prepareStatement("SELECT COUNT(*) AS total FROM  Address");
					ResultSet rrr = stats.executeQuery();
					int count1=0;
					while(rrr.next()){
					    count1 = rrr.getInt("total")+1;
					}
					PreparedStatement myStatShi = myConn.prepareStatement("INSERT INTO Address (UserID,AddressID,Country,Address,City,State,ZipCode,RegionID) VALUES (?,?,?,?,?,?,?,?)");
					System.out.println("Please enter your Country");
					country = in.nextLine();
					myStatShi.setString(3, country);
					System.out.println("Please enter your Address:");
					Address = in.nextLine();
					myStatShi.setString(4, Address);
					System.out.println("Please enter City:");
					City= in.nextLine();
					myStatShi.setString(5, City);
					System.out.println("Please enter State:");
					String state= in.nextLine();
					myStatShi.setString(6, state);
					System.out.println("Please enter your zip code:");
					Zip= in.nextLine();
					myStatShi.setString(7, Zip);
					System.out.println(i);
					myStatShi.setInt(1, i);
					myStatShi.setInt(2, count1);
					ShowRegion();
					System.out.println("Please enter the region ID you wish to be in: ");
					Scanner in2 =new Scanner(System.in);
					newUser.setRegionID(in2.nextInt());
					myStatShi.setInt(8, newUser.getRegionID());
					myStatShi.executeUpdate();
					myStatShi.close();
					//System.out.print(newUser.getrole());
					if(newUser.getrole()==1) {
						PreparedStatement st = myConn.prepareStatement("SELECT COUNT(*) AS total FROM Warehouse");
						ResultSet r1 = st.executeQuery();
						int count2=0;
						while(r1.next()){
						    count2 = r1.getInt("total")+1;
						}
						PreparedStatement st1 = myConn.prepareStatement("Insert INTO Warehouse(WarehouseID,AddressID,Volume,WarehouseDescription) Values(?,?,?,?)");
						st1.setInt(1, count2);
						st1.setInt(2, count1);
						st1.setInt(3, 10000);
						System.out.println("Please enter a warehouse desciption:");
						String wd= in.nextLine();
						st1.setString(4, wd);
						st1.executeUpdate();
						PreparedStatement st2 = myConn.prepareStatement("UPDATE Region SET WarehouseID = ? WHERE RegionID =? ");
						st2.setInt(1, count2);
						st2.setInt(2, newUser.getRegionID());
						st2.executeUpdate();
					}
					if(newUser.getrole()==3) {
						PreparedStatement st33 = myConn.prepareStatement("SELECT WarehouseID FROM Region WHERE RegionID = ?");
						st33.setInt(1, newUser.getRegionID());
						ResultSet rs33=st33.executeQuery();
						rs33.next();
						int w_id = rs33.getInt("WarehouseID");
						PreparedStatement st3 = myConn.prepareStatement("UPDATE Warehouse SET Manager_userID = ? WHERE WarehouseID = ? ");
						st3.setInt(1, newUser.getuserID());
						st3.setInt(2, w_id);
						st3.executeUpdate();
					}
					
				}
				
				//myStat11.executeUpdate();
				//in1.close();
				//sigin = true;	// Set sign in/registration success to true.
			} else {	// Result set was not empty, meaning the email was found, customer registration failed.
				System.out.println("The user already exists. Please sign in using it or register with another username");
				//initialPrompt();	// Print initial prompt again to continue asking the customer to sign in or register.
			}
			r.close();*/
			//myConn.close();
			//myStat1.close();
			//return 1;	// Return false if not registered/signed in.
		}
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return 1;
	}
	public static void initialPrompt() {
		System.out.println("Select an action by entering a displayed number, or enter 'q' to quit:\n1. Register new account.\t2. Sign in to existing account.");
	}
	public static void CustomersignedinPrompt() {
		System.out.println("Select an action by entering a displayed number, or enter 'q' to quit:\n1. Modify Personal Information.\n2. Look Up Products.");
	}
	public static void VendersignedPrompt() {
		System.out.println("Select an action by entering a displayed number, or enter 'q' to quit:\n1. Modify your vendor account.\n2. Submit a supply request");
	}
	//Sign in function to be call to  log in
	public static int signIn(User newUser, Connection c) throws Exception{
		try {
			Boolean successfulSignin = false;
			PreparedStatement s = c.prepareStatement("SELECT Username,Password,UserID,Role FROM User WHERE Username = ?;");
			s.setString(1, newUser.getusername());
			ResultSet r = s.executeQuery();
			//String Usr_name=" ";
			int id=0;
			String pass = " ";
			int role =0;
			// If the result set is empty, the customer's email was not found. Sign in failed.
			while (r.next()) {
				//Usr_name = r.getString("Username");
				pass = r.getString("Password");
				id=r.getInt("UserID");
				role=r.getInt("Role");
			} 
			if (pass.equals(newUser.getpassword())){
				System.out.println("logged in");
				//newUser.setuserID(id);
				//newUser.setrole(role);
				//successfulSignin = true;
				return role;
			}
			else {	// If the result set is not empty, the customer's email was found. Sign in success.
				System.out.println("Sign unsuccessful.");
				//successfulSignin = true;	// Set return value to true since sign in was successful.
			}
			//return successfulSignin;
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return 0;
	}

	public List<String> ShowRegion() {
		
		List<String> region = new ArrayList<String>();
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stats = myConn.prepareStatement("SELECT * FROM Region");
			ResultSet cat = stats.executeQuery();
		//	List<String> region = new ArrayList<String>();
			while(cat.next()){
			    System.out.print(cat.getString("RegionID")+" "+cat.getString("LocationDesc")+"\n");
			    region.add(cat.getString("LocationDesc"));
			}
			return region;
			
		}
		catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}
	
	
	public static boolean setAddress(int userID,String cou, String Address, String City, String state, String Zip) {

		boolean flag= false;
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement myStatShi = myConn.prepareStatement
				("INSERT INTO Address (UserID,Country,Address,City,State,ZipCode) VALUES (?,?,?,?,?,?)");
			myStatShi.setInt(1, userID);
			myStatShi.setString(2, cou);
			myStatShi.setString(3, Address);
			myStatShi.setString(4, City);
			myStatShi.setString(5, state);
			myStatShi.setString(6, Zip);
			myStatShi.executeUpdate();
			myStatShi.close();
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return false;
		}
		return flag;
}
	
	public List<Address> showAddress(int userID) {

		List<Address> add = new ArrayList<Address>();
		
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement sta = myConn.prepareStatement("SELECT * FROM Address WHERE UserID = ?");
			sta.setInt(1, userID);
			ResultSet rs= sta.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int index=0;
			
			while(rs.next()) {
				Address ad = new Address();
				ad.setCountry(rs.getString(1));
				ad.setAddress(rs.getString(2));
				ad.setCity(rs.getString(3));
				ad.setState(rs.getString(4));
				ad.setZipCode(rs.getString(5));
				ad.setUserID(Integer.parseInt(rs.getString(6)));
				
				/*	System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));*/
				add.add(ad);
			
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}
	
	return add;

	}
}
