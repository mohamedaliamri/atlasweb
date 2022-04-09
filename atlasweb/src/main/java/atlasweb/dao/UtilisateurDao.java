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
            sms.sendSMS(user.getPhoneNumber(),user.getCode());
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
    public Utilisateur getUser(String phoneNumber) {

        Transaction transaction = null;
        Utilisateur user = null;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (Utilisateur) session.get(Utilisateur.class, phoneNumber);
            System.out.println("Phone Number "+phoneNumber);
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
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}
