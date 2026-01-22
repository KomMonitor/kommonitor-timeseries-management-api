package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "Timeseries_data")
public class TimeseriesDataEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeseries_data_generator")
    @SequenceGenerator(name = "timeseries_data_generator", sequenceName = "timeseries_data_seq", allocationSize = 1)
    private Integer id;
	
    @Column(name = "timeseries_id")
    private Integer timeseriesId;
    
    @Column(name = "value")
    private Float value;
    
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp = null;
	
    public TimeseriesDataEntity() {}
	
    public TimeseriesDataEntity(Float value, Date timestamp) {
    	this.value = value;
    	this.timestamp = timestamp;
    }
	
    public TimeseriesDataEntity(Integer timeseriesId, Float value, Date timestamp) {
    	this.timeseriesId = timeseriesId;
    	this.value = value;
    	this.timestamp = timestamp;
    }

	public Integer getId() {
		return id;
	}

	public Integer getTimeseriesId() {
		return timeseriesId;
	}

	public void setTimeseriesId(Integer timeseriesId) {
		this.timeseriesId = timeseriesId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStationsId(Integer stationsId) {
		this.timeseriesId = stationsId;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
    
}
