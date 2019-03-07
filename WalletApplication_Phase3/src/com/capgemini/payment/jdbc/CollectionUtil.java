package com.capgemini.payment.jdbc;

/*
import java.sql.DriverManager;
import java.sql.SQLException;
*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectionUtil {

	
	public EntityManager getEntityManager()
	{
		try
		{
			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Abhinav");
			EntityManager entityManager = emFactory.createEntityManager();
			return entityManager;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	
	
	/*
	 * --------------------------LOADING DRIVERS AND ESTABLISHING CONNECTION-------------------------------
	 
public Connection getConnection()

{
	try
	{
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini123");
	System.out.println("Connection Succesful");
	return con;
	}
	catch(ClassNotFoundException e)
	{
	System.out.println("Error in loading class"+e.getMessage());	
	}
	catch(SQLException s)
	{
		System.out.println("sql exception"+s.getMessage());
	}
	return null;
	}
	
	*/
}


