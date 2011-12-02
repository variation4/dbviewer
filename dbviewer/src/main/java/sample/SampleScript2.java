package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Database;
import com.variation4.dbviewer.action.Script;

public class SampleScript2 implements Script {

	@Override
	public String getScriptId() {
		return this.getClass().getName();
	}

	@Override
	public String getScriptCaption() {
		return "サンプルスクリプト2";
	}

	@Override
	public List<String> getUseParams() {
		List<String> list = new ArrayList<String>();
		list.add("userName");
		return list;
	}

	@Override
	public void doScript(List<Database> databaseList, List<Command> commandList, Map<String, String> sqlParams) {
		Database db = databaseList.get(0);

		// JAMWikiのユーザデータを見る場合の例

		String userName = sqlParams.get("userName");

		Command command1 = new Command("カテゴリ", "select * from  jam_users where USERNAME='" + userName + "'");
		commandList.add(command1);
		command1.execute(db);

		String sql2 = "select * from  jam_authorities where username='" + userName + "'";
		Command command2 = new Command("ユーザの権限", sql2);
		commandList.add(command2);
		command2.execute(db);

	}

}
