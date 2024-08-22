import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
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
       int a=10;
       Integer b=a;
        System.out.println(b.getClass().getName());
    }
    class Person implements Serializable{
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;
        public Person(String name,int age){
            this.name=name;
            this.age=age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    public class SerializeExample{

    }
}
