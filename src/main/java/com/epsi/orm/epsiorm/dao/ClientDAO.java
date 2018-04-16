package com.epsi.orm.epsiorm.dao;

import com.epsi.orm.epsiorm.orm.ORM;
import com.epsi.orm.epsiorm.orm.models.Client;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class ClientDAO extends ORM<Client> {

				public ClientDAO(Connection con) {
								super(Client.class, con);
				}

				public Client getUserById(Client client) throws Exception {
								return selectOnce("where id = " + client.getId());
				}

}
