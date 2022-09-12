using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnScript : MonoBehaviour
{

    public GameObject[] aliens;

    void Start() {

        StartCoroutine(StartSpawning());
    }

    IEnumerator StartSpawning() {

        if (CounterScript.counterValue<=15)
        {
            yield return new WaitForSeconds(5);
        }
        else if (CounterScript.counterValue <= 30)
        {
            yield return new WaitForSeconds(3);
        }
        else if (CounterScript.counterValue <= 50)
        {
            yield return new WaitForSeconds(2);
        } 
        else if (CounterScript.counterValue > 80)
        {
            yield return new WaitForSeconds(1);
        }


        for (int i = 0; i < aliens.Length; i++) {


            float x1 = -0.5f+i;
            float y1 = -1f+i;
            float z1 = 3;

            float x = Random.Range(-2,2);
            float y = Random.Range(-1,1);
            float z = 5;

            if (CounterScript.counterValue <= 15)
            {
                yield return new WaitForSeconds(Random.Range(2,5));
            }
            else if (CounterScript.counterValue <= 30)
            {
                yield return new WaitForSeconds(Random.Range(1, 3));
            }
            else if (CounterScript.counterValue <= 50)
            {
                yield return new WaitForSeconds(Random.Range(0.5f, 2));
            }
            else if (CounterScript.counterValue > 80)
            {
                yield return new WaitForSeconds(Random.Range(0, 1));
            }



            GameObject g = Instantiate(aliens[i], new Vector3(x1,y1,z1) + new Vector3(x,y,z), Quaternion.identity);
            g.transform.Rotate(-90f, 0f, 0f);
        }



        StartCoroutine(StartSpawning());
    }


}
