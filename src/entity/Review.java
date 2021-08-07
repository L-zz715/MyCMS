package entity;

public class Review {
    private int reviewId;
    private String reviewerName;
    private String reviewContent;

    public Review(String reviewerName,String reviewContent) {
        this.reviewerName=reviewerName;
        this.reviewContent=reviewContent;
    }

    public Review(String reviewerName) {
        this.reviewerName=reviewerName;
    }

    public void setReviewId(int id) {this.reviewId = id;}

    public int getReviewId() { return reviewId;}

    public void setReviewerName(String reviewerName) { this.reviewerName=reviewerName; }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewContent() {
        return reviewContent;
    }
}
