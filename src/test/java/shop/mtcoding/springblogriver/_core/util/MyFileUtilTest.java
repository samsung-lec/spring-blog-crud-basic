package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.Test;
import shop.mtcoding.springblogriver._core.error.exception.Exception500;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class MyFileUtilTest {

    @Test
    public void write_test() throws IOException {
        // given
        Path filePath = Paths.get("./images/1.jpg");
        String imgBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
        imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", "image/jpeg").replace("$imgBase64", imgBase64);

        // when
        // 1. 파일명 생성
        UUID uuid = UUID.randomUUID();
        String mimeType = Base64Util.getMimeType(imgBase64);
        String imgFilename = uuid+"."+mimeType;

        // 2. base64 -> byte[]
        byte[] imgBytes = Base64Util.decodeAsBytes(imgBase64);
        try {
            Path imgFilePath = Paths.get("./images/"+imgFilename);
            Files.write(imgFilePath, imgBytes);
        } catch (Exception e) {
            throw new Exception500("파일 업로드 실패 : " + e.getMessage());
        }
        System.out.println(imgFilename);
    }
}
