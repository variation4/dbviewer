package com.variation4.dbviewer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * このアノテーションが付けられたクラスは、
 * 引数なしのgetScpiptList():Collection<com.variation4.dbviewer.Script>メソッドの実装が必要です。
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ScriptHolder {
}
