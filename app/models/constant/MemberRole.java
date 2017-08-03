package models.constant;

import com.avaje.ebean.annotation.EnumValue;

public enum MemberRole {

  /** 管理者 */
  @EnumValue("admin")
  ADMINISTRATOR,

  /** 一般 */
  @EnumValue("public")
  PUBLIC
}
