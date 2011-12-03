package com.variation4.dbviewer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variation4.util.ClassUtils;

public class ScriptFactiory {
	private final static String METHOD_NAME = "getScriptList";

	/**
	 * packageName直下でScriptHolderアノテーションが付けられた全クラスを返します。
	 */
	public static Set<Script> loadScriptsFromPackageName(String packageName) {
		List<Class<?>> classList;
		try {
			classList = ClassUtils.loadClasses(packageName);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		Set<Script> result = new HashSet<Script>();

		Set<Class<?>> scriptHolderList = ClassUtils.filterAnnotation(classList, ScriptHolder.class);
		for (Class<?> clazz : scriptHolderList) {
			Set<Script> scriptList = loadScripts(clazz);
			result.addAll(scriptList);
		}

		return result;
	}

	public static Set<Script> loadScriptsFromClassName(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return loadScripts(clazz);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * holderClassをインスタンス化し、getScriptList()からスクリプトのリストを取得
	 */
	public static Set<Script> loadScripts(Class<?> holderClass) {

		Method method;
		try {
			method = holderClass.getMethod(METHOD_NAME);
		} catch (Exception e) {
			throw new IllegalStateException(holderClass + "." + METHOD_NAME + " access is fatal..", e);
		}

		try {
			Object holder = holderClass.newInstance();
			@SuppressWarnings("unchecked")
			Set<Script> scriptList = (Set<Script>) method.invoke(holder);

			return scriptList;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}
}
