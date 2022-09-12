using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class StartScript : MonoBehaviour
{
    public GameObject pfbBeginButton;
    private GameObject instBeginButton;
    private GameObject instCrosshair;

    void Awake() {
    instBeginButton = (GameObject) Instantiate(pfbBeginButton, Vector3.zero, Quaternion.identity);
}

    public void ResetTheGame(){

        CounterScript.counterValue = 0;
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
        instBeginButton.SetActive(false);
        instCrosshair.SetActive(true);
        
    }
}
