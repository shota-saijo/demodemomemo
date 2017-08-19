package models.form;

import java.time.LocalDate;
import models.constant.Constant;
import models.constant.TaskStatus;
import models.entity.Milestone;
import play.Logger;

public class MilestoneForm {

  private Long id;

  private String name;

  private LocalDate start;

  private LocalDate end;

  private String progress;

  public static MilestoneForm newInstance(Milestone milestone) {
    MilestoneForm milestoneForm = new MilestoneForm();
    milestoneForm.id = milestone.getId();
    milestoneForm.name = milestone.getName();
    milestoneForm.start = milestone.getStartDate();
    milestoneForm.end = milestone.getEndDate();
    double completeTaskSize =
        (double)milestone
            .getTasks()
            .stream()
            .filter(task -> task.getStatus() == TaskStatus.COMPLETE)
            .count();
    double taskSize = (double)milestone.getTasks().size();
    milestoneForm.progress = taskSize == 0 ? "100%" : completeTaskSize / taskSize * 100 + "%";
    return milestoneForm;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getStart() {
    return start;
  }

  public void setStart(String start) {
    LocalDate date = LocalDate.parse(start, Constant.DATE_FORMAT);
    this.start = date;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(String end) {
    LocalDate date = LocalDate.parse(end, Constant.DATE_FORMAT);
    this.end = date;
  }

  public String getProgress() {
    return progress;
  }

  public void setProgress(String progress) {
    this.progress = progress;
  }
}
