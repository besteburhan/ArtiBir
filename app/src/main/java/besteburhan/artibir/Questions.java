package besteburhan.artibir;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by besteburhan on 15.9.2017.
 */

public class Questions {
    String userUid;
    String questionIssue;
    String questionExplanation;
    String questionDate;
    String questionCategory;
    Object questionLocationClass;
    String questionPoint;




    public  Questions(){}
    public Questions(String questionCategory, String questionDate,String questionExplanation, String questionIssue,Object questionLocationClass,String questionPoint,String userUid) {
        this.userUid = userUid;
        this.questionIssue = questionIssue;
        this.questionExplanation = questionExplanation;
        this.questionLocationClass= questionLocationClass;
        this.questionDate = questionDate;
        this.questionCategory = questionCategory;
        this.questionPoint = questionPoint;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getQuestionIssue() {
        return questionIssue;
    }

    public void setQuestionIssue(String questionIssue) {
        this.questionIssue = questionIssue;
    }

    public String getQuestionExplanation() {
        return questionExplanation;
    }

    public void setQuestionExplanation(String questionExplanation) {
        this.questionExplanation = questionExplanation;
    }

    public String getQuestionDate() {
        return questionDate;
    }
    public String getQuestionCategory() {
        return questionCategory;
    }
    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }
    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public Object getQuestionLocationClass() {
        return questionLocationClass;
    }

    public void setQuestionLocationClass(Object questionLocationClass) {
        this.questionLocationClass = questionLocationClass;
    }

    public String getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(String questionPoint) {
        this.questionPoint = questionPoint;
    }
}
