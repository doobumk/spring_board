package survey;

import java.util.List;

/**
 * Created by User on 2017-04-27.
 */
public class AnswerData {
    private List<String> response;
    private Respondent respondent;

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public Respondent getRespondent() {
        return respondent;
    }

    public void setRespondent(Respondent respondent) {
        this.respondent = respondent;
    }
}
