package com.projectmanagemant.springapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
        import com.projectmanagemant.springapp.model.ProjectEntity;
        import com.projectmanagemant.springapp.service.ProjectService;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
        import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

        import java.util.Collections;
        import java.util.List;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
 class ProjectControllerTest{

        @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProjects() throws Exception {
        when(projectService.getProjects()).thenReturn(Collections.singletonList(new ProjectEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/projects"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void getProjectById() throws Exception {
        when(projectService.getProject(1)).thenReturn(new ProjectEntity());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/project?pId=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").exists());
    }

    @Test
    void createProject() throws Exception {
        ProjectEntity project = new ProjectEntity();
        when(projectService.projectCreate(any(ProjectEntity.class))).thenReturn(project);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/project/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").exists());
    }

    @Test
    void updateProject() throws Exception {
        ProjectEntity project = new ProjectEntity();
        when(projectService.updateProject(any(ProjectEntity.class), any(Integer.class))).thenReturn(project);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").exists());
    }

    @Test
    void deleteProject() throws Exception {
        when(projectService.projectDelete(1)).thenReturn("Project deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/project/delete?pId=1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string("Project deleted successfully"));
    }
}
