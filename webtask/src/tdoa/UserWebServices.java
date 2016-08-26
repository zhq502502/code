/*    */ package tdoa;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class UserWebServices
/*    */ {
/* 14 */   private static Logger log = Logger.getRootLogger();
/*    */ 
/* 16 */   public String validate(String orgid, String name, String pwd) { String flag = "";
/* 17 */     log.error("开始AuthValidate----orgid:" + orgid + "|name:" + name + "|pwd:" + pwd);
/*    */ 
/* 19 */     InputStream is = getClass().getResourceAsStream("database.properties");
/* 20 */     Properties pro = new Properties();
/*    */     try {
/* 22 */       pro.load(is);
/*    */     } catch (IOException e) {
/* 24 */       log.error("读取配置文件错误");
/* 25 */       e.printStackTrace();
/*    */     }
/* 27 */     String companyid = pro.getProperty("orgid");
/* 28 */     if (orgid.equals(companyid)) {
/* 29 */       log.error("企业ID正确");
/*    */ 
/* 31 */       ConnMYSQL c = new ConnMYSQL();
/* 32 */       Connection conn = c.getConn();
/* 33 */       String sql = "select PASSWORD from user where USER_ID='" + name + "'";
/* 34 */       PreparedStatement ps = null;
/* 35 */       ResultSet rs = null;
/*    */       try {
/* 37 */         ps = conn.prepareCall(sql);
/* 38 */         //ps.execute("set names gbk");
/* 39 */         rs = ps.executeQuery();
/* 40 */         log.error("执行sql:" + sql);
/* 41 */         if (rs.next()) {
/* 42 */           String password = rs.getString("PASSWORD");
/* 43 */           Tools t = new Tools();
/* 44 */           password = Tools.md5(password);
/* 45 */           log.error("数据库中" + name + "的密码md5后为:" + password);
/* 46 */           if (password.equals(pwd)) {
/* 47 */             flag = "1";
/* 48 */             log.error("用户存在，并且密码正确");
/*    */           } else {
/* 50 */             flag = "0";
/* 51 */             log.error("用户密码错误");
/*    */           }
/*    */         } else {
/* 54 */           flag = "0";
/* 55 */           log.error("用户不存在");
/*    */         }
/*    */       } catch (SQLException e) {
/* 58 */         e.printStackTrace();
/* 59 */         log.error("SQLException");
/*    */       }
/* 61 */       log.error("--------------------------flag:" + flag);
/*    */     } else {
/* 63 */       log.error("企业ID错误");
/*    */     }
/* 65 */     return flag;
/*    */   }
/*    */ }

/* Location:           C:\Users\zhangqing\Desktop\
 * Qualified Name:     tdoa.UserWebServices
 * JD-Core Version:    0.6.0
 */