package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import shop.mtcoding.springblogriver._core.auth.JwtAuthorizationFilter;
import shop.mtcoding.springblogriver._core.filter.CorsFilter;

import java.nio.charset.StandardCharsets;

@ExtendWith({ SpringExtension.class, RestDocumentationExtension.class })
public class MyWithRestDoc {

    protected MockMvc mvc;
    protected RestDocumentationResultHandler document;

    @BeforeEach
    private void restDocSetting(WebApplicationContext webApplicationContext,
                       RestDocumentationContextProvider restDocumentation) {

        this.document = MockMvcRestDocumentation.document("{class-name}/{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CorsFilter())
                .addFilter(new JwtAuthorizationFilter())
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .alwaysDo(document)
                .build();
    }
}
