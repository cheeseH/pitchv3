package pitch.model;

import net._100steps.bbter.service.model.UserInfo;

public class PitchUserInfo {
	private PitchUser pitchUser;
	private UserInfo userInfo;
	
	public PitchUserInfo(PitchUser pitchUser,UserInfo userInfo){
		this.pitchUser = pitchUser;
		this.userInfo = userInfo;
	}
	public PitchUser getPitchUser() {
		return pitchUser;
	}
	public void setPitchUser(PitchUser pitchUser) {
		this.pitchUser = pitchUser;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
