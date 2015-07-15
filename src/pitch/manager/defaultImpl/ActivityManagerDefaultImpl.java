package pitch.manager.defaultImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pitch.dao.DAOException;
import pitch.dao.PitchActivityDAO;
import pitch.manager.ActivityManager;
import pitch.manager.Status;
import pitch.model.PitchActivity;
import pitch.model.SubActivity;

public class ActivityManagerDefaultImpl implements ActivityManager {

	PitchActivityDAO pitchActivityDAO;
	
	public void setPitchActivityDAO(PitchActivityDAO pitchActivityDAO) {
		this.pitchActivityDAO = pitchActivityDAO;
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
		
		for(Entry<String,Object> entry:request.entrySet()){
			String key = entry.getKey();
			String value = (String)entry.getValue();
			
		}
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
