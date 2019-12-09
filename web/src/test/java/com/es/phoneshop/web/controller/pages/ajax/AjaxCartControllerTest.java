package com.es.phoneshop.web.controller.pages.ajax;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:context/applicationContext-web-test.xml")
public class AjaxCartControllerTest {

    @Resource
    private AjaxCartController ajaxController;

    private MockMvc mockMvc;

    private JSONObject jsonObject;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(ajaxController).build();
    }

    @Test
    public void addToCartSuccessTest() throws Exception {
        jsonObject = new JSONObject();
        jsonObject.put("phoneId", 1095L);
        jsonObject.put("quantity", 3);

        RequestBuilder requestBuilder = post("/ajaxCart")
                .content(jsonObject.toString())
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType("application/json; charset=utf-8"))
                .andExpect(jsonPath("$.errorMessage").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    public void addToCartInvalidInputTest() throws Exception {
        jsonObject = new JSONObject();
        jsonObject.put("phoneId", 1095L);
        jsonObject.put("quantity", "gkhdfsgdshj");

        RequestBuilder requestBuilder = post("/ajaxCart")
                .content(jsonObject.toString())
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType("application/json; charset=utf-8"))
                .andExpect(jsonPath("$.errorMessage").value("Invalid quantity"))
                .andExpect(status().isOk());
    }

    @Test
    public void addToCartTooBigQuantityTest() throws Exception {
        jsonObject = new JSONObject();
        jsonObject.put("phoneId", 1095L);
        jsonObject.put("quantity", 100);

        RequestBuilder requestBuilder = post("/ajaxCart")
                .content(jsonObject.toString())
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType("application/json; charset=utf-8"))
                .andExpect(jsonPath("$.errorMessage").value("Quantity is too big"))
                .andExpect(status().isOk());
    }
}
