package pitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pitch_timetable")
public class TimeTable {
	int id;
	int userId;
	int table;
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
	@Column(name="table")
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	public void addLession(int date,int lession){
		int init = 1;
		int destLession = (date-1)*11+lession-1;
		init<<=destLession;
		this.table |= destLession;
	}
	
	public void removeLession(int date,int lession){
		int init = 1;
		int destLession = (date-1)*11+lession-1;
		init<<=destLession;
		this.table ^= destLession;
	}
}
