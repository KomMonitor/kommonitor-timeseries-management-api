package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * TimeseriesData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-30T09:03:51.002722+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class TimeseriesData implements Serializable {

  private static final long serialVersionUID = 1L;

  private Float value;

  private OffsetDateTime timestamp = null;

  public TimeseriesData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TimeseriesData(Float value, OffsetDateTime timestamp) {
    this.value = value;
    this.timestamp = timestamp;
  }

  public TimeseriesData value(Float value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  @NotNull 
  @Schema(name = "value", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("value")
  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public TimeseriesData timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   */
  @NotNull @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesData timeseriesData = (TimeseriesData) o;
    return Objects.equals(this.value, timeseriesData.value) &&
        Objects.equals(this.timestamp, timeseriesData.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesData {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

