package pitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="pitch_user")
public class PitchUser {
	int id;
	String studentNumber;
	int timetableId;
	int pitchTimes;
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="studentNumber",columnDefinition="char(12)")
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	@Column(name="timeTableId")
	public int getTimetableId() {
		return timetableId;
	}
	public void setTimetableId(int timetableId) {
		this.timetableId = timetableId;
	}
	@Column(name="pitchTimes")
	public int getPitchTimes() {
		return pitchTimes;
	}
	public void setPitchTimes(int pitchTimes) {
		this.pitchTimes = pitchTimes;
	}
	
}
