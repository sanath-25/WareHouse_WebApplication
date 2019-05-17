package sample.com.trial1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import sample.com.service.ServiceTest;

@Controller
public class HelloController /*extends AbstractController*/{

	ServiceTest service;

	/*   @Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		ModelAndView mv = new ModelAndView("Hellopage");	
		mv.addObject("message","this is sample test");
		return mv;
	}*/



	@RequestMapping(value="/Register", method=RequestMethod.POST) 
	/*	public ModelAndView testSubmit(@RequestParam("fname") String name1,@RequestParam("lname") String name2) {
	 */	public ModelAndView register(@RequestParam Map<String,String> t) {

		String name = t.get("uname");
		String pass = t.get("pass");


		/*if(!(name.equalsIgnoreCase("sa") & pass.equals("sa")))*/
		if(null !=name)
		{
			ModelAndView mv = new ModelAndView("Register");	
			mv.addObject("message","you are not an user please register");
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("index");	
			mv.addObject("error","");
			return mv;
		}

	}

	@RequestMapping(value="/adminPage", method=RequestMethod.GET) 
	public ModelAndView adminPage(@RequestParam Map<String,String> t, HttpServletRequest req, 
			HttpServletResponse res)  {

		ModelAndView mv = new ModelAndView("Admin");

		String uname = t.get("uname");
		String password = t.get("pass");

		System.out.println("welcome " + uname);

		//				if(!password.equals("sanath25"))
		//				{
		//					ModelAndView mv1  = new ModelAndView("error");
		//					return mv1;
		//				}		

		List<String> list = new ArrayList<String>();
		list.add("India");
		list.add("USA");
		list.add("England");
		list.add("Uganda");
		/* req.setAttribute("ls", list);*/
		//  List<String,Integer> l = new ArrayList<String,Integer>();
		//List<String,String> list2 = new ArrayList<String,String>();
		Map<String,Integer> map  = new HashMap();//<String, Integer>();
		map.put("Apple", 10);
		map.put("orange", 20);
		map.put("pineapple", 40);

		mv.addObject("items", map);


		mv.addObject("list",list);


		/*
	    HttpSession session = req.getSession();
	    session.setAttribute("list", list);*/
		return mv;
	}

	@RequestMapping(value="/warehouse", method=RequestMethod.GET) 
	@ResponseBody
	public ModelAndView  warehouse( HttpServletRequest req, 	HttpServletResponse res) {

		System.out.println("i am in warehouse");

		//String region = (String) req.getAttribute("data");
		String region = (String) req.getParameter("data");
		System.out.println(region);
		ModelAndView mv = new ModelAndView("warehouse");
		mv.addObject("wm", "Trump");
		mv.addObject("data", region);
		Sample1 s1 = new Sample1();
		Sample1 s2 = new Sample1();
		Sample1 s3 = new Sample1();

		s1.setTree("Apple");
		s1.setBranches(10);
		s2.setTree("orange");
		s2.setBranches(20);
		s3.setTree("pineapple");
		s3.setBranches(40);

		List<Sample1> l = new ArrayList<Sample1>();
		l.add(0, s1);
		l.add(1, s2);
		l.add(2, s3);

		mv.addObject("items", l);
		mv.addObject("pic", "https://plants.usda.gov/gallery/large/qupa2_002_lhp.jpg");

		return mv;

	}


	@RequestMapping(value="/submit1", method=RequestMethod.POST) 
	/*	public ModelAndView testSubmit(@RequestParam("fname") String name1,@RequestParam("lname") String name2) {
	 */	public ModelAndView testSubmit(@RequestParam Map<String,String> t) {
		/*("fname") String name1,@RequestParam("lname") String name2) {
		 * 
		 * @PathVariable("") -- is used for getting variables from url
		 * @MOdelAttribute(studentname1_asanObject in ui) Student studentname1_asanObject*/

		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext
				("C:/Users/sanath/eclipse-workspace/trial1/webproject/src/Beans.xml");
		service = (ServiceTest) context.getBean("serviceTest");

		ModelAndView mv = new ModelAndView("Hellopage");	
		String name1 = t.get("lname");
		String name2 = t.get("fname");

		//service = new ServiceTest();
		service.testService(name1,name2);



		return mv;


	}


