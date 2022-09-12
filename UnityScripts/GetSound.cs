using System;
using System.Collections.Generic;
using UnityEngine;

public class GetSound : MonoBehaviour
{

    void Sound(String Sound)
    {
        Debug.Log("Sound" + Sound);
        PlayerPrefs.SetString("Sound",Sound);
        PlayerPrefs.Save();

    }

}
