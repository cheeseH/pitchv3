package pitch.dao;

import java.util.List;

import pitch.model.PitchUser;


public interface PitchUserDAO {
	public void add(PitchUser user);
	public void remove(int userId);
	public void update(PitchUser user);
	public PitchUser getById(int UserId);
	public PitchUser getByStudentNumber(String studentNumber);
	public List<PitchUser> getAll();
}
