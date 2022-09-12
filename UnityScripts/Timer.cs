using UnityEngine;


public class Timer : MonoBehaviour
{

    private float time = 0;
    public static float final_time;
    void Update()
    {

            time += Time.deltaTime;

    }


    public void timerEnded()
    {
        final_time = time;
    }


}