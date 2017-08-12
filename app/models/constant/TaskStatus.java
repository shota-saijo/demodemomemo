package models.constant;

import com.avaje.ebean.annotation.EnumValue;

import java.util.stream.Stream;

public enum TaskStatus {
    /**
     * 未着手
     */
    @EnumValue("before_start")
    BEFORE_START("未着手"),

    /**
     * 着手
     */
    @EnumValue("start")
    START("着手"),

    /**
     * 仕掛中
     */
    @EnumValue("processing")
    PROCESSING("仕掛中"),

    /**
     * 確認待ち
     */
    @EnumValue("before_check")
    BEFORE_CHECK("確認待ち"),

    /**
     * 確認中
     */
    @EnumValue("checking")
    CHECKING("確認中"),


    /**
     * 確認済み
     */
    @EnumValue("checked")
    CHECKED("確認済み"),

    /**
     * 完了
     */
    @EnumValue("complete")
    COMPLETE("完了");

    private final String text;

    TaskStatus(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static TaskStatus getTaskStatus(String status) {
        return Stream.of(TaskStatus.values())
                .filter(taskStatus -> status.equals(taskStatus.name()))
                .findFirst()
                .orElse(null);
    }
}
