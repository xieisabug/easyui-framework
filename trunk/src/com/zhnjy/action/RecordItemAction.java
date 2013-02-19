package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.RecordItem;
import com.zhnjy.util.db.RecordItemDB;

public class RecordItemAction extends ActionSupport{
 
	private String id;
	private String name;
	
	/**
	 * 增加缴费记录到数据库
	 * @return
	 */	
	public String add(){
		RecordItem ri = new RecordItem(0, name);
		if(RecordItemDB.add(ri) != 0){
			return "addsuccess";
		}else{
			return "addfail";
		}
	}
	
	/**
	 * 删除一条缴费记录
	 * @return
	 */
	public String delete(){
		int _id = Integer.parseInt(id); 
		if(RecordItemDB.delete(_id) != 0){
			return "deletesuccess";
		}else{
			return "deletefail";
		}
	}
	
	public String updateBefore(){
		int _id = Integer.parseInt(id); 
		RecordItem ri = RecordItemDB.getRecordItem(_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("recorditem", ri);
		return "updatebeforesuccess";
	}

	
	
	/**
	 *修改缴费记录
	 * @return
	 */
	public String update(){
		int _id = Integer.parseInt(id);
		RecordItem ri = new RecordItem(_id, name);
		
		if(RecordItemDB.update(ri) != 0){
			return "updatesuccess";
		}else{
			return "updatefail";
		}
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	public String query(){
		List<RecordItem> list = RecordItemDB.getList(name);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "querysuccess";
	}
	
	public void getDatas(){
		List<RecordItem> list = RecordItemDB.getList(name);
		String result = JSONSerializer.toJSON(list).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
