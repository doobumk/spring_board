package controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import survey.AnswerData;
import survey.Question;

import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 2017-04-27.
 */
@Component
@Controller
@RequestMapping("/survey")
public class SurveyController {
    /*@RequestMapping(method = RequestMethod.GET)
    public String form(){
        return "survey/surveyForm";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("answerData") AnswerData data){
        return "survey/submit_finish";
    }*/

    /*@RequestMapping(method = RequestMethod.GET)
    public String form(Model model){
        list<Question> questions = createQuestion();
        model.addAttribute("question",questions); //jsp에서 question으로 호출(모델객체)
        return "survey/surveyForm";
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        List<Question> questions = createQuestion();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("question",questions);
        modelAndView.setViewName("survey/surveyForm");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(AnswerData answerData){
        return "survey/submit_finish";
    }

    public List<Question> createQuestion(){
        Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버","프론트","풀스택"));
        Question q2 = new Question("많이 사용하는 개발도구는?",Arrays.asList("이클립스","인텔리J","서브라임"));
        Question q3 = new Question("하고 싶은 말을 적어주세요"); //불변 객체

        return Arrays.asList(q1,q2,q3);
    }
}