	@RequestMapping(value="/customer", method=RequestMethod.GET) 
	public ModelAndView customer(@RequestParam Map<String,String> t) {
		ModelAndView mv = new ModelAndView("Firstpage_customer");
		return mv;
	}

	@RequestMapping(value="/category", method=RequestMethod.GET) 
	public ModelAndView category(@RequestParam Map<String,String> t) {
		ModelAndView mv = new ModelAndView("Add_category");
		return mv;
	}

	@RequestMapping(value="/addcategory", method=RequestMethod.GET) 
	public ModelAndView addcategory(@RequestParam Map<String,String> t) {

		ModelAndView mv = new ModelAndView("Add_category");
		return mv;
	}	

	@RequestMapping(value="/displaycategory", method=RequestMethod.GET) 
	public ModelAndView displaycategory(@RequestParam Map<String,String> t) {
		ModelAndView mv = new ModelAndView("displaycategory");

		String categoryname = t.get("categoryname");
		String categorydescription = t.get("categorydescription");

		App a = new App();
		a.AddCategory(categoryname, categorydescription);	

		mv.addObject("cname", categoryname);
		mv.addObject("cdec", categorydescription);
		return mv;
	}	

	@RequestMapping(value="/addproduct", method=RequestMethod.GET) 
	public ModelAndView addproduct(@RequestParam Map<String,String> t) {

		App a = new App();
		List<String> categories = new ArrayList<String>();
		categories = a.ShowCategory();
		List<String> region = new ArrayList<String>();
		region.addAll(a.ShowRegion());		
		ModelAndView mv = new ModelAndView("Add_product");	
		mv.addObject("categories", categories);
		return mv;
	}


