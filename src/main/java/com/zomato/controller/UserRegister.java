package com.zomato.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emailvalidation.MailidValidation;
import com.zomato.pojo.UserItemPojo;
import com.zomato.pojo.UserPojo;

import mobilevalidation.Validation;

@Controller
public class UserRegister {

	@RequestMapping(value = "/userregister", method = RequestMethod.POST)
	public String saveUserDetails(UserPojo up, Model m) {
		System.out.println(up.getUsername() + "---" + up.getEmailid() + "---" + up.getMobile() + "---"
				+ up.getPassword() + "----" + up.getCity());

		Validation valid = new Validation();
		boolean validMobile = valid.isValidMobile(up.getMobile());

		if (!validMobile) {
			m.addAttribute("message", "Invalid mobile number plese enter valid mobile number!!");
			return "Register";
		}

		// MailidValidation vm=new MailidValidation();
		boolean validEmail = MailidValidation.isValid(up.getEmailid());

		if (!validEmail) {
			m.addAttribute("message", "Invalid emailid plese enter valid emailid!!");
			return "Register";
		}

		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sf = config.buildSessionFactory();

		Session op = sf.openSession();
		Transaction tx = op.beginTransaction();

		op.save(up);
		tx.commit();
		op.close();
		return "login";
	}

	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public String loginUser(@RequestParam("emailid") String emailid, @RequestParam("password") String password,
			Model m) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sf = config.buildSessionFactory();

		Session op = sf.openSession();

		System.out.println("login page..!!");

		Query Query = op.createQuery("from UserPojo where emailid =? and password=?");
		Query.setParameter(0, emailid);
		Query.setParameter(1, password);

		List<UserPojo> list = Query.list();
		for (UserPojo up : list) {
			System.out.println(up.getEmailid() + "--" + up.getPassword());
		}
		if (list.isEmpty()) {
			m.addAttribute("message", "Invalid emailid and password");
			return "login";
		}

