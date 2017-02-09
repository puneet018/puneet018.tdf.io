package models;
import java.sql.*;
import java.util.*;

public class Profession{
	private Integer professionId;
	private String profession;


	public Profession(){

	}

	public Profession(Integer professionId){
		this.professionId=professionId;
	}

	public Profession(Integer professionId,String profession){
		this.professionId = professionId;
		this.profession = profession;
	}
	public static ArrayList collectProfessions(){
		ArrayList professions = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select profession_id,profession from professions";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Profession profession = new Profession(rs.getInt(1), rs.getString(2));

				professions.add(profession);
			}

			System.out.println("Countries list collected.....");


			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}

		return professions;
	}


	public void setProfessionId(Integer professionId){
	
	}


	public Integer getProfessionId(){
		return professionId;
	
	
	
	
	}



	public void setProfession(String profession){
	
	
	
	
	}


	public String getProfession(){
		return profession;
	
	
	
	}



}