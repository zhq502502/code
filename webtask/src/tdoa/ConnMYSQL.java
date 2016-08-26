/*    */ package tdoa;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class ConnMYSQL
/*    */ {
/*    */   public Connection getConn()
/*    */   {
/* 19 */     InputStream is = getClass().getResourceAsStream("database.properties");
/* 20 */     Properties pro = new Properties();
/*    */     try {
/* 22 */       pro.load(is);
/*    */     } catch (IOException e) {
/* 24 */       System.out.println("读取配置文件错误");
/* 25 */       e.printStackTrace();
/*    */     }
/* 27 */     String className = pro.getProperty("className");
/* 28 */     String url = pro.getProperty("url");
/* 29 */     String userName = pro.getProperty("userName");
/* 30 */     String password = pro.getProperty("password");
/*    */     try
/*    */     {
/* 34 */       Class.forName(className);
/*    */     } catch (ClassNotFoundException e) {
/* 36 */       System.out.println("加载数据库驱动错误");
/* 37 */       e.printStackTrace();
/*    */     }
/* 39 */     Connection conn = null;
/*    */     try {
/* 41 */       conn = DriverManager.getConnection(url, userName, password);
/*    */     } catch (SQLException e) {
/* 43 */       System.out.println("连接数据库错误");
/* 44 */       e.printStackTrace();
/*    */     }
/* 46 */     return conn;
/*    */   }
/*    */ 
/*    */   public static void main(String[] aegs)
/*    */   {
/* 51 */     ConnMYSQL c = new ConnMYSQL();
/* 52 */     System.out.println("数据库连接:" + c.getConn().toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\zhangqing\Desktop\
 * Qualified Name:     tdoa.ConnMYSQL
 * JD-Core Version:    0.6.0
 */