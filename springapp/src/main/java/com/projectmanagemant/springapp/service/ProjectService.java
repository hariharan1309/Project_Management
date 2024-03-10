package com.projectmanagemant.springapp.service;

import com.projectmanagemant.springapp.exception.ProjectExceptionHandler;
import com.projectmanagemant.springapp.model.ProjectEntity;
import com.projectmanagemant.springapp.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectExceptionHandler expHandler;

    public ProjectEntity projectCreate(ProjectEntity project){
        return projectRepo.save(project);
    }
    public String projectDelete(Integer projectId){
        projectRepo.deleteById(projectId);
        return "Deleted";
    }

    public List<ProjectEntity> getProjects(){
        return projectRepo.findAll();
    }

    public ProjectEntity getProject(Integer projectId){
        return projectRepo.findById(projectId).get();
    }

    public ProjectEntity updateProject(ProjectEntity project,Integer projectId){
        ProjectEntity project1=getProject(projectId);

        if(Objects.nonNull(project.getName()) &&
                !"".equalsIgnoreCase(project.getName())){
            project1.setName(project.getName());
        }
        if(Objects.nonNull(project.getDeveloperName()) &&
                !"".equalsIgnoreCase(project.getDeveloperName())){
            project1.setDeveloperName(project.getDeveloperName());
        }
        if(!"".equalsIgnoreCase(project.getLink())){
            project1.setLink(project.getLink());
        }
        if(!"".equalsIgnoreCase(project.getStatus())){
            project1.setStatus(project.getStatus());
        }
        if(Objects.nonNull(project.getDescription()) &&
                !"".equalsIgnoreCase(project.getDescription())){
            project1.setDescription(project.getDescription());
        }
        if(!"".equalsIgnoreCase(project.getCategory())){
            project1.setCategory(project.getCategory());
        }
        if(Objects.nonNull((project.getStartDate()))){
            project1.setStartDate(project.getStartDate());
        }
        if(Objects.nonNull(project.getEndDate())){
            project1.setEndDate(project.getEndDate());
        }
        return projectRepo.save(project1);
    }

}
