package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class EmployeeTransaction
 */
@WebServlet("/EmployeeTransaction")
public class EmployeeTransaction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String INSERT_EMPLOYEE_QUERY = "insert into employee (empId,name) values (?,?)";
	public static final String INSERT_EMPLOYEE_ADRESS_QUERY = "insert into address (empId,address,city,country) values (?,?,?,?)";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
						
			PrintWriter out = response.getWriter();

			// 1. load data from config.properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

			// 2. create connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));

			Connection connection = conn.getConnection();
			
			// set auto commit false
			connection.setAutoCommit(false);
			
			insertEmployeeData(connection,3,"Marry Smith");
			
			insertEmployeeAdressData(connection, 3, "Marry Smithn DR", "Sane joe", "USA");
			
			// then commit
			connection.commit();
			
			out.println("<html><body>");
			out.println("<h2> Data added Successfully !</h2>");
			out.println("</body></html>");
			conn.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void insertEmployeeData(Connection conn, int id, String name) throws SQLException {
		PreparedStatement pstm1 = conn.prepareStatement(INSERT_EMPLOYEE_QUERY);
		pstm1.setInt(1, id);
		pstm1.setString(2, name);
		pstm1.executeUpdate();
		System.out.println("Employee Data is inserted with id "+id);
		pstm1.close();
	}
	
	public static void insertEmployeeAdressData(Connection conn, int id, String address, String city, String country) throws SQLException {
		PreparedStatement pstm2 = conn.prepareStatement(INSERT_EMPLOYEE_ADRESS_QUERY);
		pstm2.setInt(1, id);
		pstm2.setString(2, address);
		pstm2.setString(3, city);
		pstm2.setString(4, country);
		pstm2.executeUpdate();
		System.out.println("Employee Address Data is inserted with emp id "+id);
		pstm2.close();
	}
	
}
