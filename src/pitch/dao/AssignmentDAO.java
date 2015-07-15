package pitch.dao;

import java.util.List;

import pitch.model.Assignment;

public interface AssignmentDAO {
	public void add(Assignment assignment);
	public void remove(int assignmentId);
	public void update(Assignment assignment);
	public Assignment getById(int assignmentId);
	public List<Assignment> getBySubActivityId(int saId);
}
