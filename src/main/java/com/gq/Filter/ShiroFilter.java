package com.gq.Filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * @Desc 用于自定义过滤器，过滤用户请求是否被授权 ，ShiroFilter是用于过滤需要权限校验的请求
 */
public class ShiroFilter extends AuthorizationFilter{

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //获取请求路径
        String path = request.getServletPath();
        Subject subject = getSubject(servletRequest,servletResponse);

        if(subject.getPrincipals() != null){
            //获取session中用户权限，比对路径确认是否具备权限
            Set<String> userPrivileges = (Set<String>) request.getSession().getAttribute("USER_PRIVILEGES");

            if(userPrivileges != null && userPrivileges.contains(path)){
                return true;
            }

        }
        return false;
    }

    /**
     * 会话超时或权限校验未通过的，统一返回401，由前端页面弹窗提示
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        if (isAjax((HttpServletRequest) request)){
            WebUtils.toHttp(response).sendError(401);
        } else {
            String unauthorizedUrl  = getUnauthorizedUrl();
            if(StringUtils.hasText(unauthorizedUrl)){
               WebUtils.issueRedirect(request,response,unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }
        return false;
    }


    private boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("x-requested-with");

        if(null != header && "XMLHttpRequest".endsWith(header)){
            return true;
        }
        return false;
    }
}

