package com.variation4.dbviewer;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

public class ScriptFactioryTest extends ScriptFactiory {

	@Test
	public void testLoadScripts() throws IOException {
		Set<Script> scriptList = ScriptFactiory.loadScriptsFromPackageName("sample.script");
		assertFalse(scriptList.isEmpty());
	}

}
