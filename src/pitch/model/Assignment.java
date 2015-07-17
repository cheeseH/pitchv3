package pitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pitch_assignment")
public class Assignment {
	int id;
	int userId;
	int subActivityId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="userId")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="subActivityId")
	public int getSubActivityId() {
		return subActivityId;
	}
	public void setSubActivityId(int subActivityId) {
		this.subActivityId = subActivityId;
	}
}
