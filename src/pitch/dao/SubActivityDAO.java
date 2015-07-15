package pitch.dao;

import java.util.List;

import pitch.model.SubActivity;

public interface SubActivityDAO {
	public void add(SubActivity activity);
	public void remove(int activityId);
	public void update(SubActivity activity);
	public List<SubActivity> getByPitchId(int pitchActivityId);
	public SubActivity getById(int activityId);
}
