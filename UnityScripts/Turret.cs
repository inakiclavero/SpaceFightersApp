using UnityEditor;
using UnityEngine;
using System.Collections;
using System.Collections.Generic;


public class Turret : MonoBehaviour
{
    public Gun gun;
    public MountPoint[] mountPoints;
    private Transform target;

 

    void Start()
    {
        StartCoroutine(DestroyObject());
    }

    void Update()
    {

        target = GameObject.Find("AR Camera").transform;
        // do nothing when no target
        if (!target) return;

        // aim target
        var aimed = true;
        foreach (var mountPoint in mountPoints)
        {
            if (!mountPoint.Aim(target.position))
            {
                aimed = false;
            }
        }

        // shoot when aimed
        if (aimed)
        {
            gun.Fire();
        }
    }

    IEnumerator DestroyObject()
    {
        yield return new WaitForSeconds(30);
        Destroy(gameObject);
        SpawnSpaceships.n -= 1;
        SpawnSpaceships.spaceships=0;

        StartCoroutine(DestroyObject());
    }

  
}