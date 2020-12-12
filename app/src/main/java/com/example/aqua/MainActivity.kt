package com.example.aqua

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var sharedViewModel : SharedViewModel
    val MyPREFERENCES = "MyPrefs"
    lateinit var sharedpreferences: SharedPreferences
    val temp = "currenttemperatur"
    val quality = "quality"
    val ph = "currentph"
    val water = "waterlevel"
    val algea = "algea"
    val mintemp = "mintemp"
    val maxtemp = "maxtemp"
    val minph = "minph"
    val maxph = "maxph"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_temperatur, R.id.nav_pH, R.id.nav_waterlevel
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        loadModel()
        observeModel()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun loadModel() {
        val midtemp = sharedpreferences.getInt(temp, 25)
        val midquality = sharedpreferences.getInt(quality, 1)
        val midph = sharedpreferences.getFloat(ph, 7.0f)
        val midwater = sharedpreferences.getInt(water, -3)
        val midalgea = sharedpreferences.getFloat(algea, 0.0f)
        val midmintemp = sharedpreferences.getInt(mintemp, 25)
        Log.d("mintemp3", midmintemp.toString())
        val midmaxtemp = sharedpreferences.getInt(maxtemp, 25)
        val midminph = sharedpreferences.getFloat(minph, 7.0f)
        val midmaxph = sharedpreferences.getFloat(maxph, 7.0f)
        sharedViewModel.setAllValue(midtemp, midquality, midph, midwater, midalgea, midmintemp, midmaxtemp, midminph, midmaxph)
    }

    fun observeModel() {

        sharedViewModel.quality.observeForever(Observer {
            saveInt(quality, it)
        })

        sharedViewModel.currentph.observeForever(Observer {
            saveFloat(ph, it)
        })

        sharedViewModel.currenttemperatur.observeForever(Observer {
            saveInt(temp, it)
        })

        sharedViewModel.waterlevel.observeForever(Observer {
            saveInt(water, it)
        })

        sharedViewModel.algea.observeForever(Observer {
            saveFloat(algea, it)
        })

        sharedViewModel.mintemp.observeForever(Observer {
            Log.d("mintemp1", it.toString())
            saveInt(mintemp, it)
        })

        sharedViewModel.maxtemp.observeForever(Observer {
            saveInt(maxtemp, it)
        })

        sharedViewModel.minph.observeForever(Observer {
            saveFloat(minph, it)
        })

        sharedViewModel.maxph.observeForever(Observer {
            saveFloat(maxph, it)
        })
    }

    fun saveInt(name: String, value: Int) {
        val editor = sharedpreferences.edit()
        editor.putInt(name,value)
        editor.commit()
        Log.d("mintemp2", value.toString())
        Log.d("frapref", sharedpreferences.getInt(mintemp, 25).toString())
    }

    fun saveFloat(name: String, value: Float) {
        val editor = sharedpreferences.edit()
        editor.putFloat(name,value)
        editor.commit()
    }

}