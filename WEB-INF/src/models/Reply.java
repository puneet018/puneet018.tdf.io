package models;

import java.sql.*;
import java.util.*;


public class Reply extends Post{
	private Integer replyId;
	private Question question;
	private Post post;
	private String message;

	public static ArrayList collectReplies(Integer questionId){
		System.out.println("in collect replies");
		ArrayList replies = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select r.reply_id,r.message,p.post_id,u.user_name,u.user_id,like_count,dislike_count from replies as r inner join posts as p inner join users as u where p.post_id=r.post_id and p.user_id=u.user_id and question_id=?";
			PreparedStatement ps = connection.prepareStatement(query);	
			
			ps.setInt(1,questionId);	

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Reply reply = new Reply();
				reply.replyId = rs.getInt(1);
				reply.message = rs.getString(2);
				reply.setPostId(rs.getInt(3));
				reply.setUser(new User(rs.getString(4),rs.getInt(5)));
				reply.setLikeCount(rs.getInt(6));
				reply.setDislikeCount(rs.getInt(7));
				replies.add(reply);
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return replies;
	}

	public Reply postNewReply(String message){
		Reply reply = new Reply();
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "insert into replies(question_id,post_id,message) values (?,?,?)";
			System.out.println("@@@@@@@@@@");
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,question.getQuestionId());
			ps.setInt(2,getPostId());
			ps.setString(3,message);
			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				flag = true;
				ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()){
						replyId = rs.getInt(1);
				}
			}
			if(flag){
				String query1 = "select r.message,p.post_id,u.user_name,u.user_id,like_count,dislike_count from replies as r inner join posts as p inner join users as u where p.post_id=r.post_id and p.user_id=u.user_id and reply_id=?";
				PreparedStatement ps1 = connection.prepareStatement(query1);
				ps1.setInt(1,replyId);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					System.out.println("in reply quey 1");
					
					reply.message = rs1.getString(1);
					reply.setPostId(rs1.getInt(2));
					reply.setUser(new User(rs1.getString(3),rs1.getInt(4)));
					reply.setLikeCount(rs1.getInt(4));
					reply.setDislikeCount(rs1.getInt(6));
					//arrQuestion.add(question);
			}

			}else{
			}
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}
			
			return reply;
	}


	public Reply(){
		super();
	
	
	}



	public void setReplyId(Integer replyId){
		this.replyId = replyId;
	}


	public Integer getReplyId(){
		return replyId;
	
	
	}



	public void setQuestion(Question question){
	
		this.question = question;
	
	
	
	}



	public Question getQuestion(){
		return question;
	
	
	
	}

	public void setPost(Post post){
		this.post = post;
	
	
	
	
	}



	public Post getPost(){
		return post;
	
	
	
	}


	
	public void setMessage(String message){
	
	
	
	
	
	}



	public String getMessage(){
		return message;
	
	
	
	}







}