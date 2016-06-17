package com.packages.util;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class WordExport{

    private Dispatch  doc = null;     
    private ActiveXComponent word = null;    
    private Dispatch documents = null;
    
    private Dispatch tables = null;
   
    private static Object appLock = new Object(); 	
	public WordExport()
	{
		// use jacob
	}
	public boolean export(String templatefile, String tempfile, String datasource)
	{
		if (!load(templatefile)) {
			return false;
		}
		
		tables = Dispatch.get(doc, "Tables").toDispatch();
		
		Object jsonObject =JSONValue.parse(datasource);
		JSONArray jsonArray=(JSONArray)jsonObject;
		for(int i=0; i<jsonArray.size(); i++){			
			JSONObject obj = (JSONObject)jsonArray.get(i);
			Iterator iter = obj.entrySet().iterator(); 
			while (iter.hasNext()) { 
			    Map.Entry entry = (Map.Entry) iter.next(); 
			    Object key = entry.getKey(); 
			    Object val = entry.getValue(); 
			    String marcoValue = key.toString();
			    String cellValue = val.toString();
			    if(cellValue.indexOf("[") != -1 || cellValue.indexOf("]") != -1){
			    	cellValue = cellValue.replace("[", "");
			    	cellValue = cellValue.replace("]", "");
			    	String[] cellArr = cellValue.split(",");
			    	//replace table text
			    	replaceOneTable(marcoValue, cellArr);
			    } else {
			    	//replace label text
			    	replaceOneLabel(marcoValue, cellValue.trim());
			    }
			}
		}

		try
		{
			save(tempfile);
		}
		catch (Exception e)
		{
	       	e.printStackTrace();   	
		}
        finally {   
  	
            if (doc != null)   
                close(doc);   
        }   
		return true;
	}

    public Dispatch select() {   
        return word.getProperty("Selection").toDispatch();   
    }   
    
    public void moveStart(Dispatch selection) {   
        Dispatch.call(selection, "HomeKey", new Variant(6));   
    }   
	
	private void  replaceOneLabel(String macro, String value)
	{
		try
		{
			while (true)
			{
				Dispatch selection = select();
				moveStart(selection);
				if (find(selection, macro))
				{
					replace(selection, value);
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e)
		{
	       	e.printStackTrace();   	
		}
	}


	private void replaceOneTableCell(String macro, String value,  int tableindex, int colindex, int rowindex)
	{
		try
		{
			Dispatch selection = select();
			moveStart(selection);
			
			boolean exist = find(selection, macro);
			
			//if (exist)
			//{
				Dispatch table = Dispatch.call(tables, "Item", new Variant(tableindex)).toDispatch();
				
				if (table == null)
					return;
				
				
/*				if (newline)
				{
					Dispatch rows = Dispatch.call(table, "Rows").toDispatch();
					Dispatch.call(rows, "Add"); 
	
				}*/

				Dispatch cell = Dispatch.call(table, "Cell", new Variant(rowindex), new Variant(colindex)).toDispatch();
				Dispatch.call(cell, "Select");
				
		        String text = Dispatch.get(selection, "Text").toString();	

				replace(selection, value);
				Dispatch.call(cell, "Select");
/*				if (!lastline)
				{
					Dispatch newcell = Dispatch.call(table, "Cell", new Variant(rowindex+1), new Variant(colindex)).toDispatch();
					Dispatch.call(newcell, "Select");
					Dispatch.put(selection, "Text", text);
				}*/
			//}				
			
		}
        catch (Exception e) {   
        	e.printStackTrace();
        }		
	}
	
  
    public Dispatch open(String inputDoc) {   
        return Dispatch.call(documents, "Open", inputDoc).toDispatch();    
    }  
    
    public boolean find(Dispatch selection, String toFindText) {   
        Dispatch find = Dispatch.call(selection, "Find").toDispatch();   
        Dispatch.put(find, "Text", toFindText);   
        Dispatch.put(find, "Forward", "True");   
        Dispatch.put(find, "Format", "True");   
        Dispatch.put(find, "MatchCase", "True");   
        Dispatch.put(find, "MatchWholeWord", "True");   
        return Dispatch.call(find, "Execute").getBoolean();   
    }   
   
    public void replace(Dispatch selection, String newText) {   
        Dispatch.put(selection, "Text", newText);   
    }   
   
    
    private void close(Dispatch doc) {   
        Dispatch.call(doc, "Close", new Variant(false));   
        closeApp();   
    }   
   
    private void closeApp() {   
        Dispatch.call(word, "Quit");   
        word = null;   
        doc = null;   
        ComThread.Release();
    }

    public void save(String outputPath) {   
        //Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(),"FileSaveAs", outputPath);   
        Dispatch.call(doc, "SaveAS", outputPath);   
    }   
    
	private boolean load(String templatefile)
	{
		ComThread.InitSTA();
		try  
		{  
			if (word == null) {   
	            synchronized (appLock) {   
	                if (word == null) {   
	                    word = new ActiveXComponent("Word.Application"); 
	                }   
	            }   
	        }   
			if (word == null)
				System.out.println("advance export word load error");			
			word.setProperty("Visible", new Variant(false));  
			documents = word.getProperty("Documents").toDispatch();
	        //documents = Dispatch.get(word, "Documents").toDispatch();	        
	        doc = open(templatefile);
		}
        catch (Exception e) {   
        	e.printStackTrace();
			return false;
        }		
		return true;
	}

	private boolean getColumnInfoByMacro(String macro, int[] index)
	{
//		macro = macro.trim();
	
		int tableCount = Dispatch.call(tables, "Count").changeType(Variant.VariantInt).getInt();;

		System.out.print("getColumnInfoByMacro table count="+tableCount+"\n");
	
		for (int i = 1; i <= tableCount; i++)
		{
			Dispatch table = Dispatch.call(tables, "Item", new Variant(i)).toDispatch();
			Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
			
			int colCount = Dispatch.call(cols, "Count").changeType(Variant.VariantInt).getInt();
			
			System.out.print("getColumnInfoByMacro col count="+colCount+"\n");
			for (int j = 1; j <= colCount; j++)
			{
				
				Dispatch cell = Dispatch.call(table, "Cell",
						new Variant(2), new Variant(j)).toDispatch(); // first line is column name
				
				if (cell != null)
				{
					Dispatch range = Dispatch.get(cell, "Range").toDispatch();
					if (range != null)
					{
						String text = Dispatch.get(range, "Text").changeType(Variant.VariantString).getString();
						
						System.out.print("getColumnInfoByMacro celltext="+text+"\n");
//						text = text.trim();
						if (text.indexOf('\r') != -1)
							text = text.substring(0, text.indexOf('\r'));
						System.out.print("getColumnInfoByMacro celltext2 ="+text+"\n");
//						if (text.equals(macro))
						if (text.indexOf(macro) != -1)
						{
							index[0] = i;
							index[1] = j;
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	
	private void replaceOneTable(String macro, String[] colValus)
	{
		int[] index = new int[2];
		if (getColumnInfoByMacro(macro,  index)) {
			for (int i = 0; i < colValus.length; i++)
			{
				replaceOneTableCell(macro, colValus[i].replace("\"", ""), index[0],  index[1],  i+2);				
				Dispatch selection = Dispatch.get(word, "Selection").toDispatch();
				Dispatch.call(selection, "MoveDown");
			}
		}
	}
	
	
	public static void main(String arg[]){
		WordExport we = new WordExport();
		//we.export("D:\\chenksoft\\apache\\htdocs\\CK_WEB\\export\\源圭入库打印.doc", "D:\\chenksoft\\apache\\htdocs\\CK_WEB\\temp\\helloworld.doc");
		
	}

}
