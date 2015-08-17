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
	long table;
	long newTable;
	int state;
	public TimeTable(){
		id = 0;
		userId = 0;
		table = 0;
		newTable = 0;
		state = -1;
	}
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
	public long getTable() {
		return table;
	}
	public void setTable(long table) {
		this.table = table;
	}
	@Column(name = "newTable")
	public long getNewTable() {
		return newTable;
	}
	public void setNewTable(long newTable) {
		this.newTable = newTable;
	}
	@Column(name = "state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void addLession(int date,int lession){
		long init = 1;
		int destLession = (date-1)*5+lession-1;
		init<<=destLession;
		this.newTable |= destLession;
	}
	
	public void removeLession(int date,int lession){
		long init = 1;
		int destLession = (date-1)*11+lession-1;
		init<<=destLession;
		this.newTable ^= destLession;
	}
}
