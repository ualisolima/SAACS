package saacs.ufc.com.br.saacs.activity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.dao.AcsDAO;
import saacs.ufc.com.br.saacs.dao.PessoaDAO;
import saacs.ufc.com.br.saacs.model.Acs;
import saacs.ufc.com.br.saacs.other.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    CalendarView calendarView;
    Button listarButton, relatorioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        sessionManager = new SessionManager(MainActivity.this);

        sessionManager.checkLogin();

        calendarView.setMinDate(calendarView.getDate());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PessoaDAO pDAO = new PessoaDAO(MainActivity.this);
        pDAO.cleanDB();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav = navigationView.getHeaderView(0);
        TextView nomeAcsTextView = (TextView) nav.findViewById(R.id.textViewNomeAcs);
        TextView numeroSUSTextView = (TextView) nav.findViewById(R.id.textViewSusNumber);
        String acsNumber = sessionManager.getUserDetails().get("susNumber");
        Acs a = new AcsDAO(MainActivity.this).recuperar(acsNumber);
        nomeAcsTextView.setText(a.getNome());
        numeroSUSTextView.setText(acsNumber);
        listarButton = (Button) findViewById(R.id.listarButton);
        listarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListarGrupoActivity.class);
                startActivity(i);
                finish();
            }
        });
        relatorioButton = (Button) findViewById(R.id.relatorioButton);
        relatorioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RelatorioActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addGrupoFamiliar) {
            Intent i = new Intent(MainActivity.this,CadastroGrupoFamiliarActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_pesquisar) {
            Intent i = new Intent(MainActivity.this, ListarGrupoActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_relatorios) {
            Intent i = new Intent(MainActivity.this, RelatorioActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_logout){
            sessionManager.logoutUser();
            sessionManager.checkLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