	public int getWarehouse(String name) {

		int w_id =0;

		try {

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stat = myConn.prepareStatement("SELECT WarehouseID FROM Region WHERE LocationDesc = ?" );
			stat.setString(1, name);
			ResultSet r= stat.executeQuery();
			//r.next();
			while(r.next() ) {
				/*if(null != r.getInt("WarehouseID"))		*/	
				w_id = r.getInt("WarehouseID");
			}
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return w_id;

	}
	

	public int getWHIDForWHM(int  uId) {

		int w_id =0;

		try {

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement stat = myConn.prepareStatement("SELECT WarehouseID FROM warehouse WHERE Manager_userID = ?" );
			/*stat.setString(1, uId);*/
			stat.setInt(1,uId);
			ResultSet r= stat.executeQuery();
			//r.next();
			while(r.next() ) {
				/*if(null != r.getInt("WarehouseID"))		*/
				w_id =	r.getInt("WarehouseID");
			
			};
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return 0;
		}
		return w_id;

	}




	@RequestMapping(value="/displayproduct", method=RequestMethod.GET) 
	public ModelAndView displayproduct(@RequestParam Map<String,String> t,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("displayproduct");

		String category = t.get("cat");
		String quantity = t.get("productquantity");
		String productname = t.get("productname");
		String productdescription = t.get("productdescription");
		String weight = t.get("productweight");
		String productsize = t.get("productsize");
		String productprice = t.get("productprice");
		String productcolour = t.get("productcolour");
		String location = t.get("region");

		String username = (String)req.getSession().getAttribute("username");


		int wareHouseId = getWarehouse(location);
		App a = new App();


		Product p = new Product();
		p.setCategoryID(a.getCategoryID(category));
		p.setProductColor(productcolour);
		p.setProductName(productname);
		p.setQuantity(Integer.parseInt(quantity));
		p.setSizeMeasurement(productsize);
		p.setUnitPrice(Double.parseDouble(productprice));
		p.setWeight(weight);
		p.setUserID(a.getUserID(username));
		p.setWarehouseID(wareHouseId);

		User u = new User();
		u.setuserID(a.getUserID(username));
		u.setUsername(username);

		a.SubmitProduct(u, p);

		mv.addObject("category", category);
		mv.addObject("productdescription", productdescription);

		mv.addObject("productcname", productname);
		mv.addObject("quantity", quantity);
		return mv;
	}	

	@RequestMapping(value="/addAddress", method=RequestMethod.GET) 
	public ModelAndView addAddress(@RequestParam Map<String,String> t) {
		ModelAndView mv = new ModelAndView("Add_address");
		/*List<String> categories = new ArrayList<String>();
		categories.add("sports");
		categories.add("toys");
		mv.addObject("categories", categories);*/
		return mv;
	}


	@RequestMapping(value="/displayaddress", method=RequestMethod.GET) 
	public ModelAndView displayaddress(@RequestParam Map<String,String> t,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("displayaddress");

		App a = new App();
		String country= t.get("region");
		String address = t.get("addressline");
		String zipCode =t.get("zipcode");
		int userId = a.getUserID((String)req.getSession().getAttribute("username"));
		String city = t.get("city");
		String state = t.get("state");

		a.setAddress(userId, country, address, city, state, zipCode);

		List<Address> add = new ArrayList<Address>();
		add.addAll(a.showAddress(userId));

		/*Address ad = new Address();
			ad.setCountry(country);
			ad.setAddress(address);
			ad.setCity(city);
			ad.setState(state);
			ad.setZipCode(zipCode);
			ad.setUserID(userId);*/

		mv.addObject("addresess", add);

		return mv;
	}	


	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public ModelAndView login(@RequestParam Map<String,String> t) {

		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value="/loginResponse", method=RequestMethod.GET) 
	public ModelAndView loginResponse(@RequestParam Map<String,String> t,HttpServletRequest req,HttpServletResponse res) {

		//String role = "admin";
		String uname = t.get("uname");
		String password = t.get("psw");
		//validation part here for user details

		User u = new User();
		u.setUsername(uname);
		u.setPassword(password);

		HttpSession hs  = req.getSession();
		hs.setAttribute("username", uname);
		

		ModelAndView mv;
		App app = new App();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","sanath25");

			int role = app.signIn(u, Conn);
			if(role == 0)
			{
				mv= new ModelAndView("error");
				mv.addObject("message","please enter valid credentials");
				return mv;	
			}

			//stubbing for customer as role = 2
			//role = 2;

			System.out.println("details"+uname+password);
			if(role == 4)
			{
				mv = new ModelAndView("Admin");
				mv.addObject("ename", uname);


			}
			else if(role == 3)
			{			
				mv = new ModelAndView("Firstpage_warehouse");
				mv.addObject("ename", uname);

			}
			else {
				mv = new ModelAndView("Firstpage_customer");
				mv.addObject("ename", uname);

			}
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			mv = new ModelAndView("error");
			return mv;
		}

		return mv;
	}	


	@RequestMapping(value="/signup", method=RequestMethod.GET) 
	public ModelAndView signup(@RequestParam Map<String,String> t) {

		String name = t.get("firstname");
		System.out.println("name is"+ name);

		ModelAndView mv = new ModelAndView("signup");

		List<String> ls = new ArrayList<String>();
		ls.add("Admin");
		ls.add("Customer");
		ls.add("WareHouse Manager");

		mv.addObject("Role", ls);
		return mv;

	}


