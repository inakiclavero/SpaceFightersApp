using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class Gun : MonoBehaviour
{
    public GameObject shotPrefab;
    public Transform[] gunPoints;
    public float fireRate;

    bool firing;
    float fireTimer;

    int gunPointIndex;

    void Update()
    {
        if (firing)
        {
            while (fireTimer >= 1 / fireRate)
            {
                SpawnShot();
                fireTimer -= 1 / fireRate;
            }

            fireTimer += Time.deltaTime;
            firing = false;
        }
        else
        {
            if (fireTimer < 1 / fireRate)
            {
                fireTimer += Time.deltaTime;
            }
            else
            {
                fireTimer = 1 / fireRate;
            }
        }
    }

    void SpawnShot()
    {
        var gunPoint = gunPoints[gunPointIndex++];
        GameObject g = Instantiate(shotPrefab, gunPoint.position, gunPoint.rotation);
        g.transform.Rotate(0f, 90f, 90f);
        gunPointIndex %= gunPoints.Length;
    }

    public void Fire()
    {
        firing = true;
    }
}