package pitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pitch_sub_activity")
public class SubActivity {
	int id;
	int pitchActivityId;
	String date;
	int lession;
	int day;
	int header;

	int needNumber;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="activity_id")
	public int getPitchActivityId() {
		return pitchActivityId;
	}
	public void setPitchActivityId(int pitchActivityId) {
		this.pitchActivityId = pitchActivityId;
	}
	@Column(name="date",columnDefinition="char(10)")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(name="lession")
	public int getLession() {
		return lession;
	}
	public void setLession(int lession) {
		this.lession = lession;
	}
	@Column(name="day")
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Column(name="header")
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
	@Column(name="needNumber")
	public int getNeedNumber() {
		return needNumber;
	}
	public void setNeedNumber(int needNumber) {
		this.needNumber = needNumber;
	}
	
	
	
}
