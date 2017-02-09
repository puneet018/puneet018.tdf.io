package models;

public class FeedbackType{
	private Integer feedbackTypeId;
	private String feedbackType;


	public static final int LIKE=1;
	public static final int DISLIKE=2;
	public static final int SPAM=3;

	public FeedbackType(){
		super();
	
	}

	public FeedbackType(Integer feedbackTypeId){

		this.feedbackTypeId = feedbackTypeId;	
		}

	public Integer getFeedbackTypeId(){
		return feedbackTypeId;
	
	}


	public void setFeedbackTypeId(Integer feedbackTypeId){
	
	
	}


	public String getFeedckType(){
		return feedbackType;
	
	}


	public void setFeedckType(String feedbackType){
	
	
	}





}