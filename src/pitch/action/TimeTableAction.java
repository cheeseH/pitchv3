package pitch.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;
import com.xiao.util.json.xjson.JSONObj;
import com.xiao.util.json.xjson.JSONObjFactory;

import net._100steps.bbter.service.api.rmi.UserManagerRO;
import pitch.manager.Status;
import pitch.manager.TimeTableManager;

public class TimeTableAction extends ActionSupport {
	TimeTableManager timeTableManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8949514484260784159L;

	
	public  String getTimeTable(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		int userId = Integer.parseInt(((String[])parameters.get("userId"))[0]);
		Status status = timeTableManager.getTimeTable(userId);
		JMapObj jMap = new JMapObj();
		jMap.put("code", status.getCode());
		JArrayObj msg = (JArrayObj)status.getMsg();
		if(status.isOk()){
			jMap.put("data", msg);
		}else{
			jMap.put("msg", msg);
		}
		ActionContext.getContext().put("timeTable", jMap);
		if(status.isOk()){
			return "success";
		}else{
			return "fail";
		}
		
	}
	
	public String editTimeTable(){
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		int userId = Integer.parseInt(((String[])parameters.get("userId"))[0]);
		JSONObject Json = new JSONObject(((String[])parameters.get("date"))[0]);
		String[][] table = (String[][]) Json.get("table");
		Map<Integer,Integer> timeList = new HashMap<Integer,Integer>();
		for(int i = 0 ; i < 7 ; i++){
			for(int j = 0 ;j<6;j++){
				timeList.put(i+1, Integer.parseInt(table[j][i]));
			}
		}
		Status status = timeTableManager.EditTimeTable(timeList, userId);
		ActionContext.getContext().put("code", status.getCode());
		ActionContext.getContext().put("msg", status.getMsg());
		return "result";
	}
}
