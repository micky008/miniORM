package com.epsi.orm.epsiorm.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class ORM<T> extends ORMHelper<T> {

				public ORM(Class clazz, Connection con) {
								super(clazz, con);
				}

				protected T selectOnce(String where) throws Exception {
								List<T> l = select(where);
								if (!l.isEmpty()) {
												return l.get(0);
								}
								return null;
				}

				public int update(T t) throws Exception {
								return update(t, createIdLine(t));
				}

				protected int update(T t, String where) throws Exception {
								List<T> liste = new ArrayList<>(1);
								liste.add(t);
								return update(liste, where);
				}

				public int insert(T obj) throws Exception {
								List<T> l = new ArrayList<>(1);
								l.add(obj);
								return insert(l);
				}

				public int delete(T t) throws Exception {
								return delete(createIdLine(t));
				}

				public int delete(List<T> ts) throws Exception {
								List<String> ls = new ArrayList<>(ts.size());
								for (T t : ts) {
												ls.add(createIdLine(t));
								}
								return deleteWheres(ls);
				}

				protected int delete(String where) throws Exception {
								List<String> l = new ArrayList<>(1);
								l.add(where);
								return deleteWheres(l);
				}

				protected List<T> select(String where) throws Exception {
								ORMBuilder builder = new ORMBuilder();
								for (Field f : clazz.getDeclaredFields()) {
												builder.append(convertFieldToChamp(f.getName())).comma();
								}
								builder.trim();
								String sql = "SELECT " + builder.toString() + " from " + getTableName() + " " + where;
								System.out.println(sql);
								Statement st = con.createStatement();
								List<T> liste;
								try (ResultSet rs = st.executeQuery(sql)) {
												liste = new ArrayList<>();
												while (rs.next()) {
																liste.add(buildObject(rs));
												}
								}
								return liste;
				}

				protected int update(List<T> liste, String where) throws Exception {
								Statement st = con.createStatement();
								ORMBuilder builder = null;
								for (T t : liste) {
												builder = new ORMBuilder();
												for (Field f : clazz.getDeclaredFields()) {
																builder = this.makeLineBuilder(f, builder, t);
												}
												builder.trim();
												st.addBatch("UPDATE " + getTableName() + " SET " + builder.toString() + " WHERE " + where);
								}
								int res[] = st.executeBatch();
								st.close();
								int tot = 0;
								for (int i : res) {
												tot += i;
								}
								return tot;
				}

				//			INSERT INTO table (nom_colonne_1, nom_colonne_2, ...
				//VALUES ('valeur 1', 'valeur 2', ...)
				public int insert(List<T> liste) throws Exception {
								ORMBuilder builderTable = new ORMBuilder();

								for (Field f : clazz.getDeclaredFields()) {
												String fieldStr = convertFieldToChamp(f.getName());
												builderTable.append(fieldStr).comma();
								}
								builderTable.trim();
								ORMBuilder builderValues = null;
								Statement st = con.createStatement();
								Object objValue;
								String sql = null;
								for (T t : liste) {
												builderValues = new ORMBuilder();
												for (Field f : clazz.getDeclaredFields()) {
																objValue = getFieldValue(f, t);
																if (this.needQuote(f)) {
																				builderValues.toQuote(objValue.toString()).comma();
																				continue;
																}
																if (objValue == null) {
																				builderValues.append("null");
																} else {
																				builderValues.append(objValue.toString());
																}
																builderValues.comma();
												}
												builderValues.trim();
												sql = "INSERT INTO " + getTableName() + " (" + builderTable.toString() + ") VALUES (" + builderValues.toString() + ")";
												System.out.println(sql);
												st.addBatch(sql);
								}
								int res[] = st.executeBatch();
								st.close();
								int tot = 0;
								for (int i : res) {
												tot += i;
								}
								return tot;
				}

				//	DELETE FROM `table` WHERE condition
				protected int deleteWheres(List<String> wheres) throws Exception {
								Statement st = con.createStatement();
								ORMBuilder builder = null;
								for (String w : wheres) {
												builder = new ORMBuilder();
												builder.append("DELETE FROM ").append(getTableName()).space().append("WHERE ").append(w);
												st.addBatch(builder.toString());
								}
								int res[] = st.executeBatch();
								st.close();
								int tot = 0;
								for (int i : res) {
												tot += i;
								}
								return tot;
				}

}
