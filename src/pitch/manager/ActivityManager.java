package pitch.manager;

import java.util.Map;

import pitch.model.SubActivity;

public interface ActivityManager {
	public Status addPitchActivity(int userId,String name,String detail, int needDepartmentId, int boyFirst);
	public Status addSubActivity(Map<String,Object> request);
	public Status addHeader(Map<String,Object> request);
	public Status assign(Map<Integer, SubActivity> distributionChooseUser);
	public Status checkIn(Map<String,Object> request);
	public Status replace(Map<String,Object> request);
	public Status getSubActivity(int pitchActivityId);
	public Status getActivity(int pitchActivityId);
	
}
