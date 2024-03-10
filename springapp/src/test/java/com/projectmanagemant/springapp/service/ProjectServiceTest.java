package com.projectmanagemant.springapp.service;

import com.projectmanagemant.springapp.exception.ProjectExceptionHandler;
        import com.projectmanagemant.springapp.model.ProjectEntity;
        import com.projectmanagemant.springapp.repository.ProjectRepo;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.springframework.boot.test.context.SpringBootTest;

        import java.time.LocalDate;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private ProjectExceptionHandler expHandler;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void projectCreate() {
        ProjectEntity project = new ProjectEntity();
        when(projectRepo.save(any(ProjectEntity.class))).thenReturn(project);

        ProjectEntity result = projectService.projectCreate(new ProjectEntity());

        assertEquals(project, result);
        verify(projectRepo, times(1)).save(any(ProjectEntity.class));
    }

    @Test
    void projectDelete() {
        Integer projectId = 1;
        doNothing().when(projectRepo).deleteById(projectId);

        String result = projectService.projectDelete(projectId);

        assertEquals("Deleted", result);
        verify(projectRepo, times(1)).deleteById(projectId);
    }

    @Test
    void getProjects() {
        List<ProjectEntity> projects = Arrays.asList(new ProjectEntity(), new ProjectEntity());
        when(projectRepo.findAll()).thenReturn(projects);

        List<ProjectEntity> result = projectService.getProjects();

        assertEquals(2, result.size());
        verify(projectRepo, times(1)).findAll();
    }

    @Test
    void getProject() {
        Integer projectId = 1;
        ProjectEntity project = new ProjectEntity();
        when(projectRepo.findById(projectId)).thenReturn(Optional.of(project));

        ProjectEntity result = projectService.getProject(projectId);

        assertEquals(project, result);
        verify(projectRepo, times(1)).findById(projectId);
    }

    @Test
    void updateProject() {
        Integer projectId = 1;
        ProjectEntity existingProject = new ProjectEntity();
        existingProject.setProjectId(projectId);

        when(projectRepo.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepo.save(any(ProjectEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProjectEntity updatedProject = new ProjectEntity();
        updatedProject.setName("Updated Project");

        ProjectEntity result = projectService.updateProject(updatedProject, projectId);

        assertEquals("Updated Project", result.getName());
        verify(projectRepo, times(1)).findById(projectId);
        verify(projectRepo, times(1)).save(any(ProjectEntity.class));
    }
}
