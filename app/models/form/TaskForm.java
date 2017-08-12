package models.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskForm {
    public String title;
    public String content;
    public Long userId;
    public Long[] labels;
    public Long milestone;
    public String status;
}
