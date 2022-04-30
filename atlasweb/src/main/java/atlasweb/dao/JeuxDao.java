package atlasweb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import atlasweb.model.Jeux;

public class JeuxDao {

	@SuppressWarnings("unchecked")
	public Jeux getJeux(String name) {
		Transaction transaction = null;
		List<Jeux> listOfJeux = null;
		try {
			Session session = atlasweb.conf.HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String hql = "FROM Jeux E WHERE E.name = '" + name+"'";
			listOfJeux = session.createQuery(hql).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfJeux.get(0);
	}

}
