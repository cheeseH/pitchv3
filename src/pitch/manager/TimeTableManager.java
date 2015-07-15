package pitch.manager;

import java.util.Map;

import pitch.model.TimeTable;

public interface TimeTableManager {
	public void EditTimeTable(Map<Integer,Integer> timeList,int userId);
	public TimeTable getTimeTable(int userId);
	
	
}
