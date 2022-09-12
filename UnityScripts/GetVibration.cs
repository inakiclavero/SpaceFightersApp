using System;
using System.Collections.Generic;
using UnityEngine;

public class GetVibration : MonoBehaviour
{

    void Vibration(String Vibration)
    {
        Debug.Log("Tokendetails" + Vibration);
        PlayerPrefs.SetString("Vibration", Vibration);
        PlayerPrefs.Save();

    }
}
