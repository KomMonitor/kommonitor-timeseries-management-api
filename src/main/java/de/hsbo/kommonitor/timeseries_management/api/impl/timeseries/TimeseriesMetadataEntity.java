package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "Timeseries_metadata")
public class TimeseriesMetadataEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeseries_metadata_generator")
    @SequenceGenerator(name = "timeseries_metadata_generator", sequenceName = "timeseries_metadata_seq", allocationSize = 1)
    private Integer id;
	
    @Column(name = "stations_id")
    private Integer stationsId;
    
    @Column(name = "parameters_id")
    private Integer parametersId;
	
    public TimeseriesMetadataEntity() {}
	
    public TimeseriesMetadataEntity(Integer stationsId, Integer parametersId) {
    	this.stationsId = stationsId;
    	this.parametersId = parametersId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStationsId() {
		return stationsId;
	}

	public void setStationsId(Integer stationsId) {
		this.stationsId = stationsId;
	}

	public Integer getParametersId() {
		return parametersId;
	}

	public void setParametersId(Integer parametersId) {
		this.parametersId = parametersId;
	}
    
}
