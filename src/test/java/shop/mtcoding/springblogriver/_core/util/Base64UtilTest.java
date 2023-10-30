package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64UtilTest {

    @Test
    public void mimeType_test() throws IOException {
        // given
        Path filePath = Paths.get("./images/1.jpg");
        byte[] imgBytes = Files.readAllBytes(filePath);
        String imgBase64 = Base64.getEncoder().encodeToString(imgBytes);
        imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", "image/jpeg").replace("$imgBase64", imgBase64);

        // when
        int beginIndex = imgBase64.indexOf("/")+1;
        int endIndex = imgBase64.indexOf(";");
        String mimeType = imgBase64.substring(beginIndex, endIndex);
        System.out.println(mimeType);
    }

    // data:image/jpeg;base64
    @Test
    public void encode_test() throws IOException {
        Path filePath = Paths.get("./images/1.jpg");
        byte[] imgBytes = Files.readAllBytes(filePath);
        String imgBase64 = Base64.getEncoder().encodeToString(imgBytes);
        String mimeType = "image/jpeg";
        imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", mimeType).replace("$imgBase64", imgBase64);
        System.out.println(imgBase64);
    }

    @Test
    public void decode_test(){
        try {
            // given - base64 encode with mime add
            Path filePath = Paths.get("./images/1.jpg");
            String imgBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
            imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", "image/jpeg").replace("$imgBase64", imgBase64);
            //System.out.println(imgBase64);

            // when
            // 1. mimetype parsing
            int beginIndex = imgBase64.indexOf("/")+1;
            int endIndex = imgBase64.indexOf(";");
            String mimeType = imgBase64.substring(beginIndex, endIndex);
            //System.out.println(mimeType);

            // 2. img parsing
            int prefixEndIndex = imgBase64.indexOf(",");
            String img = imgBase64.substring(prefixEndIndex+1);
            //System.out.println(img);

            // 3. base64 decode to byte[]
            byte[] imgBytes = Base64.getDecoder().decode(img);


            // 3.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
