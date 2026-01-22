package de.hsbo.kommonitor.timeseries_management.api.impl.parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "Parameters")
public class ParametersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_generator")
    @SequenceGenerator(name = "parameter_generator", sequenceName = "parameter_seq", allocationSize = 1)
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private String unit;
	
    public ParametersEntity() {}
	
    public ParametersEntity(String name, String unit) {
    	this.name = name;
    	this.unit = unit;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
    
}
