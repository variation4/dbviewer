package com.variation4.dbviewer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class Database extends SimpleJdbcDaoSupport {

	private static final DateFormat FORMATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	private String caption;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public List<DbRecord> query(String sql) {
		return getSimpleJdbcTemplate().query(sql, new MyRowMapper());
	}

	public static String cnvColNm(String s) {
		return s.toUpperCase();
	}

	private static class MyRowMapper implements ParameterizedRowMapper<DbRecord> {
		public DbRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			ResultSetMetaData meta = rs.getMetaData();

			DbRecord record = new DbRecord();

			for (int i = 0; i < meta.getColumnCount(); i++) {
				int colIndex = i + 1;
				String colName = meta.getColumnName(colIndex);
				String col = cnvColNm(colName);

				int type = meta.getColumnType(i + 1);
				if (type == Types.TIMESTAMP) {
					Timestamp ts = rs.getTimestamp(colName);
					if (ts == null) {
						record.put(col, null);
					} else {
						String s = FORMATE.format(new Date(ts.getTime()));
						record.put(col, s);
					}
				} else if (type == Types.DATE) {
					Date date = rs.getDate(colName);
					if (date == null) {
						record.put(col, null);
					} else {
						String s = FORMATE.format(date);
						record.put(col, s);
					}
				} else if (type == Types.TIME) {
					Time time = rs.getTime(colName);
					if (time == null) {
						record.put(col, null);
					} else {
						String s = FORMATE.format(time);
						record.put(col, s);
					}
				} else {
					record.put(col, rs.getString(colName));
				}

			}
			return record;
		}
	}

}
