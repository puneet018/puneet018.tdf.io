package models;
import java.sql.*;
import java.util.*;

public class Country{

	//varibles--------------------------
	private Integer countryId;
	private String country;

	
	//constructors----------------------

	public Country(){
		super();
	
	
	}


	public Country(Integer countryId, String country){
		this.countryId=countryId;
		this.country=country;
	
	}

	public Country(Integer countryId){
		this.countryId=countryId;
	}


	//Other methods----------------------

	public static ArrayList collectCountries(){
		ArrayList countries = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select country_id,country from countries";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Country country = new Country(rs.getInt(1), rs.getString(2));

				countries.add(country);
			}

			System.out.println("Countries list collected.....");


			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}

		return countries;
	}


	


	public Integer getCountryId(){
		return countryId;
	
	
	}



	public void setCountryId(Integer countryId){
		this.countryId = countryId;
	}


	public void setCountry(String country){
		this.country = country;	
	}

	public String getCountry(){
		return country;
	
	}

}