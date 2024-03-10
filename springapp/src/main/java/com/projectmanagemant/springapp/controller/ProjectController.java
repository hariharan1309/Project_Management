package com.projectmanagemant.springapp.controller;

import com.projectmanagemant.springapp.model.ProjectEntity;
import com.projectmanagemant.springapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectEntity>> getAllProject(){
        List<ProjectEntity> response=projectService.getProjects();
        HttpHeaders httpHead=new HttpHeaders();
        httpHead.add("info","getting all projects available");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHead).body(response);
    }
    @GetMapping("/api/project")
    public ResponseEntity<ProjectEntity> getProjectById(@RequestParam("pId") Integer pId){
        ProjectEntity project=projectService.getProject(pId);
        HttpHeaders httpHead=new HttpHeaders();
        httpHead.add("info","Founded a Project");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHead).body(project);
    }
    @PostMapping("/api/project/new")
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectEntity project){
        ProjectEntity project1=projectService.projectCreate(project);
        HttpHeaders httpHead=new HttpHeaders();
        httpHead.add("info","created a Project");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHead).body(project1);    }

    @PutMapping("/api/project/update")
    public ResponseEntity<ProjectEntity> updateProject(@RequestBody ProjectEntity project){
        Integer projectId=project.getProjectId();
        ProjectEntity project1=projectService.updateProject(project, projectId);
        HttpHeaders httpHead=new HttpHeaders();
        httpHead.add("info","updated the Project");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHead).body(project1);
    }
    @DeleteMapping("/api/project/delete")
    public ResponseEntity<String> deleteProject(@RequestParam("pId") Integer projectId){
        String resp=projectService.projectDelete(projectId);
        HttpHeaders httpHead=new HttpHeaders();
        httpHead.add("info","Deleted the Project");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHead).body(resp
        );
    }

}
