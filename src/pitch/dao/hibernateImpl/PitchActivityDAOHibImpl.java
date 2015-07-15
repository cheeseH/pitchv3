package pitch.dao.hibernateImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pitch.dao.DAOException;
import pitch.dao.PitchActivityDAO;
import pitch.model.PitchActivity;

public class PitchActivityDAOHibImpl implements PitchActivityDAO {

	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void add(PitchActivity pa) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().save(pa);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void update(PitchActivity pa) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().update(pa);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void remove(int activityId) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().createQuery("from PitchActivity as pa where pa.id = ?").setInteger(0, activityId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public PitchActivity getById(int activityId) {
		// TODO Auto-generated method stub
		try{
			return (PitchActivity)this.sessionFactory.getCurrentSession().createQuery("from PitchActivity as pa where pa.id = ?").setInteger(0, activityId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PitchActivity> getAll() {
		// TODO Auto-generated method stub
		try{
			return (List<PitchActivity>)this.sessionFactory.getCurrentSession().createQuery("from PitchActivity as pa").list();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}
	
	
	
}
