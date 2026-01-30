package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/**
 * TimeseriesAggregateDataValue
 */

@JsonTypeName("TimeseriesAggregateData_value")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-29T15:59:34.443462600+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class TimeseriesAggregateDataValue implements Serializable {

  private static final long serialVersionUID = 1L;

  private OffsetDateTime sequence = null;

  private @Nullable Float value;

  public TimeseriesAggregateDataValue sequence(OffsetDateTime sequence) {
    this.sequence = sequence;
    return this;
  }

  /**
   * Get sequence
   * @return sequence
   */
  @Valid 
  @Schema(name = "sequence", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sequence")
  public OffsetDateTime getSequence() {
    return sequence;
  }

  public void setSequence(OffsetDateTime sequence) {
    this.sequence = sequence;
  }

  public TimeseriesAggregateDataValue value(Float value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesAggregateDataValue timeseriesAggregateDataValue = (TimeseriesAggregateDataValue) o;
    return Objects.equals(this.sequence, timeseriesAggregateDataValue.sequence) &&
        Objects.equals(this.value, timeseriesAggregateDataValue.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sequence, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesAggregateDataValue {\n");
    sb.append("    sequence: ").append(toIndentedString(sequence)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

