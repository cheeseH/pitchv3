package pitch.manager.defaultImpl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

<<<<<<< HEAD
=======
import org.json.JSONObject;

>>>>>>> qiang
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

import pitch.dao.AssignmentDAO;
import pitch.dao.DAOException;
import pitch.dao.PitchActivityDAO;
import pitch.dao.PitchUserDAO;
import pitch.dao.SubActivityDAO;
import pitch.manager.ActivityManager;
import pitch.manager.Status;
import pitch.model.Assignment;
import pitch.model.PitchActivity;
import pitch.model.PitchUser;
import pitch.model.SubActivity;

public class ActivityManagerDefaultImpl implements ActivityManager {

	PitchActivityDAO pitchActivityDAO;
	SubActivityDAO subActivityDAO;
	PitchUserDAO	pitchUserDAO;
	AssignmentDAO assignmentDAO;
	public void setPitchActivityDAO(PitchActivityDAO pitchActivityDAO) {
		this.pitchActivityDAO = pitchActivityDAO;
	}
	public void setSubActivityDAO(SubActivityDAO subActivityDAO){
		this.subActivityDAO = subActivityDAO;
	}

	@Override
	public Status addPitchActivity(int userId,String name, String detail,int needDepartmentId,int boyFirst) {
		// TODO Auto-generated method stub
		if(name == null){
			Map<String,Integer> errMsg = new HashMap<String,Integer>();
			errMsg.put("name", 402);
			return Status.Error(501,errMsg);
		}
		if(detail == null){
			Map<String,Integer> errMsg = new HashMap<String,Integer>();
			errMsg.put("detail", 402);
			return Status.Error(501,errMsg);
		}
		PitchActivity pa = new PitchActivity();
		pa.setName(name);
		pa.setDetail(detail);
		pa.setNeedDepartmentId(needDepartmentId);
		pa.setBoyFirst(boyFirst);
		try{
			this.pitchActivityDAO.add(pa);
		}catch(DAOException e){
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
		return Status.OK();
		
	}

	@Override
	public Status addSubActivity(Map<String, Object> request) {
		// TODO Auto-generated method stub
		SubActivity sa = new SubActivity();
		Method[] methods = sa.getClass().getMethods();
		try{
			for(Method method:methods){
				String methodName = method.getName().toString();
				if(methodName.startsWith("set")){
					Class<?>[] parameterTypes = method.getParameterTypes();
					String key = methodName.substring(3);
					if(!key.equals("Id")){
						if(parameterTypes[0] == String.class){
							method.invoke(sa, ((String[])request.get(key))[0]);
						}else if(parameterTypes[0] == int.class){
							method.invoke(sa,Integer.parseInt(((String[])request.get(key))[0]));
						}
					}
					
					
				}
			}
		subActivityDAO.add(sa);	
		}catch(Exception e){
			e.printStackTrace();
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
		return Status.OK();
	}

	@Override
	public Status addHeader(String studentNumber,int subPitchActivityIdt) {
		// TODO Auto-generated method stub
		try{
			Status s = new Status();
			PitchUser user = pitchUserDAO.getByStudentNumber(studentNumber);
			SubActivity sa = subActivityDAO.getById(subPitchActivityIdt);
			sa.setHeader(user.getId());
			Assignment assignment = assignmentDAO.getByActivityAndUserId(sa.getId(), user.getId());
			if(assignment == null){
				Assignment newAssignment = new Assignment();
				newAssignment.setSubActivityId(subPitchActivityIdt);
				newAssignment.setUserId(user.getId());
				assignmentDAO.add(newAssignment);
			}
			subActivityDAO.update(sa);
			return Status.OK();
		}catch(DAOException e){
			e.printStackTrace();
			return Status.Error(500, null);
			
		}
	}

	@Override
	public Status assign(Map<Integer, SubActivity> request) {
		// TODO Auto-generated method stub
		Assignment assignment = new Assignment();
		for(Entry<Integer,SubActivity> entry : request.entrySet()){
			assignment.setUserId(entry.getKey());
			assignment.setSubActivityId(entry.getValue().getId());
			assignmentDAO.add(assignment);
		}
		return null;
	}

	@Override
	public Status checkIn(int userId,int activityId) {
		// TODO Auto-generated method stub
		try{
			Assignment assgnment =  assignmentDAO.getByActivityAndUserId(activityId, userId);
			if(assgnment == null){
				JMapObj map = new JMapObj();
				JArrayObj array = new JArrayObj();
				map.put("assginment", "not such an assignment found");
				array.add(map);
				Status s  = new Status ();
				s.setCode(501);
				s.setMsg(array);
				return s;
			}
			assgnment.setChecked(1);
			assignmentDAO.update(assgnment);
			return Status.OK();
		}catch(DAOException e){
			e.printStackTrace();
			return Status.Error(500, null);
		}
	}

	@Override
	public Status replace(int oldUserId,String newUserStudentNumber,int activityId) {
		// TODO Auto-generated method stub
		try{
			Assignment assgnment =  assignmentDAO.getByActivityAndUserId(activityId, oldUserId);
			if(assgnment == null){
				JMapObj map = new JMapObj();
				JArrayObj array = new JArrayObj();
				map.put("assginment", "not such an assignment found");
				array.add(map);
				Status s  = new Status ();
				s.setCode(501);
				s.setMsg(array);
				return s;
			}
		
			PitchUser user = pitchUserDAO.getByStudentNumber(newUserStudentNumber);
			if(user == null){
				JMapObj map = new JMapObj();
				JArrayObj array = new JArrayObj();
				map.put("studentNumber", "no such a user  found");
				array.add(map);
				Status s  = new Status ();
				s.setCode(501);
				s.setMsg(array);
				return s;
			}
			Assignment newAssignment = new Assignment();
			newAssignment.setSubActivityId(activityId);
			newAssignment.setUserId(user.getId());
			newAssignment.setChecked(1);
			assignmentDAO.add(newAssignment);
			assignmentDAO.remove(assgnment.getId());
			return Status.OK();
		}catch(DAOException e){
			e.printStackTrace();
			return Status.Error(500, null);
		}
	}
	@Override
	public Status getSubActivity(int pitchActivityId) {
		// TODO Auto-generated method stub
		try{
			List<SubActivity> subActivityList = subActivityDAO.getByPitchId(pitchActivityId);
			Status ok = Status.OK();
			JArrayObj msg = new JArrayObj();
			JMapObj jmap = new JMapObj();
			jmap.put("subActivity", subActivityList);
			msg.add(jmap);
			ok.setMsg(msg);
			return ok;
		}catch(DAOException e){
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
	}
	@Override
	public Status getActivity(int pitchActivityId) {
		// TODO Auto-generated method stub
		try{
			PitchActivity activity = pitchActivityDAO.getById(pitchActivityId);
			Status ok = Status.OK();
			JArrayObj msg = new JArrayObj();
			JMapObj jmap = new JMapObj();
			jmap.put("pitchActivity", activity);
			msg.add(jmap);
			ok.setMsg(msg);
			return ok;
		}catch(DAOException e){
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
		
	}

}
