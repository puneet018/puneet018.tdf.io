package models;

import java.sql.*;
import java.sql.Timestamp;

public class Post{

	
	
	private Integer postId;
	private User user;
	private PostType postType;
	private Timestamp postDateTime;
	private Integer spamCount;
	private Integer likeCount;
	private Integer dislikeCount;
	private Status status;


	public Post(){
		super();
	
	}

	public Post(Integer postId){
		this.postId = postId;	
	}

	public boolean deletePost(){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "delete from posts where post_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,getPostId());

			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				flag = true;
			}else{
				flag=false;
			}
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}
			System.out.println("flag===+++++++++++===="+flag);
			return flag;
	}

	public int storePostInfo(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "insert into posts(user_id,post_type_id) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,user.getUserId());
			ps.setInt(2,postType.getPostTypeId());

			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					postId = rs.getInt(1);
				}
			}
			
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}	

			return postId;
	}

	public boolean likeDislikeCount(Integer postId,Integer status){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			if(status==1){
				String query = "update posts set like_count=like_count+1 where post_id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,postId);
				

				int rowCount = ps.executeUpdate();

				if(rowCount==1){
					flag = true;
					}else{
						flag = false;
					}
			}else{
				String query = "update posts set dislike_count=dislike_count+1 where post_id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,postId);
				

				int rowCount = ps.executeUpdate();

				if(rowCount==1){
					flag = true;
					}else{
						flag = false;
					}
			}
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}
			return flag;
	}



	public void setPostId(Integer postId){
		this.postId = postId;
	}


	public Integer getPostId(){
		return postId;
	
	
	}	


	public User getUser(){
		return user;
	
	
	}


	public void setUser(User user){
		this.user = user;	
	}




	public PostType getPostType(){
		return postType;
	
	
	}


	public void setPostType(PostType postType){
		this.postType = postType;
	}


	public void setPostDateTime(Timestamp postDateTime){
		this.postDateTime = postDateTime;
	}


	public Timestamp getPostDateTime(){
		return postDateTime;

	}


	
		public void setSpamCount(Integer spamCount){
	
	
	
	}




	public Integer getSpamCount(){
		return spamCount;	
	
	
	
	}


		
	


	public void setLikeCount(Integer likeCount){
		System.out.println(likeCount);
		this.likeCount = likeCount;
		
	
	
	}




	public Integer getLikeCount(){
		return likeCount;	
	
	
	
	}


	public void setDislikeCount(Integer dislikeCount){
		this.dislikeCount=dislikeCount;
	
	
	}


	public Integer getDislikeCount(){
		return dislikeCount;	
	
	
	
	}


	public void setStatus(Status status){
		this.status = status;
	
	
	
	}
	


	public Status getStatus(){
		return status;
	}
}