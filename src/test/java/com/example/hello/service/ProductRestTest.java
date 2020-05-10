package com.example.hello.service;

import com.example.hello.controller.ProductController;
import com.example.hello.model.Category;
import com.example.hello.model.Product;
import com.example.hello.service.CategoryService;
import com.example.hello.service.ProductService;
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

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductRestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenListProducts_thenGetAllProducts() throws Exception {
        Date date = new Date();
        Product product = new Product("name", "address", "imageUrls", date, date, new Category("category"));
        product.setId((long) 1);

        given(productService.findAll()).willReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(product.getName())))
                .andExpect(jsonPath("$[0].address", is(product.getAddress())))
                .andExpect(jsonPath("$[0].imageUrls", is(product.getImageUrls())))
                .andExpect(jsonPath("$[0].category.name", is(product.getCategory().getName())));
        verify(productService, VerificationModeFactory.times(1)).findAll();
        reset(productService);
    }

    @Test
    public void givenProducts_thenGetDetailsProducts() throws Exception {
        Date date = new Date();
        Product product = new Product("name", "address", "imageUrls", date, date, new Category("category"));
        product.setId((long) 1);

        given(productService.findById(Mockito.anyLong())).willReturn(Optional.of(product));
        mockMvc.perform(get("/api/products/{id}", product.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.address", is(product.getAddress())))
                .andExpect(jsonPath("$.imageUrls", is(product.getImageUrls())))
                .andExpect(jsonPath("$.category.name", is(product.getCategory().getName())));
        verify(productService, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        reset(productService);
    }

    @Test
    public void whenPostProductHaveCategory_thenCreateProduct() throws Exception {
        Date date = new Date();
        Category category = new Category("category");
        Product product = new Product("name", "address", "imageUrls", date, date, category);
        product.setId((long) 1);

        given(categoryService.findByName(Mockito.anyString())).willReturn(category);
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        verify(categoryService, VerificationModeFactory.times(1)).findByName(Mockito.any());
        reset(productService);
    }

    @Test
    public void whenPutProductHaveCategory_thenEditProduct() throws Exception {
        Date date = new Date();
        Category category = new Category("category");
        Product product = new Product("name", "address", "imageUrls", date, date, category);
        product.setId((long) 1);

        given(productService.findById(Mockito.anyLong())).willReturn(Optional.of(product));
        given(categoryService.findByName(Mockito.anyString())).willReturn(category);

        mockMvc.perform(put("/api/products/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product)))
                .andExpect(status().isOk());
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        verify(categoryService, VerificationModeFactory.times(1)).findByName(Mockito.any());
        reset(productService);
    }


    @Test
    public void whenDeleteProduct_thenDeleteProduct() throws Exception {
        Date date = new Date();
        Category category = new Category("category");
        Product product = new Product("name", "address", "imageUrls", date, date, category);
        product.setId((long) 1);

        given(productService.findById(Mockito.anyLong())).willReturn(Optional.of(product));

        mockMvc.perform(delete("/api/products/{id}", product.getId()))
                .andExpect(status().isOk());
        verify(productService, VerificationModeFactory.times(1)).delete(Mockito.any());
        reset(productService);
    }

    @Test
    public void givenProductHaveNoCategory_thenCreateProduct() throws Exception {
        Date date = new Date();
        Product product = new Product("name", "address", "imageUrls", date, date, null);
        product.setId((long) 1);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        verify(categoryService, VerificationModeFactory.times(0)).findByName(Mockito.any());
        reset(productService);
    }

    @Test
    public void givenProductHaveDummyCategory_thenCreateProduct() throws Exception {
        Date date = new Date();
        Category category = new Category("dummy category");
        Product product = new Product("name", "address", "imageUrls", date, date, category);
        product.setId((long) 1);

        given(categoryService.findByName(Mockito.anyString())).willReturn(null);
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product)))
                .andExpect(status().isNotFound());
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        verify(categoryService, VerificationModeFactory.times(1)).findByName(Mockito.any());
        reset(productService);
    }

    @Test
    public void whenPutProductHaveDummyCategory_thenEditProduct() throws Exception {
        Date date = new Date();
        Category category = new Category("dummy category");
        Product product = new Product("name", "address", "imageUrls", date, date, category);
        product.setId((long) 1);

        given(productService.findById(Mockito.anyLong())).willReturn(Optional.of(product));
        given(categoryService.findByName(Mockito.anyString())).willReturn(null);

        mockMvc.perform(put("/api/products/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product)))
                .andExpect(status().isNotFound());
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        verify(categoryService, VerificationModeFactory.times(1)).findByName(Mockito.any());
        reset(productService);
    }
}
