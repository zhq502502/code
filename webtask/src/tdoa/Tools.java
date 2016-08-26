/*    */ package tdoa;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class Tools
/*    */ {
/*    */   public static String md5(String str)
/*    */   {
/* 11 */     MessageDigest messageDigest = null;
/*    */     try
/*    */     {
/* 14 */       messageDigest = MessageDigest.getInstance("MD5");
/*    */ 
/* 16 */       messageDigest.reset();
/*    */ 
/* 18 */       messageDigest.update(str.getBytes("UTF-8"));
/*    */     } catch (NoSuchAlgorithmException e) {
/* 20 */       System.out.println("NoSuchAlgorithmException caught!");
/* 21 */       System.exit(-1);
/*    */     } catch (UnsupportedEncodingException e) {
/* 23 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 26 */     byte[] byteArray = messageDigest.digest();
/*    */ 
/* 28 */     StringBuffer md5StrBuff = new StringBuffer();
/*    */ 
/* 30 */     for (int i = 0; i < byteArray.length; i++) {
/* 31 */       if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
/* 32 */         md5StrBuff.append("0").append(
/* 33 */           Integer.toHexString(0xFF & byteArray[i]));
/*    */       else {
/* 35 */         md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
/*    */       }
/*    */     }
/* 38 */     return md5StrBuff.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zhangqing\Desktop\
 * Qualified Name:     tdoa.Tools
 * JD-Core Version:    0.6.0
 */