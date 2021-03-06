package pitch.dao.hibernateImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pitch.dao.DAOException;
import pitch.dao.PitchUserDAO;
import pitch.model.PitchUser;
import pitch.model.TimeTable;

public class PitchUserDAOHibImpl implements PitchUserDAO {
	
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void add(PitchUser user) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}
	@Override
	public void remove(int userId) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().createQuery("delete from PitchUser as pu where pu.id = ").setInteger(0, userId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	
	}

	@Override
	public void update(PitchUser user) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(user);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public PitchUser getById(int UserId) {
		try{
			return (PitchUser)sessionFactory.getCurrentSession().createQuery(" from PitchUser as pu where pu.id = ? ").setInteger(0, UserId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public PitchUser getByStudentNumber(String studentNumber) {
		// TODO Auto-generated method stub
		try{
			return (PitchUser)sessionFactory.getCurrentSession().createQuery(" from PitchUser as pu "
					+ "where pu.id = ?").setString(0, studentNumber).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}

	}

	@Override
	public List<PitchUser> getAll() {
		// TODO Auto-generated method stub
		try{
			return sessionFactory.openSession().createQuery("from PitchUser").list();
		}catch(HibernateException e){
			throw new DAOException(e);
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}

	


}
