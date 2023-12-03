package shop.mtcoding.springblogriver._core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.mtcoding.springblogriver._core.auth.JwtAuthorizationFilter;
import shop.mtcoding.springblogriver._core.filter.CorsFilter;

@Slf4j
@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        log.debug("corsFilter scan");
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호부터 실행됨.
        return bean;
    }

    @Bean
    FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilter() {
        log.debug("JwtAuthorizationFilter scan");
        FilterRegistrationBean<JwtAuthorizationFilter> bean =
                new FilterRegistrationBean<>(new JwtAuthorizationFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        return bean;
    }
}
