package pitch.manager;

import java.util.Map;

import pitch.model.TimeTable;

public interface TimeTableManager {
	public Status EditTimeTable(Map<Integer,Integer> timeList,int userId);
	public Status getTimeTable(int userId);
	
	
}
