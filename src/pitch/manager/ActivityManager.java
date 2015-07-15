package pitch.manager;

import java.util.Map;

public interface ActivityManager {
	public Status addPitchActivity(int userId,String name,String detail);
	public Status addSubActivity(Map<String,Object> request);
	public Status addHeader(Map<String,Object> request);
	public Status assign(Map<String,Object> request);
	public Status checkIn(Map<String,Object> request);
	public Status replace(Map<String,Object> request);
	
}
