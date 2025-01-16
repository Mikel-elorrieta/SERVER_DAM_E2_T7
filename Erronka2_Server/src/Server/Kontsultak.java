package Server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Controlers.HibernateUtil;
import modelo.Users;

public class Kontsultak {

	public static Object conectar(String query) {

		ArrayList<Object> list = new ArrayList();

		Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = query;
		Query q = session.createQuery(hql);
		List<Object> emps = (List<Object>) q.list();
		for (int i = 0; i < emps.size(); i++) {
			Object use = emps.get(i);
			list.add(use);
		}
		System.out.println(list);
		session.close();
		return list;
	}
	
	
	
	public static String getAllUsers() {
		
		String query = "";
		query = "FROM Users";
		return query;
		
	}
	public static String getUserByName(String param) {
		
		String query = "";
		query = "FROM Users WHERE nombre = '"+  param  +"' ";
		return query;
		
	}

}
