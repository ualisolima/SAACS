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
import saacs.ufc.com.br.saacs.dao.PessoaDAO;
import saacs.ufc.com.br.saacs.other.SessionManager;

public class RelatorioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    TextView textViewHomens, textViewMulheres, textViewGestantes, textViewHipertensos,
    textViewDiabtes, textViewCardiaca, textViewRins, textViewRespiratorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        sessionManager = new SessionManager(RelatorioActivity.this);
        textViewHomens = (TextView) findViewById(R.id.textViewHomens);
        textViewMulheres = (TextView) findViewById(R.id.textViewMulheres);
        textViewGestantes = (TextView) findViewById(R.id.textViewGestante);
        textViewHipertensos = (TextView) findViewById(R.id.textViewHipertenso);
        textViewDiabtes = (TextView) findViewById(R.id.textViewDiabetes);
        textViewCardiaca = (TextView) findViewById(R.id.textViewCardiaca);
        textViewRins = (TextView) findViewById(R.id.textViewRins);
        textViewRespiratorio = (TextView) findViewById(R.id.textViewRespiratorio);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PessoaDAO pDAO = new PessoaDAO(RelatorioActivity.this);
        pDAO.cleanDB();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRelatorio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Listar Grupos", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(RelatorioActivity.this, ListarGrupoActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent i = new Intent(RelatorioActivity.this, MainActivity.class);
            startActivity(i);
            finish();
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
            Intent i = new Intent(RelatorioActivity.this,CadastroGrupoFamiliarActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_pesquisar) {
            Intent i = new Intent(RelatorioActivity.this, ListarGrupoActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_relatorios) {

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
