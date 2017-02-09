package models;
import java.sql.Timestamp;
import java.sql.*;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class User{
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	//private Integer userTypeId; //can't write
	//privaye classname variablename for foriegn key;
	private UserType userType;
	private Timestamp joiningDate;
	private Integer userQuestionCount;
	private Integer userReplyCount;
	private Status status;
	private String activationCode;
	private Boolean activationStatus;
	private Profession profession;
	private String interest;
	private String picPath;
	private Date dateOfBirth;
	private Gender gender;
	private Country country;

	public User(){
	
	}

	public User(String userName){
		this.userName = userName;
	}

	public User(Integer userId){
		this.userId = userId;
	
	}

	public User(UserType userType){
		this.userType = userType;
	
	}
	public User(String userName,Integer userId){
		this.userName = userName;
		this.userId = userId;
	
	}

	public User(String email, String password){
		this.email=email;
		this.password=password;
	}

	
	public boolean changeUserType(Integer userTypeId,Integer otherUserId){
		boolean flag = false;
		System.out.println("otherUserId"+otherUserId);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			if(userTypeId==3){
				String query = "update users set user_type_id=2 where user_id=?";
				PreparedStatement ps = connection.prepareStatement(query);

				ps.setInt(1,otherUserId);

				int rowCount = ps.executeUpdate();
				if(rowCount == 1){
					flag = true;
				}
			}else if(userTypeId==2){
					String query = "update users set user_type_id=3 where user_id=?";
					PreparedStatement ps = connection.prepareStatement(query);

					ps.setInt(1,otherUserId);

					int rowCount = ps.executeUpdate();
					if(rowCount == 1){
						flag = true;
				
					}
				}else{
				
				}
				connection.close();
			}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return flag;
	}
	
	public User showProfile(){
		User usrData = new User();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "select u.user_id,u.user_name,u.email,s.status,s.status_id,u.joining_date,u.user_question_count,u.user_reply_count,u.user_type_id,u.date_of_birth,u.gender_id,u.country_id from users as u inner join status as s where s.status_id = u.status_id and user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1,userId);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				usrData.userId = rs.getInt(1);
				usrData.userName = rs.getString(2);
				usrData.email = rs.getString(3);
				usrData.status = new Status(rs.getString(4),rs.getInt(5));
				usrData.joiningDate = rs.getTimestamp(6);
				usrData.userQuestionCount = rs.getInt(7);
				usrData.userReplyCount = rs.getInt(8);
				usrData.userType = new UserType(rs.getInt(9));
				usrData.dateOfBirth = rs.getDate(10);
				usrData.gender = new Gender(rs.getInt(11));
				usrData.country = new Country(rs.getInt(12));
			
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
		}
		System.out.println("status+++"+usrData.getStatus().getStatus());
		return usrData;
	
	
	}

	public boolean saveProfession(Integer professionId){
		System.out.println("aaaaaaaaaaaaa"+professionId);
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update users set profession_id=? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1,professionId);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
				flag = true;
				this.profession.setProfessionId(professionId);
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return flag;
	}

	public boolean saveInterest(String interest){
		boolean flag = false;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update users set interest=? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1,interest);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
				flag = true;
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		System.out.println("date of birth"+flag);
		return flag;
	}

	public boolean saveCountry(Integer countryId){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update users set country_id=? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1,countryId);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
				flag = true;
				this.country.setCountryId(countryId);
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return flag;
	}
	
	public boolean saveGender(Integer genId){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update users set gender_id =? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1,genId);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
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
	public boolean saveDob(Date dob){
		boolean flag = false;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "update users set date_of_birth =? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setDate(1,dob);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
				flag = true;
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		System.out.println("date of birth"+flag);
		return flag;
	}

	public boolean checkStatus(String email, String activationCode){
		boolean flag = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			String query = "select user_id from users where email=? and activation_code=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, email);
			ps.setString(2, activationCode);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
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
	
	public boolean registerUser(){
		boolean flag = false;

		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");

			String query = "insert into users (user_name,email,password,activation_code) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,userName);
			ps.setString(2,email);


			StrongPasswordEncryptor spec = new StrongPasswordEncryptor();
			String encpass = spec.encryptPassword(password);

				
			ps.setString(3,encpass);
			ps.setString(4,activationCode);
			
			

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

	public boolean loginUser(){
		boolean flag = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "select password,user_id,user_name,activation_status,joining_date,user_question_count,user_reply_count,user_type_id,date_of_birth,gender_id,country_id,interest,profession_id from users where email=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1,email);
			

			ResultSet rs = ps.executeQuery();
			StrongPasswordEncryptor spec = new StrongPasswordEncryptor();


			if(rs.next()&& spec.checkPassword(password, rs.getString(1))){
				userId = rs.getInt(2);
				userName = rs.getString(3);
				activationStatus = rs.getBoolean(4);
				joiningDate = rs.getTimestamp(5);
				userQuestionCount = rs.getInt(6);
				userReplyCount = rs.getInt(7);
				userType = new UserType(rs.getInt(8));
				dateOfBirth = rs.getDate(9);
				gender = new Gender(rs.getInt(10));
				country = new Country(rs.getInt(11));
				interest = rs.getString(12);
				profession = new Profession(rs.getInt(13));
				System.out.println("gendersss"+getGender().getGenderId());
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


	public static void activateAccount(String email){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "update users set activation_status=1 where email=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, email);
			
			ps.executeUpdate();

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}

	public boolean saveUserName(String userName){
		boolean flag = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=");
			
			String query = "update users set user_name=? where user_id=?";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1,userName);
			ps.setInt(2,userId);

			int rowCount = ps.executeUpdate();
			if(rowCount == 1){
				flag = true;
			}else{
				flag = false;
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return flag;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}



	public Integer getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}


	public void setPassword(String password){
		this.password=password;
	
	}

	public String getPassword(){
		return password;
	
	}

	public void setUserType(UserType userType){
		this.userType = userType;
	
	}

	public UserType getUserType(){
		return userType;
	
	}

	public void setJoiningDate(Timestamp joiningDate){
		this.joiningDate = joiningDate;
	
	}

	public Timestamp getJoiningDate(){
		return joiningDate;
	
	}

	public void setUserQuestionCount(Integer userQuestionCount){
		this.userQuestionCount = userQuestionCount;	
	}

	public Integer getUserQuestionCount(){
		return userQuestionCount;	
	}
	
	public void setUserReplyCount(Integer userReplyCount){
		this.userReplyCount = userReplyCount;

	}

	public Integer getUserReplyCount(){
		return userReplyCount;		
	}

	public void setStatus(Status status){
		this.status = status;	
	}

	public Status getStatus(){
		return status;
	}
	public void setActivationCode(String activationCode){
		this.activationCode = activationCode;	
	}

	public String getActivationCode(){
		return activationCode;	
	}

	public void setActivationStatus(Boolean activationStatus){
		this.activationStatus = activationStatus;	
	}

	public Boolean getActivationStatus(){
		return activationStatus;
	}
	
	public void setProfession(Profession profession){
		this.profession = profession;
	}

	public Profession getProfession(){
		return profession;
	}

	public void setInterest(String interest){
		this.interest = interest;
	}

	public String getInterest(){
		return interest;
	}

	public void setPicPath(String picPath){
		this.picPath = picPath;
	}

	public String getPicPath(){
		return picPath;
	}

	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth(){
		return dateOfBirth;
	}

	public void setGender(Gender gender){
		this.gender = gender;

	}

	public Gender getGender(){
		return gender;
	}

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;	
	}
}
