package de.hsbo.kommonitor.timeseries_management.api.impl.stations;

import java.util.HashSet;
import java.util.Set;

import org.locationtech.jts.geom.Point;

import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

@Entity(name = "Stations")
public class StationsEntity {

    @Id
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private Point position;

    @ElementCollection
    @JoinTable(
            name = "stations_parameters",
            joinColumns = @JoinColumn(
                    name = "stations_id"))
    @Column(
            name = "parameters")
    private Set<ParametersEntity> parameters = new HashSet<ParametersEntity>();
	
    public StationsEntity() {}
    
    public StationsEntity(Integer id) {
    	this.id = id;
    }
	
    public StationsEntity(Integer id, String name) {
    	this(id);
    	this.name = name;
    }
	
    public StationsEntity(Integer id, String name, Point position) {
    	this(id, name);
    	this.position = position;
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

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Set<ParametersEntity> getParameters() {
		return parameters;
	}

	public void setParameters(Set<ParametersEntity> parameters) {
		this.parameters = parameters;
	}
	
	public boolean addParameter(ParametersEntity parameter) {
		return parameters.add(parameter);
	}
    
}
