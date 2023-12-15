package userms.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import userms.dto.PagingParam;
import userms.dto.User;
import userms.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author ZHZ
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/selectById")
    public User select(@RequestParam(value = "id") Integer id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> res = restTemplate.getForEntity("http://localhost:8080/user/selectById?id=" + id, User.class);
        System.out.println("UserSelectById");
        return res.getBody();
    }

    @PostMapping("/selectAll")
    public List<User> selectAll(@RequestBody User user) {
        System.out.println("UserSelectAll");
        return userService.selectAll(user.getPageNum(),user.getPageSize());
    }

    @GetMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") Integer id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List> res = restTemplate.getForEntity("http://localhost:8080/user/deleteById?id=" + id, List.class);
        System.out.println("deleteById");
        return !Objects.requireNonNull(res.getBody()).isEmpty();
    }

    @PostMapping("/insert")
    public boolean insertUser(@RequestBody User user){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List> res = restTemplate.postForEntity("http://localhost:8080/user/insert", user, List.class);
        System.out.println("insertUser");
        return !Objects.requireNonNull(res.getBody()).isEmpty();
    }

    @PostMapping("/update")
    public Boolean updateUser(@RequestBody User user){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List> res = restTemplate.postForEntity("http://localhost:8080/user/update", user, List.class);
        System.out.println("updateUser");
        return !Objects.requireNonNull(res.getBody()).isEmpty();
    }
}
