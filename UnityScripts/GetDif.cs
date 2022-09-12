using System;
using System.Collections.Generic;
using UnityEngine;

public class GetDif : MonoBehaviour
{

    void Dificulty(String Dificulty)
    {
        Debug.Log("Dificulty" + Dificulty);
        PlayerPrefs.SetString("Dificulty", Dificulty);
        PlayerPrefs.Save();

    }
}
