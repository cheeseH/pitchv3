package pitch.dao;

import pitch.model.TimeTable;

public interface TimeTableDAO {
	public void add(TimeTable timeTable);
	public void remove(int ttId);
	public void update(TimeTable timeTable);
	public TimeTable getByUserId(int userId);
	public TimeTable getById(int id);
}
