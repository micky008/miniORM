package com.epsi.orm.epsiorm;

import com.epsi.orm.epsiorm.config.BddConfig;
import com.epsi.orm.epsiorm.config.BddConfigReader;
import com.epsi.orm.epsiorm.dao.ClientDAO;
import com.epsi.orm.epsiorm.orm.models.Client;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Michael
 */
public class NewMain {

				public static void main(String[] args) throws Exception {
								BddConfig bc = BddConfigReader.read("./config.properties");

								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://" + bc.getHost() + "/" + bc.getBase() + "?user=" + bc.getLogin() + "&password=" + bc.getPwd() + "&useSSL=false");
								ClientDAO cdao = new ClientDAO(con);
								Client c = new Client();
								c.setId(3);
								c = cdao.getUserById(c);
								System.out.println(c);
								c.setNom("nouveaux");
								cdao.update(c);
								c = cdao.getUserById(c);
								System.out.println(c);
								c.setId(null);
								c.setAdresse("12 rue du moulin");
								c.setCodePostal("90999");
								c.setEmail("toto@toto.fr");
								c.setIsEntreprise(false);
								c.setNom("yep");
								c.setPrenom("prenom yep");
								c.setTelephone("0909090909");
								c.setVille("une ville");
								//cdao.insert(c);
								c.setId(7); //mis a la main car voila quoi...
								cdao.delete(c);

				}

}
