package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.Test;
import shop.mtcoding.springblogriver._core.error.exception.Exception500;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class MyFileUtilTest {

    //@Test
    public void write_test() throws IOException {
        // given
        Path filePath = Paths.get("./images/1.jpg");
        String imgBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(filePath));
        imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", "image/jpeg").replace("$imgBase64", imgBase64);

        // when
        // 1. 파일명 생성
        UUID uuid = UUID.randomUUID();
        String mimeType = Base64Util.getMimeType(imgBase64);
        String imgFilename = uuid + "." + mimeType;

        // 2. base64 -> byte[]
        byte[] imgBytes = Base64Util.decodeAsBytes(imgBase64);
        try {
            Path imgFilePath = Paths.get("./images/" + imgFilename);
            Files.write(imgFilePath, imgBytes);
        } catch (Exception e) {
            throw new Exception500("파일 업로드 실패 : " + e.getMessage());
        }
        System.out.println(imgFilename);
    }

    @Test
    public void pkg_path_test() throws URISyntaxException {
        // 1. pkg 이름 지정
        String pkg = "shop.mtcoding.springblogriver";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageUrl = classLoader.getResource(pkg.replace(".", "/"));
        File packageDirectory = new File(packageUrl.toURI());
        System.out.println(packageDirectory);
    }

@Test
public void root_path_test(){
    // 1. root 패스 찾음
    String projectRootPath = System.getProperty("user.dir")+File.separator;
    System.out.println(projectRootPath);

    // 2. 윈도우 ,맥 시스템에 따라서 / 쓸지, \ 쓸지 세팅하기
    projectRootPath = projectRootPath.replace("/", File.separator);
    System.out.println(projectRootPath);
}

@Test
public void other_path_test(){
    String otherPath = ".idea/misc.xml";
    String projectRootPath = System.getProperty("user.dir")+File.separator;

    // 2. 윈도우 ,맥 시스템에 따라서 / 쓸지, \ 쓸지 세팅하기
    otherPath = otherPath.replace("/", File.separator);
    projectRootPath = projectRootPath.replace("/", File.separator);
    System.out.println(projectRootPath+otherPath);
}
}
