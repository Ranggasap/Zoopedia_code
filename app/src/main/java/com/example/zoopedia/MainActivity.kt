package com.example.zoopedia

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvZoo: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvZoo = findViewById(R.id.rv_zoo)
        rvZoo.setHasFixedSize(true)

        list.addAll(getListAnimals())
        showRecyclerList()


    }

    private fun getListAnimals():ArrayList<Animal>{
        val animalName = resources.getStringArray(R.array.animal_name)
        val animalDescription = resources.getStringArray(R.array.animal_description)
        val animalPhoto = resources.obtainTypedArray(R.array.animal_photo)
        val animalScientificName = resources.getStringArray(R.array.animal_scientific_name)
        val animalFoodName1 = resources.getStringArray(R.array.animal_food_1)
        val animalFoodPhoto1 = resources.obtainTypedArray(R.array.animal_food_category_1)
        val animalFoodName2 = resources.getStringArray(R.array.animal_food_2)
        val animalFoodPhoto2 = resources.obtainTypedArray(R.array.animal_food_category_2)
        val animalFoodName3 = resources.getStringArray(R.array.animal_food_3)
        val animalFoodPhoto3 = resources.obtainTypedArray(R.array.animal_food_category_3)
        val animalFunfact = resources.getStringArray(R.array.animal_fun_fact)
        val sourceInfo = resources.getStringArray(R.array.animal_information_source)
        val listAnimal = ArrayList<Animal>()
        for(i in animalName.indices){
            val animal = Animal(animalName[i] , animalDescription[i],
                animalPhoto.getResourceId(i, -1), animalScientificName[i], animalFoodName1[i],
                animalFoodPhoto1.getResourceId(i, -1),
                animalFoodName2[i],animalFoodPhoto2.getResourceId(i,-1),
                animalFoodName3[i], animalFoodPhoto3.getResourceId(i,-1),
                animalFunfact[i], sourceInfo[i])
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showSelectedAnimal(animal: Animal){
        val detailAnimalIntent = Intent(this,DetailAnimalActivity::class.java)
        detailAnimalIntent.putExtra(DetailAnimalActivity.EXTRA_ANIMAL, animal)
        startActivity(detailAnimalIntent)
    }

    private fun showRecyclerList(){
        rvZoo.layoutManager = GridLayoutManager(this,2)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvZoo.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object: ListAnimalAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Animal) {
                showSelectedAnimal(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}