import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.orange.domain.entity.UserRole;
import org.orange.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName test
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@SpringBootTest
@Configuration
public class test {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Test
    public void Test(){
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,7);
        UserRole userRoles=userRoleMapper.selectOne(queryWrapper);
        System.out.println(userRoles);
    }
}
