package pitch.manager;

import java.util.Map;

import pitch.model.SubActivity;

public interface ActivityManager {
	public Status addPitchActivity(int userId,String name,String detail, int needDepartmentId, int boyFirst);
	public Status addSubActivity(Map<String,Object> request);
	public Status addHeader(String studentNumber,int subPitchActivityIdt);
	public Status assign(Map<Integer, SubActivity> distributionChooseUser);
	public Status checkIn(int userId,int activityId);
	public Status replace(int oldUserId,String newUserStudentNumber,int activityId);
	public Status getSubActivity(int pitchActivityId);
	public Status getActivity(int pitchActivityId);
	
}
