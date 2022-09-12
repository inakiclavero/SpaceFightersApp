using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SoundSettings : MonoBehaviour
{
    
    public Toggle MusicToggle;

    public static string snd = "0";

    private int soundInt = 0;
   

    public void UpdateToogle()
    {

        if (PlayerPrefs.HasKey("Sound"))
        {

            int.TryParse(PlayerPrefs.GetString("Sound"), out soundInt);

            bool MusicBool = (soundInt == 1) ? true : false;
            MusicToggle.isOn = MusicBool;
        }
        else
        {
            PlayerPrefs.SetString("Sound", "1");
            int.TryParse(PlayerPrefs.GetString("Sound"), out soundInt);
            bool MusicBool = (soundInt == 1) ? true : false;
            MusicToggle.isOn = MusicBool;
        }
    }


    public void MusicBool(bool value)
    {
        if (value == true)
        {
            snd = "1";
        }
        else
        {
            snd = "0";
        }
    }


    public void Save()
    {
        PlayerPrefs.SetString("Sound", snd);

        Debug.Log(PlayerPrefs.GetString("Sound") + " ----- " + PlayerPrefs.GetString("Vibration"));

    }

}