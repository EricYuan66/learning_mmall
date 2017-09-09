package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str, String type);
    ServerResponse<String> selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username, String question, String answer);
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);
    ServerResponse<User> updateInformation(User user);
    ServerResponse<User> get_information(Integer userId);
    ServerResponse checkAdminRole(User user);
}
