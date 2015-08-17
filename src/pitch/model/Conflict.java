package pitch.model;

import java.util.List;

public class Conflict {
	int userId;
	SubActivity subActivity;
	List<PitchUser> uerList;
	int departmentId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public List<PitchUser> getUerList() {
		return uerList;
	}
	public void setUerList(List<PitchUser> uerList) {
		this.uerList = uerList;
	}
	public SubActivity getSubActivity() {
		return subActivity;
	}
	public void setSubActivity(SubActivity subActivity) {
		this.subActivity = subActivity;
	}
}
