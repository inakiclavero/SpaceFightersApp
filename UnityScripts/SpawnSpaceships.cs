using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnSpaceships : MonoBehaviour
{
    public static int n=0;
    public int maxEnem;
    public static int spaceships = 0;

    public GameObject[] aliens;
    private GameObject[] clones;

    void Start()
    {
        StartCoroutine(StartSpawning());
    }

    IEnumerator StartSpawning()
    {

            for (int i = 0; i < aliens.Length; i++)
            {


            float x1 = -1f + i;
            float y1 = 1f + i;
            float z1 = 5;



            float x = Random.Range(-0.5f, 0.5f);
            float y = Random.Range(-0.5f, 0.5f);
            float z = 5;

                yield return new WaitForSeconds(Random.Range(5, 15));

                Instantiate(aliens[i], new Vector3(x1, y1, z1) + new Vector3(x, y, z), Quaternion.identity);
                spaceships++;
            yield return new WaitForSeconds(30);



            }   

        StartCoroutine(StartSpawning());

       

    }


}
