package saacs.ufc.com.br.saacs.activity;

import android.app.Activity;
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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.dao.GrupoFamiliarDAO;
import saacs.ufc.com.br.saacs.dao.PessoaDAO;
import saacs.ufc.com.br.saacs.model.GrupoFamiliar;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;
import saacs.ufc.com.br.saacs.other.SessionManager;

public class CadastroGrupoFamiliarActivity extends AppCompatActivity {

    public GrupoFamiliar grupoFamiliar;
    public List<Pessoa> pessoas;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private GoogleApiClient client;
    public boolean valide = false;
    public int lPage = 0, cPage = 0;
    public Boolean isUpdate = false;
    public Boolean sucesso = false;
    public int id = 0;

    public boolean validarAtributos(){

        if (grupoFamiliar.getTipoLogradouro() == null ){
            selectPage(0);
            return  false;
        }
        if (grupoFamiliar.getLocalizacao() == null ){
            selectPage(1);
            return  false;
        }
        if (grupoFamiliar.isColetaLixo() == null  ){
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
                        PessoaDAO pDAO = new PessoaDAO(CadastroGrupoFamiliarActivity.this);
                        for (Pessoa p : grupoFamiliar.getPessoas())
                            pDAO.deletar(p.getNumSUS());
                        Toast.makeText(getBaseContext(), "As mudanças foram descartadas", Toast.LENGTH_LONG).show();
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public void onDestroy() {
        System.out.println("Aqui");
        if (!sucesso) {
            PessoaDAO pDAO = new PessoaDAO(CadastroGrupoFamiliarActivity.this);
            for (Pessoa p : grupoFamiliar.getPessoas())
                pDAO.deletar(p.getNumSUS());
            Toast.makeText(getBaseContext(), "As mudanças foram descartadas", Toast.LENGTH_LONG).show();
        }
        super.onDestroy();

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
        grupoFamiliar = new GrupoFamiliar();
        grupoFamiliar.setPessoas(new ArrayList<Pessoa>());
        SessionManager sessionManager = new SessionManager(CadastroGrupoFamiliarActivity.this);
        Long numSus = Long.parseLong(sessionManager.getUserDetails().get("susNumber"));
        grupoFamiliar.setId_agente(numSus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPostition = 0;

            @Override
            public void onPageSelected(int newPosition) {
                cPage = newPosition;
                System.out.println(lastPostition+":"+newPosition);
                Fragment fa = (Fragment) mSectionsPagerAdapter.getItem(lastPostition);
                fa.onPause();
                Fragment fc = (Fragment) mSectionsPagerAdapter.getItem(newPosition);
                fc.onResume();
                lastPostition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);

        isUpdate = getIntent().getBooleanExtra("isUpdate", false);
        id = getIntent().getIntExtra("id", 0);
        if (isUpdate){
            grupoFamiliar = (GrupoFamiliar) getIntent().getSerializableExtra("grupoFamiliar");
            pessoas = (List<Pessoa>) getIntent().getSerializableExtra("pessoas");
            grupoFamiliar.setPessoas(pessoas);
        }
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

        static EditText tipoLogradouroEditText, logradouroEditText, numeroCasaEditText, bairroEditText,
                cepEditText, phoneEditText, municipioEditText;
        static Spinner ufSpinnerText;
        static CadastroGrupoFamiliarActivity cadastroGrupoFamiliarActivity;

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
        public void onResume(){
            super.onResume();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.cPage == 0 && cadastroGrupoFamiliarActivity.lPage != 0) {
                cadastroGrupoFamiliarActivity.cPage = cadastroGrupoFamiliarActivity.lPage;
                cadastroGrupoFamiliarActivity.lPage = 0;
                cadastroGrupoFamiliarActivity.valide = true;
                cadastroGrupoFamiliarActivity.selectPage(cadastroGrupoFamiliarActivity.cPage);
            }
            else if (cadastroGrupoFamiliarActivity.cPage == 0) {
                cadastroGrupoFamiliarActivity.valide = false;
                cadastroGrupoFamiliarActivity.lPage = 0;
            }
        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.lPage == 0) {
                String tipoLogradouro = tipoLogradouroEditText.getText().toString();
                String logradouro = logradouroEditText.getText().toString();
                String numeroCasa = numeroCasaEditText.getText().toString();
                String bairro = bairroEditText.getText().toString();
                String cep = cepEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String municipio = municipioEditText.getText().toString();
                String uf = ufSpinnerText.getSelectedItem().toString();

                if (tipoLogradouro.equals("") || logradouro.equals("") ||
                        numeroCasa.equals("") || bairro.equals("") ||
                        cep.equals("") || phone.equals("") || municipio.equals("")
                        || uf.equals("Selecione")) {
                    if (tipoLogradouro.equals(""))
                        tipoLogradouroEditText.setError("Tipo logradouro não pode ser vazio");
                    if (logradouro.equals(""))
                        logradouroEditText.setError("Logradouro não pode ser vazia");
                    if (numeroCasa.equals(""))
                        numeroCasaEditText.setError("Numero da casa pode ser vazio");
                    if (bairro.equals(""))
                        bairroEditText.setError("Bairro não pode ser vazio");
                    if (cep.equals(""))
                        cepEditText.setError("Data nascimento não pode ser vazia");
                    if (phone.equals(""))
                        phoneEditText.setError("Nome não pode ser vazio");
                    if (municipio.equals(""))
                        municipioEditText.setError("Município não pode ser vazio");

                }
                else {
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setTipoLogradouro(tipoLogradouro);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setLogradouro(logradouro);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setNumCasa(numeroCasa);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setBairro(bairro);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setContato(phone);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setCep(cep);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setMunicipio(municipio);
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setuF(uf);
                    cadastroGrupoFamiliarActivity.valide = true;
                    System.out.println("validou1");
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar, container, false);
            cadastroGrupoFamiliarActivity = (CadastroGrupoFamiliarActivity) getActivity();
            tipoLogradouroEditText = (EditText) rootView.findViewById(R.id.tipoLogradouroEditText);
            logradouroEditText = (EditText) rootView.findViewById(R.id.logradouroEditText);
            numeroCasaEditText = (EditText) rootView.findViewById(R.id.numeroCasaEditText);
            bairroEditText = (EditText) rootView.findViewById(R.id.bairroEditText);
            cepEditText =  (EditText) rootView.findViewById(R.id.cepEditText);
            phoneEditText = (EditText) rootView.findViewById(R.id.phoneEditText);
            municipioEditText = (EditText) rootView.findViewById(R.id.municipioEditText);
            ufSpinnerText = (Spinner) rootView.findViewById(R.id.ufSpinnerText);

            if (cadastroGrupoFamiliarActivity.isUpdate){
                tipoLogradouroEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getTipoLogradouro());
                logradouroEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getLogradouro());
                numeroCasaEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getNumCasa());
                bairroEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getBairro());
                cepEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getCep());
                phoneEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getContato());
                municipioEditText.setText(cadastroGrupoFamiliarActivity.grupoFamiliar.getMunicipio());
                ufSpinnerText.setPrompt(cadastroGrupoFamiliarActivity.grupoFamiliar.getuF());
            }

            return rootView;
        }
    }
    public static class PlaceholderFragment2 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        static RadioGroup radioGroupLocalizacao, radioGroupMoradia, radioGroupDomicilio, radioGroupEnergia, radioGroupSaneamento;
        static CadastroGrupoFamiliarActivity cadastroGrupoFamiliarActivity;
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
        public void onResume(){
            super.onResume();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.cPage == 1 && cadastroGrupoFamiliarActivity.lPage != 1) {
                cadastroGrupoFamiliarActivity.cPage = cadastroGrupoFamiliarActivity.lPage;
                cadastroGrupoFamiliarActivity.lPage = 1;
                cadastroGrupoFamiliarActivity.valide = true;
                cadastroGrupoFamiliarActivity.selectPage(cadastroGrupoFamiliarActivity.cPage);
            }
            else if (cadastroGrupoFamiliarActivity.cPage == 1) {
                cadastroGrupoFamiliarActivity.valide = false;
                cadastroGrupoFamiliarActivity.lPage = 1;
            }
        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.lPage == 1) {

                if (radioGroupLocalizacao.getCheckedRadioButtonId() <= 0 ||
                    radioGroupMoradia.getCheckedRadioButtonId() <= 0 ||
                    radioGroupDomicilio.getCheckedRadioButtonId() <= 0 ||
                    radioGroupEnergia.getCheckedRadioButtonId() <=0 ||
                    radioGroupSaneamento.getCheckedRadioButtonId() <=0){

                    if (radioGroupLocalizacao.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupLocalizacao.getChildCount(); k++)
                            ((RadioButton)radioGroupLocalizacao.getChildAt(k)).setError("Selecione Localização");

                    if (radioGroupMoradia.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupMoradia.getChildCount(); k++)
                            ((RadioButton)radioGroupMoradia.getChildAt(k)).setError("Selecione Moradia");

                    if (radioGroupDomicilio.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupDomicilio.getChildCount(); k++)
                            ((RadioButton)radioGroupDomicilio.getChildAt(k)).setError("Selecione Domicilio");

                    if (radioGroupEnergia.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupEnergia.getChildCount(); k++)
                            ((RadioButton)radioGroupEnergia.getChildAt(k)).setError("Selecione Energia");

                    if (radioGroupSaneamento.getCheckedRadioButtonId() <=0)
                        for (int k = 0; k < radioGroupSaneamento.getChildCount(); k++)
                            ((RadioButton)radioGroupSaneamento.getChildAt(k)).setError("Selecione Saneamento");
                }
                else {
                    cadastroGrupoFamiliarActivity.valide =  true;
                    System.out.println("validou2");
                }
            }

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_2, container, false);
            cadastroGrupoFamiliarActivity = (CadastroGrupoFamiliarActivity) getActivity();
            radioGroupLocalizacao = (RadioGroup) rootView.findViewById(R.id.radioGroupLocalizacao);
            radioGroupMoradia = (RadioGroup) rootView.findViewById(R.id.radioGroupMoradia);
            radioGroupDomicilio = (RadioGroup) rootView.findViewById(R.id.radioGroupDomicilio);
            radioGroupEnergia = (RadioGroup) rootView.findViewById(R.id.radioGroupEnergia);
            radioGroupSaneamento = (RadioGroup) rootView.findViewById(R.id.radioGroupSaneamento);
            radioGroupLocalizacao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i)!= null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setLocalizacao(((RadioButton) rootView.findViewById(i)).getText().toString());
                        for (int k = 0; k < radioGroupLocalizacao.getChildCount(); k++)
                            ((RadioButton) radioGroupLocalizacao.getChildAt(k)).setError(null);
                    }
                }
            });
            radioGroupMoradia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setCondsMoradia(((RadioButton) rootView.findViewById(i)).getText().toString());
                        for (int k = 0; k < radioGroupMoradia.getChildCount(); k++)
                            ((RadioButton) radioGroupMoradia.getChildAt(k)).setError(null);
                    }
                }
            });
            radioGroupDomicilio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setTipoDomicilio(((RadioButton) rootView.findViewById(i)).getText().toString());
                        for (int k = 0; k < radioGroupDomicilio.getChildCount(); k++)
                            ((RadioButton) radioGroupDomicilio.getChildAt(k)).setError(null);
                    }
                }
            });
            radioGroupEnergia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setEnergiaEletrica(((RadioButton) rootView.findViewById(i)).getText().toString().equals("Sim"));
                        for (int k = 0; k < radioGroupEnergia.getChildCount(); k++)
                            ((RadioButton) radioGroupEnergia.getChildAt(k)).setError(null);
                    }
                }
            });
            radioGroupSaneamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setSaneamentoBasico(((RadioButton) rootView.findViewById(i)).getText().toString().equals("Sim"));
                        for (int k = 0; k < radioGroupSaneamento.getChildCount(); k++)
                            ((RadioButton) radioGroupSaneamento.getChildAt(k)).setError(null);
                    }
                }
            });

            if(cadastroGrupoFamiliarActivity.isUpdate){
                for (int k = 0; k < radioGroupLocalizacao.getChildCount(); k++)
                    if (( (RadioButton) radioGroupLocalizacao.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.getLocalizacao()))
                        ((RadioButton) radioGroupLocalizacao.getChildAt(k)).setChecked(true);

                for (int k = 0; k < radioGroupMoradia.getChildCount(); k++)
                    if (( (RadioButton) radioGroupMoradia.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.getCondsMoradia()))
                        ((RadioButton) radioGroupMoradia.getChildAt(k)).setChecked(true);

                for (int k = 0; k < radioGroupDomicilio.getChildCount(); k++)
                    if (( (RadioButton) radioGroupDomicilio.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.getTipoDomicilio()))
                        ((RadioButton) radioGroupDomicilio.getChildAt(k)).setChecked(true);

                for (int k = 0; k < radioGroupEnergia.getChildCount(); k++)
                    if (( (RadioButton) radioGroupEnergia.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.isEnergiaEletrica() ? "Sim" : "Não"))
                        ((RadioButton) radioGroupEnergia.getChildAt(k)).setChecked(true);

                for (int k = 0; k < radioGroupSaneamento.getChildCount(); k++)
                    if (( (RadioButton) radioGroupSaneamento.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.isSaneamentoBasico() ? "Sim" : "Não"))
                        ((RadioButton) radioGroupSaneamento.getChildAt(k)).setChecked(true);
            }

            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        static CadastroGrupoFamiliarActivity cadastroGrupoFamiliarActivity;
        static RadioGroup radioGroupColeta, radioGroupAnimal;
        static CheckBox checkBoxAnimaisCachorro, checkBoxAnimaisGato, checkBoxAnimaisOutro;

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
        public void onResume() {
            super.onResume();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.cPage == 2 && cadastroGrupoFamiliarActivity.lPage != 2) {
                cadastroGrupoFamiliarActivity.cPage = cadastroGrupoFamiliarActivity.lPage;
                cadastroGrupoFamiliarActivity.lPage = 2;
                cadastroGrupoFamiliarActivity.valide = true;
                cadastroGrupoFamiliarActivity.selectPage(cadastroGrupoFamiliarActivity.cPage);
            }
            else if (cadastroGrupoFamiliarActivity.cPage == 2) {
                cadastroGrupoFamiliarActivity.valide = false;
                cadastroGrupoFamiliarActivity.lPage = 2;
            }

        }

        @Override
        public void onPause() {
            super.onPause();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.lPage == 2) {

                if (radioGroupColeta.getCheckedRadioButtonId() <= 0 ||
                        radioGroupAnimal.getCheckedRadioButtonId() <= 0 ||
                        (radioGroupAnimal.getCheckedRadioButtonId() == R.id.radioButtonAnimaisSim &&
                        !checkBoxAnimaisCachorro.isChecked() && !checkBoxAnimaisGato.isChecked()
                        && !checkBoxAnimaisOutro.isChecked())){

                    if (radioGroupColeta.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupColeta.getChildCount(); k++)
                            ((RadioButton)radioGroupColeta.getChildAt(k)).setError("Selecione coleta");

                    if (radioGroupAnimal.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupAnimal.getChildCount(); k++)
                            ((RadioButton)radioGroupAnimal.getChildAt(k)).setError("Selecione animal");

                    if (radioGroupAnimal.getCheckedRadioButtonId() == R.id.radioButtonAnimaisSim &&
                            !checkBoxAnimaisCachorro.isChecked() && !checkBoxAnimaisGato.isChecked()
                            && !checkBoxAnimaisOutro.isChecked() ) {
                        checkBoxAnimaisCachorro.setError("Selecione");
                        checkBoxAnimaisGato.setError("Selecione");
                        checkBoxAnimaisOutro.setError("Selecione");
                    }
                }
                else {
                    cadastroGrupoFamiliarActivity.valide =  true;
                    System.out.println("validou2");
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_3, container, false);
            cadastroGrupoFamiliarActivity = (CadastroGrupoFamiliarActivity) getActivity();
            radioGroupColeta = (RadioGroup) rootView.findViewById(R.id.radioGroupColeta);
            radioGroupAnimal = (RadioGroup) rootView.findViewById(R.id.radioGroupAnimal);
            checkBoxAnimaisCachorro = (CheckBox) rootView.findViewById(R.id.checkBoxAnimaisCachorro);
            checkBoxAnimaisGato = (CheckBox) rootView.findViewById(R.id.checkBoxAnimaisGato);
            checkBoxAnimaisOutro = (CheckBox) rootView.findViewById(R.id.checkBoxAnimaisOutro);
            radioGroupColeta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setColetaLixo(((RadioButton) rootView.findViewById(i)).getText().toString().equals("Sim"));
                        for (int k = 0; k < radioGroupColeta.getChildCount(); k++)
                            ((RadioButton) radioGroupColeta.getChildAt(k)).setError(null);
                    }
                }
            });
            final String[] qualAnimais = {""};
            radioGroupAnimal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroGrupoFamiliarActivity.grupoFamiliar.setTemAnimais(((RadioButton) rootView.findViewById(i)).getText().toString().equals("Sim"));
                        System.out.println("aqui");
                        for (int k = 0; k < radioGroupAnimal.getChildCount(); k++)
                            ((RadioButton) radioGroupAnimal.getChildAt(k)).setError(null);
                        if (!cadastroGrupoFamiliarActivity.grupoFamiliar.isTemAnimais()) {
                            qualAnimais[0] = "";
                            checkBoxAnimaisCachorro.setChecked(false);
                            checkBoxAnimaisGato.setChecked(false);
                            checkBoxAnimaisOutro.setChecked(false);
                            checkBoxAnimaisCachorro.setEnabled(false);
                            checkBoxAnimaisGato.setEnabled(false);
                            checkBoxAnimaisOutro.setEnabled(false);
                            checkBoxAnimaisCachorro.setError(null);
                            checkBoxAnimaisGato.setError(null);
                            checkBoxAnimaisOutro.setError(null);
                            cadastroGrupoFamiliarActivity.grupoFamiliar.setAnimais(qualAnimais[0]);
                        } else {
                            checkBoxAnimaisCachorro.setEnabled(true);
                            checkBoxAnimaisGato.setEnabled(true);
                            checkBoxAnimaisOutro.setEnabled(true);
                        }
                    }
                }
            });
            checkBoxAnimaisCachorro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        checkBoxAnimaisCachorro.setError(null);
                        checkBoxAnimaisGato.setError(null);
                        checkBoxAnimaisOutro.setError(null);
                        qualAnimais[0] += checkBoxAnimaisCachorro.getText().toString() + ";";

                    } else
                        qualAnimais[0].replace(checkBoxAnimaisCachorro.getText().toString()+";", "");
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setAnimais(qualAnimais[0]);
                }
            });
            checkBoxAnimaisGato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        checkBoxAnimaisCachorro.setError(null);
                        checkBoxAnimaisGato.setError(null);
                        checkBoxAnimaisOutro.setError(null);
                        qualAnimais[0] += checkBoxAnimaisGato.getText().toString() + ";";
                    } else
                        qualAnimais[0].replace(checkBoxAnimaisGato.getText().toString()+";", "");
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setAnimais(qualAnimais[0]);
                }
            });
            checkBoxAnimaisOutro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        checkBoxAnimaisCachorro.setError(null);
                        checkBoxAnimaisGato.setError(null);
                        checkBoxAnimaisOutro.setError(null);
                        qualAnimais[0] += checkBoxAnimaisOutro.getText().toString() + ";";
                    } else
                        qualAnimais[0].replace(checkBoxAnimaisOutro.getText().toString()+";", "");
                    cadastroGrupoFamiliarActivity.grupoFamiliar.setAnimais(qualAnimais[0]);
                }
            });

            if (cadastroGrupoFamiliarActivity.isUpdate){
                for (int k = 0; k < radioGroupColeta.getChildCount(); k++)
                    if (( (RadioButton) radioGroupColeta.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.isColetaLixo() ? "Sim" : "Não"))
                        ((RadioButton) radioGroupColeta.getChildAt(k)).setChecked(true);

                for (int k = 0; k < radioGroupAnimal.getChildCount(); k++)
                    if (( (RadioButton) radioGroupAnimal.getChildAt(k)).getText().toString().equals(cadastroGrupoFamiliarActivity.grupoFamiliar.isTemAnimais() ? "Sim" : "Não"))
                        ((RadioButton) radioGroupAnimal.getChildAt(k)).setChecked(true);

                if (cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais() != null && cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais().contains(checkBoxAnimaisCachorro.getText()))
                    checkBoxAnimaisCachorro.setChecked(true);

                if (cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais() != null && cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais().contains(checkBoxAnimaisGato.getText()))
                    checkBoxAnimaisGato.setChecked(true);

                if (cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais() != null && cadastroGrupoFamiliarActivity.grupoFamiliar.getAnimais().contains(checkBoxAnimaisOutro.getText()))
                    checkBoxAnimaisOutro.setChecked(true);


            }

            return rootView;
        }
    }

    public static class PlaceholderFragment4 extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        static CadastroGrupoFamiliarActivity cadastroGrupoFamiliarActivity;
        static GridView gridViewPessoas;
        static Button addPessoaButton, addGrupoButton;


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
        public void onResume(){
            super.onResume();
            if (!cadastroGrupoFamiliarActivity.valide && cadastroGrupoFamiliarActivity.cPage == 3 && cadastroGrupoFamiliarActivity.lPage != 3) {
                cadastroGrupoFamiliarActivity.cPage = cadastroGrupoFamiliarActivity.lPage;
                cadastroGrupoFamiliarActivity.lPage = 3;
                cadastroGrupoFamiliarActivity.valide = true;
                cadastroGrupoFamiliarActivity.selectPage(cadastroGrupoFamiliarActivity.cPage);
            }
            else if (cadastroGrupoFamiliarActivity.cPage == 3) {
                cadastroGrupoFamiliarActivity.valide = false;
                cadastroGrupoFamiliarActivity.lPage = 3;
                List<String> items = new ArrayList<String>();
                for (Pessoa p : cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas()) {
                    items.add(p.getNome());
                    items.add("Editar");
                    items.add("Remover");
                }
                gridViewPessoas.setAdapter(new ArrayAdapter<String>(cadastroGrupoFamiliarActivity, android.R.layout.simple_list_item_1, items));

            }

        }

        @Override
        public void onPause(){
            super.onPause();
            cadastroGrupoFamiliarActivity.valide = true;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cadastro_grupo_familiar_4, container, false);
            cadastroGrupoFamiliarActivity = (CadastroGrupoFamiliarActivity) getActivity();
            gridViewPessoas = (GridView) rootView.findViewById(R.id.gridViewPessoas);
            addPessoaButton = (Button) rootView.findViewById(R.id.addPessoaButton);
            addGrupoButton = (Button) rootView.findViewById(R.id.addGrupoButton);
            addPessoaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), CadastroPessoaActivity.class);
                    startActivityForResult(i,999);
                }
            });
            gridViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView t = (TextView) view;
                    System.out.println(t.getText() + " : " + l);
                    switch (t.getText().toString()){
                        case "Editar":
                            Intent intent = new Intent(getContext(), CadastroPessoaActivity.class);
                            intent.putExtra("isUpdate",true);
                            intent.putExtra("id", i/3);
                            intent.putExtra("pessoa", cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().get(i/3));
                            intent.putExtra("situacaoSaude", cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().get(i/3).getSaude());
                            startActivityForResult(intent,888);
                            break;
                        case "Remover":
                            cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().remove(i/3);
                            List<String> items = new ArrayList<String>();
                            for (Pessoa p : cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas()) {
                                items.add(p.getNome());
                                items.add("Editar");
                                items.add("Remover");
                            }
                            gridViewPessoas.setAdapter(new ArrayAdapter<String>(cadastroGrupoFamiliarActivity, android.R.layout.simple_list_item_1, items));
                            break;
                        default:
                            return;
                    }
                }
            });
            addGrupoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cadastroGrupoFamiliarActivity.validarAtributos()) {
                        GrupoFamiliarDAO gDAO = new GrupoFamiliarDAO(cadastroGrupoFamiliarActivity);
                        Long id = gDAO.inserir(cadastroGrupoFamiliarActivity.grupoFamiliar);
                        if (id >= 0) {
                            cadastroGrupoFamiliarActivity.grupoFamiliar.setId(id);
                            cadastroGrupoFamiliarActivity.sucesso = true;
                            cadastroGrupoFamiliarActivity.finish();
                        }
                    }
                }
            });
            return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if( requestCode == 999 && resultCode == 999) {
                Pessoa pessoa = (Pessoa) data.getExtras().get("pessoa");
                SituacaoSaude situacaoSaude = (SituacaoSaude) data.getExtras().get("situacaoSaude");
                pessoa.setSaude(situacaoSaude);
                cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().add(pessoa);
                List<String> items = new ArrayList<String>();
                for (Pessoa p : cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas()) {
                    items.add(p.getNome());
                    items.add("Editar");
                    items.add("Remover");
                }
                gridViewPessoas.setAdapter(new ArrayAdapter<String>(cadastroGrupoFamiliarActivity, android.R.layout.simple_list_item_1, items));
            }
            if ( requestCode == 888 && resultCode == 888 ) {
                Pessoa pessoa = (Pessoa) data.getExtras().get("pessoa");
                SituacaoSaude situacaoSaude = (SituacaoSaude) data.getExtras().get("situacaoSaude");
                int id = data.getIntExtra("id", 0);
                cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().remove(id);
                pessoa.setSaude(situacaoSaude);
                cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas().add(id, pessoa);
                List<String> items = new ArrayList<String>();
                for (Pessoa p : cadastroGrupoFamiliarActivity.grupoFamiliar.getPessoas()) {
                    items.add(p.getNome());
                    items.add("Editar");
                    items.add("Remover");
                }
                gridViewPessoas.setAdapter(new ArrayAdapter<String>(cadastroGrupoFamiliarActivity, android.R.layout.simple_list_item_1, items));
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
                    return "Endereço";
                case 1:
                    return "Moradia 1";
                case 2:
                    return "Moradia 2";
                case 3:
                    return "Pessoas";
            }
            return null;
        }
    }
}
