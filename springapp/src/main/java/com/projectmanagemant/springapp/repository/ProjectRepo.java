package com.projectmanagemant.springapp.repository;

import com.projectmanagemant.springapp.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity,Integer> {

}
