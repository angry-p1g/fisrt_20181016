package com.gq.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * shiro中唯一需要编写的两个类:类ShiroRealm完成根据用户名去数据库的查询,并且将用户信息放入shiro中,供第二个
 * 类调用.CredentialsMatcher,完成对于密码的校验.其中用户的信息来自ShiroRealm类
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utocken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassWord = new String(utocken.getPassword());
        //获得数据库中的密码
        String dbPassWord = (String) info.getCredentials();
        return this.equals(inPassWord,dbPassWord);
    }
}
