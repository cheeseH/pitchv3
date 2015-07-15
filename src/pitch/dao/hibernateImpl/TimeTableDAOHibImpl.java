package pitch.dao.hibernateImpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pitch.dao.DAOException;
import pitch.dao.TimeTableDAO;
import pitch.model.TimeTable;

public class TimeTableDAOHibImpl implements TimeTableDAO{

SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void add(TimeTable timeTable) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().save(timeTable);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void remove(int ttId) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().createQuery("from TimeTbale as tt where tt.id = ?").setInteger(0, ttId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
		
	}

	@Override
	@Transactional
	public void update(TimeTable timeTable) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().update(timeTable);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	

	@Override
	@Transactional
	public TimeTable getById(int id) {
		// TODO Auto-generated method stub
		try{
			return (TimeTable)this.sessionFactory.getCurrentSession().createQuery("from TimeTable as tt where tt.id = ?").setInteger(0,id ).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}
	@Override
	@Transactional
	public TimeTable getByUserId(int userId) {
		// TODO Auto-generated method stub
		try{
			return (TimeTable)this.sessionFactory.getCurrentSession().createQuery("from TimeTable as tt where tt.userId = ?").setInteger(0, userId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

}
