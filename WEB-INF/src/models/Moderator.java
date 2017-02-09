package models;

import java.sql.*;

public class Moderator{
	private Integer moderatorId;
	private User user;
	private Topic topic;
	private Status status;


	public Moderator(){
		super();
	}
	

	public Moderator TopicName(Integer userId){
		Moderator moderator = new Moderator();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select u.user_name,t.topic from users as u inner join moderators as m inner join topics as t where m.topic_id = t.topic_id and u.user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				moderator.user = new User(rs.getString(1));
				moderator.topic = new Topic(rs.getString(2));
			}

			connection.close();
		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return moderator;
	}

	public int addModerator(Integer topicId ,Integer status,Integer otherUserId){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "insert into moderators(topic_id,status_id,user_id) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,topicId);
			ps.setInt(2,status);
			ps.setInt(3,otherUserId);

			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					moderatorId = rs.getInt(1);
				}
			}
			
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}	
		return moderatorId;
	}
	
	public boolean deleteModerator(Integer otherUserId){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "delete from moderators where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,otherUserId);

			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				flag = true;
				}			
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}	
		return flag;
	
	}

	public void setModeratorId(Integer moderatorId){
		this.moderatorId = moderatorId;
	}


	public Integer getModeratorId(){
		return moderatorId;

	}


	public User getUser(){
		return user;
	
	
	}


	public void setUser(User user){
		this.user = user;	
	}


	public void setTopic(Topic topic){
		this.topic = topic;
	}
	
	

	public Topic getTopic(){
		return topic;
	
	
	}


	public void setStatus(Status status){
		this.status = status;	
	}


	public Status getStatus(){
		return status;		
	}

}