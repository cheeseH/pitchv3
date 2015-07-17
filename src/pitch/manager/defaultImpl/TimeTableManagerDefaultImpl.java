package pitch.manager.defaultImpl;

import java.util.Map;
import java.util.Map.Entry;

import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

import pitch.dao.DAOException;
import pitch.dao.TimeTableDAO;
import pitch.manager.Status;
import pitch.manager.TimeTableManager;
import pitch.model.TimeTable;

public class TimeTableManagerDefaultImpl implements TimeTableManager {
	TimeTableDAO timeTableDAO;
	
	public void setTimeTableDAO(TimeTableDAO timeTableDAO){
		this.timeTableDAO = timeTableDAO;
	}
	@Override
	public Status EditTimeTable(Map<Integer, Integer> timeList, int userId) {
		// TODO Auto-generated method stub
		try{
			TimeTable t = new TimeTable();
			t.setUserId(userId);
			for(Entry<Integer,Integer> entry : timeList.entrySet()){
				if(entry.getValue().intValue() == 1){
					int key = entry.getKey().intValue();
					int date = (key-1)/5+1;
					int lession = (key-1)%5+1;
					t.addLession(date, lession);
				}
			}
			TimeTable timeTable = timeTableDAO.getByUserId(userId);
			if(timeTable == null){
				timeTableDAO.add(t);
			}else{
				timeTableDAO.update(t);
			}
		}catch(DAOException e){
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
		return Status.OK();
	}

	@Override
	public Status getTimeTable(int userId) {
		// TODO Auto-generated method stub
		try{
			TimeTable timeTable = timeTableDAO.getByUserId(userId);
			if(timeTable == null){
				throw new DAOException(); 
			}
			int table = timeTable.getTable();
			JArrayObj msg = new JArrayObj();
			for(int i = 0 ; i < 25 ; i++){
				JMapObj jMap = new JMapObj();
				jMap.put((i+1)+"", table&(1<<i));
				msg.add(jMap);
			}
			Status status = new Status();
			status.setMsg(msg);
			return status;
		}catch(DAOException e){
			Status s = new Status();
			s.setCode(500);
			s.setSuccess(false);
			return s;
		}
		
	}

}