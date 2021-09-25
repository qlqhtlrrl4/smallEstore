package kr.ac.hansung.cse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity
@TableGenerator(
		name = "covid_seq_generator",
		table = "covid_sequence",
		pkColumnValue = "covid_seq",
		valueColumnName = "next_val",
		allocationSize = 1)
@Table(name = "covid")
public class CovidData {
	
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "covid_seq_generator")
	@Id
	@Column(name="covid_id", nullable=false , updatable=false)
	private int id;
	
	private String dywk;
	private String endTm;
	private String hldyYn;
	private String lunchEndTm;
	private String lunchSttTm;
	private String orgTlno;
	private String orgZipaddr;
	private String orgcd;
	private String orgnm;
	private String slrYmd;
	private String sttTm;
}