package cn.itcast.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 测试数据库连接是否有问题。
 * @author Administrator
 *
 */
public class Test {
//	public void main
	public static void main(String[] argns)    {
		ApplicationContext ctx = new
				ClassPathXmlApplicationContext("beans.xml");
			// 获取chinese 实例
			DataBaseTest p =  ctx.getBean("dataBaseTest" , DataBaseTest.class);
			System.out.println(p.getMark()+p.getDriverClass()+p.getJdbcUrl()+"---!!!-");
			Connection con=null;  
			try {  
		          Class.forName(p.getDriverClass());  
		          //加载驱动类  
		          con = DriverManager.getConnection(p.getJdbcUrl(),  
		                  p.getUser(), p.getPassword());  
		          //获取与目标数据库的连接，参数（"jdbc:mysql://localhost/数据库名"，"root"，"数据库密码"；  
		      } catch (Exception e) {  
		          // TODO Auto-generated catch block  
		          e.printStackTrace();  
		      }
			ResultSet set=null;  
		      PreparedStatement prepar;
			try {
				prepar = con.
						  prepareStatement("SELECT * FROM eb_logs WHERE create_time > STR_TO_DATE('2018-06-08 01:00:00','%Y-%m-%d %H:%i:%s') and 	"
						  		+ "deviceId = 'd7278dbf90b5d746'");
			
		      //把sql语句发送到数据库，得到预编译类的对象，这句话是选择该student表里的所有数据  
		      set=prepar.executeQuery();  
		      //将得到的数据库响应的查询结果存放在ResultSet对象中  
		   
		      while(set.next())  
		      {  
		    	  try{
			    	  
			    	
		          System.out.println(set.getString("id"));//输出对应表内容，“表的列名”  

		    	  }catch(Exception e){e.printStackTrace();};
		      }  
			}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
	}
}
