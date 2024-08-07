import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName test
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/7
 * @Version: 1.0
 */
public class test {
    @Test
    public void test() {
        float t=1;
        String a = "123";
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(6);
        list.add(0, 4);
        list.remove(1);
        System.out.println(list);
    }
}
