package pitch.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

import net._100steps.bbter.service.api.rmi.DepartmentManagerRO;
import net._100steps.bbter.service.api.rmi.UserManagerRO;
import net._100steps.bbter.service.model.Department;
import net._100steps.bbter.service.model.User;
import net._100steps.bbter.service.model.UserInfo;
import net._100steps.general.message.Message;
import pitch.manager.ActivityManager;
import pitch.manager.PitchUserManager;
import pitch.manager.Status;
import pitch.manager.TimeTableManager;
import pitch.model.Conflict;
import pitch.model.PitchActivity;
import pitch.model.PitchUser;
import pitch.model.SubActivity;

public class ActivityAction extends ActionSupport {
	ActivityManager activityManager;
	DepartmentManagerRO departmentManagerRO;
	UserManagerRO userManagerRO;
	TimeTableManager timeTableManager;
	PitchUserManager pitchUserManager;
	
	public void setPitchUserManager(PitchUserManager pitchUserManager) {
		this.pitchUserManager = pitchUserManager;
	}
	public void setDepartmentManagerRO(DepartmentManagerRO departmentManagerRO){
		this.departmentManagerRO = departmentManagerRO;
	}
	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}
	
	public void setUserManagerRO(UserManagerRO userManagerRO) {
		this.userManagerRO = userManagerRO;
	}
	public void setTimeTableManager(TimeTableManager timeTableManager) {
		this.timeTableManager = timeTableManager;
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
		String needDepartmentNameJson = ((String[])parameters.get("NeedDepartmentId"))[0];
		int needDepartmentId = getNeedDepartmentId(needDepartmentNameJson);
		String boyFirstString = ((String[])parameters.get("boyFirst"))[0];
		int boyFirst = Integer.parseInt(boyFirstString);
		Status status = activityManager.addPitchActivity(userId, name, detail,needDepartmentId,boyFirst);
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
	public int getNeedDepartmentId(String needDepartmentNameJson){
		int needDepartmentId = 0;
		try {
			JSONObject json = new JSONObject(needDepartmentNameJson);
			Message msg = departmentManagerRO.getAllDepartments();
			Map<String,Integer> map = new HashMap<String,Integer>();
			List<Department> data = (List<Department>) msg.getDataTree().get(1).getData();
			for(Department d:data){
				map.put(d.getName(), d.getId());
			}
			for(int i = 0 ; i < json.length() ; i++){
				String name = (String) json.get(i+1+"");
				int departmentId = map.get(name);
				needDepartmentId |= (1<<departmentId);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return needDepartmentId;
	}
	public String distribution(){
		try {
			int pitchActivityId = Integer.parseInt(ServletActionContext.getRequest().getParameter("pitchActivityId"));
			Status status = activityManager.getSubActivity(pitchActivityId);
			JArrayObj msg = status.getMsg();
			JMapObj jmap = (JMapObj) msg.get(0);
			List<SubActivity> subActivityList = (List<SubActivity>) jmap.get("subActivity");
			Status statusActivity = activityManager.getActivity(pitchActivityId);
			JArrayObj msg2 = statusActivity.getMsg();
			JMapObj jmap2 = (JMapObj)msg2.get(0);
			PitchActivity pitchActivity = (PitchActivity) jmap2.get("pitchActivity");
			List<PitchUser> userList = pitchUserManager.searchAll();
			
			Map<Integer, SubActivity> distributionChooseUser = distributionChooseUser(pitchActivity,subActivityList,userList);
			
			activityManager.assign(distributionChooseUser);
			return "success";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
		
	}
	/*
	 * 初步筛选摆摊人员
	 */
	public Map<Integer,SubActivity> distributionChooseUser(PitchActivity pitchActivity,List<SubActivity> subActivityList,List<PitchUser> userList){
		try{
		Map<SubActivity,List<PitchUser>> map = new HashMap<SubActivity,List<PitchUser>> ();
		for(SubActivity sa : subActivityList){
			List<PitchUser> subUserList = new ArrayList<PitchUser>();
			int day = sa.getDay();
			int lession = sa.getLession();
			int needDepartmentId = pitchActivity.getNeedDepartmentId();
			for(PitchUser user : userList){
				List<Integer> l = new ArrayList<Integer>();
				l.add(user.getId());
				Message userDetailsByUserId = userManagerRO.getUserDetailsByUserId(l);
				List<UserInfo> data = (List<UserInfo>) userDetailsByUserId.getDataTree().get(1).getData();
				Department department = data.get(0).getDepartment();
				if(((needDepartmentId&(1<<department.getId()))>>1)==1){
					Status status = timeTableManager.getTimeTable(user.getId());
					JMapObj jmap = (JMapObj) status.getMsg().get(0);
					int hasClass = (int) jmap.get(day*6+lession+"");
					if(hasClass == 0){
						subUserList.add(user);
					}
				}
				
			}
			Collections.sort(subUserList,new Comparator<PitchUser>(){

				@Override
				public int compare(PitchUser o1, PitchUser o2) {
					// TODO Auto-generated method stub
					return -(o1.getPitchTimes()-o2.getPitchTimes());
				}
				
			});
			map.put(sa, subUserList);
		}
		return distributionAchieve(pitchActivity,map);
		}catch(RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public Map<Integer,SubActivity> distributionAchieve(PitchActivity pitchActivity,Map<SubActivity,List<PitchUser>> subUserList){
		Map<Integer,SubActivity> hasChoosed = new HashMap<Integer,SubActivity>();
		List<Conflict> conflict = new ArrayList<Conflict>(); 
		try{
		for(Entry<SubActivity,List<PitchUser>> entry:subUserList.entrySet()){
			List<PitchUser> value = entry.getValue();
			SubActivity subActivity = entry.getKey();
			int subAcitivityId = subActivity.getId();
			int needNum = subActivity.getNeedNumber();
			int needDepartmentId = pitchActivity.getNeedDepartmentId();
			for (int i = 0; i < needNum; i++) {
				
				for(PitchUser user : value){
					if(needDepartmentId!=0){
						List<Integer> l = new ArrayList<Integer>();
						l.add(user.getId());
						Message userDetailsByUserId = userManagerRO.getUserDetailsByUserId(l);
						List<UserInfo> data = (List<UserInfo>) userDetailsByUserId.getDataTree().get(1).getData();
						Department department = data.get(0).getDepartment();
						if(((needDepartmentId&(1<<department.getId()))>>department.getId())==1){
							if(!hasChoosed.containsKey(user.getId())){
								hasChoosed.put(user.getId(), subActivity);
								value.remove(user);
								break;
							}else{
								Conflict con = new Conflict();
								con.setUserId(user.getId());
								con.setSubActivity(subActivity);
								con.setDepartmentId(department.getId());
								con.setUerList(value);
								conflict.add(con);
								
								
								value.remove(user);
								
							}
							needDepartmentId ^= 1<<department.getId();
						}
					}else{
						if(!hasChoosed.containsKey(user.getId())){
							hasChoosed.put(user.getId(), subActivity);
							value.remove(user);
							break;
						}else{
							Conflict con = new Conflict();
							con.setUserId(user.getId());
							con.setSubActivity(subActivity);
							con.setDepartmentId(0);
							con.setUerList(value);
							conflict.add(con);
							
							
							value.remove(user);
						}
					}
					
				}
			}
			
		}
		Collections.sort(conflict,new Comparator<Conflict>(){

			@Override
			public int compare(Conflict o1, Conflict o2) {
				// TODO Auto-generated method stub
				return (o1.getUerList().size()-o2.getUerList().size());
			}
			
		});
		for (Conflict con : conflict) {
			List<PitchUser> userList = con.getUerList();
			if(userList.size()==0){
				Conflict e = new Conflict();
				SubActivity subActivity = hasChoosed.get(con.getUserId());
				hasChoosed.put(con.getUserId(), con.getSubActivity());
				conflict.remove(con);
				e.setDepartmentId(con.getDepartmentId());
				e.setUserId(con.getUserId());
				e.setSubActivity(subActivity);
				e.setUerList(subUserList.get(subActivity));
				conflict.add(e);
			}
			if(con.getDepartmentId()!=0){
				for (PitchUser user : userList) {
					List<Integer> l = new ArrayList<Integer>();
					l.add(user.getId());
					Message userDetailsByUserId = userManagerRO.getUserDetailsByUserId(l);
					List<UserInfo> data = (List<UserInfo>) userDetailsByUserId.getDataTree().get(1).getData();
					Department department = data.get(0).getDepartment();
					if(con.getDepartmentId()==department.getId()){
						
						
						hasChoosed.remove(con.getUserId());
						hasChoosed.put(user.getId(), con.getSubActivity());
						conflict.remove(con);
						userList.remove(user);
						
					}
				}
			}
			else{
				for (PitchUser user : userList) {
					hasChoosed.remove(con.getUserId());
					hasChoosed.put(user.getId(), con.getSubActivity());
					conflict.remove(con);
					userList.remove(user);
				}
			}
		}
		}catch(RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return hasChoosed;
	}
	public String assign(){
		
		return "result";
	}
	
	public String assignBoss(){
		
		return "result";
	}
	
	

}
