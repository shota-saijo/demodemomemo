package models.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectForm {

    public String projectName;

    public String description;

    public String startDate;

    public String endDate;

    public Long chief;
}
