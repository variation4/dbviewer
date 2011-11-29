package com.variation4.dbviewer;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Command {
	private String name;
	private String sql;
	private List<DbRecord> result;

	public Command(String name, String sql) {
		this.name = name;
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public String getSql() {
		return sql;
	}

	public void execute(Dao dao) {
		OutPut.output("");
		OutPut.output(sql);
		OutPut.output(name);
		result = dao.query(sql);
		OutPut.outputHead(result);
		OutPut.outputData(result);
	}

	public String getFirstVal(String colName) {
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0).get(colName.toUpperCase());
	}

	public String getLastVal(String colName) {
		if (result == null || result.isEmpty()) {
			return null;
		}

		return result.get(result.size() - 1).get(colName.toUpperCase());
	}

	public Set<String> getHeaders() {
		if (result == null || result.isEmpty()) {
			return Collections.emptySet();
		}
		return result.get(0).keySet();
	}

	public List<DbRecord> getResult() {
		return result;
	}

}
