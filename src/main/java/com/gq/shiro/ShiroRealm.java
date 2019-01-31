package com.gq.shiro;

import com.gq.common.util.StringUtils;
import com.gq.model.Menu;
import com.gq.model.Role;
import com.gq.model.User;
import com.gq.service.MenuService;
import com.gq.service.RoleService;
import com.gq.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
 * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
 * 该方法主要执行以下操作:
 *
 * 检查提交的进行认证的令牌信息
 * 根据令牌信息从数据源(通常为数据库)中获取用户信息
 * 对用户信息进行匹配验证。
 * 验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
 * 验证失败则抛出AuthenticationException异常信息。而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo()，重写获取用户信息的方法。
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    /**
     * 授权用户权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        User user1 = (User) principalCollection.getPrimaryPrincipal();
//        //获取session中的用户
//        User user2 = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = this.roleService.findRolesByUserId(user.getId());
        //获取用户角色
        Set<String> roleSet = new HashSet<>();
        for(Role role : roles){
            roleSet.add(role.getName());
        }
        info.setRoles(roleSet);

        List<Menu> menus = this.menuService.findMenusByUserId(user.getId());
        //获取用户权限
        Set<String> permissionSet = new HashSet<>();
        for(Menu menu : menus){
            if(StringUtils.isNotBlank(menu.getPermission())){
                CollectionUtils.mergeArrayIntoCollection(menu.getPermission().split(","),permissionSet);
            }
        }
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String passWord = new String((char[])token.getCredentials()) ;

        //查询用户，可以加缓存。没有的话shiro 有时间间隔机制，2分钟内不会重复执行该方法
        User user = this.userService.findByName(userName);

        //这里校验了，CredentialsMatcher就不需要了，如果这里不校验，调用CredentialsMatcher校验
        if(user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        if(!passWord.equals(user.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        if("0".equals(user.getEnabled())){
            throw new LockedAccountException("账户已被锁定，联系管理员");
        }
        //salt = username+salt;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,passWord,getName());
        return info;
    }
}
