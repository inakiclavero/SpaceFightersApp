using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootScript : MonoBehaviour
{

    public GameObject arCamera;
    public GameObject smoke;
    public GameObject explosion;
    public GameObject[] dObjects;
    public GameObject shot;
    public GameObject shotInicialPoint;

    [SerializeField]
    private AudioClip clip1;
    private AudioSource source1;
    [SerializeField]
    private AudioClip clip2;
    private AudioSource source2;



    public void Shoot() {

        RaycastHit hit;
        source2 = GetComponent<AudioSource>();

        int soundInt = 1;

        int.TryParse(PlayerPrefs.GetString("Sound"), out soundInt);


        if (source2 == null)
        {
       
        }
        else if (soundInt == 1)
        {
            source2.clip = clip2;
            source2.Play();

        }


 



        if (Physics.Raycast(arCamera.transform.position, arCamera.transform.forward, out hit))
        {
            source1 = GetComponent<AudioSource>();


            /*

            if (source2 == null || source1 == null)
            {
                Debug.LogError("Fallo audio source");
            }
            else if (PlayerPrefs.GetInt("Sound", 1) == 1)
            {
                source2.Play();
                //source1.Play();
                Debug.LogError("repsonido");

            }
            */

            if (hit.transform.name == "alien(Clone)" || hit.transform.name == "alien 1(Clone)"
                || hit.transform.name == "alien 2(Clone)")
            {

                CounterScript.counterValue += 1;


                /*

                if (source2 == null || source1 == null)
                {
                    Debug.LogError("Fallo audio source");
                }
                else if (PlayerPrefs.GetInt("Sound", 1) == 1)
                {
                    source2.Play();
                    //source1.Play();
                    Debug.LogError("repsonido");

                }
                */
                Destroy(hit.transform.gameObject);
                Instantiate(smoke, hit.point, Quaternion.LookRotation(hit.normal));
                smoke.transform.localScale = new Vector3(0.0001f, 0.0000001f, 0.0000001f);


            }

            if (hit.transform.name == "SpecialDestroy(Clone)")
            {


                /*

                if (source2 == null || source1 == null)
                {
                    Debug.LogError("Fallo audio source");
                }
                else if (PlayerPrefs.GetInt("Sound", 1) == 1)
                {
                    source2.Play();
                    //source1.Play();
                    Debug.LogError("repsonido");

                }
                */
                Instantiate(smoke, hit.point, Quaternion.LookRotation(hit.normal));
                dObjects = GameObject.FindGameObjectsWithTag("enemy");
                for (var i = 0; i < dObjects.Length; i++)
                {
                    CounterScript.counterValue += 1;
                    Destroy(dObjects[i]);
                }


            }

            if (hit.transform.name == "SpecialLife(Clone)")
            {
                LivesCounterScript.livescounterValue += 1;
                CounterScript.counterValue += 1;

                Destroy(hit.transform.gameObject);
                Instantiate(smoke, hit.point, Quaternion.LookRotation(hit.normal));
                smoke.transform.localScale = new Vector3(0.0001f, 0.0000001f, 0.0000001f);
                //smoke.transform.localScale = new Vector3(0.0001f, 0.0000001f, 0.0000001f);
                //explosion = Instantiate(smoke, hit.point, Quaternion.LookRotation(hit.normal));
                //explosion.transform.localScale = new Vector3(0.0001f,0.0001f,0.0001f);

            }

            if (hit.transform.name == "AATurret(Clone)" || hit.transform.name == "Spaceship(Clone)" )
            {
                TurretHealth.health -= 1;



                /*

                if (source2 == null || source1 == null)
                {
                    Debug.LogError("Fallo audio source");
                }
                else if (PlayerPrefs.GetInt("Sound", 1) == 1)
                {
                    source2.Play();
                    //source1.Play();
                    Debug.LogError("repsonido");

                }
                */


            }
        }
    }


}
