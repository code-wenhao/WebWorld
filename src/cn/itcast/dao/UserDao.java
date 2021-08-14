package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 操作数据库中User表的类
 */
public class UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据,没有查询到，返回null
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());


            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }
    public User jc(User loginUser){
        //注册检查
        try {
            //1.编写sql
            String sql1 = "select * from user where username = ?";
            String sql = "SELECT * FROM user WHERE username = ?;";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername());


            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }
    public int zhuce(User zhuce){
        int d = 0;
        try {
            //1.编写sql
            String sql = "insert into user(username,password) values(?,?);";
            //2.调用query方法
            d = template.update(sql,zhuce.getUsername(),zhuce.getPassword());

        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return 0;
        }
        return d;
    }
}
