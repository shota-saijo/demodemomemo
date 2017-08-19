package models.form;

import java.util.stream.Stream;
import models.constant.Color;

public class LabelForm {

  private String content;

  private Color color;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color =
        Stream.of(Color.values())
            .filter(c -> c.getText().equals(color))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
  }
}
