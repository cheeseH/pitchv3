package pitch.dao;

import java.util.List;

import pitch.model.PitchActivity;

public interface PitchActivityDAO {
	public void add(PitchActivity pa);
	public void update(PitchActivity pa);
	public void remove(int activityId);
	public PitchActivity getById(int activityId);
	public List<PitchActivity> getAll();
}
