package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * ParameterResponseAllOfRange
 */

@JsonTypeName("ParameterResponse_allOf_range")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-20T15:38:36.943031800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class ParameterResponseAllOfRange implements Serializable {

  private static final long serialVersionUID = 1L;

  private OffsetDateTime start = null;

  private OffsetDateTime end = null;

  public ParameterResponseAllOfRange() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ParameterResponseAllOfRange(OffsetDateTime start, OffsetDateTime end) {
    this.start = start;
    this.end = end;
  }

  public ParameterResponseAllOfRange start(OffsetDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * Get start
   * @return start
   */
  @NotNull @Valid 
  @Schema(name = "start", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("start")
  public OffsetDateTime getStart() {
    return start;
  }

  public void setStart(OffsetDateTime start) {
    this.start = start;
  }

  public ParameterResponseAllOfRange end(OffsetDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * Get end
   * @return end
   */
  @NotNull @Valid 
  @Schema(name = "end", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("end")
  public OffsetDateTime getEnd() {
    return end;
  }

  public void setEnd(OffsetDateTime end) {
    this.end = end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParameterResponseAllOfRange parameterResponseAllOfRange = (ParameterResponseAllOfRange) o;
    return Objects.equals(this.start, parameterResponseAllOfRange.start) &&
        Objects.equals(this.end, parameterResponseAllOfRange.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParameterResponseAllOfRange {\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
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

