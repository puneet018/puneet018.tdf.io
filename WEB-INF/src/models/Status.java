package models;

public class Status{

	private Integer statusId;
	private String status;

	public static final int ACTIVE=1;
	public static final int BLOCKED=2;
	public static final int CLOSED=3;


	public Status(){
		
	}

	public Status(String status,Integer statusId){
		this.status = status;
		this.statusId = statusId;	
	}


	public void setStatusId(Integer statusId){
		this.statusId = statusId;	
	
	}



	public Integer getStatusId(){
		return statusId;
	
	
	
	}

	public void setStatus(String status){
		this.status=status;
	}



	public String getStatus(){
		return status;
	
	
	
	}

}