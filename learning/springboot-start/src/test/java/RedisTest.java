import org.example.springbootstart.db.model.biz.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void test() throws JsonProcessingException {
        String key = "user";
        //1、从redis中获得数据 数据的形式json字符串
        String userJson = redisTemplate.boundValueOps(key).get();
        //2、判断redis中是否存在数据
        if(null==userJson){
            //3、不存在数据 从数据库查询
            User user = new User();
            user.setId(1L);
            user.setUsername("san");
            user.setPassword("123456");
            user.setAddr("beijing");
            //4、将查询出的数据存储到redis缓存中
            //向将list集合转换成json格式的字符串  使用jackson进行转换
            /*ObjectMapper objectMapper = new ObjectMapper();
            userListJson = objectMapper.writeValueAsString(all);*/
            ObjectMapper objectMapper = new ObjectMapper();
            userJson = objectMapper.writeValueAsString(user);
            redisTemplate.boundValueOps(key).set(userJson);
            System.out.println("=======从数据库中获得user的数据======");
        }else{
            System.out.println("=======从redis缓存中获得user的数据======");
        }
        //4、将数据在控制台打印
        System.out.println(userJson);
    }
}
