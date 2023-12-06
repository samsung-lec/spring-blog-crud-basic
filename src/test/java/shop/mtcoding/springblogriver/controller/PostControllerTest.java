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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import shop.mtcoding.springblogriver._core.auth.JwtUtil;
import shop.mtcoding.springblogriver.post.PostRequest;
import shop.mtcoding.springblogriver.user.User;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql("classpath:db/teardown.sql")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

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
    public void init_post_test() throws Exception {
        ResultActions resultActions = mvc.perform(
                get("/init/post").param("page", "0")
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.isFirst").value(true));
        resultActions.andExpect(jsonPath("$.response.isLast").value(false));
        resultActions.andExpect(jsonPath("$.response.pageNumber").value(0));
        resultActions.andExpect(jsonPath("$.response.size").value(10));
        resultActions.andExpect(jsonPath("$.response.totalPage").value(3));
        resultActions.andExpect(jsonPath("$.response.posts[0].id").value(23));
        resultActions.andExpect(jsonPath("$.response.posts[0].title").value("title 23"));
        resultActions.andExpect(jsonPath("$.response.posts[0].content").value("content 23"));
        resultActions.andExpect(jsonPath("$.response.posts[0].createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.posts[0].updatedAt").exists());
        resultActions.andExpect(jsonPath("$.response.posts[0].bookmarkCount").value(0));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.id").value(2));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.username").value("cos"));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    public void find_all_test() throws Exception {
        ResultActions resultActions = mvc.perform(
                get("/api/post").param("page", "0").header("Authorization", accessToken)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.isFirst").value(true));
        resultActions.andExpect(jsonPath("$.response.isLast").value(false));
        resultActions.andExpect(jsonPath("$.response.pageNumber").value(0));
        resultActions.andExpect(jsonPath("$.response.size").value(10));
        resultActions.andExpect(jsonPath("$.response.totalPage").value(3));
        resultActions.andExpect(jsonPath("$.response.posts[0].id").value(23));
        resultActions.andExpect(jsonPath("$.response.posts[0].title").value("title 23"));
        resultActions.andExpect(jsonPath("$.response.posts[0].content").value("content 23"));
        resultActions.andExpect(jsonPath("$.response.posts[0].createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.posts[0].updatedAt").exists());
        resultActions.andExpect(jsonPath("$.response.posts[0].bookmarkCount").value(0));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.id").value(2));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.username").value("cos"));
        resultActions.andExpect(jsonPath("$.response.posts[0].user.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }



    @Test
    public void save_test() throws Exception {
        PostRequest.SaveDTO saveDTO = new PostRequest.SaveDTO();
        saveDTO.setTitle("title 24");
        saveDTO.setContent("content 24");

        String requestBody = om.writeValueAsString(saveDTO);
        System.out.println(requestBody);

        ResultActions resultActions = mvc.perform(
                post("/api/post").content(requestBody).contentType(MediaType.APPLICATION_JSON).header("Authorization", accessToken)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(24));
        resultActions.andExpect(jsonPath("$.response.title").value("title 24"));
        resultActions.andExpect(jsonPath("$.response.content").value("content 24"));
        resultActions.andExpect(jsonPath("$.response.createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.updatedAt").exists());
        resultActions.andExpect(jsonPath("$.response.bookmarkCount").value(0));
        resultActions.andExpect(jsonPath("$.response.user.id").value(1));
        resultActions.andExpect(jsonPath("$.response.user.username").value("ssar"));
        resultActions.andExpect(jsonPath("$.response.user.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    public void find_by_id_test() throws Exception {
        int id = 1;
        ResultActions resultActions = mvc.perform(
                get("/api/post/"+id).header("Authorization", accessToken)
        );

        // console
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(1));
        resultActions.andExpect(jsonPath("$.response.title").value("title 1"));
        resultActions.andExpect(jsonPath("$.response.content").value("content 1"));
        resultActions.andExpect(jsonPath("$.response.createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.updatedAt").exists());
        resultActions.andExpect(jsonPath("$.response.bookmarkCount").value(1));
        resultActions.andExpect(jsonPath("$.response.isBookmark").value(true));
        resultActions.andExpect(jsonPath("$.response.user.id").value(1));
        resultActions.andExpect(jsonPath("$.response.user.username").value("ssar"));
        resultActions.andExpect(jsonPath("$.response.user.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.response.replies[0].id").value(5));
        resultActions.andExpect(jsonPath("$.response.replies[0].comment").value("comment 5"));
        resultActions.andExpect(jsonPath("$.response.replies[0].createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.replies[0].replyUser.id").value(3));
        resultActions.andExpect(jsonPath("$.response.replies[0].replyUser.username").value("love"));
        resultActions.andExpect(jsonPath("$.response.replies[0].replyUser.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    public void update_test() throws Exception {
        Integer id = 1;
        PostRequest.UpdateDTO updateDTO = new PostRequest.UpdateDTO();
        updateDTO.setTitle("title 100");
        updateDTO.setContent("content 100");

        String requestBody = om.writeValueAsString(updateDTO);
        System.out.println(requestBody);

        ResultActions resultActions = mvc.perform(
                put("/api/post/$id".replace("$id", id.toString())).header("Authorization", accessToken).content(requestBody).contentType(MediaType.APPLICATION_JSON)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);


        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(1));
        resultActions.andExpect(jsonPath("$.response.title").value("title 100"));
        resultActions.andExpect(jsonPath("$.response.content").value("content 100"));
        resultActions.andExpect(jsonPath("$.response.createdAt").exists());
        resultActions.andExpect(jsonPath("$.response.updatedAt").exists());
        resultActions.andExpect(jsonPath("$.response.bookmarkCount").value(1));
        resultActions.andExpect(jsonPath("$.response.user.id").value(1));
        resultActions.andExpect(jsonPath("$.response.user.username").value("ssar"));
        resultActions.andExpect(jsonPath("$.response.user.imgUrl").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    public void delete_test() throws Exception {
        Integer id = 1;

        ResultActions resultActions = mvc.perform(
                delete("/api/post/$id".replace("$id", id.toString())).header("Authorization", accessToken)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);


        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response").isEmpty());
        resultActions.andExpect(jsonPath("$.status").value(200));
        resultActions.andExpect(jsonPath("$.errorMessage").isEmpty());
    }

}
