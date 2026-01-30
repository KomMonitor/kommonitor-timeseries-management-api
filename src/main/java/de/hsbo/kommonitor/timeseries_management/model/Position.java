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
 * Position
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-30T09:03:51.002722+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public class Position implements Serializable {

  private static final long serialVersionUID = 1L;

  private BigDecimal x;

  private BigDecimal y;

  public Position() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Position(BigDecimal x, BigDecimal y) {
    this.x = x;
    this.y = y;
  }

  public Position x(BigDecimal x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * @return x
   */
  @NotNull @Valid 
  @Schema(name = "x", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("x")
  public BigDecimal getX() {
    return x;
  }

  public void setX(BigDecimal x) {
    this.x = x;
  }

  public Position y(BigDecimal y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * @return y
   */
  @NotNull @Valid 
  @Schema(name = "y", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("y")
  public BigDecimal getY() {
    return y;
  }

  public void setY(BigDecimal y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(this.x, position.x) &&
        Objects.equals(this.y, position.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Position {\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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

