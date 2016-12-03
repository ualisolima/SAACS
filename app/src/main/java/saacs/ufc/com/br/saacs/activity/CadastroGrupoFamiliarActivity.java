package saacs.ufc.com.br.saacs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.model.GrupoFamiliar;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;

public class CadastroGrupoFamiliarActivity extends AppCompatActivity {

    public GrupoFamiliar grupoFamiliar;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private GoogleApiClient client;

    public boolean validarAtributos(){

        if (grupoFamiliar.getTipoLogradouro() == null || grupoFamiliar.getLogradouro() == null ||
                grupoFamiliar.getNumCasa() == null || grupoFamiliar.getBairro() == null ||
                grupoFamiliar.getCep() == null || grupoFamiliar.getPhone() == null ||
                grupoFamiliar.getuF() == null || grupoFamiliar.getMunicipio() == null){
            selectPage(0);
            return  false;
        }
        if (grupoFamiliar.getLocalizacao() == null || grupoFamiliar.getCondsMoradia() == null ||
                grupoFamiliar.getTipoDomicilio() == null || grupoFamiliar.isEnergiaEletrica() == null ||
                grupoFamiliar.isSaneamentoBasico() == null){
            selectPage(1);
            return  false;
        }
        if (grupoFamiliar.getDestLixo() == null || grupoFamiliar.isTemAnimais() == null ||
                grupoFamiliar.getAnimais() == null ){
            selectPage(2);
            return  false;
        }
        if (grupoFamiliar.getPessoas().isEmpty()){
            selectPage(3);
            return  false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cancelar Cadastro")
                .setMessage("Você tem certeza que deseja descartar as mudanças?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "As mudanças foram descartadas", Toast.LENGTH_LONG).show();
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void selectPage(final int page) {
        mViewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                mViewPager.setCurrentItem(page);
            }
        }, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_grupo_familiar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_grupo_familiar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CadastroGrupoFamiliar Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            CadastroGrupoFamiliarActivity cadastroGrupoFamiliarActivity = (CadastroGrupoFamiliarActivity) getActivity();
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar, container, false);
            return rootView;
        }
    }
    public static class PlaceholderFragment2 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment2() {
        }
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_2, container, false);
            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment3() {
        }
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_3, container, false);
            return rootView;
        }
    }

    public static class PlaceholderFragment4 extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment4() {
        }

        public static PlaceholderFragment4 newInstance(int sectionNumber) {
            PlaceholderFragment4 fragment = new PlaceholderFragment4();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_4, container, false);
            Button addPessoaButton = (Button) rootView.findViewById(R.id.addPessoaButton);
            addPessoaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), CadastroPessoaActivity.class);
                    startActivityForResult(i,999);
                }
            });
            return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if( requestCode == 999 ) {
                Pessoa pessoa = (Pessoa) data.getExtras().get("pessoa");
                SituacaoSaude situacaoSaude = (SituacaoSaude) data.getExtras().get("situacaoSaude");
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println(position);
            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(1);
                case 1:
                    return PlaceholderFragment2.newInstance(2);
                case 2:
                    return PlaceholderFragment3.newInstance(3);
                case 3:
                    return PlaceholderFragment4.newInstance(4);
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
