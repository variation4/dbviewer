package com.variation4.dbviewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Script集
 */
public class ScliptList {
	private List<Script> scriptList = new ArrayList<Script>();

	private boolean sorted = false;

	public void add(Script script) {
		scriptList.add(script);
		sorted = false;
	}

	public void addAll(Collection<Script> scripts) {
		scriptList.addAll(scripts);
		sorted = false;
	}

	public List<Script> getList() {
		if (!sorted) {
			Collections.sort(scriptList, new MySort());
			sorted = true;
		}
		return scriptList;
	}

	private static class MySort implements Comparator<Script> {

		@Override
		public int compare(Script o1, Script o2) {
			return o1.getScriptCaption().compareTo(o2.getScriptCaption());
		}

	}
}
