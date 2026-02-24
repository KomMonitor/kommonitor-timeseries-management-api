package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * ParameterResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-20T15:38:36.943031800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class ParameterResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable BigDecimal id;

  private String name;

  private String unit;

  private @Nullable TimeseriesData lastEntry;

  private @Nullable ParameterResponseAllOfRange range;

  public ParameterResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ParameterResponse(String name, String unit) {
    this.name = name;
    this.unit = unit;
  }

  public ParameterResponse id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public ParameterResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ParameterResponse unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
   */
  @NotNull 
  @Schema(name = "unit", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("unit")
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public ParameterResponse lastEntry(TimeseriesData lastEntry) {
    this.lastEntry = lastEntry;
    return this;
  }

  /**
   * Get lastEntry
   * @return lastEntry
   */
  @Valid 
  @Schema(name = "last_entry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_entry")
  public TimeseriesData getLastEntry() {
    return lastEntry;
  }

  public void setLastEntry(TimeseriesData lastEntry) {
    this.lastEntry = lastEntry;
  }

  public ParameterResponse range(ParameterResponseAllOfRange range) {
    this.range = range;
    return this;
  }

  /**
   * Get range
   * @return range
   */
  @Valid 
  @Schema(name = "range", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("range")
  public ParameterResponseAllOfRange getRange() {
    return range;
  }

  public void setRange(ParameterResponseAllOfRange range) {
    this.range = range;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParameterResponse parameterResponse = (ParameterResponse) o;
    return Objects.equals(this.id, parameterResponse.id) &&
        Objects.equals(this.name, parameterResponse.name) &&
        Objects.equals(this.unit, parameterResponse.unit) &&
        Objects.equals(this.lastEntry, parameterResponse.lastEntry) &&
        Objects.equals(this.range, parameterResponse.range);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, unit, lastEntry, range);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParameterResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    lastEntry: ").append(toIndentedString(lastEntry)).append("\n");
    sb.append("    range: ").append(toIndentedString(range)).append("\n");
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

