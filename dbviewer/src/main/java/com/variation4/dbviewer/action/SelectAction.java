package com.variation4.dbviewer.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Dao;

public class SelectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Dao> daoList;

	public void setDaoList(List<Dao> daoList) {
		this.daoList = daoList;
	}

	@Override
	public String execute() throws Exception {
		Dao dao = daoList.get(0);

		doScript(dao);

		return super.execute();
	}

	private void doScript(Dao dao) {
		Command command1 = new Command("カテゴリ", "select * from  jam_users");
		command1.execute(dao);

		String firstName = command1.getFirstVal("username");
		String sql2 = "select * from  jam_authorities where username='" + firstName + "'";
		Command command2 = new Command("最初のユーザの権限", sql2);
		command2.execute(dao);

		String lastName = command1.getLastVal("username");
		String sql3 = "select * from  jam_authorities where username='" + lastName + "'";
		Command command3 = new Command("最後のユーザの権限", sql3);
		command3.execute(dao);

	}
}
