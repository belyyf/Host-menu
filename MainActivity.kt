import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.example.mysettingsapp.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // просто контейнер для фрагментов

        // Добавляем MenuProvider
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: android.view.Menu, menuInflater: android.view.MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: android.view.MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.action_settings -> {
                        // Открыть SettingsFragment
                        supportFragmentManager.commit {
                            replace(R.id.fragment_container, SettingsFragment())
                            addToBackStack(null)
                        }
                        true
                    }
                    R.id.action_about -> {
                        Toast.makeText(this@MainActivity, "Приложение MySettingsApp v1.0", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_exit -> {
                        finish()
                        true
                    }
                    else -> false
                }
            }
        }, this, Lifecycle.State.RESUMED)
    }
}
