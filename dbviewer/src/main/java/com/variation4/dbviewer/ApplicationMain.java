package com.variation4.dbviewer;

class ApplicationMain extends AbstractApplication {

	public static void main(String[] args) {
		new ApplicationMain().execute();
	}

	@Override
	public void doScript(Dao dao) {
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
