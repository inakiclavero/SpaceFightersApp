using System;
using System.Collections.Generic;
using UnityEngine;

public class Geturl : MonoBehaviour
{
    void APIurl(String APIurl)
    {
        PlayerPrefs.SetString("APIurl", APIurl);
        PlayerPrefs.Save();

    }
}
