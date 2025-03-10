package pl.wsei.pam.lectures.lecture7.dagger

import kotlin.random.Random

class RandomNameService{
    private val names = arrayOf("Adam", "Karol", "Ewa", "Stefan", "Monika")
    fun getName(): String {
        return names[Random.Default.nextInt(names.size)]
    }
}