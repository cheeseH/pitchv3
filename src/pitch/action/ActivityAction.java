package pitch.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pitch.manager.ActivityManager;
import pitch.manager.Status;

public class ActivityAction extends ActionSupport {
	ActivityManager activityManager;
	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1440648322011330107L;
	
	public String addActivity(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		String userIdString = ((String[])parameters.get("userId"))[0];
		int userId = Integer.parseInt(userIdString);
		String name = ((String[])parameters.get("name"))[0];
		String detail = ((String[])parameters.get("detail"))[0];
		System.out.println(detail);
		Status status = activityManager.addPitchActivity(userId, name, detail);
		if(status.isOk())
			return "success";
		System.out.println(status.getMsg().toJSONString());
		return "fail";
	}
	public String addSubActivity(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		Status status = activityManager.addSubActivity(parameters);
		if(status.isOk()){
			return "success";
		}
		return "fail";
	}
	
	public String assign(){
		
		return "result";
	}
	
	public String assignBoss(){
		
		return "result";
	}
	
	

}
