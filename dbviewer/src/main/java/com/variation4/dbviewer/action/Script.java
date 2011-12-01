package com.variation4.dbviewer.action;

import java.util.List;
import java.util.Map;

import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Database;

public interface Script {
	public String getScriptId();

	public String getScriptCaption();

	public List<String> getUseParams();

	public void doScript(List<Database> databaseList, List<Command> commandList, Map<String, String> sqlParams);
}
