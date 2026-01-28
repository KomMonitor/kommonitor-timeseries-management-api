package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * TimeseriesMetadata
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-28T08:52:09.622875100+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class TimeseriesMetadata implements Serializable {

  private static final long serialVersionUID = 1L;

  private BigDecimal stationId;

  private Parameter parameter;

  public TimeseriesMetadata() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TimeseriesMetadata(BigDecimal stationId, Parameter parameter) {
    this.stationId = stationId;
    this.parameter = parameter;
  }

  public TimeseriesMetadata stationId(BigDecimal stationId) {
    this.stationId = stationId;
    return this;
  }

  /**
   * Get stationId
   * @return stationId
   */
  @NotNull @Valid 
  @Schema(name = "station_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("station_id")
  public BigDecimal getStationId() {
    return stationId;
  }

  public void setStationId(BigDecimal stationId) {
    this.stationId = stationId;
  }

  public TimeseriesMetadata parameter(Parameter parameter) {
    this.parameter = parameter;
    return this;
  }

  /**
   * Get parameter
   * @return parameter
   */
  @NotNull @Valid 
  @Schema(name = "parameter", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("parameter")
  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesMetadata timeseriesMetadata = (TimeseriesMetadata) o;
    return Objects.equals(this.stationId, timeseriesMetadata.stationId) &&
        Objects.equals(this.parameter, timeseriesMetadata.parameter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stationId, parameter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesMetadata {\n");
    sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n");
    sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
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

