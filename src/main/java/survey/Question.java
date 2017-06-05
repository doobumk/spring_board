package survey;

import java.util.Collections;
import java.util.List;

/**
 * Created by User on 2017-04-28.
 */
public class Question {
    private String title;
    private List<String> option;

    public Question(String title, List<String> option) {
        this.title = title;
        this.option = option;
    }

    public Question(String title) {
        this(title, Collections.emptyList()); //불변 객체 add불가능
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOption() {
        return option;
    }

    public boolean isChoice(){
        return option != null&&!option.isEmpty();
    }
}
