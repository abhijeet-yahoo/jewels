package com.jiyasoft.jewelplus.common;

public class DbBackupUtils {
	
	
	 public static String backUpDB(String path){
		 Process p = null;
		 
		 try {
             Runtime runtime = Runtime.getRuntime();
/*             p = runtime.exec("mysqldump -uroot -pdbpass --add-drop-database -B dbname -r " + "filepath" + "Filename" + ".sql");*/

             p = runtime.exec(path);
             int processComplete = p.waitFor();

         	return processComplete+"";
           

         } catch (Exception e) {
             e.printStackTrace();
             
             return "Error";
         }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		/* 
		 
		//String executeCmd = "C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump -u root -proot123 --routines -B mklive -r" + path;
		
		 int processComplete = 2;
		    
		 try{
	    	Process runtimeProcess = Runtime.getRuntime().exec(path);
	    	processComplete = runtimeProcess.waitFor();
		 }catch(Exception ex){
	       ex.printStackTrace();
		 }*/
	    	
	
		
		
	 }
	 
	 
	 public static String restoreProc(String path){
		 
		 //String executeCmd = "C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump -u root -proot123 --routines --no-data --no-create-db --skip-opt -B mklive -r" + path;
		 	
		 int processComplete = 2;
		    
		 try{
	    	Process runtimeProcess = Runtime.getRuntime().exec(path);
	    	processComplete = runtimeProcess.waitFor();
		 }catch(Exception ex){
	       ex.printStackTrace();
	      
		 }
		 
		 return processComplete+"";
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		 
		     /* public static void main(String[]args){
		 
		    	  DbBackupUtils db = new DbBackupUtils();
		    	   
		    	  Date date = new Date();
		    	  SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy");
		    	 
		    	  //String fileName = sf.format(date)+"proc";
		    	    
		    	  String fileName = "ggg";
		    	  
		    	  System.out.println(fileName);
		    	  
		    	  String path = "D:/datatest/"+fileName+".sql";
		    	  System.out.println("method called");
		    	  db.demo(path);
		    	   
		      }*/
		
	}
	
	
	

