package models;

import java.sql.*;
import java.util.*;

public class Question extends Post{

	private Integer questionId;
	private String questionTitle;
	private String questionDetail;
	private Integer replyCount;
	private Topic topic;
	//private Post post; because they have IS-A relationship so we extends it;



	public Question(){
	
	}
	public Question(Integer questionId){
		this.questionId = questionId;
	
	}
	
	public ArrayList searchPost(String post){
		
		ArrayList questions = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "select q.question_id,q.question_title,q.question_detail,p.post_id,u.user_name,u.user_id,like_count,dislike_count from questions as q inner join posts as p inner join users as u where p.post_id=q.post_id and p.user_id=u.user_id and question_detail like '%"+post+"%'";
			PreparedStatement ps = connection.prepareStatement(query);
			//ps.setString(1,post);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.questionId = rs.getInt(1);
				question.questionTitle = rs.getString(2);
				question.questionDetail = rs.getString(3);
				question.setPostId(rs.getInt(4));
				question.setUser(new User(rs.getString(5),rs.getInt(6)));
				question.setLikeCount(rs.getInt(7));
				question.setDislikeCount(rs.getInt(8));
				questions.add(question);
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		System.out.println("questionSSSSSS"+questions);

		return questions;
	}
	

	public Question postNewQuestion(Integer topicId){
		Question question = new Question();
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "insert into questions(question_title,question_detail,topic_id,post_id) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,questionTitle);
			ps.setString(2,questionDetail);
			ps.setInt(3,topic.getTopicId());
			ps.setInt(4,getPostId());

			int rowCount = ps.executeUpdate();

			if(rowCount==1){
				flag = true;
				ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()){
						questionId = rs.getInt(1);
				}
			}
			if(flag){
				String query1 = "select q.question_id,q.question_title,q.question_detail,p.post_id,u.user_name,u.user_id,like_count,dislike_count from questions as q inner join posts as p inner join users as u where p.post_id=q.post_id and p.user_id=u.user_id and question_id=?";
				PreparedStatement ps1 = connection.prepareStatement(query1);
				ps1.setInt(1,questionId);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					System.out.println("in query 1");
					question.questionId = rs1.getInt(1);
					question.questionTitle = rs1.getString(2);
					question.questionDetail = rs1.getString(3);
					question.setPostId(rs1.getInt(4));
					question.setUser(new User(rs1.getString(5),rs1.getInt(6)));
					question.setLikeCount(rs1.getInt(7));
					question.setDislikeCount(rs1.getInt(8));
					//arrQuestion.add(question);
			}

			}else{
				questionTitle = "";
			}
			if(flag){
				Topic topic = new Topic();
				topic.updatePostCount(topicId);
			}
			connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e){
			e.printStackTrace();
			}
			
			return question;
	}

	public static ArrayList collectQuestionTitles(Integer topicId){
		boolean flag = false;
		System.out.println("in collect question title");
		ArrayList questions = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select q.question_id,q.question_title,q.question_detail,p.post_id,u.user_name,u.user_id,like_count,dislike_count from questions as q inner join posts as p inner join users as u where p.post_id=q.post_id and p.user_id=u.user_id and topic_id=?";
			PreparedStatement ps = connection.prepareStatement(query);	
			
			ps.setInt(1,topicId);	

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				flag = true;
				Question question = new Question();
				question.questionId = rs.getInt(1);
				question.questionTitle = rs.getString(2);
				question.questionDetail = rs.getString(3);
				question.setPostId(rs.getInt(4));
				question.setUser(new User(rs.getString(5),rs.getInt(6)));
				question.setLikeCount(rs.getInt(7));
				question.setDislikeCount(rs.getInt(8));
				questions.add(question);
			}
			
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return questions;
	}

	public Question questionDetail(Integer postId){
		System.out.println("in question detail"+postId);
		Question question = new Question();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select question_title,question_detail from questions where post_id=?";
				
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1,postId);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				question.questionTitle = rs.getString(1);
				question.questionDetail = rs.getString(2);

			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return question;
	}

	public void setQuestionId(Integer questionId){
		this.questionId = questionId;
	}


	public Integer getQuestionId(){
		return questionId;
	
	}


	public void setQuestionTitle(String questionTitle){
		this.questionTitle = questionTitle;
	}



	public String getQuestionTitle(){
		return questionTitle;
	}

	public void setQuestionDetail(String questionDetail){
		this.questionDetail = questionDetail;
	}



	public String getQuestionDetail(){
		return questionDetail;
	
	}

	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}



	public Integer getReplyCount(){
		return replyCount;
	}


	public void setTopic(Topic topic){
		this.topic = topic;
	}



	public Topic getTopic(){
		return topic;
	}

}