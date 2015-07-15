package pitch.dao.hibernateImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pitch.dao.DAOException;
import pitch.dao.SubActivityDAO;
import pitch.model.SubActivity;

public class SubActivityDAOHibImpl implements SubActivityDAO{

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void add(SubActivity activity) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().save(activity);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void remove(int activityId) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().createQuery("from SubActivity as sa where sa.id = ?").setInteger(0, activityId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void update(SubActivity activity) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.getCurrentSession().update(activity);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<SubActivity> getByPitchId(int pitchActivityId) {
		// TODO Auto-generated method stub
		try{
			return (List<SubActivity>)this.sessionFactory.getCurrentSession().createQuery("from SubActivity as sa where sa.pitchActivityId = ?").setInteger(0,pitchActivityId).list();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public SubActivity getById(int activityId) {
		// TODO Auto-generated method stub
		try{
			return (SubActivity)this.sessionFactory.getCurrentSession().createQuery("from PitchActivity as pa where pa.id = ?").setInteger(0, activityId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

}
