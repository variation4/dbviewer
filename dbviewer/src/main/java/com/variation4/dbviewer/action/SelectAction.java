package com.variation4.dbviewer.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Database;
import com.variation4.dbviewer.Script;
import com.variation4.dbviewer.ScriptFactiory;

public class SelectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String PARAM_HEAD = "param-";

	/** DIフィールド */
	private List<Database> databaseList;

	/** DIフィールド */
	private List<String> scriptHolderList;

	private List<Script> scriptList;

	private String scriptId;

	private List<Command> commandList = new ArrayList<Command>();

	/** DIフィールド */
	public void setDatabaseList(List<Database> databaseList) {
		this.databaseList = databaseList;
	}

	/** DIフィールド */
	public Database getDatabase(int index) {
		return databaseList.get(index);
	}

	/** DIフィールド */
	public void setScriptHolderList(List<String> scriptHolderList) {
		this.scriptHolderList = scriptHolderList;
	}

	// Tomcat起動だと、ClassUtils.loadClasses("sample.script")クラスが見つけられない。。;
	// public List<Script> getScriptList() {
	// if (scriptList == null) {
	// scriptList = ScriptFactiory.loadScripts("sample.script");
	// }
	// return scriptList ;
	// }

	public List<Script> getScriptList() {
		if (scriptList == null) {
			scriptList = new ArrayList<Script>();
			for (String className : scriptHolderList) {
				scriptList.addAll(ScriptFactiory.loadScriptsFromClassName(className));
			}
		}
		return scriptList;
	}

	public String getScriptId() {
		return scriptId;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	protected void addCommand(Command command) {
		commandList.add(command);
	}

	@Override
	public String execute() {
		return SUCCESS;
	}

	public String query() {
		Map<String, String> sqlParams = getSqlParams();
		for (Entry<String, String> entry : sqlParams.entrySet()) {
			System.out.println(entry);
		}

		Script script = getScript();

		try {
			script.doScript(databaseList, commandList, sqlParams);
		} finally {
			for (Database db : databaseList) {
				try {
					db.getDataSource().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return SUCCESS;
	}

	protected Map<String, String> getSqlParams() {
		HttpServletRequest request = ServletActionContext.getRequest();

		@SuppressWarnings("unchecked")
		Map<Object, Object> parameterMap = (Map<Object, Object>) request.getParameterMap();
		Map<String, String> result = new HashMap<String, String>();
		for (Entry<Object, Object> entry : parameterMap.entrySet()) {
			if (entry.getKey() == null || entry.getValue() == null) {
				continue;
			}
			String key = entry.getKey().toString();
			if (!key.startsWith(PARAM_HEAD)) {
				continue;
			}
			String sqlParamKey = key.substring(PARAM_HEAD.length());
			String[] values = (String[]) entry.getValue();
			if (values.length != 1) {
				continue;
			}
			result.put(sqlParamKey, values[0]);
		}

		return result;
	}

	protected Script getScript() {
		Class<?> clazz;
		try {
			clazz = Class.forName(scriptId);
			return (Script) clazz.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("scriptId=" + scriptId, e);
		}
	}

}
