using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Linq;

public class GameManagerPic : MonoBehaviour {


    public Question_Pic[] questions;
    private static List<Question_Pic> unansweredQuestions;

    private Question_Pic currentQuestion;
    private int currentRightAnswer;

    [SerializeField]
    private Text questionText;

    [SerializeField]
    private Sprite PicSprite;

    [SerializeField]
    private Text YesButtonText;

    [SerializeField]
    private Text NoButtonText;

    [SerializeField]
    private Text answerText;


    // Use this for initialization
    void Start () {

        if (unansweredQuestions == null || unansweredQuestions.Count == 0)
        {
            unansweredQuestions = questions.ToList<Question_Pic>();
        }

        SetCurrentQuestion();

    }


    void SetCurrentQuestion()
    {
        currentQuestion = unansweredQuestions[0];

        questionText.text = currentQuestion.question;

        PicSprite = currentQuestion.picture;

        unansweredQuestions.RemoveAt(0);
    }



    public void TransitionToNextQuestion()
    {
        answerText.fontStyle = FontStyle.Italic;
        answerText.color = Color.gray;
        unansweredQuestions.Remove(currentQuestion);

        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public void UserSelectQuestion(int selectedAnswer)
    {

        if (selectedAnswer == currentQuestion.currentRightAnswer)
        {
            Debug.Log("Correct");
            answerText.color = Color.green;
            answerText.fontStyle = FontStyle.Normal;
            answerText.text = "Richtig\n"+currentQuestion.rightexplanation;
        }
        else
        {
            Debug.Log("Wrong");
            answerText.color = Color.red;
            answerText.fontStyle = FontStyle.Normal;
            answerText.text = "Falsch";
        }
    }







}
