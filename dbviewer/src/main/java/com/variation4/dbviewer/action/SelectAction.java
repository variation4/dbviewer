package com.variation4.dbviewer.action;

import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Dao;

public class SelectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	protected void doScript(Dao dao) {

		// JAMWikiのユーザデータを見る場合の例

		Command command1 = new Command("カテゴリ", "select * from  jam_users");
		addCommand(command1);
		command1.execute(dao);

		String firstName = command1.getFirstVal("username");
		String sql2 = "select * from  jam_authorities where username='" + firstName + "'";
		Command command2 = new Command("最初のユーザの権限", sql2);
		addCommand(command2);
		command2.execute(dao);

		String lastName = command1.getLastVal("username");
		String sql3 = "select * from  jam_authorities where username='" + lastName + "'";
		Command command3 = new Command("最後のユーザの権限", sql3);
		addCommand(command3);
		command3.execute(dao);

	}
}
