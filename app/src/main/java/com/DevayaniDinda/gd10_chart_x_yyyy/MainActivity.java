package com.DevayaniDinda.gd10_chart_x_yyyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static android.view.Gravity.CENTER_HORIZONTAL;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new PieFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.pie_chart:
                        fragment = new PieFragment();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "INI PIE CHART", Toast.LENGTH_SHORT);
                        toast.setGravity(CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        break;
                    case R.id.line_chart:
                        fragment = new LineFragment();
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                "INI LINE CHART", Toast.LENGTH_SHORT);
                        toast1.setGravity(CENTER_HORIZONTAL, 0, 0);
                        toast1.show();
                        break;
                    case R.id.bar_chart:
                        fragment = new BarFragment();
                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "INI BAR CHART", Toast.LENGTH_SHORT);
                        toast2.setGravity(CENTER_HORIZONTAL, 0, 0);
                        toast2.show();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}