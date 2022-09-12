using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PauseMenuScript : MonoBehaviour
{

    public static bool GameIsPaused = false;

    public GameObject PauseMenuUI;


    public void Resume () {
        PauseMenuUI.SetActive(false);
        Time.timeScale = 1f;
        CounterScript1.counterValue=0;

    }


    public void ResetTheGame()
    {

        CounterScript.counterValue = 0;
        LivesCounterScript.livescounterValue = 0;
        SceneManager.LoadScene(0);

    }

    public void Pause () {

        Debug.Log("Pausando................");
        PauseMenuUI.SetActive(true);
        Time.timeScale = 0f;
        CounterScript1.counterValue = 1;
    }


     public void QuitGame() {

        Application.Quit();
        
    }

     public void Menu() {

         SceneManager.LoadScene(1);
    }

}
