package com.variation4.dbviewer.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Database;

public class SelectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;



	/** DIフィールド */
	private List<Database> databaseList;

	private List<Command> commandList = new ArrayList<Command>();

	/** DIフィールド */
	public void setDatabaseList(List<Database> databaseList) {
		this.databaseList = databaseList;
	}

	public Database getDatabase(int index) {
		return databaseList.get(index);
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	protected void addCommand(Command command) {
		commandList.add(command);
	}

	@Override
	public String execute()  {
		System.out.println("call::execute()");
		return SUCCESS;
	}

	public String query()   {
		System.out.println("call::query()");

		try {
			doScript();
		} finally {
			for(Database db:databaseList){
				try {
					db.getDataSource().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return SUCCESS;
	}

	private void doScript() {

		Database db = getDatabase(0);

		// JAMWikiのユーザデータを見る場合の例

		Command command1 = new Command("カテゴリ", "select * from  jam_users");
		addCommand(command1);
		command1.execute(db);

		String firstName = command1.getFirstVal("username");
		String sql2 = "select * from  jam_authorities where username='" + firstName + "'";
		Command command2 = new Command("最初のユーザの権限", sql2);
		addCommand(command2);
		command2.execute(db);

		String lastName = command1.getLastVal("username");
		String sql3 = "select * from  jam_authorities where username='" + lastName + "'";
		Command command3 = new Command("最後のユーザの権限", sql3);
		addCommand(command3);
		command3.execute(db);

	}
}
