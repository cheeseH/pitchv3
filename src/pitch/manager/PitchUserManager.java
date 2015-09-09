package pitch.manager;

import java.util.List;

import pitch.model.PitchUser;

public interface PitchUserManager {
	public Status search(int userid);
	public Status searchAll();
	public void updatePitchUser(PitchUser user);
	public Status getById(int id);
}
