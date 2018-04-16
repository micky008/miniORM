package com.epsi.orm.epsiorm.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Michael
 */
public class ORMHelper<T> {

				protected Class clazz;
				protected Connection con;

				public ORMHelper(Class clazz, Connection con) {
								this.clazz = clazz;
								this.con = con;
				}

				/**
				 * Convertie un Field en colonne sql.
				 *
				 * @param fieldName
				 * @return
				 */
				protected String convertFieldToChamp(String fieldName) {
								StringBuilder res = new StringBuilder(fieldName.length() + 2);
								for (int i = 0; i < fieldName.length(); i++) {
												char c = fieldName.charAt(i);
												if (Character.isUpperCase(c)) {
																res.append('_');
												}
												res.append(c);
								}
								return res.toString().toLowerCase();
				}

				/**
				 * Convertie une colonne en Field [camelCase]
				 *
				 * @param columnName
				 * @return
				 */
				protected String convertChampToField(String columnName) {
								StringBuilder res = new StringBuilder(columnName.length() + 2);
								for (int i = 0; i < columnName.length(); i++) {
												char c = columnName.charAt(i);
												c = Character.toLowerCase(c);
												if (c == '_') {
																++i;
																c = columnName.charAt(i);
																c = Character.toUpperCase(c);
												}
												res.append(c);
								}
								return res.toString();
				}

				protected Object getObject(ResultSetMetaData rsmd, ResultSet rs, int i) throws SQLException {
								Object obj = null;
								switch (rsmd.getColumnType(i)) {
												case Types.VARCHAR: //12
																obj = rs.getString(i);
																break;
												case Types.INTEGER: //4
																obj = rs.getInt(i);
																break;
												case Types.TINYINT: //-6
												case Types.BIT: //-7
																obj = rs.getBoolean(i);
																break;
												default:
																break;
								}
								return obj;
				}

				protected void setFieldValue(String fStr, Object entity, Object toInject) throws Exception {
								Field f = clazz.getDeclaredField(fStr);
								f.setAccessible(true);
								f.set(entity, toInject);
				}

				protected Object getFieldValue(Field f, Object entity) throws Exception {
								f.setAccessible(true);
								return f.get(entity);
				}

				protected T buildObject(ResultSet rs) throws Exception {
								ResultSetMetaData rsmd = rs.getMetaData();
								String result = null;
								Object objEntity = clazz.newInstance();
								Object toInject = null;
								for (int i = 1; i < rsmd.getColumnCount(); i++) {
												result = convertChampToField(rsmd.getColumnName(i));
												toInject = getObject(rsmd, rs, i);
												setFieldValue(result, objEntity, toInject);
								}
								return (T) objEntity;
				}

				protected String getTableName() {
								return clazz.getSimpleName().toLowerCase() + "s";
				}

				protected boolean needQuote(Field f) {
								return (f.getType() == String.class);
				}

				/**
				 * Cree une ligne du builder colonne=value. si la colonne est de type string
				 * alors on quote et ca devient colonne='value'.
				 *
				 * @param f
				 * @param builder
				 * @param t
				 * @return
				 * @throws Exception
				 */
				protected ORMBuilder makeLineBuilder(Field f, ORMBuilder builder, T t) throws Exception {
								String fieldStr = convertFieldToChamp(f.getName());
								builder = builder.append(fieldStr).equal();
								Object val = getFieldValue(f, t);
								if (val == null) {
												builder.append("null").comma();
												return builder;
								}
								if (needQuote(f)) {
												builder = builder.toQuote(val.toString());
								} else {
												builder = builder.append(val.toString());
								}
								builder.space().comma();
								return builder;
				}

				protected String createIdLine(T t) throws Exception {
								ORMBuilder idBuilder = new ORMBuilder();
								for (Field f : clazz.getDeclaredFields()) {
												if (f.getName().startsWith("id")) {
																this.makeLineBuilder(f, idBuilder, t);
												}
								}
								idBuilder.trim();
								return idBuilder.toString();
				}

}
