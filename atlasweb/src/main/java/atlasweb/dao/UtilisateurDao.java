package atlasweb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import atlasweb.conf.HibernateUtil;
import atlasweb.model.Utilisateur;
import atlasweb.service.SendSmsBasic;

public class UtilisateurDao {

    public void saveUser(Utilisateur user) {
        Transaction transaction = null;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();            
            session.save(user);
            transaction.commit();
            SendSmsBasic sms = new SendSmsBasic();
            sms.sendSMS();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

  /* 
    public void updateUser(Utilisateur user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Utilisateur user = session.get(Utilisateur.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
*/
    public Utilisateur getUser(int id) {

        Transaction transaction = null;
        Utilisateur user = null;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (Utilisateur) session.get(Utilisateur.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    @SuppressWarnings("unchecked")
    public List < Utilisateur > getAllUser() {

        Transaction transaction = null;
        List < Utilisateur > listOfUser = null;

        try {
        	Session session = atlasweb.conf.HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            listOfUser = session.createQuery("from Utilisateur").list();
            for (Utilisateur utilisateur : listOfUser) {
				System.out.println(">>" +utilisateur.getName() + utilisateur.getRole().getName());
			}
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listOfUser;
    }
}
