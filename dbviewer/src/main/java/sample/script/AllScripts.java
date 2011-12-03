package sample.script;

import java.util.HashSet;
import java.util.Set;

import com.variation4.dbviewer.Script;
import com.variation4.dbviewer.ScriptHolder;

@ScriptHolder
public class AllScripts {
	public Set<Script> getScriptList() {
		Set<Script> result = new HashSet<Script>();
		result.add(new SampleScript1());
		result.add(new SampleScript2());
		return result;
	}
}
