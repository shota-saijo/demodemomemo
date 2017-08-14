package models.form;

public class TaskForm {
  private String title;
  private String content;
  private Long userId;
  private Long[] labels;
  private Long milestone;
  private String status;

  /** Getter and Setter */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long[] getLabels() {
    return labels;
  }

  public void setLabels(Long[] labels) {
    this.labels = labels;
  }

  public Long getMilestone() {
    return milestone;
  }

  public void setMilestone(Long milestone) {
    this.milestone = milestone;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
