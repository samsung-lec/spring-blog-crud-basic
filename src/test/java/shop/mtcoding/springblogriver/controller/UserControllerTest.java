package shop.mtcoding.springblogriver.controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import shop.mtcoding.springblogriver.user.UserRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void join_test() throws Exception {
        UserRequest.JoinDTO joinDTO = new UserRequest.JoinDTO();
        joinDTO.setUsername("hello");
        joinDTO.setPassword("1234");
        joinDTO.setEmail("hello@nate.com");

        String requestBody = om.writeValueAsString(joinDTO);
        System.out.println(requestBody);

        ResultActions resultActions = mvc.perform(
                post("/join").content(requestBody).contentType(MediaType.APPLICATION_JSON)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(4));
        resultActions.andExpect(jsonPath("$.response.username").value("hello"));
        resultActions.andExpect(jsonPath("$.response.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    public void login_test() throws Exception {
        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
        loginDTO.setUsername("ssar");
        loginDTO.setPassword("1234");

        String requestBody = om.writeValueAsString(loginDTO);
        System.out.println(requestBody);

        ResultActions resultActions = mvc.perform(
                post("/login").content(requestBody).contentType(MediaType.APPLICATION_JSON)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // 헤더에서 Authorization 값을 추출
        String authorizationHeaderValue = resultActions.andReturn().getResponse().getHeader("Authorization");
        System.out.println("Authorization 헤더 값: " + authorizationHeaderValue);

        // verify
        resultActions.andExpect(header().string("Authorization", Matchers.notNullValue()));
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(1));
        resultActions.andExpect(jsonPath("$.response.username").value("ssar"));
        resultActions.andExpect(jsonPath("$.response.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());

    }
}
