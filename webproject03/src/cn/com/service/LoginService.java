package cn.com.service;

import cn.com.pojo.User;

public interface LoginService {
    //校验用户登录信息
    User checkLoginService(String uname, String pwd);
    //校验用户cookie信息
    User checkUidService(String uid);
}
