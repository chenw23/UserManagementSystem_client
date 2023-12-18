package userms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import userms.client.UserClient;
import userms.dto.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private UserClient userClient;
    private final String providerName = "provide-service";

    @GetMapping("/selectById")
    @ResponseBody
    public User select(@RequestParam(value = "id") Integer id) {
//        User res = restTemplate.getForObject("http://" + providerName + "/user/selectById?id=" + id, User.class);

        System.out.println("UserSelectById");
//        return res;
        return userClient.select(id);
    }

    @GetMapping("/deleteById")
    public List<User> deleteById(@RequestParam(value = "id") Integer id) {
//        List<User> res = restTemplate.getForObject("http://localhost:8080/user/deleteById?id=" + id, List.class);
//        System.out.println("deleteById");
//        return res;
        return userClient.deleteById(id);
    }

    @PostMapping("/insert")
    public List<User> insertUser(@RequestBody User user) {
        List<User> res = restTemplate.postForObject("http://localhost:8080/user/insert", user, List.class);
        System.out.println("insertUser");
        return res;
    }

    @PostMapping("/update")
    public Boolean updateUser(@RequestBody User user) {
        HttpEntity<List> res = restTemplate.postForEntity("http://localhost:8080/user/update", user, List.class);
        System.out.println("updateUser");
        return !Objects.requireNonNull(res.getBody()).isEmpty();
    }
}
