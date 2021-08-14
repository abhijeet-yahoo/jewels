package com.jiyasoft.jewelplus.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.DbBackupUtils;

@Controller
@RequestMapping("/admin")
public class DataBackupController {
	
	@Value("${data.backup.path}")
	private String dataBackupPath;
	
	@Value("${mysql.server.path}")
	private String mySqlServerPath;
	
	@Value("${mysql.server.procpath}")
	private String mySqlServerProcPath;
	
	
	@RequestMapping("/dataBackup")
	public String dataBackup(){
		return "dataBackup";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/dataBackup/confirm")
	public String dataBackupConfirm(){
		
		  Date date = new Date();
	  	  SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy");
	  	  String fileName = sf.format(date);
	  	  
	  	  /*String msPath="C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump -uroot -proot123 --routines -B jewels_demo -r " + dataBackupPath + fileName + ".sql";*/
	  	  
	  	  
	  	  
	  	  String path = mySqlServerPath+dataBackupPath+fileName+".sql";
	  	  
	  	  /*System.out.println("path       "+path);*/
	  	  /*String status = DbBackupUtils.restoreDB(path);*/
	  	  
	  	String status = DbBackupUtils.backUpDB(path);
	  	  
	  	/*  String procFileName = sf.format(date)+"-proc";
	  	  String procPath = mySqlServerProcPath+dataBackupPath+procFileName+".sql";
	  	  String procStatus = DbBackupUtils.restoreProc(procPath);*/
	  	  
		
		return status;
	}
	

 }
