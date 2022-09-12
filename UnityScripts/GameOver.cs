using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.Networking;

public class GameOver : MonoBehaviour
{

    [SerializeField]
    private GameObject session;

    void Update()
    {

        if (  LivesCounterScript.livescounterValue == 4) {
            string token = "";
            SceneManager.LoadScene(1);
            session.GetComponent<Timer>().timerEnded();
            GameValues.score = CounterScript.counterValue;
            GameValues.time = Timer.final_time;

            token = PlayerPrefs.GetString("Tokeninfo");
            Debug.Log(token.ToString());
            if (token != "")
            {
       
                StartPut(CounterScript.counterValue, Timer.final_time, token);

            }


          
        }

    }
    public void StartPut(int score, float time, string auth)
    {
        this.StartCoroutine(this.PostStats(score, time, auth));

    }





    IEnumerator PostStats(int score, float time, string auth)
    {

        string URL = PlayerPrefs.GetString("APIurl")+"api/uprofile";
   
        string body = "{\"score\": " + score.ToString() + ",\"time\": " + time.ToString().Replace(",",".") + " }";

        UnityWebRequest www = UnityWebRequest.Put(URL, body); //"{\"score\": 22, \"time\": 2.4 }");

        www.SetRequestHeader("Content-Type", "application/json");
        www.SetRequestHeader("Authorization", "Bearer " + auth);

        yield return www.SendWebRequest();

        if (www.result == UnityWebRequest.Result.ConnectionError || www.result == UnityWebRequest.Result.ProtocolError)
        {
            Debug.Log(www.error);
            print("Error: " + www.error.ToString());
        }
        else
        {
            Debug.Log("Form upload complete!");
            print("Form upload complete!");
        }
    }
}


