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
 * StationMetadataResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-20T15:38:36.943031800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class StationMetadataResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private BigDecimal id;

  private Position position;

  @Valid
  private List<ParameterResponse> parameters = new ArrayList<>();

  public StationMetadataResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StationMetadataResponse(String name, BigDecimal id, Position position) {
    this.name = name;
    this.id = id;
    this.position = position;
  }

  public StationMetadataResponse name(String name) {
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

  public StationMetadataResponse id(BigDecimal id) {
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

  public StationMetadataResponse position(Position position) {
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

  public StationMetadataResponse parameters(List<ParameterResponse> parameters) {
    this.parameters = parameters;
    return this;
  }

  public StationMetadataResponse addParametersItem(ParameterResponse parametersItem) {
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
  @Valid 
  @Schema(name = "parameters", description = "list of parameters of the station", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parameters")
  public List<ParameterResponse> getParameters() {
    return parameters;
  }

  public void setParameters(List<ParameterResponse> parameters) {
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
    StationMetadataResponse stationMetadataResponse = (StationMetadataResponse) o;
    return Objects.equals(this.name, stationMetadataResponse.name) &&
        Objects.equals(this.id, stationMetadataResponse.id) &&
        Objects.equals(this.position, stationMetadataResponse.position) &&
        Objects.equals(this.parameters, stationMetadataResponse.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, position, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StationMetadataResponse {\n");
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