	@RequestMapping(value="/viewproduct", method= {RequestMethod.GET}) 
	public ModelAndView viewProduct(@RequestParam Map<String,String> t,HttpServletRequest req) {


		ModelAndView mv = new ModelAndView("View_product");

		App a = new App();
		User u = new User();
		u.setUsername((String)req.getSession().getAttribute("username"));
		u.setuserID(a.getUserID((String)req.getSession().getAttribute("username")));

		List<Product> prod = new ArrayList<Product>();
		//	prod.addAll(a.ShowProducts(u));

		for(int i=0;i<a.ShowProducts(u).size()-1;i++)
		{
			if( null != a.ShowProducts(u).get(i).getProductName()  )
				prod.add(i, a.ShowProducts(u).get(i));
		}


		mv.addObject("products", prod);


		return mv;
	}



	@RequestMapping(value="/signupResponse", method= {RequestMethod.GET}) 
	public ModelAndView getStartPage(@RequestParam Map<String,String> t) {

		String fname = t.get("firstname");
		String lname = t.get("Lastname");
		String email = t.get("email");
		String uname = t.get("username");
		String number = t.get("number");
		String Role = t.get("Role");
		String psw = t.get("psw");		
		System.out.println("sign up details are "+ email);

		User u = new User();
		u.setFirstName(fname);
		u.setLastName(lname);
		u.setEmail(email);
		u.setUsername(uname);
		u.setPhone(number);
		if("customer".equalsIgnoreCase(Role))
		{ u.setrole(2);}
		else if("Admin".equalsIgnoreCase(Role))
		{  u.setrole(4);}
		else
		{  u.setrole(3);}

		u.setPassword(psw);
		u.setRegionID(1);
		ModelAndView mv;
		App app = new App();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","sanath25");
			Statement stmt = Conn.createStatement();
			app.registerCustomer(u, Conn);		
			mv = new ModelAndView("index");	
			/*mv.addObject("message","this is sample test");*/
		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			return null;
		}

		return mv;

	}




	@RequestMapping(value="/Messagealerts", method= {RequestMethod.GET}) 
	public ModelAndView messageAlerts(@RequestParam Map<String,String> t,HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("Messagealerts");
		App a = new App();
		
		int uId = a.getUserID((String)(req.getSession().getAttribute("username")));
		
		User u = new User();
		u.setuserID(uId);
		Map<String,String> map = new HashMap<String,String>();
		
		
		//map = (a.WarehouseReport(u, getWHIDForWHM(uId)));
		
		String cWareHouse = (a.WarehouseReport(u, getWHIDForWHM(uId))).get("wCapacity");
		String cOccupancy = (a.WarehouseReport(u, getWHIDForWHM(uId))).get("cVolume");
		String rOccupancy = (a.WarehouseReport(u, getWHIDForWHM(uId))).get("rVolume");
		
		mv.addObject("a", cWareHouse);
		mv.addObject("b", cOccupancy);
		mv.addObject("c", rOccupancy);

		return mv;
	}

	public List<String> showUsers(String country) {

		List<String> un = new ArrayList<String>();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse","root","sanath25");
			PreparedStatement sta = myConn.prepareStatement("SELECT * FROM User WHERE Country = ? and role =2");
			sta.setString(1, country);
			ResultSet rs= sta.executeQuery();
			/*ResultSetMetaData rsmd = rs.getMetaData();*/
			int index=0;
			while(rs.next()) {
						
			un.add(rs.getString(2));
						/*System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
					*/
				
				}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
			return un;


	}



	/*@RequestMapping(value="/Customers", method= {RequestMethod.GET}) 
	public ModelAndView seeCustomers(@RequestParam Map<String,String> t,HttpServletRequest req) {

		ModelAndView mv 
		
	}*/


	/*	
			public static void main(String[] s1) throws SQLException
			{


				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","sanath25");
				Statement stmt = Conn.createStatement();

				PreparedStatement s = Conn.prepareStatement("SELECT Username,Password,UserID,Role FROM User WHERE Username = 'a'");
				ResultSet r = s.executeQuery();
				while (r.next()) {
					//Usr_name = r.getString("Username");

					System.out.println("result"+ r.getString("Password"));
				} 

			}
	 */





}
