using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;

public class Shot : MonoBehaviour
{

    public float speed;

    public Canvas HealthCanvas;

    Rigidbody rb;
    Vector3 velocity;

    void Awake()
    {
        TryGetComponent(out rb);
    }

    void Start()
    {

        velocity = transform.up * speed;
        Destroy(gameObject, 5);
    }

    void FixedUpdate()
    {
        var displacement = velocity * Time.deltaTime;
        rb.MovePosition(rb.position + displacement);
    }

    void OnCollisionEnter(Collision other)
    {

        int vibInt = 1;

        int.TryParse(PlayerPrefs.GetString("Vibration"), out vibInt);

        LivesCounterScript.livescounterValue += 1;

        Destroy(gameObject);
        if (vibInt == 1) {
            Handheld.Vibrate();
        }

    }





}