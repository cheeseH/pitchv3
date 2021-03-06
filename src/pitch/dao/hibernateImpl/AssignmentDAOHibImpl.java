package pitch.dao.hibernateImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pitch.dao.AssignmentDAO;
import pitch.dao.DAOException;
import pitch.model.Assignment;

public class AssignmentDAOHibImpl implements AssignmentDAO {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void add(Assignment assignment) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.openSession().save(assignment);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void remove(int assignmentId) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.openSession().createQuery("delete from Assignment as a where a.id=?").setInteger(0, assignmentId);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public void update(Assignment assignment) {
		// TODO Auto-generated method stub
		try{
			this.sessionFactory.openSession().update(assignment);
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public Assignment getById(int assignmentId) {
		// TODO Auto-generated method stub
		try{
			return (Assignment)this.sessionFactory.openSession().createQuery("from Assignment as a where a.id = ?").setInteger(0, assignmentId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Assignment> getBySubActivityId(int saId) {
		// TODO Auto-generated method stub
		try{
			return (List<Assignment>)this.sessionFactory.openSession().createQuery("from Assignment as a where a.subActivityId = ?").list();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
		
	}

	@Override
	@Transactional
	public Assignment getByActivityAndUserId(int activityId, int userId) {
		// TODO Auto-generated method stub
		try{
			return (Assignment)this.sessionFactory.openSession().createQuery("from Assignment as a where a.userId = ? and a.subPitchActivityId = ?").setInteger(0, userId).setInteger(1, activityId).uniqueResult();
		}catch(HibernateException e){
			throw new DAOException(e);
		}
	}

}
