package Server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controlers.HibernateUtil;

public class Kontsultak {
	
	/**
	 * Ejecuta el HQL query recibido y devuelve una lista de tipo Objeto como resultado
	 *
	 * @param query de HQL query para ejecutar
	 * @return lista de tipo Objeto de la query
	 */
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
/**
 * Ejecuta el HQL query recibido y devuelve un entero con el numero de filas afectadas
 * 
 * Los querys que afectan a la base de datos como insert, update o delete
 * 
 * transaction.commit() para confirmar los cambios
 * transaction.rollback() para deshacer
 * 
 * @param query
 * @return numero de filas afectadas
 */
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
	public static String getAllAlumnos() {

		String query = "";
		query = "FROM Users WHERE tipos.name = 'alumno'";
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

	public static String getHorariosByProfeId(String param) {

		String query = "";
		query = "FROM Horarios h WHERE h.id.profeId  = '" + param + "'";
		System.out.println(query);
		return query;

	}

//	public static String getHorariosByAlumnoId(String param) {
//
//		String query = "";
//		 query = "select distinct h from Horarios as h "
//                + "join fetch h.modulos as m "
//                + "join fetch h.users as u "
//                + "join fetch u.tipos as t "
//                + "join fetch m.ciclos as c "
//                + "join fetch c.matriculacioneses as mat "
//                + "where mat.id.alumId = '" + param  + " "
//                + "' and m.nombre not in ('Tutoria', 'Guardia')";
//		System.out.println(query);
//		return query;
//
//	}
	
	public static String getHorariosByAlumnoId(String param) {

		String query = "";
		query = "FROM VistaHorariosUsuarios v WHERE v.alumId = '" + param + "'";
		System.out.println(query);
		return query;

	}

	public static String isLoginOk(String user, String pass) {

		String query = "";
		query = "FROM Users WHERE username = '" + user + "' and password = '" + pass + "'";
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

	public static String getBilerakByProfesorId(String param) {
		String query = "";
		query = "FROM Reuniones WHERE usersByProfesorId.id = '" + param + "' ";
		return query;

	}
	
	public static String getBilerakByAlumnoId(String param) {
		String query = "";
		query = "FROM Reuniones WHERE usersByAlumnoId.id = '" + param + "' ";
		return query;

	}

	public static String getMatriculacionByUserId(String param) {
		String query = "";
		query = "FROM Matriculaciones WHERE users.id  = '" + param + "' ";
		return query;

	}
	
	public static String updateStatus(String estado, String id) {
        String query = "";
        query = "UPDATE Reuniones SET estado = '" + estado + "' WHERE id = '" + id + "' ";
        System.out.println(query);
        return query;
    }
	
	
	
	


	
	
}
