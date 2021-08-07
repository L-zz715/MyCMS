package entity;

public class Chair extends User{

    private String userEmail;
    private String userPassword;
    private String userName;
    private int userMobileNumber;
    private String userQualification;
    private String userOccupation;
    private String userEmployerDetail;
    private String userInterestArea;
    private final String roleType;


    public Chair(String userName, String userEmail, String userPassword,
                 int userMobileNumber, String userQualification, String userOccupation,
                 String userEmployerDetail, String userInterestArea, String roleType) {
        super(userName, userEmail, userPassword,
                userMobileNumber, userQualification, userOccupation,
                userEmployerDetail, userInterestArea);
        this.roleType = roleType;

    }
}
