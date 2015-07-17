package pitch.manager.defaultImpl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pitch.dao.DAOException;
import pitch.dao.PitchActivityDAO;
import pitch.dao.SubActivityDAO;
import pitch.manager.ActivityManager;
import pitch.manager.Status;
import pitch.model.PitchActivity;
import pitch.model.SubActivity;

public class ActivityManagerDefaultImpl implements ActivityManager {

	PitchActivityDAO pitchActivityDAO;
	SubActivityDAO subActivityDAO;
	public void setPitchActivityDAO(PitchActivityDAO pitchActivityDAO) {
		this.pitchActivityDAO = pitchActivityDAO;
	}
	public void setSubActivityDAO(SubActivityDAO subActivityDAO){
		this.subActivityDAO = subActivityDAO;
	}

	@Override
	public Status addPitchActivity(int userId,String name, String detail) {
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
		try{
			this.pitchActivityDAO.add(pa);
			System.out.println("11");
		}catch(DAOException e){
			e.printStackTrace();
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
					System.out.println(key);
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
	public Status addHeader(Map<String, Object> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status assign(Map<String, Object> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status checkIn(Map<String, Object> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status replace(Map<String, Object> request) {
		// TODO Auto-generated method stub
		return null;
	}

}
