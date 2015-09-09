package pitch.dao.hibernateImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pitch.dao.DAOException;
import pitch.dao.TimeTableDAO;
import pitch.model.TimeTable;

public class TimeTableDAOHibImpl implements TimeTableDAO{

SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void add(TimeTable timeTable) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.openSession().save(timeTable);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	public void remove(int ttId) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.openSession().createQuery("from TimeTbale as tt where tt.id = ?").setInteger(0, ttId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
		
	}

	@Override
	public void update(TimeTable timeTable) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().update(timeTable);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	

	@Override
	public TimeTable getById(int id) {
		// TODO Auto-generated method stub
		try{
			return (TimeTable)this.sessionFactory.getCurrentSession().createQuery("from TimeTable as tt where tt.id = ?").setInteger(0,id).uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}
	@Override
	public TimeTable getByUserId(int userId) {
		// TODO Auto-generated method stub
		try{
			return (TimeTable)sessionFactory.getCurrentSession().createQuery("from TimeTable as tt where tt.userId = ?").setInteger(0, userId).uniqueResult();
			//TimeTable tt = (TimeTable)this.sessionFactory.openSession().createQuery("from TimeTable as tt where tt.userId = ?").setInteger(0, userId).uniqueResult();
			
		}catch(HibernateException e){
			e.printStackTrace();
			throw new DAOException(e);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

}
