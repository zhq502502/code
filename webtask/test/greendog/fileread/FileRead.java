package greendog.fileread;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRead {
	
	public static void findfile(List<Map<String,String>> list,String path){
		File file = new File(path);
		if(!file.isDirectory()){
			if(!file.exists()){
				return;
			}
			Map<String,String> map = new HashMap<String, String>();
			map.put("path", file.getPath().replace("\\", "/"));
			map.put("name", file.getName());
			list.add(map);
		}else{
			Map<String,String> map = new HashMap<String, String>();
			map.put("path", file.getPath().replace("\\", "/"));
			map.put("name", file.getName());
			list.add(map);
			String [] fileList = file.list();
			for(int i = 0;i<fileList.length;i++){
				findfile(list, path+"/"+fileList[i]);
			}
		}
		file = null;
	}
	
	public static void main(String [] a){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String path = "E:/work/doc/";
		findfile(list, path);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			System.out.println("----------------------");
			System.out.println("path:"+list.get(i).get("path"));
			System.out.println("name:"+list.get(i).get("name"));
		}
	}
}
