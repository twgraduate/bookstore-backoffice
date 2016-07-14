package com.thoughtworks.ControlPacage;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import com.thoughtworks.ModlePacage.Greeting;





@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    static String sql = null;
    static DBConnection db1 = null;
    static ResultSet ret = null;


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/tony")
    public ResponseEntity tony(){
        sql = "select *from User";//SQL语句
        db1 = new DBConnection(sql);//创建DBHelper对象
        String result = null;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                String uid = ret.getString(1);
                String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);
                result = uid + ufname + ulname +udate;
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = "{\"data\":"+"\""+result+"\"}";
        String o = new String(str);
        ResponseEntity  responseEntity = new ResponseEntity<String>(o, HttpStatus.OK);
        System.out.println(o);
        return responseEntity;
    }

}
