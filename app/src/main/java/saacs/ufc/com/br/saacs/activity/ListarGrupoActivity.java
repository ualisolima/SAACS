package saacs.ufc.com.br.saacs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.dao.GrupoFamiliarDAO;
import saacs.ufc.com.br.saacs.dao.PessoaDAO;
import saacs.ufc.com.br.saacs.model.GrupoFamiliar;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.other.SessionManager;

public class ListarGrupoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    SessionManager sessionManager;
    GridView gridViewGrupos;
    List<GrupoFamiliar> grupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_grupo);
        sessionManager = new SessionManager(ListarGrupoActivity.this);
        gridViewGrupos = (GridView) findViewById(R.id.gridVewGrupos);
        GrupoFamiliarDAO gDAO = new GrupoFamiliarDAO(ListarGrupoActivity.this);
        grupos = gDAO.buscarTodos("");
        System.out.println(grupos.size());
        List<String> items = new ArrayList<>();
        for (GrupoFamiliar g : grupos){
            if (!g.getResponsaveis().isEmpty()) {
                items.add(g.getResponsaveis().get(0).getNome());
                items.add("Editar");
                items.add("Remover");
            }
        }
        gridViewGrupos.setAdapter(new ArrayAdapter<String>(ListarGrupoActivity.this, android.R.layout.simple_list_item_1, items));
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridViewGrupos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                TextView t = (TextView) view;
                System.out.println(t.getText() + " : " + l);
                switch (t.getText().toString()){
                    case "Editar":
                        Intent intent = new Intent(ListarGrupoActivity.this, CadastroGrupoFamiliarActivity.class);
                        intent.putExtra("isUpdate",true);
                        intent.putExtra("id", i/3);
                        intent.putExtra("grupoFamiliar", grupos.get(i/3));
                        intent.putExtra("pessoas", (ArrayList<Pessoa>) grupos.get(i/3).getPessoas());
                        intent.putExtra("responsaveis", (ArrayList<Pessoa>) grupos.get(i/3).getResponsaveis());
                        startActivity(intent);
                        break;
                    case "Remover":
                        new AlertDialog.Builder(ListarGrupoActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Excluir pessoa")
                                .setMessage("Você tem certeza que deseja excluir o Grupo selecionado?")
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        GrupoFamiliar gg = grupos.get(i/3);
                                        new GrupoFamiliarDAO(ListarGrupoActivity.this).deletar(gg.getId());
                                        grupos.remove(i/3);
                                        List<String> items = new ArrayList<String>();
                                        for (GrupoFamiliar g : grupos) {
                                            items.add(g.getResponsaveis().get(0).getNome());
                                            items.add("Editar");
                                            items.add("Remover");
                                        }
                                        gridViewGrupos.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items));
                                        Toast.makeText(getBaseContext(), "As mudanças foram aplicadas", Toast.LENGTH_LONG).show();

                                    }

                                })
                                .setNegativeButton("Não", null)
                                .show();
                        break;
                    default:
                        return;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(ListarGrupoActivity.this, MainActivity.class);
            startActivity(intent);
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
            Intent i = new Intent(ListarGrupoActivity.this,CadastroGrupoFamiliarActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_pesquisar) {

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
