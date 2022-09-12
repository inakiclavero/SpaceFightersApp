using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LivesCounterScript : MonoBehaviour
{
public static int livescounterValue = 0;
Text livescounter;

void Start() {
    livescounter = GetComponent<Text> ();
}

void Update() {
    livescounter.text = "" + livescounterValue;
}
}