package models;
import java.sql.*;
import java.util.*;

public class Topic{
	private Integer topicId;
	private String topic;
	private Integer postCount;
	private Subject subject;

	public Topic(){

	}

	public Topic(String topic){
		this.topic = topic;
	}
	
	public Topic(Integer topicId,String topic,Integer postCount){
		this.topicId = topicId;
		this.topic = topic;
		this.postCount = postCount;
		
	}

	public Topic(Integer topicId,String topic){
		this.topicId = topicId;
		this.topic = topic;
	}

	public Topic(Integer topicId){
		this.topicId = topicId;
	}

	public void updatePostCount(Integer topicId){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update topics set post_count=post_count+1 where topic_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,topicId);
			int rowCount = ps.executeUpdate();
			connection.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
	
	}
	
	public Topic searchTopic(String topicName){
		Topic topic = new Topic();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "select topic_id,topic,post_count from topics where topic=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1,topicName);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				topic.topicId = rs.getInt(1);
				topic.topic = rs.getString(2);
				topic.postCount = rs.getInt(3);
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return topic;
	}

	public ArrayList selectTopics(){
		ArrayList topics = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select topic_id,topic from topics";
			PreparedStatement ps = connection.prepareStatement(query);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Topic topic = new Topic(rs.getInt(1), rs.getString(2));
				topics.add(topic);
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}

		return topics;	
	}

	public boolean removeTopic(Integer topicId,Integer subjectId){
		boolean flag = false;
		Subject decSubj = new Subject();
		System.out.println("topicId"+topicId);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "delete from topics where topic_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,topicId);
			int rowCount = ps.executeUpdate();
			if(rowCount==1){
				flag = true;
			}else{
				flag = false;
			}
			if(flag){
				decSubj.decreaseTopicCount(subjectId);
			}
			connection.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
		return flag;
	}



	public Topic addTopic(String topic,Subject subject){
		boolean flag = false;
		Topic newTop = new Topic();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "insert into topics (topic,subject_id) value (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,topic);
			ps.setInt(2,subject.getSubjectId());
			if(ps.executeUpdate()==1){
				flag = true;
				String query1 = "select topic_id,topic,post_count from topics where subject_id=? order by topic_id desc";
				PreparedStatement ps1 = con.prepareStatement(query1);
				ps1.setInt(1,subject.getSubjectId());
				ResultSet rs = ps1.executeQuery();
				if(rs.next()){
					//Subject subject = new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3));
					newTop.topicId = rs.getInt(1);
					newTop.topic = rs.getString(2);
					newTop.postCount = rs.getInt(3);
					//subjects.add(subject);
					//System.out.println("new Subject is here"+newSubj);
					flag = true;	
				}
				if(flag){
					subject.updateTopicCount(subject.getSubjectId());
					
				}

			}

			con.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}

		return newTop;
	}


	public static  ArrayList collectTopics(Integer subjectId){
		ArrayList topics = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select topic_id,topic,post_count from topics where subject_id=?";
			PreparedStatement ps = connection.prepareStatement(query);			
			ps.setInt(1,subjectId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Topic topic = new Topic(rs.getInt(1), rs.getString(2), rs.getInt(3));
				topics.add(topic);
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}

		return topics;	
	}

	public void setTopicId(Integer topicId){
		this.topicId = topicId;
	
	
	
	
	}



	public Integer getTopicId(){
		return topicId;
	
	
	
	}

	public void setTopic(String topic){
		this.topic = topic;
	}



	public String getTopic(){
		return topic;
	
	
	
	}



	public void setPostCount(Integer postCount){
		this.postCount = postCount;
	
	
	
	
	}



	public Integer getPostCount(){
		return postCount;	
	}

	public void setSubject(Subject subject){	
		this.subject = subject;	
	}



	public Subject getSubject(){
		return subject;
	
	
	
	}
}