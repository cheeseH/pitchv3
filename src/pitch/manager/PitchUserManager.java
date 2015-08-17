package pitch.manager;

import java.util.List;

import pitch.model.PitchUser;

public interface PitchUserManager {
	public List<PitchUser> searchAll();
	public void search(int userid);
}
