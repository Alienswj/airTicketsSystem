package cn.cuit.beans;

public class travInfo {
	private String userId,travName,idNum,phoneNum;

	public travInfo(String userId, String travName, String idNum, String phoneNum) {
		super();
		this.userId = userId;
		this.travName = travName;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
	}

	public travInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTravName() {
		return travName;
	}

	public void setTravName(String travName) {
		this.travName = travName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "travInfo [userId=" + userId + ", travName=" + travName + ", idNum=" + idNum + ", phoneNum=" + phoneNum
				+ "]";
	}
	
	
}
