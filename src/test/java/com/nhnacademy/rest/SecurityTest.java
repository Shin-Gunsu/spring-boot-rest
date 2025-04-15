package com.nhnacademy.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    public void notfound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/public"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_ADMIN"})
    public void admin2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    public void admin_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    public void private_project_member() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private-project/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void private_project_admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private-project/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    public void public_project_member() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/public-project/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void public_project_admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/public-project/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void private_project_unLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private-project/**"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void public_project_unLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/public-project/**"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}