package userms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import userms.dto.User;

import java.util.List;

@FeignClient(name = "provide-service")
public interface UserClient {
    @GetMapping("/user/selectById")
    User select(@RequestParam(value = "id") Integer id);

    @PostMapping("/user/selectAll")
    List<User> selectAll(@RequestBody User user);

    @GetMapping("/user/deleteById")
    List<User> deleteById(@RequestParam(value = "id") Integer id);

    @PostMapping("/user/insert")
    List<User> insertUser(@RequestBody User user);

    @PostMapping("/user/update")
    List<User> updateUser(@RequestBody User user);
}
