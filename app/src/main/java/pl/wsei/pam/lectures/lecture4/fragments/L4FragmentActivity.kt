package pl.wsei.pam.lectures.lecture4.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgs
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.databinding.ActivityL4FragmentBinding
import pl.wsei.pam.lectures.lecture4.fragments.ui.main.Lab04FragmentStart
import pl.wsei.pam.lectures.lecture4.fragments.ui.main.Lab04FragmentDetails
import pl.wsei.pam.lectures.lecture4.fragments.ui.main.Lab04FragmentSettings

class L4FragmentActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityL4FragmentBinding
    lateinit var mNavigator: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityL4FragmentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.l4Container) as NavHostFragment
        mNavigator = navHostFragment.navController
        mNavigator.graph = mNavigator.createGraph(
            startDestination = "start"
        ) {
            fragment<Lab04FragmentDetails>("details/{city}"){
                label = "Details"
                argument("city"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            }
            fragment<Lab04FragmentStart>("start"){
                label = "Start"
            }
            fragment<Lab04FragmentSettings>("settings"){
                label = "Settings"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.l3_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.l3MenuItemSettings -> mNavigator.navigate("settings")
            R.id.l3MenuItemReturn -> mNavigator.navigate("start")
        }
        return super.onOptionsItemSelected(item)
    }
}