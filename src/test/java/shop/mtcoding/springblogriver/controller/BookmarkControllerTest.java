package shop.mtcoding.springblogriver.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver._core.util.MyWithRestDoc;
import shop.mtcoding.springblogriver.bookmark.BookmarkRequest;
import shop.mtcoding.springblogriver.reply.ReplyRequest;
import shop.mtcoding.springblogriver.user.User;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql("classpath:db/teardown.sql")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BookmarkControllerTest extends MyWithRestDoc {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ObjectMapper om = new ObjectMapper();
    private String accessToken;
    private String refreshToken;

    @BeforeEach
    public void setUp(){
        User user = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .imgUrl("/images/1.jpg")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        accessToken = JwtUtil.createdAccessToken(user);
        refreshToken = JwtUtil.createdRefreshToken(user);

        accessToken = "Bearer "+accessToken;
        refreshToken = "Bearer "+refreshToken;

        redisTemplate.opsForValue().set(accessToken, refreshToken);
    }

    @Test
    public void bookmark_test() throws Exception {
        BookmarkRequest.SaveDTO saveDTO = new BookmarkRequest.SaveDTO();
        saveDTO.setPostId(1);

        String requestBody = om.writeValueAsString(saveDTO);
        System.out.println(requestBody);

        ResultActions resultActions = mvc.perform(
                post("/api/bookmark").content(requestBody).contentType(MediaType.APPLICATION_JSON).header("Authorization", accessToken)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(2));
        resultActions.andExpect(jsonPath("$.response.postId").value(1));
        resultActions.andExpect(jsonPath("$.response.userId").value(1));
        resultActions.andExpect(jsonPath("$.response.createdAt").exists());
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
        resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andDo(document);
    }

    @Test
    public void cancel_test() throws Exception {
        int id = 1;

        ResultActions resultActions = mvc.perform(
                delete("/api/bookmark/"+id).header("Authorization", accessToken)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response").isEmpty());
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
        resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andDo(document);
    }
}
