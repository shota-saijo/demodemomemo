package models.constant;

import com.avaje.ebean.annotation.EnumValue;

public enum Color {

  /** bootstrap default color */
  @EnumValue("default")
  DEFAULT("default"),

  /** bootstrap primary color */
  @EnumValue("primary")
  PRIMARY("primary"),

  /** bootstrap success color */
  @EnumValue("success")
  SUCCESS("success"),

  /** bootstrap info color */
  @EnumValue("info")
  INFO("info"),

  /** bootstrap warning color */
  @EnumValue("warning")
  WARNING("warning"),

  /** bootstrap danger color */
  @EnumValue("danger")
  DANGER("danger");

  private final String text;

  Color(final String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
