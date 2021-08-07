package entity;

import java.util.ArrayList;

public class User {

    private int userId;
    private final String userEmail;
    private String userPassword;
    private String userName;
    private int userMobileNumber;
    private String userQualification;
    private String userOccupation;
    private String userEmployerDetail;
    private String userInterestArea;
    private ArrayList<String> userRoleType;

    private int r;


    public User(String userName, String userEmail, String userPassword,
                int userMobileNumber, String userQualification, String userOccupation,
                String userEmployerDetail, String userInterestArea) {
        this.userName = userName;
        this.userEmail= userEmail;
        this.userPassword = userPassword;
        this.userMobileNumber = userMobileNumber;
        this.userQualification = userQualification;
        this.userOccupation = userOccupation;
        this.userEmployerDetail = userEmployerDetail;
        this.userInterestArea = userInterestArea;

    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserMobileNumber(int userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public int getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserQualification(String userQualification) {
        this.userQualification = userQualification;
    }

    public String getUserQualification() {
        return userQualification;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserEmployerDetail(String userEmployerDetail) {
        this.userEmployerDetail = userEmployerDetail;
    }

    public String getUserEmployerDetail() {
        return userEmployerDetail;
    }

    public void setUserInterestArea(String userInterestArea) {
        this.userInterestArea = userInterestArea;
    }

    public String getUserInterestArea() {
        return userInterestArea;
    }

}

