using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class GameOverMenu : MonoBehaviour
{




    public void ResetTheGame(){

        CounterScript.counterValue = 0;
        LivesCounterScript.livescounterValue = 0;
        SceneManager.LoadScene(0);
        
    }
    public void QuitGame() {

        Application.Quit();
        
    }


         public void Menu() {

         SceneManager.LoadScene(1);
    }
}
