package pitch.manager;

import java.util.Map;

public interface ActivityManager {
	public Status addPitchActivity(int userId,String name,String detail);
	public Status addSubActivity(Map<String,Object> request);
	public Status addHeader(String studentNumber,int subPitchActivityId);
	public Status assign(Map<String,Object> request);
	public Status checkIn(int userId,int activityId);
	public Status replace(int oldUserId,String newUserStudentNumber,int activityId);
	
}
