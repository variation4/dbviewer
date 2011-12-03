package com.variation4.util;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ClassUtilsTest extends ClassUtils {

	@Test
	public void testLoadClasses() throws IOException {
		List<Class<?>> classList = ClassUtils.loadClasses("sample.script");
		assertFalse(classList.isEmpty());
	}

}
