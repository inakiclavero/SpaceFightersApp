using System;
using System.Collections.Generic;
using UnityEngine;

public class GetMessage : MonoBehaviour
{
    void Tokendetails(string Tokeninfo)
    {
        Debug.Log("Tokendetails" + Tokeninfo);
        PlayerPrefs.SetString("Tokeninfo", Tokeninfo);

        PlayerPrefs.Save();

    }


}
