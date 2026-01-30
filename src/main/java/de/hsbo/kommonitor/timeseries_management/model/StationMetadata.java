package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * StationMetadata
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-30T09:03:51.002722+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class StationMetadata implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private BigDecimal id;

  private Position position;

  @Valid
  private List<@Valid Parameter> parameters = new ArrayList<>();

  public StationMetadata() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StationMetadata(String name, BigDecimal id, Position position, List<@Valid Parameter> parameters) {
    this.name = name;
    this.id = id;
    this.position = position;
    this.parameters = parameters;
  }

  public StationMetadata name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the name of the station
   * @return name
   */
  @NotNull 
  @Schema(name = "name", description = "the name of the station", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StationMetadata id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * the id of the station
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", description = "the id of the station", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public StationMetadata position(Position position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
   */
  @NotNull @Valid 
  @Schema(name = "position", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("position")
  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public StationMetadata parameters(List<@Valid Parameter> parameters) {
    this.parameters = parameters;
    return this;
  }

  public StationMetadata addParametersItem(Parameter parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

  /**
   * list of parameters of the station
   * @return parameters
   */
  @NotNull @Valid 
  @Schema(name = "parameters", description = "list of parameters of the station", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("parameters")
  public List<@Valid Parameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<@Valid Parameter> parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StationMetadata stationMetadata = (StationMetadata) o;
    return Objects.equals(this.name, stationMetadata.name) &&
        Objects.equals(this.id, stationMetadata.id) &&
        Objects.equals(this.position, stationMetadata.position) &&
        Objects.equals(this.parameters, stationMetadata.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, position, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StationMetadata {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

