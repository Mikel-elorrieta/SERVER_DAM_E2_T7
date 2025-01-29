package Server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controlers.HibernateUtil;

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

	public static int conectarCrud(String query) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int rowsAffected = 0;

		try {
			transaction = session.beginTransaction();
			Query q = session.createQuery(query);
			rowsAffected = q.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return rowsAffected;
	}

	public static String getAllUsers() {

		String query = "";
		query = "FROM Users";
		return query;

	}
	
	public static String getAllTeachers() {

		String query = "";
		query = "FROM Users WHERE tipos.name = 'profesor'";
		return query;

	}

	public static String getUserByName(String param) {

		String query = "";
		query = "FROM Users WHERE username = '" + param + "' ";
		return query;

	}

	public static String getUserByEmail(String param) {

		String query = "";
		query = "FROM Users WHERE email = '" + param + "' ";
		return query;
	}

	public static String getHorariosByUserId(String param) {

		String query = "";
		query = "FROM Horarios h WHERE h.id.profeId  = '" + param + "' OR h.users.id = '" + param + "' ";
		System.out.println(query);
		return query;

	}

	public static String isLoginOk(String user, String pass) {

		String query = "";
		query = "FROM Users WHERE username = '" + user + "' AND password = '" + pass + "' ";
		System.out.println(query);
		return query;

	}
	
	public static String updateImg(String img, String param) {

		String query = "";
		query = "UPDATE Users SET argazkia = '" + img + "' WHERE id = '" + param + "' ";
		System.out.println(query);
		return query;

	}

	public static String updatePassword(String param, String pass) {

		String query = "";
		query = "UPDATE Users SET password = '" + pass + "' WHERE id = '" + param + "' ";
		System.out.println(query);
		return query;
	}

	public static String getBilerakByUserId(String param) {
		String query = "";
		query = "FROM Reuniones WHERE profesor_id = '" + param + "' ";
		return query;

	}
	
	public static String getMatriculacionByUserId(String param) {
		String query = "";
		query = "FROM Matriculaciones WHERE users.id  = '" + param + "' ";
		return query;
		
	}
}
