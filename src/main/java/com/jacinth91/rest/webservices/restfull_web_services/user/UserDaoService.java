package com.jacinth91.rest.webservices.restfull_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService
{

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static  {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"jim", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"eve", LocalDate.now().minusYears(30)));
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }
    //public List<User> FindALL()
    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public List<User> findAll(){
        return users;
    }

    public void deleteUserById(int id) {
        Predicate<? super User> predicate = user ->user.getId().equals(id);
        users.removeIf(predicate);
    }
}
