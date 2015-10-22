package miage.mbds.cours_mbds;

import android.content.Intent;
<<<<<<< HEAD
import android.net.Uri;
=======
>>>>>>> 32b12d5d98f9e7cb1f08eb93b47dafd0ef77825e
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

<<<<<<< HEAD
        Uri chemin = Uri.parse("http://www.google.fr");
        Intent naviguer = new Intent(Intent.ACTION_VIEW, chemin);
        startActivity(naviguer);
=======
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login", "Thibaut" );
        intent.putExtra("password", "pass" );
        startActivity(intent);
>>>>>>> 32b12d5d98f9e7cb1f08eb93b47dafd0ef77825e
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
