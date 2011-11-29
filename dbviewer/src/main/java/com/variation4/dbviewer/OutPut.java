package com.variation4.dbviewer;

import java.util.ArrayList;
import java.util.List;

public class OutPut {

	private static void out(String s) {
		System.out.print(s);
	}

	private static void outReturn() {
		System.out.println();
	}

	public static void output(String s) {
		out(s);
		outReturn();
	}

	private static void outCol(String s) {
		out(s);
		out("\t");
	}

	public static void outputHead(List<DbRecord> recordList) {
		if (recordList == null || recordList.isEmpty()) {
			return;
		}

		for (String s : getHeads(recordList)) {
			outCol(s);
		}
		outReturn();
	}

	public static void outputData(List<DbRecord> recordList) {
		if (recordList == null || recordList.isEmpty()) {
			outCol("nothing");
			outReturn();
			return;
		}

		List<String> heads = getHeads(recordList);

		for (DbRecord record : recordList) {
			for (String colName : heads) {
				outCol(record.get(colName));
			}
			outReturn();
		}

	}

	private static List<String> getHeads(List<DbRecord> record) {
		List<String> list = new ArrayList<String>();

		for (String head : record.get(0).keySet()) {
			list.add(head);
		}

		return list;
	}
}
