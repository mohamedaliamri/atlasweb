package atlasweb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import atlasweb.conf.HibernateUtil;
import atlasweb.model.Role;

public class RoleDao {

    public Role getRole(String name) {

        Transaction transaction = null;
        Role role = null;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            role =  (Role) session.get(Role.class, name);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return role;
    }
}
