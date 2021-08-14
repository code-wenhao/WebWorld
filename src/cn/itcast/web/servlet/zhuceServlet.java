package cn.itcast.web.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/zhuceServlet")
public class zhuceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        //2.获取请求参数
        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");


        if(password1.equals(password2) && username.length()>=4 && password1.length()>=3 && password2.length()>=3){
            //3.封装user对象
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password1);
            //4.调用UserDao的login方法
            UserDao dao = new UserDao();
            User user = dao.jc(loginUser);

            //5.判断user
            if(user == null){
                //数据库中没有该数据

                if(dao.zhuce(loginUser)==1){
                    //向数据库中添加数据成功
                    //设置编码
                    resp.setContentType("text/html;charset=utf-8");
                    //输出
                    resp.getWriter().write("注册成功！！");
                }else {
                    //设置编码
                    resp.setContentType("text/html;charset=utf-8");
                    //输出
                    resp.getWriter().write("注册失败！请重试！");
                }
            }else{
                //数据库中有相匹配的数据
                //设置编码
                resp.setContentType("text/html;charset=utf-8");
                //输出
                resp.getWriter().write("该用户名已被使用！");
            }
        }else {
            //设置编码
            resp.setContentType("text/html;charset=utf-8");
            //输出
            resp.getWriter().write("请重试！(两次输入的密码必须一致，输入的账号长度大于4 密码长度大于3)");
        }


/*        //2.获取所有请求参数
        Map<String, String[]> map = req.getParameterMap();
        //3.创建User对象
        User loginUser = new User();
        //3.2使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
