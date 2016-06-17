package com.packages.util;

import org.apache.commons.codec.binary.Base64;


public class Test {
	public static void main(String arg[]){

/*int[][] a = {
    {69,89,109,139,169,189},
    {100,120,140,160,180,200}
};

int[][] b = new int[a[0].length][a.length];
for (int i=0; i<a.length; i++) {
    for (int j=0; j<a[i].length; j++) {
        b[j][i] = a[i][j];
    }
}

for (int[] t : b) {
    System.out.println(Arrays.toString(t));
}*/
	

	new Base64();
	SK_MD5 md5 = new SK_MD5();
	byte[] passmd5 = md5.getMD5ofStr2("123");
	byte[] pass=Base64.encodeBase64(passmd5);
	//System.out.println(new String(pass));
	
	String role_ids = "";
	String[] roleids = role_ids.split(";");
	
	System.out.println(roleids[0]);
	
	float num1 = 2;
	float num2 = 3;
	System.out.println(num1/num2);
}
}
