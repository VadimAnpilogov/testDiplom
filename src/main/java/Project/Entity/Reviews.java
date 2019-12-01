package Project.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewsOp;
    private String userReviews;
    private String authorReviews;

    public Reviews(){}

    public Reviews(String reviewsOp, String userReviews, String authorReviews) {
        this.reviewsOp = reviewsOp;
        this.userReviews = userReviews;
        this.authorReviews = authorReviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewsOp() {
        return reviewsOp;
    }

    public void setReviewsOp(String reviewsOp) {
        this.reviewsOp = reviewsOp;
    }

    public String getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(String userReviews) {
        this.userReviews = userReviews;
    }

    public String getAuthorReviews() {
        return authorReviews;
    }

    public void setAuthorReviews(String authorReviews) {
        this.authorReviews = authorReviews;
    }
}
