package radar.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="dynamicData")
public class DynamicData {
	private Integer dataId;//数据id	
	private Radar radarId;//	所属雷达id,外键	
	private String dataVaule;//	参数值	
	private Date collectDate;//	采集时间
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "dataId",unique=true,nullable=false, length = 11)
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	@ManyToOne
	@JoinColumn(name="radarId",nullable=false)
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(name="dataValue",length=64)
	public String getDataVaule() {
		return dataVaule;
	}
	public void setDataVaule(String dataVaule) {
		this.dataVaule = dataVaule;
	}
	@DateTimeFormat(pattern="yy-mm-dd hh:mm:ss")
	@Column(name="collectDate",nullable=false)
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
}
