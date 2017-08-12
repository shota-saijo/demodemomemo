package models.repository;

import models.entity.Label;

import java.util.List;
import java.util.stream.Collectors;

public class LabelRepository {

    public List<Label> findByIds(List<Long> ids) {
        return ids.stream().map(Label.find::byId).collect(Collectors.toList());
    }
}