		return "result";
	}

	@RequestMapping(value = "/additem", method = RequestMethod.POST)
	public String addItems() {

		System.out.println("This is the java class--userItem");

		return "enteritem";
	}

	@RequestMapping(value = "/addtomenu", method = RequestMethod.POST)
	public String addItemsToDb(UserItemPojo user, Model m) {
		System.out.println(user.getItemid() + "--" + user.getItem() + "--" + user.getTypeof());
		System.out.println("This is the java class--addItemsToDb");

		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sf = config.buildSessionFactory();

		Session op = sf.openSession();
		Transaction tx = op.beginTransaction();

		op.save(user);
		tx.commit();
		op.close();
		m.addAttribute("msg", "Item added to menu successfully..!!");
		return "enteritem";
	}

	@RequestMapping(value = "/showitemmenu", method = RequestMethod.POST)
	public String showMenu(Model m) {

		System.out.println("this is the show menu ..!!");
		Configuration config = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sf = config.buildSessionFactory();

		Session op = sf.openSession();
		Query Query = op.createQuery("from UserItemPojo");

		List<UserItemPojo> list = Query.list();
		for (UserItemPojo Item : list) {

			System.out.println(
					Item.getItemid() + "  " + Item.getItem() + "  " + Item.getPrice() + "   " + Item.getQuantity());
		}

		m.addAttribute("menu", list);
		return "showmenu";
	}

	@RequestMapping(value = "/deleteitem", method = RequestMethod.POST)
	public String deleteitem(@RequestParam("itemid") int itemid, Model m) {
		System.out.println("enter into the  deleteitem");

		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		Session openSession = buildSessionFactory.openSession();
		Transaction tn = openSession.beginTransaction();

		Query query = openSession.createQuery("delete UserItemPojo where itemid = :id");
		query.setParameter("id", itemid);
		int result = query.executeUpdate();
		tn.commit();
		if (result == 1) {
			System.out.println("successfully deleted item..!!");
			String message = itemid + "delete sucessfully..!!";
			m.addAttribute("msg", message);

		} else {
			System.out.println("failed to deleted..!!");
			m.addAttribute("msg", itemid + "delete failed");
		}
		Query fetchMenuQuery = openSession.createQuery("from UserItemPojo");
		List menuData = fetchMenuQuery.list();
		
		//m.addAttribute("message", itemid+" "+"itemId delete sucessfully..!!");
		m.addAttribute("menu", menuData);
		openSession.close();
		return "showmenu";
	}

	@RequestMapping(value = "/showuserdetails", method = RequestMethod.POST)
	public String showUserDetails(Model model) {

		System.out.println("This is the java class--showuserdetails");

		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory session = configure.buildSessionFactory();
		Session openSession = session.openSession();
		Transaction beginTransaction = openSession.beginTransaction();

		Query query = openSession.createQuery("from UserPojo");
		List userdetails = query.list();

		model.addAttribute("userdetails", userdetails);
		return "showuserdetails";
	}

	@RequestMapping(value = "/deleteuser")
	public String deleteUserDetails(@RequestParam("username") String username, Model model) {
		System.out.println("delete user details method...!!");

		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		Session openSession = buildSessionFactory.openSession();
		Transaction beginTransaction = openSession.beginTransaction();

		Query query = openSession.createQuery("delete UserPojo where username=:username");
		query.setParameter("username", username);
		int result = query.executeUpdate();
		beginTransaction.commit();

		if (result == 1) {
			model.addAttribute("user", username + " details deleted sucessfully..!!");
		} else {
			model.addAttribute("user", "failed to deleted..!!");
		}

		Query query2 = openSession.createQuery("from UserPojo");
		List userdetails = query2.list();
		model.addAttribute("userdetails", userdetails);
		openSession.close();
		return "showuserdetails";
	}

	@RequestMapping(value = "/edititem")
	public String edititem(@RequestParam("itemid") int itemid, Model model) {

		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		Session openSession = buildSessionFactory.openSession();

		Query query = openSession.createQuery("from UserItemPojo where itemid = :id");
		query.setParameter("id", itemid);
		UserItemPojo itemInfo = (UserItemPojo) query.list().get(0);
		model.addAttribute("itemInfo", itemInfo);
		if(itemInfo.getTypeof().equals("veg")) {
			model.addAttribute("veg", "checked");
		}
		else {
			model.addAttribute("nonveg", "checked");
		}
		return "updateitem";
	}
	@RequestMapping(value="/updateitem",method= RequestMethod.POST)
	public String updateitem(UserItemPojo useritem,Model model) {
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		Session openSession = buildSessionFactory.openSession();
			Transaction txn = openSession.beginTransaction();
			openSession.update(useritem);
			txn.commit();
			System.out.println("hello");
			Query query = openSession.createQuery("from UserItemPojo");
			List<UserItemPojo> itemInfo = query.list();
		
			model.addAttribute("menu", itemInfo);
			openSession.close();
		return "showmenu";
	}
	
	
	@RequestMapping(value="/edituser",method= RequestMethod.POST)
	public String editUser(@RequestParam("username") String username,Model model) {
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		Session openSession = buildSessionFactory.openSession();
		
		Query query = openSession.createQuery("from UserPojo where username=:username");
	
		query.setParameter("username", username);
		UserPojo userInfo = (UserPojo) query.list().get(0);
		
		model.addAttribute("userInfo" , userInfo);
		openSession.close();
		
		return "updateUser";
		
	}
	
	@RequestMapping(value="/updateUserPage",method=RequestMethod.POST)
	public String updateUser(UserPojo user, Model model) {
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		Session session = configure.buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(user);
		transaction.commit();
		
		Query query = session.createQuery("from UserPojo");
		List userInfo = query.list();
		
		model.addAttribute("user", "user details updated sucessfully..!!");
		model.addAttribute("userdetails", userInfo);
		session.close();
		return "showuserdetails";
	}
	
	@RequestMapping(value="/deleteitem")
	public String multipleDelete(@RequestParam("itemid") int list,Model model)  {
		System.out.println("hai -hello");
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		Session session = configure.buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from delete where itemid=:itemid");
		
		query.setParameter("itemid", list);
		int result = query.executeUpdate();
		
		transaction.commit();
		Query query2 = session.createQuery("from UserItemPojo");
		List<UserItemPojo> itemInfo = query2.list();
		model.addAttribute("message", "delete rows sucessfully..!!");
		model.addAttribute("menu", itemInfo);
		session.close();
	
		
		return "showmenu";
	}
}

	

