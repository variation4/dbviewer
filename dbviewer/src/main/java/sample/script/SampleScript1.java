package sample.script;

import java.util.List;
import java.util.Map;

import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Database;
import com.variation4.dbviewer.DefaultScript;
import com.variation4.dbviewer.ScriptHolder;

@ScriptHolder
public class SampleScript1 extends DefaultScript {

	public SampleScript1() {
		super("サンプルスクリプト1");
	}

	@Override
	public void doScript(List<Database> databaseList, List<Command> commandList, Map<String, String> sqlParams) {
		Database db = databaseList.get(0);

		// JAMWikiのユーザデータを見る場合の例

		Command command1 = new Command("カテゴリ", "select * from  jam_users");
		commandList.add(command1);
		command1.execute(db);

		String firstName = command1.getFirstVal("username");
		String sql2 = "select * from  jam_authorities where username='" + firstName + "'";
		Command command2 = new Command("最初のユーザの権限", sql2);
		commandList.add(command2);
		command2.execute(db);

		String lastName = command1.getLastVal("username");
		String sql3 = "select * from  jam_authorities where username='" + lastName + "'";
		Command command3 = new Command("最後のユーザの権限", sql3);
		commandList.add(command3);
		command3.execute(db);

	}

}
