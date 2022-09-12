
using UnityEngine.UI;
using UnityEngine;

public class TurretHealth : MonoBehaviour
{

    public static float health;
    public float maxHealth;

    public GameObject healthBarUI;
    public Slider slider;


    void Start()
    {
        health = maxHealth;
        slider.value = CalculateHealth();
    }


    void Update()
    {
        slider.value = CalculateHealth();

        if(health < maxHealth)
        {
            healthBarUI.SetActive(true);
        }

        if (health <= 0)
        {
            Destroy(gameObject);
            SpawnSpaceships.spaceships = 0;
        }

        if (health > maxHealth)
        {
            health = maxHealth;
        }
    }

    float CalculateHealth()
    {
        return health / maxHealth;
    }


}
