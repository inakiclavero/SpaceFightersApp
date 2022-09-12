using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollowing : MonoBehaviour
{
    private GameObject arCamera=null;
    private int dificultyInt = 0;






    // Update is called once per frame
    void Update()
    {
        arCamera= GameObject.Find("AR Camera");
        


        if (CounterScript1.counterValue == 1){


            transform.position = Vector3.MoveTowards(transform.position, arCamera.transform.position, 0.000f);


        } else{

            int.TryParse(PlayerPrefs.GetString("Dificulty"), out dificultyInt);
            if (dificultyInt == 0)
            {
                transform.position = Vector3.MoveTowards(transform.position, arCamera.transform.position, 0.01f);
            } else if (dificultyInt == 1)
            {
                transform.position = Vector3.MoveTowards(transform.position, arCamera.transform.position, 0.025f);
            } else if (dificultyInt == 2)
            {
                transform.position = Vector3.MoveTowards(transform.position, arCamera.transform.position, 0.050f);
            }
            

        }



    }




}
