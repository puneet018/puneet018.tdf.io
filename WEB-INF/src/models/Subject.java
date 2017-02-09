package models;
import java.sql.*;
import java.util.*;

public class Subject{
	private Integer subjectId;
	private String subject;
	private Integer topicCount;


	public Subject(){
	
	}

	public Subject(Integer subId){
		this.subjectId = subId;
	}

	public Subject(String subject){
		this.subject = subject;
	}

	public Subject(Integer subjectId, String subject, Integer topicCount)
	{
		this.subjectId = subjectId;
		this.subject = subject;
		this.topicCount = topicCount;
		
	}
	
	public boolean equals(Object obj){
		boolean flag = false;
		
		if(obj instanceof Subject){
			Subject s1 = this;
			Subject s2 = (Subject)obj;

			if(s1.subjectId.equals(s2.subjectId)){
				flag = true;
			}
		}
		
		return flag;
	}

	public Subject searchSubject(String subjectName){
		Subject subject = new Subject();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "select subject_id,subject,topic_count from subjects where subject=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1,subjectName);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				subject.subjectId = rs.getInt(1);
				subject.subject = rs.getString(2);
				subject.topicCount = rs.getInt(3);
			
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return subject;
	
	

	}
	public void updateTopicCount(Integer subjectId){
		try{
			Subject subject = new Subject();
			System.out.println("ddddddddddddddsssssssssssssss"+subjectId);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query1 = "update subjects set topic_count = topic_count+1 where subject_id = ?";
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setInt(1,subjectId);
			int s = ps1.executeUpdate();
			con.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
	}

public void decreaseTopicCount(Integer subjectId){
		try{
			Subject subject = new Subject();
			System.out.println("ddddddddddddddsssssssssssssss"+subjectId);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query1 = "update subjects set topic_count = topic_count-1 where subject_id = ?";
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setInt(1,subjectId);
			int s = ps1.executeUpdate();
			con.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
	}

	public ArrayList removeSubject(){
		
		 ArrayList allSubjects = new ArrayList(); 
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query1 = "delete from subjects where subject_id=?";
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setInt(1,subjectId);
			int s = ps1.executeUpdate();
			con.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
		allSubjects = Subject.collectSubjects();
		return allSubjects;
	}

	


public Subject addSubject(String subject){
		
		boolean flag = false;
		Subject newSubj = new Subject();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "insert into subjects (subject) value (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,subject);
			int s = ps.executeUpdate();
			System.out.println("------------"+s);
			if(s==1){
				flag = true;
				String query1 = "select subject_id,subject,topic_count from subjects order by subject_id desc";
				PreparedStatement ps1 = con.prepareStatement(query1);
				ResultSet rs = ps1.executeQuery();
				if(rs.next()){
					//Subject subject = new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3));
					newSubj.subjectId = rs.getInt(1);
			
					newSubj.subject = rs.getString(2);
					newSubj.topicCount = rs.getInt(3);
					//subjects.add(subject);
					System.out.println("new Subject is here"+newSubj);
					
				}

			}else{

				newSubj=null;				
			}


			con.close();		
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();		
		}
		System.out.println("***********"+newSubj);
		return newSubj;
	}

	public static ArrayList collectSubjects(){
		ArrayList subjects = new ArrayList();
		System.out.println(subjects+"subject@@@@@@@@@@@@");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select subject_id, subject, topic_count from subjects";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Subject subject = new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3));
				subjects.add(subject);
			
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}

		return subjects;
	}
	




	public void setSubjectId(Integer subjectId){
		this.subjectId = subjectId;

	}



	public Integer getSubjectId(){
		return subjectId;
	
	
	
	}

	public void setSubject(String subject){
		this.subject = subject;
	
	}



	public String getSubject(){
		return subject;
		
	}

	public void setTopicCount(Integer topicCount){
		this.topicCount = topicCount;
	
	}



	public Integer getTopicCount(){
		return topicCount;
		
	}


}