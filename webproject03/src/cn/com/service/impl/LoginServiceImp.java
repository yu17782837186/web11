package cn.com.service.impl;

import cn.com.dao.LoginDao;
import cn.com.dao.impl.LoginDaoImp;
import cn.com.pojo.User;
import cn.com.service.LoginService;

public class LoginServiceImp implements LoginService {
    //创建Dao层对象
    LoginDao ld = new LoginDaoImp();
    //校验用户登录信息
    @Override
    public User checkLoginService(String uname, String pwd) {
        return ld.checkLoginDao(uname,pwd);
    }
    //校验cookie信息
    @Override
    public User checkUidService(String uid) {
        return ld.checkUidDao(uid);
    }
}
