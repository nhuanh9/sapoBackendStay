package com.example.hello.service;

import com.example.hello.controller.CategoryController;
import com.example.hello.model.Category;
import com.example.hello.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryRestTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenCategories_whenGetAllCategories() throws Exception {
        Category farmWork = new Category("Farm work");
        Category chores = new Category("Chores");

        List<Category> allCategories = Arrays.asList(farmWork, chores);
        given(service.findAll()).willReturn(allCategories);

        mvc.perform(get("/api/category")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(farmWork.getName())))
                .andExpect(jsonPath("$[1].name", is(chores.getName())));
        verify(service, VerificationModeFactory.times(1)).findAll();
        reset(service);
    }

    @Test
    public void whenPostCategory_thenCreateCategory() throws Exception {
        Category hostFamily = new Category("Host family");

        mvc.perform(post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(hostFamily)))
                .andExpect(status().isCreated());
        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(service);
    }

    @Test
    public void whenGetCategory_thenReturnDetailsCategory() throws Exception {
        Category hostFamily = new Category("Host family");
        hostFamily.setId((long) 1);
        given(service.findById(Mockito.any())).willReturn(Optional.of(hostFamily));

        mvc.perform(get("/api/category/{id}", hostFamily.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hostFamily.getId()))
                .andExpect(jsonPath("$.name").value(hostFamily.getName()));
        verify(service, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCategory_whenDeleteCategory() throws Exception {
        Category deletedCategory = new Category("Deleted Category");
        deletedCategory.setId((long) 1);
        given(service.findById(Mockito.any())).willReturn(Optional.of(deletedCategory));

        mvc.perform(delete("/api/category/{id}", deletedCategory.getId()))
                .andExpect(status().isOk());
        verify(service, VerificationModeFactory.times(1)).delete(Mockito.any());
        reset(service);
    }

    @Test
    public void givenNullCategory_whenDeleteCategory() throws Exception {
        given(service.findById(Mockito.any())).willReturn(null);

        mvc.perform(delete("/api/category/{id}", 1))
                .andExpect(status().isNotFound());
        verify(service, VerificationModeFactory.times(0)).delete(Mockito.any());
        reset(service);
    }

    @Test
    public void givenNullCategory_WhenEditCategory() throws Exception {
        Category category = new Category("Dummy Category");
        category.setId((long) 1);
        given(service.findById(Mockito.any())).willReturn(Optional.empty());

        mvc.perform(put("/api/category/{id}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(category)))
                .andExpect(status().isNotFound());
        verify(service, VerificationModeFactory.times(0)).save(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCategory_whenEditCategory() throws Exception {
        Category category = new Category("Dummy Category");
        category.setId((long) 1);
        given(service.findById(Mockito.any())).willReturn(Optional.of(category));

        mvc.perform(put("/api/category/{id}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(category)))
                .andExpect(status().isOk());
        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(service);
    }
}
