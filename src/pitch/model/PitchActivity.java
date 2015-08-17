package pitch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="pitch_activity")
public class PitchActivity {
	int id;
	String name;
	String detail;
	int needDepartmentId;
	int boyFirst;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name",columnDefinition="varchar(10)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="detail",columnDefinition="text")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(name="needDepartmentId",columnDefinition="int")
	public int getNeedDepartmentId() {
		return needDepartmentId;
	}
	public void setNeedDepartmentId(int needDepartmentId) {
		this.needDepartmentId = needDepartmentId;
	}
	@Column(name="boyFirst")
	public int getBoyFirst() {
		return boyFirst;
	}
	public void setBoyFirst(int boyFirst) {
		this.boyFirst = boyFirst;
	}
}
