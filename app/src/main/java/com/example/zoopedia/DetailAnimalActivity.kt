package com.example.zoopedia

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailAnimalActivity : AppCompatActivity(){

    companion object{
        const val EXTRA_ANIMAL = "extra_animal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_animal)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        val tvAnimalName: TextView = findViewById(R.id.tv_detail_animal_name)
        val tvAnimalScientificName: TextView = findViewById(R.id.tv_detail_animal_scientific)
        val tvAnimalDescription: TextView = findViewById(R.id.tv_detail_animal_description)
        val ivAnimalPhoto: ImageView = findViewById(R.id.iv_detail_animal_photo)
        val tvAnimalFoodName1: TextView = findViewById(R.id.tv_detail_food_name_1)
        val ivAnimalFoodPhoto1: ImageView = findViewById(R.id.iv_detail_food_photo_1)
        val tvAnimalFoodName2: TextView = findViewById(R.id.tv_detail_food_name_2)
        val ivAnimalFoodPhoto2: ImageView = findViewById(R.id.iv_detail_food_photo_2)
        val tvAnimalFoodName3: TextView = findViewById(R.id.tv_detail_food_name_3)
        val ivAnimalFoodPhoto3: ImageView = findViewById(R.id.iv_detail_food_photo_3)
        val tvAnimalFunfact: TextView = findViewById(R.id.tv_animal_funfact)

        val btnShare: Button = findViewById(R.id.action_share)
        val btnSource: Button = findViewById(R.id.btn_source)

        var uriSource = ""
        var shareText = ""

        val animal = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Animal>(EXTRA_ANIMAL, Animal::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Animal>(EXTRA_ANIMAL)
        }

        if(animal != null){
            tvAnimalName.text = animal.name
            tvAnimalScientificName.text = animal.scientificName
            tvAnimalDescription.text = animal.description
            ivAnimalPhoto.setImageResource(animal.photo)
            tvAnimalFoodName1.text = animal.animalFoodName1
            ivAnimalFoodPhoto1.setImageResource(animal.animalFoodPhoto1)
            tvAnimalFoodName2.text = animal.animalFoodName2
            ivAnimalFoodPhoto2.setImageResource(animal.animalFoodPhoto2)
            tvAnimalFoodName3.text = animal.animalFoodName3
            ivAnimalFoodPhoto3.setImageResource(animal.animalFoodPhoto3)
            tvAnimalFunfact.text = animal.animalFunfact

            uriSource = animal.sourceInfo
            shareText = "Hey coba lihat aplikasi apa yang aku temukan\n\n" +
                    "Aplikasi ini menyediakan informasi terkait hewan salah satunya ${animal.name}." +
                    " Coba kamu download aplikasi di link berikut untuk mengetahui informasi terkait hewan favoritemu:\n"+
                    "https://github.com/Ranggasap/Zoopedia.git"
        }

        getSupportActionBar()?.setTitle("About ${animal?.name}")

        btnSource.setOnClickListener {
            val web = Intent(Intent.ACTION_VIEW, Uri.parse(uriSource))
            startActivity(web)
        }

        btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(share, "Zoopedia"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                this.finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}