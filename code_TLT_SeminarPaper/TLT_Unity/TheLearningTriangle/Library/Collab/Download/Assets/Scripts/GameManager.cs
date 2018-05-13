using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Linq;
using System;

public class GameManager : MonoBehaviour {

    public Question[] questions;
    private static List<Question> unansweredQuestions;

    private Question currentQuestion;
    private int currentRightAnswer;

    [SerializeField]
    private Text questionText;

    [SerializeField]
    private Text AButtonText;

    [SerializeField]
    private Text BButtonText;

    [SerializeField]
    private Text CButtonText;

    [SerializeField]
    private Text DButtonText;

    [SerializeField]
    private Text answerText;

    [SerializeField]
    private Button weiterButton;

    [SerializeField]
    private Button moreInfoButton;


    void Start()
    {
        if (unansweredQuestions == null || unansweredQuestions.Count == 0)
        {
            unansweredQuestions = questions.ToList<Question>();
        }

        weiterButton.gameObject.SetActive(false);
        moreInfoButton.gameObject.SetActive(false);
        SetCurrentQuestion();
        
    }

    void SetCurrentQuestion()
    {
        currentQuestion = unansweredQuestions[0];

        questionText.text = currentQuestion.question;

        SetButtonText();

        unansweredQuestions.RemoveAt(0);
    }

    void SetButtonText()
    {

        //TODO: Random Belegung der Buttons, damit nicht immer A richtig ist.
        AButtonText.text = currentQuestion.answerRight;
        BButtonText.text = currentQuestion.answerWrongOne;
        CButtonText.text = currentQuestion.answerWrongTwo;
        DButtonText.text = currentQuestion.answerWrongThree;
        currentRightAnswer = 1;
    }

    public void TransitionToNextQuestion()
    {
        answerText.fontStyle = FontStyle.Italic;
        answerText.color = Color.gray;
        unansweredQuestions.Remove(currentQuestion);

        if (unansweredQuestions.Count == 0 && SceneManager.GetActiveScene().buildIndex == 2)
        {
            SceneManager.LoadScene(3);
        }
        else
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
        }
    }

    public void ShowMoreInformation()
    {
        string moreInformationText = currentQuestion.moreInformation;
        answerText.color = Color.cyan;
        answerText.fontSize = 14;      
        answerText.text = moreInformationText;
    }

    public void DisableMoreInformationButtonIfNull()
    {
        string moreInformationText = currentQuestion.moreInformation;
        if (String.IsNullOrEmpty(moreInformationText))
        {
            moreInfoButton.gameObject.SetActive(false);
        }
        else
        {
            moreInfoButton.gameObject.SetActive(true);
        }
        
    }

    public void UserSelectQuestion(int selectedAnswer)
    {
        // selectedAnswer enthält Werte zwischen 1 und 4, welche den Button angibt, der gedrückt wurde.
        // 1=A 4=D
        if (selectedAnswer == currentRightAnswer)
        {
            Debug.Log("Correct");
            answerText.color = Color.green;
            answerText.fontSize = 30;
            answerText.fontStyle = FontStyle.Normal;
            answerText.text = "Correct";       
            DisableMoreInformationButtonIfNull();
            weiterButton.gameObject.SetActive(true);
        } else
        {
            Debug.Log("Wrong");
            answerText.color = Color.red;
            answerText.fontSize = 30;
            answerText.fontStyle = FontStyle.Normal;
            answerText.text = "Wrong";

            DisableMoreInformationButtonIfNull();
            weiterButton.gameObject.SetActive(false);
        }
    }

}
