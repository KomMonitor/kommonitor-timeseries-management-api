package de.hsbo.kommonitor.timeseries_management.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
/**
 * TimeseriesAggregateData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-20T15:38:36.943031800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class TimeseriesAggregateData implements Serializable {

  private static final long serialVersionUID = 1L;

  private OffsetDateTime timeBucket = null;

  public TimeseriesAggregateData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TimeseriesAggregateData(OffsetDateTime timeBucket) {
    this.timeBucket = timeBucket;
  }

  public TimeseriesAggregateData timeBucket(OffsetDateTime timeBucket) {
    this.timeBucket = timeBucket;
    return this;
  }

  /**
   * Get timeBucket
   * @return timeBucket
   */
  @NotNull @Valid 
  @Schema(name = "time-bucket", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("time-bucket")
  public OffsetDateTime getTimeBucket() {
    return timeBucket;
  }

  public void setTimeBucket(OffsetDateTime timeBucket) {
    this.timeBucket = timeBucket;
  }
    /**
    * A container for additional, undeclared properties.
    * This is a holder for any undeclared properties as specified with
    * the 'additionalProperties' keyword in the OAS document.
    */
    private Map<String, Float> additionalProperties;

    /**
    * Set the additional (undeclared) property with the specified name and value.
    * If the property does not already exist, create it otherwise replace it.
    */
    @JsonAnySetter
    public TimeseriesAggregateData putAdditionalProperty(String key, Float value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<String, Float>();
        }
        this.additionalProperties.put(key, value);
        return this;
    }

    /**
    * Return the additional (undeclared) property.
    */
    @JsonAnyGetter
    public Map<String, Float> getAdditionalProperties() {
        return additionalProperties;
    }

    /**
    * Return the additional (undeclared) property with the specified name.
    */
    public Float getAdditionalProperty(String key) {
        if (this.additionalProperties == null) {
            return null;
        }
        return this.additionalProperties.get(key);
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesAggregateData timeseriesAggregateData = (TimeseriesAggregateData) o;
    return Objects.equals(this.timeBucket, timeseriesAggregateData.timeBucket) &&
    Objects.equals(this.additionalProperties, timeseriesAggregateData.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeBucket, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesAggregateData {\n");
    sb.append("    timeBucket: ").append(toIndentedString(timeBucket)).append("\n");
    
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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

