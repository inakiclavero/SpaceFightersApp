using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR.ARFoundation;
using UnityEngine.XR.ARSubsystems;


[RequireComponent(typeof(ARPlaneManager))]
public class SpawnOnPlane : MonoBehaviour
{
    private GameObject spawnedObject=null;

    private List<GameObject> placeablePrefabList = new List<GameObject>();

    static List<ARRaycastHit> s_Hits = new List<ARRaycastHit>();

    [SerializeField]
    private int maxPrefabSpawnCount = 0;
    private int placeablePrefabCount;

    [SerializeField]
    private GameObject placeablePrefab;

    private List<GameObject> placedObjectList = new List<GameObject>();



    [SerializeField]
    private ARPlaneManager arPM;

   
    void Awake()
    {
        arPM = GetComponent<ARPlaneManager>();
        arPM.planesChanged += PlaneChanged;
    }

    private void PlaneChanged(ARPlanesChangedEventArgs args){
/*
        if (CounterScript.counterValue >= 20) {
            maxPrefabSpawnCount = 5;

        }
*/


 //      if (CounterScript.counterValue >= 10)
   //     {


            if (args.added != null && spawnedObject == null && SpawnSpaceships.spaceships==0)
            {

                ARPlane arPlane = args.added[0];
                spawnedObject = Instantiate(placeablePrefab, arPlane.transform.position, Quaternion.identity);
            }
   //     }


    }




}
