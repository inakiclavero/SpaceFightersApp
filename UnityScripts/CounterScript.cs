using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CounterScript : MonoBehaviour
{
public static int counterValue = 0;
Text counter;

void Start() {
    counter = GetComponent<Text> ();
}

void Update() {
    counter.text = "" + counterValue;
}
}