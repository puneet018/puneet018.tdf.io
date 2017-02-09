package models;

public class UserType{
	private Integer userTypeId;
	private String userType;

	public static final int ADMIN=1;
	public static final int MODERATOR=2;
	public static final int GENERAL_USER=3;



	public UserType(){
		super();	
	}

	public UserType(Integer userTypeId){
		super();
		this.userTypeId = userTypeId;
	}


	public void setUserTypeId(Integer userTypeId){
		this.userTypeId = userTypeId;
	
	
	
	
	
	}



	public Integer getUserTypeId(){
		return userTypeId;
	
	
	
	}

	public void setUserType(String userType){
		this.userType = userType;
	
	
	
	
	}



	public String getUserType(){
		return userType;
	
	
	
	}






}