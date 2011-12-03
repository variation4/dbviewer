package com.variation4.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class ClassUtils {

	private final static String CLASS_FOOTER = ".class";

	/**
	 * packageName直下の全クラスを返す。
	 */
	public static List<Class<?>> loadClasses(String packageName) throws IOException {
		Iterable<JavaFileObject> jFiles = loadClassFileObject(StandardLocation.CLASS_PATH, packageName, false);
		return toClassList(packageName, jFiles);
	}

	/**
	 * classListからannotationClassアノテーションがついたクラスのみ抽出
	 */
	public static Set<Class<?>> filterAnnotation(List<Class<?>> classList, Class<? extends Annotation> annotationClass) {
		Set<Class<?>> result = new HashSet<Class<?>>();
		for (Class<?> clazz : classList) {
			Annotation a = clazz.getAnnotation(annotationClass);

			if (a != null) {
				result.add(clazz);
			}
		}
		return result;
	}

	private static List<Class<?>> toClassList(String packageName, Iterable<JavaFileObject> fileObjects) {
		List<Class<?>> result = new ArrayList<Class<?>>();
		for (JavaFileObject javaFileObject : fileObjects) {
			String className = editClassName(packageName, javaFileObject);
			try {
				Class<?> clazz = Class.forName(className);
				result.add(clazz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static String editClassName(String packageName, JavaFileObject javaFileObject) {
		String className = javaFileObject.getName();
		return packageName + "." + className.substring(0, className.length() - CLASS_FOOTER.length());
	}

	private static JavaFileManager getFileManager() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		// 致命的でない診断情報用の診断リスナー
		// null の場合、コンパイラのデフォルトのメソッドを使って診断情報を報告
		DiagnosticCollector<JavaFileObject> diagnosticListener = null;
		Locale locale = null;// 標準
		Charset charset = null;// 標準
		return compiler.getStandardFileManager(diagnosticListener, locale, charset);
	}

	private static Iterable<JavaFileObject> loadClassFileObject(StandardLocation location, String packageName, boolean includeSubPackage)
			throws IOException {

		JavaFileManager fileManager = getFileManager();

		Set<JavaFileObject.Kind> kind = new HashSet<JavaFileObject.Kind>();
		kind.add(JavaFileObject.Kind.CLASS);

		return fileManager.list(location, packageName, kind, includeSubPackage);
	}
}
