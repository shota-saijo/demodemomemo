package models.form;

public class ProjectForm {

  private String projectName;

  private String description;

  private String startDate;

  private String endDate;

  private Long chief;

  /** Getter and Setter */
  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public Long getChief() {
    return chief;
  }

  public void setChief(Long chief) {
    this.chief = chief;
  }
}
