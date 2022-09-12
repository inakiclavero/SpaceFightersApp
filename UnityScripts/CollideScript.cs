using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CollideScript : MonoBehaviour
{




    public GameObject arCamera;





    void Update()
    {

   
            transform.position = Vector3.MoveTowards(transform.position, arCamera.transform.position, 1f);
    }

    void OnCollisionEnter(Collision col) {

        int vibInt = 1;

        int.TryParse(PlayerPrefs.GetString("Vibration"), out vibInt);


        if (col.gameObject.name == "alien(Clone)" || col.gameObject.name == "alien 1(Clone)"
            || col.gameObject.name == "alien 2(Clone)" || col.gameObject.name == "SpecialDestroy(Clone)"
            || col.gameObject.name == "SpecialLife(Clone)")
        {
            LivesCounterScript.livescounterValue += 1;
           if (vibInt == 1)
            {
                Handheld.Vibrate();
            }
            Destroy(col.gameObject);

        }
    }



}
