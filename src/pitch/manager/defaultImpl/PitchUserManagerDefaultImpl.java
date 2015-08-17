package pitch.manager.defaultImpl;

<<<<<<< HEAD
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

import net._100steps.bbter.service.api.rmi.UserManagerRO;
import net._100steps.bbter.service.model.UserInfo;
import net._100steps.general.message.Message;
import pitch.dao.PitchUserDAO;
import pitch.manager.PitchUserManager;
import pitch.manager.Status;
import pitch.model.PitchUser;
import pitch.model.PitchUserInfo;

public class PitchUserManagerDefaultImpl implements PitchUserManager{

	private UserManagerRO userManagerRO;
	private PitchUserDAO pitchUserDAO;


	@Override
	public Status search(int userid) {
		// TODO Auto-generated method stub
		List<Integer> userList = new ArrayList<Integer>();
		JMapObj jmap = new JMapObj();
		userList.add(userid);
		try {
			Message m =userManagerRO.getUserDetailsByUserId(userList);
			if(m.getMsgCode() != 0){
				//以后再改
				return Status.Error(500, null);
			}
			@SuppressWarnings("unchecked")
			Object data = m.getDataTree().get(1).getData();
			if(data == null){
				//处理
				return new Status(true,200,null);
			}
			@SuppressWarnings("unchecked")
			List<UserInfo> userInfos = (List<UserInfo>)data;
			UserInfo userInfo = userInfos.get(0);
			PitchUser pitchUser = pitchUserDAO.getById(userid);
			PitchUserInfo pitchUserInfo = new PitchUserInfo(pitchUser, userInfo);
			Status s = new Status();
			s.setCode(201);
			s.setSuccess(true);
			JArrayObj array = new JArrayObj();
			JMapObj map = new JMapObj();
			map.put("id", userInfo.getUser().getId());
			map.put("name", userInfo.getUserDetail().getName());
			map.put("pitchTimes", pitchUserInfo.getPitchUser().getPitchTimes());
			map.put("mobile",userInfo.getUserDetail().getMobile());
			map.put("departmentId",userInfo.getDepartment().getId());
			map.put("departmentName", userInfo.getDepartment().getName());
			map.put("groupId", userInfo.getGroup().getId());
			map.put("groupName", userInfo.getGroup().getName());
			array.add(map);
			s.setMsg(array);
			return s;
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Status.Error(502, null);
		}
		
	}

	public void setUserManagerRO(UserManagerRO userManagerRO) {
		this.userManagerRO = userManagerRO;
	}


	public void setPitchUserDAO(PitchUserDAO pitchUserDAO) {
		this.pitchUserDAO = pitchUserDAO
	}

}
