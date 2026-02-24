package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * TimeseriesDataWithParameterName
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-20T15:38:36.943031800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class TimeseriesDataWithParameterName implements Serializable {

  private static final long serialVersionUID = 1L;

  private String parameterName;

  @Valid
  private List<@Valid TimeseriesData> timeseries = new ArrayList<>();

  public TimeseriesDataWithParameterName() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TimeseriesDataWithParameterName(String parameterName, List<@Valid TimeseriesData> timeseries) {
    this.parameterName = parameterName;
    this.timeseries = timeseries;
  }

  public TimeseriesDataWithParameterName parameterName(String parameterName) {
    this.parameterName = parameterName;
    return this;
  }

  /**
   * Get parameterName
   * @return parameterName
   */
  @NotNull 
  @Schema(name = "parameter_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("parameter_name")
  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public TimeseriesDataWithParameterName timeseries(List<@Valid TimeseriesData> timeseries) {
    this.timeseries = timeseries;
    return this;
  }

  public TimeseriesDataWithParameterName addTimeseriesItem(TimeseriesData timeseriesItem) {
    if (this.timeseries == null) {
      this.timeseries = new ArrayList<>();
    }
    this.timeseries.add(timeseriesItem);
    return this;
  }

  /**
   * Get timeseries
   * @return timeseries
   */
  @NotNull @Valid 
  @Schema(name = "timeseries", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timeseries")
  public List<@Valid TimeseriesData> getTimeseries() {
    return timeseries;
  }

  public void setTimeseries(List<@Valid TimeseriesData> timeseries) {
    this.timeseries = timeseries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesDataWithParameterName timeseriesDataWithParameterName = (TimeseriesDataWithParameterName) o;
    return Objects.equals(this.parameterName, timeseriesDataWithParameterName.parameterName) &&
        Objects.equals(this.timeseries, timeseriesDataWithParameterName.timeseries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameterName, timeseries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesDataWithParameterName {\n");
    sb.append("    parameterName: ").append(toIndentedString(parameterName)).append("\n");
    sb.append("    timeseries: ").append(toIndentedString(timeseries)).append("\n");
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

