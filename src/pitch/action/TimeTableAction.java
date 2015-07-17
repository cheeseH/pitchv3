package pitch.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

import pitch.manager.Status;
import pitch.manager.TimeTableManager;

public class TimeTableAction extends ActionSupport {
	TimeTableManager timeTableManager;
	HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * 
	 */
	private static final long serialVersionUID = 8949514484260784159L;

	
	public  String getTimeTable(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		int userId = ((Integer[])parameters.get("userId"))[0].intValue();
		Status status = timeTableManager.getTimeTable(userId);
		JMapObj jMap = new JMapObj();
		jMap.put("code", status.getCode());
		JArrayObj msg = (JArrayObj)status.getMsg();
		if(status.isOk()){
			jMap.put("data", msg);
		}else if(status.getCode() != 500){
			jMap.put("msg", msg);
		}
		request.setAttribute("timeTable", jMap);
		if(status.isOk()){
			return "success";
		}else{
			return "fail";
		}
		
	}
	
	public String editTimeTable(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		int userId = ((Integer[])parameters.get("userId"))[0].intValue();
		int timeTable = ((Integer[])parameters.get("timeTable"))[0].intValue();
//		timeTableManager.EditTimeTable(timeList, userId)
		return "result";
	}
	
}
