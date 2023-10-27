package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64UtilTest {

    @Test
    public void encode_test(){
        try {
            Path filePath = Paths.get("./images/1.jpg");
            byte[] imgBytes = Files.readAllBytes(filePath);
            String imgBase64 = Base64.getMimeEncoder().encodeToString(imgBytes);
            System.out.println(imgBase64);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
