package atlasweb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import atlasweb.conf.HibernateUtil;
import atlasweb.model.Bracelet;
import atlasweb.model.Utilisateur;
import atlasweb.service.FirstPdf;
import atlasweb.service.SendSmsBasic;

public class BraceletDao {

	public void saveBracelet(Bracelet bracelet) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(bracelet);
			transaction.commit();
			SendSmsBasic sms = new SendSmsBasic();
			sms.sendSMS(bracelet.getUtilisateur().getPhoneNumber(), "Bracelet : prix = " + bracelet.getPrix());
			FirstPdf firstPdf = new FirstPdf();
			firstPdf.generatePdf(bracelet);
		} catch (Exception e) {
			System.out.println(">>> "+e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
    @SuppressWarnings("unchecked")
    public List < Bracelet > getAllBracelet() {

        Transaction transaction = null;
        List < Bracelet > res = null;

        try {
        	Session session = atlasweb.conf.HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            res = session.createQuery("from Bracelet").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return res;
    }

}
