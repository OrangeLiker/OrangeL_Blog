import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
        TreeSet<Integer> set=new TreeSet<>();
        TreeSet<Integer> subSet=new TreeSet<>();
        for (int i = 606; i < 613; i++) {
            if(i%2==0){
                set.add(i);
            }
        }
        subSet=(TreeSet)set.subSet(608,true,611,true);
        subSet.add(629);
        System.out.println(set+" "+subSet);
    }
}
