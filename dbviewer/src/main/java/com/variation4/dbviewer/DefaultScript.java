package com.variation4.dbviewer;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Scriptの標準実装
 */
public abstract class DefaultScript implements Script {

	private String caption;

	public DefaultScript(String caption) {
		this.caption = caption;
	}

	@Override
	public String getScriptId() {
		return this.getClass().getName();
	}

	@Override
	public String getScriptCaption() {
		return caption;
	}

	@Override
	public List<String> getUseParams() {
		return Collections.emptyList();
	}

	/**
	 * ScriptHolder用
	 */
	public Set<Script> getScriptList() {
		Set<Script> result = new HashSet<Script>();
		result.add(this);
		return result;
	}

}
