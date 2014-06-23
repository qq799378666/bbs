package com.action.json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dto.ParamDTO;
import com.form.TimeScopeForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ArticleService;
import com.service.CommentService;
import com.service.MenuService;
import com.service.ModuleService;
import com.service.PostionService;
import com.service.UserService;

public abstract class JSONBaseAction<T> extends ActionSupport {
	@Resource(name="articleService")
	protected ArticleService articleService;
	@Resource(name="userService")
	protected UserService userService;
	@Resource(name="moduleService")
	protected ModuleService moduleService;
	@Resource(name="commentService")
	protected CommentService commentService;
	@Resource(name="menuService")
	protected MenuService menuService;
	@Resource(name="postionService")
	protected PostionService postionService;
	
	private Object root;
	public Object getRoot() {
		return root;
	}
	public void setRoot(Object root) {
		this.root = root;
	}

	private Map<String,Object> temp = new HashMap<String, Object>();
	
	public void p(String key,Object value){
		temp.put(key, value);
	}
	
	public JSONBaseAction(){
		temp.put("success", true);
		this.root = temp;
	}
	
	
	protected T model;	
	protected List<ParamDTO> pvs = new ArrayList<ParamDTO>();
	protected Map<String,String> search = new HashMap<String, String>();
	protected List<TimeScopeForm> tsfs = new ArrayList<TimeScopeForm>();
	
	public List<ParamDTO> getPvs() {
		return pvs;
	}

	public void setPvs(List<ParamDTO> pvs) {
		this.pvs = pvs;
	}

	public Map<String, String> getSearch() {
		return search;
	}

	public void setSearch(Map<String, String> search) {
		this.search = search;
	}

	public List<TimeScopeForm> getTsfs() {
		return tsfs;
	}

	public void setTsfs(List<TimeScopeForm> tsfs) {
		this.tsfs = tsfs;
	}

	//对Action里面的getList方法进行筛选结果
	protected void controlList(){
			try{
				if(model!=null){
					Field[] fs = model.getClass().getDeclaredFields();
					for(Field f:fs){
						f.setAccessible(true);
						Object o = f.get(model);
						if(o==null)continue;
						Class type = o.getClass();
						if(type.equals(Integer.class)||type.equals(Boolean.class)){
							this.pvs.add(new ParamDTO(f.getName(), o, "eq"));
						}
						else{
							Field[] fs2 = o.getClass().getDeclaredFields();
							for(Field f2:fs2){
								if(f2.getName().equals("id")){
									this.pvs.add(new ParamDTO(f.getName(), o, "eq"));
									break;
								}
							}
						}
					}
				}
				if(this.search!=null)
					for(Map.Entry<String, String> entry:search.entrySet()){    
						ParamDTO p = new ParamDTO(entry.getKey(), entry.getValue(), "like");
						pvs.add(p);
					}  
				
				if(this.tsfs!=null)
					for(TimeScopeForm tsf:tsfs){
						ParamDTO p = new ParamDTO(tsf.getKey(), tsf.getStartDate(), tsf.getEndDate(), "between");
						pvs.add(p);
					}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	
	
	
	
	
}
