package shop.mtcoding.springblogriver._core.util;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeTest {

    //@Test
    public void encode_test(){
        String encPassword = BCrypt.hashpw("1234", BCrypt.gensalt());
        System.out.println(encPassword);
    }
}
