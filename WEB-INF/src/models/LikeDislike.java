package models;

import java.sql.*;
public class LikeDislike{
	private Integer likeDislikeId;
	private User user;
	private Post post;
	private FeedbackType feedbackType;

	public LikeDislike(){
		super();	
	
	}

	public boolean likeDislike(){
		System.out.println("like question");
		boolean flag = false;
		System.out.println(user.getUserId()+"userId"+post.getPostId());
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "insert into like_dislikes(user_id,post_id,feedback_type_id) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,user.getUserId());
			ps.setInt(2,post.getPostId());
			ps.setInt(3,feedbackType.getFeedbackTypeId());
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


	 public void setLikeDislikeId(Integer likeDislikeId){
	
	
	}


	public Integer getLikeDislikeId(){
		return likeDislikeId;
	
	}


	public void setUser(User user){
		this.user = user;
	
	}


	public User getUser(){
		return user;
	
	
	}


	public void setPost(Post post){
		this.post = post;
	
	
	}
	
	

	public Post getPost(){
	
		return post;
	
	}


	public void setFeedbackType(FeedbackType feedbackType){
		this.feedbackType = feedbackType;
	
	
	}


	public FeedbackType getFeedbackType(){
	
		return feedbackType;
	
	}





}


