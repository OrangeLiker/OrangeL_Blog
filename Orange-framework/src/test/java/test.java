import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName test
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
public class test {
    @Test
    public void Test(){
        String password="812908";
        BCryptPasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
        String encode = cryptPasswordEncoder.encode(password);
        System.out.println(encode);
    }
}
