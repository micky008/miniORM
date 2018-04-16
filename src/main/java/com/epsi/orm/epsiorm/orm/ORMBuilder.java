package com.epsi.orm.epsiorm.orm;

/**
 *
 * @author Michael
 */
public class ORMBuilder {

				private StringBuilder sb = new StringBuilder();

				public ORMBuilder append(String what) {
								sb.append(what);
								return this;
				}

				public ORMBuilder comma() {
								sb.append(',');
								return this;
				}

				public ORMBuilder equal() {
								sb.append('=');
								return this;
				}

				public ORMBuilder space() {
								sb.append(' ');
								return this;
				}

				public ORMBuilder toQuote(String what) {
								sb.append('\'');
								sb.append(what);
								sb.append('\'');
								return this;
				}

				public ORMBuilder trim() {
								sb = sb.delete(sb.length() - 1, sb.length());
								return this;
				}

				public String toString() {
								return sb.toString();
				}

}
