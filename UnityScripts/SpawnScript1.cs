using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnScript1 : MonoBehaviour
{

    public GameObject[] aliens;

    void Start() {
        StartCoroutine(StartSpawning1());
    }

    IEnumerator StartSpawning1() {
        yield return new WaitForSeconds(30);

        for (int i =0; i < aliens.Length; i++) {


            float x1 = -0.5f+i;
            float y1 = -1f+i;
            float z1 = 3;

            float x = Random.Range(-2,2);
            float y = Random.Range(-1,1);
            float z = 5;

            yield return new WaitForSeconds(Random.Range(1, 30));
            Instantiate(aliens[i], new Vector3(x1,y1,z1) + new Vector3(x,y,z), Quaternion.identity);
        }



        StartCoroutine(StartSpawning1());
    }


}
