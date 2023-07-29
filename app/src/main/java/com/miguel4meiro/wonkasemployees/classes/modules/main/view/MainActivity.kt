package com.miguel4meiro.wonkasemployees.classes.modules.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.miguel4meiro.wonkasemployees.R
import com.miguel4meiro.wonkasemployees.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}