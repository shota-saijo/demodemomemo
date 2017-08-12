package models.repository;

import models.entity.Milestone;

public class MilestoneRepository {

    public Milestone findById(Long id) {
        return Milestone.find.byId(id);
    }
}
