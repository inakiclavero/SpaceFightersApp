using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class VibrationSettings : MonoBehaviour
{
    //
    public Toggle VibToggle;

    public static string vib = "0";

    private int vibInt = 0;



    public void UpdateToogle()
    {

        if (PlayerPrefs.HasKey("Vibration"))
        {

            int.TryParse(PlayerPrefs.GetString("Vibration"), out vibInt);
            bool VibrationBool = (vibInt == 1) ? true : false;
            VibToggle.isOn = VibrationBool;
        }
        else
        {
            PlayerPrefs.SetString("Vibration", "1");

            int.TryParse(PlayerPrefs.GetString("Vibration"), out vibInt);
            bool VibrationBool = (vibInt == 1) ? true : false;
            VibToggle.isOn = VibrationBool;
        }
    }

    public void VibrationBool(bool value)
    {
        if (value == true)
        {
            vib = "1";
            //PlayerPrefs.SetInt("Vibration", 1);
        }
        else
        {
            vib = "0";
            //PlayerPrefs.SetInt("Vibration", 0);
        }
    }

    public void Save()
    {
        PlayerPrefs.SetString("Vibration", vib);

    }

}
