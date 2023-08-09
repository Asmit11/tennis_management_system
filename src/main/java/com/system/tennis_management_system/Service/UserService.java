package com.system.tennis_management_system.Service;

import com.system.tennis_management_system.Pojo.UserPojo;
import com.system.tennis_management_system.entity.User;

import java.util.List;

public interface UserService {




    String save(UserPojo userPojo);

    User findByEmail(String email);

    List<User> fetchAll();

    User fetchById(Integer id);

    void deleteById(Integer id);

    String updateResetPassword(String email);


    void processPasswordResetRequest(String email);

    void sendEmail();
}

