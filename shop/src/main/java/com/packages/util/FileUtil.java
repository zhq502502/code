package com.packages.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;

public final class FileUtil {

	public static String readFile(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line+"\r\n");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	
	//写入文件
	public static void writeFile(String dstfile, String filecontent){
		File file = new File(dstfile);
		if(file.exists()){
			forceDelete(file);
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(dstfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	    OutputStreamWriter write = null;
		try {
			write = new OutputStreamWriter(fos,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	      BufferedWriter writer = new BufferedWriter(write);
	      try {
			writer.write(filecontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	      try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		/*FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.write(filecontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public static String readFile(InputStream is, String inserttag, String content){

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				if(line.contains(inserttag)){
					sb.append(content+"\r\n");
				}
				sb.append(line+"\r\n");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	//复制文件
	public void copyFile(String srcfile, String dstfile){
		File file = new File(dstfile);
		if(file.exists()){
			file.delete();
		}
        // 获取源文件和目标文件的输入输出流  
        FileInputStream fin = null;
		try {
			fin = new FileInputStream(srcfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(dstfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
  
        // 获取输入输出通道  
        FileChannel fcin = fin.getChannel();  
        FileChannel fcout = fout.getChannel();  
  
        // 创建缓冲区  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
  
        while (true) {  
            // clear方法重设缓冲区，使它可以接受读入的数据  
            buffer.clear();  
  
            // 从输入通道中将数据读到缓冲区  
            int r = 0;
			try {
				r = fcin.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}  
  
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1  
            if (r == -1) {  
                break;  
            }  
  
            // flip方法让缓冲区可以将新读入的数据写入另一个通道  
            buffer.flip();  
  
            // 从输出通道中将数据写入缓冲区  
            try {
				fcout.write(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }  
	}
	
	public static void createJspFile(String srcfile, String dstfile, String insertTag, String cellbuContent){	
		File dstFile = new File(dstfile);
		if(dstFile.exists()){
			forceDelete(dstFile);
		}
		File file = new File(srcfile);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String filecontent = FileUtil.readFile(is, insertTag, cellbuContent);
		FileUtil.writeFile(dstfile, filecontent);		
	};
	
	
	public static void createJspFile(String srcfile, String dstfile){	
		File dstFile = new File(dstfile);
		if(dstFile.exists()){
			forceDelete(dstFile);
		}
		File file = new File(srcfile);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String filecontent = FileUtil.readFile(is);
		FileUtil.writeFile(dstfile, filecontent);		
	};
	
	public void modifyFile(String fileName, String inserttag, String content){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String line = null;
        StringBuilder sb = new StringBuilder();
       
        try {
			while ((line = reader.readLine()) != null) {
				if(line.contains(inserttag)){
					sb.append(content+"\r\n");
				}
				sb.append(line+"\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   //   PrintWriter pw = new PrintWriter(fos);
	   //   pw.write(buf.toString().toCharArray());
	   //   pw.flush();
	   //   pw.close();
      
	    OutputStreamWriter write = null;
		try {
			write = new OutputStreamWriter(fos,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	      BufferedWriter writer = new BufferedWriter(write);
	      try {
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	      try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}       
	}
	
	public static void fileAppender(String fileName, String inserttag, String content){
        
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String line = null;
        StringBuilder sb = new StringBuilder();
       
        try {
			while ((line = reader.readLine()) != null) {
				if(line.contains(inserttag)){
					sb.append(content+"\r\n");
				}
				sb.append(line+"\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        //写回去
        RandomAccessFile mm = null;
		try {
			mm = new RandomAccessFile(fileName, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
			mm.write(sb.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			mm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
	
	public static boolean forceDelete(File f)  
	{  
	    boolean result = false;  
	    int tryCount = 0;  
	    while(!result && tryCount++ <10)  
	    {  
	    	System.out.println("try to delete file "+ f.getName() +" cnt:"+tryCount);  
	    	System.gc();  
	    	result = f.delete();  
	    }  
	    return result;  
	}  
	
	
	public static StringBuffer convertStreamToString(InputStreamReader is){
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(is);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line+"\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	public static String readFile(InputStreamReader is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				sb.append(line+"\r\n");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
	}

}
