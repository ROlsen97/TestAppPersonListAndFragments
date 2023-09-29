package com.example.viewmodelsimpleexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class PersonViewModel : ViewModel() {
    private var _nextId = 1
    private var _personsList = mutableListOf(
        Person(_nextId++, "Ruben", 26, "m"),
        Person(_nextId++, "Carlo", 56, "m")
    )
    private var _persons = MutableLiveData<List<Person>>(_personsList)

    var selected = MutableLiveData<Person>()

    var persons: LiveData<List<Person>> = _persons

    val adding: MutableLiveData<Boolean> = MutableLiveData(false)

    fun add(person: Person) {
        person.id = _nextId++
        _personsList.add(person)
        _persons.value = _personsList
    }

    fun remove(id: Int) {
        _personsList.removeAll { person -> person.id == id }
    }

    fun update(id: Int, info: Person) {
        val person: Person = _personsList.first { pe -> pe.id == id }
        person.name = info.name
        person.age = info.age
        person.gender = info.gender
    }

    operator fun get(position: Int): Person {
        return _personsList[position]
    }
}