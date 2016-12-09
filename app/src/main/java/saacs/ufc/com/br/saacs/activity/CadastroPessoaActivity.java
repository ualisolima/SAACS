package saacs.ufc.com.br.saacs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;

public class CadastroPessoaActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public Pessoa pessoa;
    public SituacaoSaude situacaoSaude;
    public boolean valide = false;
    public int lPage = 0, cPage = 0;
    public Boolean isUpdate = false;
    public int id = 0;

    private ViewPager mViewPager;

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
                        valide = true;
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }

    public boolean validarAtributos(){
        valide = true;
        if ( pessoa.getNumSUS() == null){
            selectPage(0);
            return false;
        }
        if ( pessoa.getNumeroNis() == null){
            selectPage(1);
            return false;
        }
        if (pessoa.isResponsavelFamiliar() == null){
            selectPage(2);
            return false;
        }
        if (pessoa.getProfissao() == null ){
            selectPage(3);
            return false;
        }
        if (situacaoSaude.isDeficiencia() == null ){
            selectPage(4);
            return false;
        }
        if (situacaoSaude.isDiabete() == null){
            selectPage(5);
            return false;
        }

        if (situacaoSaude.isInternacao() == null){
            selectPage(6);
            return true;
        }


        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        pessoa = new Pessoa();
        situacaoSaude = new SituacaoSaude();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerPessoa);
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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        isUpdate = getIntent().getBooleanExtra("isUpdate", false);
        id = getIntent().getIntExtra("id", 0);
        if (isUpdate){
            pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
            situacaoSaude = (SituacaoSaude) getIntent().getSerializableExtra("situacaoSaude");
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_pessoa, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        static EditText numSusEditText, dtNascimentoEditText, nomeCompletoEditText;
        static RadioGroup radioGroupRaca, radioGroupSex;
        static CadastroPessoaActivity cadastroPessoaActivity;
        //static FragmentManager fm;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 0 && cadastroPessoaActivity.lPage != 0) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 0;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
            }
            else if (cadastroPessoaActivity.cPage == 0) {
                cadastroPessoaActivity.valide = false;
                cadastroPessoaActivity.lPage = 0;
            }

        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 0) {
                String numSus = numSusEditText.getText().toString();
                String dtNascimento = dtNascimentoEditText.getText().toString();
                String nomeCompleto = nomeCompletoEditText.getText().toString();

                if (numSus.equals("") || dtNascimento.equals("") || nomeCompleto.equals("") || radioGroupSex.getCheckedRadioButtonId() <= 0 || radioGroupRaca.getCheckedRadioButtonId() <= 0) {
                    if (numSus.equals(""))
                        numSusEditText.setError("Numero SUS não pode ser vazio");
                    if (dtNascimento.equals(""))
                        dtNascimentoEditText.setError("Data nascimento não pode ser vazia");
                    if (nomeCompleto.equals(""))
                        nomeCompletoEditText.setError("Nome não pode ser vazio");
                    if (radioGroupSex.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupSex.getChildCount(); k++)
                            ((RadioButton)radioGroupSex.getChildAt(k)).setError("Selecione o sexo");
                    if (radioGroupRaca.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupRaca.getChildCount(); k++)
                            ((RadioButton)radioGroupRaca.getChildAt(k)).setError("Selecione a Etnia");
                }
                else {
                    cadastroPessoaActivity.pessoa.setNumSUS(Long.parseLong(numSus));
                    cadastroPessoaActivity.pessoa.setDataNascimento(dtNascimento);
                    cadastroPessoaActivity.pessoa.setNome(nomeCompleto);
                    cadastroPessoaActivity.valide = true;
                    System.out.println("validou");
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa, container, false);
            radioGroupRaca = (RadioGroup) rootView.findViewById(R.id.radioGroupRaca);
            radioGroupSex = (RadioGroup) rootView.findViewById(R.id.radioGroupSex);
            numSusEditText = (EditText) rootView.findViewById(R.id.numSusEditText);
            dtNascimentoEditText = (EditText) rootView.findViewById(R.id.dtNascimentoEditText);
            nomeCompletoEditText = (EditText) rootView.findViewById(R.id.nomeCompletoEditText);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            //fm = getActivity().getSupportFragmentManager();
            radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((RadioButton)radioGroupSex.getChildAt(0)).setError(null);
                    ((RadioButton)radioGroupSex.getChildAt(1)).setError(null);
                   cadastroPessoaActivity.pessoa.setSexo(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            radioGroupRaca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    for (int k = 0; k < radioGroupRaca.getChildCount(); k++)
                        ((RadioButton)radioGroupRaca.getChildAt(k)).setError(null);
                    cadastroPessoaActivity.pessoa.setEtnia(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        static RadioGroup nacionalidadeGroup;
        static EditText paisDeOrigemEditText, numeroNisEditText, nomeDaMaeEditText,cidadeNatalEditText,estadoEditText,telefoneEditText,emailEditText;
        static CadastroPessoaActivity cadastroPessoaActivity;
        public PlaceholderFragment2() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onResume(){
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 1 && cadastroPessoaActivity.lPage != 1 ) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 1;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui");
            }
            else if (cadastroPessoaActivity.cPage == 1){
                System.out.println("Nao Entrou aqui");
                cadastroPessoaActivity.lPage = 1;
                cadastroPessoaActivity.valide = false;
            }
            super.onResume();

        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 1) {
                String numeroNis = numeroNisEditText.getText().toString();
                String nomeDaMae = nomeDaMaeEditText.getText().toString();
                String cidadeNatal = cidadeNatalEditText.getText().toString();
                String estado = estadoEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String telefone = telefoneEditText.getText().toString();
                String paisDeOrigem = paisDeOrigemEditText.getText().toString();
                if (numeroNis.equals("") || nomeDaMae.equals("") || cidadeNatal.equals("") || paisDeOrigem.equals("") || estado.equals("")) {
                    if (numeroNis.equals(""))
                        numeroNisEditText.setError("Numero NIS não pode ser vazio");
                    if (nomeDaMae.equals(""))
                        nomeDaMaeEditText.setError("Nome da mãe não pode ser vazio");
                    if (cidadeNatal.equals(""))
                        cidadeNatalEditText.setError("Cidade natal não pode ser vazio");
                    if (paisDeOrigem.equals(""))
                        paisDeOrigemEditText.setError("País de origem não pode ser vazio");
                    if (estado.equals(""))
                        estadoEditText.setError("Estado não pode ser vazio");
                }
                else {
                    cadastroPessoaActivity.pessoa.setNumeroNis(Long.parseLong(numeroNis));
                    cadastroPessoaActivity.pessoa.setCidadeNatal(cidadeNatal);
                    cadastroPessoaActivity.pessoa.setNomedaMae(nomeDaMae);
                    cadastroPessoaActivity.pessoa.setEstado(estado);
                    cadastroPessoaActivity.pessoa.setEmail(email);
                    cadastroPessoaActivity.pessoa.setPaisDeOrigem(paisDeOrigem);
                    cadastroPessoaActivity.pessoa.setTelefone(telefone);
                    cadastroPessoaActivity.valide = true;
                    System.out.println("validou");
                }
            }


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_2, container, false);
            nacionalidadeGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupNacionalidade);
            numeroNisEditText = (EditText) rootView.findViewById(R.id.numeroNisEditText);
            nomeDaMaeEditText = (EditText) rootView.findViewById(R.id.nomeDaMaeEditText);
            paisDeOrigemEditText = (EditText) rootView.findViewById(R.id.paisDeOrigemEditText);
            cidadeNatalEditText = (EditText) rootView.findViewById(R.id.cidadeNatalEditText);
            estadoEditText = (EditText) rootView.findViewById(R.id.estadoEditText);
            telefoneEditText = (EditText) rootView.findViewById(R.id.telefoneEditText);
            emailEditText = (EditText) rootView.findViewById(R.id.emailEditText);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            nacionalidadeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i != R.id.radioButtonNacionalidadeBrasileiro) {
                        paisDeOrigemEditText.setEnabled(true);
                        paisDeOrigemEditText.setText("");
                    }
                    else {
                        paisDeOrigemEditText.setEnabled(false);
                        paisDeOrigemEditText.setText("Brasileiro");
                    }
                    paisDeOrigemEditText.setError(null);
                    cadastroPessoaActivity.pessoa.setNacionalidade(paisDeOrigemEditText.getText().toString());
                }
            });
            RadioButton brasileiroButton = (RadioButton) rootView.findViewById(R.id.radioButtonNacionalidadeBrasileiro);
            brasileiroButton.setChecked(true);
            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        static CadastroPessoaActivity cadastroPessoaActivity;
        static RadioGroup radioGroupResponsavel, radioGroupParentesco;

        public PlaceholderFragment3() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onResume(){
            super.onResume();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 2 && cadastroPessoaActivity.lPage != 2) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 2;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui 3");
            }
            else if ( cadastroPessoaActivity.cPage == 2) {
                cadastroPessoaActivity.lPage = 2;
                cadastroPessoaActivity.valide = false;
            }

        }

        @Override
        public void onPause(){
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 2){
                if (radioGroupResponsavel.getCheckedRadioButtonId() <=0 || (radioGroupResponsavel.getCheckedRadioButtonId() == R.id.radioButtonResponsavelNao && radioGroupParentesco.getCheckedRadioButtonId() <= 0 )){
                    if (radioGroupResponsavel.getCheckedRadioButtonId() <=0)
                        for (int k = 0; k < radioGroupResponsavel.getChildCount(); k++)
                            ((RadioButton)radioGroupResponsavel.getChildAt(k)).setError("Selecione um item");
                    else if (radioGroupResponsavel.getCheckedRadioButtonId() == R.id.radioButtonResponsavelNao && radioGroupParentesco.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupParentesco.getChildCount(); k++)
                            ((RadioButton)radioGroupParentesco.getChildAt(k)).setError("Selecione parentesco");
                }
                else
                    cadastroPessoaActivity.valide = true;
            }
            super.onPause();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_3, container, false);
            radioGroupResponsavel = (RadioGroup) rootView.findViewById(R.id.radioGroupResponsavel);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            radioGroupParentesco = (RadioGroup) rootView.findViewById(R.id.RadioGroupParentesco);
            radioGroupParentesco.setEnabled(false);
            for (int k = 0; k < radioGroupParentesco.getChildCount(); k++)
                radioGroupParentesco.getChildAt(k).setEnabled(false);
            radioGroupResponsavel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.radioButtonResponsavelSim) {
                        radioGroupParentesco.setEnabled(false);
                        for (int k = 0; k < radioGroupParentesco.getChildCount(); k++)
                            radioGroupParentesco.getChildAt(k).setEnabled(false);
                        radioGroupParentesco.clearCheck();
                        cadastroPessoaActivity.pessoa.setRelacaoParentRF(null);
                    }
                    else {
                        radioGroupParentesco.setEnabled(true);
                        for (int k = 0; k < radioGroupParentesco.getChildCount(); k++)
                            radioGroupParentesco.getChildAt(k).setEnabled(true);
                    }
                    for (int k = 0; k < radioGroupResponsavel.getChildCount(); k++)
                        ((RadioButton)radioGroupResponsavel.getChildAt(k)).setError(null);
                    cadastroPessoaActivity.pessoa.setResponsavelFamiliar(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupParentesco.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroPessoaActivity.pessoa.setRelacaoParentRF(((RadioButton)rootView.findViewById(i)).getText().toString());
                        for (int k = 0; k < radioGroupParentesco.getChildCount(); k++)
                            ((RadioButton)radioGroupParentesco.getChildAt(k)).setError(null);
                    }
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment4 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment4() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment4 newInstance(int sectionNumber) {
            PlaceholderFragment4 fragment = new PlaceholderFragment4();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        static RadioGroup radioGroupEscolaridade, radioGroupTrabalho;
        static EditText profissaoEditText;
        static CadastroPessoaActivity cadastroPessoaActivity;

        @Override
        public void onResume(){
            super.onResume();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 3 && cadastroPessoaActivity.lPage != 3) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 3;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui 4");
            }
            else if ( cadastroPessoaActivity.cPage == 3) {
                cadastroPessoaActivity.lPage = 3;
                cadastroPessoaActivity.valide = false;
            }
        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 3){
                String profissao = profissaoEditText.getText().toString();
                if (profissao.equals("") || radioGroupEscolaridade.getCheckedRadioButtonId() <= 0 || radioGroupTrabalho.getCheckedRadioButtonId() <= 0) {
                    if (profissao.equals(""))
                        profissaoEditText.setError("Profissão não pode ser vazio");
                    if (radioGroupEscolaridade.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupEscolaridade.getChildCount(); k++)
                            ((RadioButton)radioGroupEscolaridade.getChildAt(k)).setError("Selecione um Item");
                    if (radioGroupTrabalho.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupTrabalho.getChildCount(); k++)
                            ((RadioButton)radioGroupTrabalho.getChildAt(k)).setError("Selecione um Item");


                }
                else {
                    cadastroPessoaActivity.pessoa.setProfissao(profissao);
                    cadastroPessoaActivity.valide = true;
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_4, container, false);
            radioGroupEscolaridade = (RadioGroup) rootView.findViewById(R.id.radioGroupEscolaridade);
            radioGroupTrabalho = (RadioGroup) rootView.findViewById(R.id.radioGroupTrabalho);
            profissaoEditText = (EditText) rootView.findViewById(R.id.profissaoEditText);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            radioGroupEscolaridade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        for (int k = 0; k < radioGroupEscolaridade.getChildCount(); k++)
                            ((RadioButton)radioGroupEscolaridade.getChildAt(k)).setError(null);
                        cadastroPessoaActivity.pessoa.setEscolaridade(((RadioButton) rootView.findViewById(i)).getText().toString());
                        System.out.println(cadastroPessoaActivity.pessoa.getEscolaridade());
                        //cadastroPessoaActivity.valide = true;
                    }
                }
            });
            radioGroupTrabalho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null) {
                        cadastroPessoaActivity.pessoa.setSituacaoMercado(((RadioButton) rootView.findViewById(i)).getText().toString());
                        //cadastroPessoaActivity.valide = true;

                        for (int k = 0; k < radioGroupTrabalho.getChildCount(); k++)
                            ((RadioButton) radioGroupTrabalho.getChildAt(k)).setError(null);
                    }
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment5 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment5() {
        }

        @Override
        public void onResume() {
            super.onResume();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 4 && cadastroPessoaActivity.lPage != 4) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 4;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui 5");
            }
            else if ( cadastroPessoaActivity.cPage == 4) {
                cadastroPessoaActivity.lPage = 4;
                cadastroPessoaActivity.valide = false;
            }

            System.out.println(cadastroPessoaActivity.pessoa.getSexo());
            if (cadastroPessoaActivity.pessoa.getSexo() != null  && cadastroPessoaActivity.pessoa.getSexo().equals("Masculino")) {
                radioGroupGestante.setEnabled(false);
                for (int k = 0; k < radioGroupGestante.getChildCount(); k++)
                    radioGroupGestante.getChildAt(k).setEnabled(false);
                radioGroupGestante.clearCheck();
                cadastroPessoaActivity.situacaoSaude.setGestante(false);
            }
            else if (cadastroPessoaActivity.pessoa.getSexo() != null){
                radioGroupGestante.setEnabled(true);
                for (int k = 0; k < radioGroupGestante.getChildCount(); k++)
                    radioGroupGestante.getChildAt(k).setEnabled(true);
            }
        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 4){
                if (radioGroupDeficiencia.getCheckedRadioButtonId() <= 0)
                    for (int k = 0; k < radioGroupDeficiencia.getChildCount(); k++)
                        ((RadioButton)radioGroupDeficiencia.getChildAt(k)).setError("Selecione Item");
                else if (radioGroupDeficiencia.getCheckedRadioButtonId() == R.id.radioButtonDeficienciaSim && !checkBoxDeficienciaAuditiva.isChecked() && !checkBoxDeficienciaVisual.isChecked() && !checkBoxDeficienciaFisica.isChecked() && ! checkBoxDeficienciaIntelectual.isChecked()){
                    checkBoxDeficienciaAuditiva.setError("Selecione um item");
                    checkBoxDeficienciaVisual.setError("Selecione um item");
                    checkBoxDeficienciaFisica.setError("Selecione um item");
                    checkBoxDeficienciaIntelectual.setError("Selecione um item");
                }
                else {
                    cadastroPessoaActivity.valide = true;
                }
            }
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment5 newInstance(int sectionNumber) {
            PlaceholderFragment5 fragment = new PlaceholderFragment5();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        static RadioGroup radioGroupGestante, radioGroupDeficiencia,radioGroupPeso,radioGroupFumante,radioGroupBebidas,radioGroupDroga,ragioGroupHipertenção;
        static CadastroPessoaActivity cadastroPessoaActivity;
        static CheckBox checkBoxDeficienciaAuditiva, checkBoxDeficienciaVisual,checkBoxDeficienciaFisica,checkBoxDeficienciaIntelectual;
        static String[] qualDeficiencia = {""};
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            System.out.println(container.getChildCount());
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_5, container, false);
            radioGroupDeficiencia = (RadioGroup) rootView.findViewById(R.id.radioGroupDeficiencia);
            checkBoxDeficienciaAuditiva = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaAuditiva);
            checkBoxDeficienciaVisual = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaVisual);
            checkBoxDeficienciaFisica = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaFisica);
            checkBoxDeficienciaIntelectual = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaIntelectual);
            radioGroupGestante = (RadioGroup) rootView.findViewById(R.id.radioGroupGestante);
            radioGroupPeso = (RadioGroup) rootView.findViewById(R.id.radioGroupPeso);
            radioGroupFumante = (RadioGroup) rootView.findViewById(R.id.radioGroupFumante);
            radioGroupBebidas = (RadioGroup) rootView.findViewById(R.id.radioGroupBebidas);
            radioGroupDroga = (RadioGroup) rootView.findViewById(R.id.radioGroupDroga);
            ragioGroupHipertenção = (RadioGroup) rootView.findViewById(R.id.ragioGroupHipertenção);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            radioGroupDeficiencia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.radioButtonDeficienciaSim){
                        checkBoxDeficienciaAuditiva.setEnabled(true);
                        checkBoxDeficienciaVisual.setEnabled(true);
                        checkBoxDeficienciaFisica.setEnabled(true);
                        checkBoxDeficienciaIntelectual.setEnabled(true);
                        checkBoxDeficienciaAuditiva.setError(null);
                        checkBoxDeficienciaVisual.setError(null);
                        checkBoxDeficienciaFisica.setError(null);
                        checkBoxDeficienciaIntelectual.setError(null);
                    }
                    else {
                        checkBoxDeficienciaAuditiva.setEnabled(false);
                        checkBoxDeficienciaVisual.setEnabled(false);
                        checkBoxDeficienciaFisica.setEnabled(false);
                        checkBoxDeficienciaIntelectual.setEnabled(false);
                        checkBoxDeficienciaAuditiva.setChecked(false);
                        checkBoxDeficienciaVisual.setChecked(false);
                        checkBoxDeficienciaFisica.setChecked(false);
                        checkBoxDeficienciaIntelectual.setChecked(false);
                        checkBoxDeficienciaAuditiva.setError(null);
                        checkBoxDeficienciaVisual.setError(null);
                        checkBoxDeficienciaFisica.setError(null);
                        checkBoxDeficienciaIntelectual.setError(null);
                        qualDeficiencia[0] = "";


                    }
                    for (int k = 0; k < radioGroupDeficiencia.getChildCount(); k++)
                        ((RadioButton)radioGroupDeficiencia.getChildAt(k)).setError(null);
                    cadastroPessoaActivity.situacaoSaude.setDeficiencia(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            checkBoxDeficienciaAuditiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaAuditiva.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaAuditiva.getText()+";","");
                    cadastroPessoaActivity.situacaoSaude.setQualDeficiencia(qualDeficiencia[0]);
                    checkBoxDeficienciaAuditiva.setError(null);
                    checkBoxDeficienciaVisual.setError(null);
                    checkBoxDeficienciaFisica.setError(null);
                    checkBoxDeficienciaIntelectual.setError(null);
                }
            });

            checkBoxDeficienciaVisual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaVisual.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaVisual.getText()+";","");
                    cadastroPessoaActivity.situacaoSaude.setQualDeficiencia(qualDeficiencia[0]);
                    checkBoxDeficienciaAuditiva.setError(null);
                    checkBoxDeficienciaVisual.setError(null);
                    checkBoxDeficienciaFisica.setError(null);
                    checkBoxDeficienciaIntelectual.setError(null);
                }
            });

            checkBoxDeficienciaFisica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaFisica.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaFisica.getText()+";","");
                    cadastroPessoaActivity.situacaoSaude.setQualDeficiencia(qualDeficiencia[0]);
                    checkBoxDeficienciaAuditiva.setError(null);
                    checkBoxDeficienciaVisual.setError(null);
                    checkBoxDeficienciaFisica.setError(null);
                    checkBoxDeficienciaIntelectual.setError(null);
                }
            });
            checkBoxDeficienciaIntelectual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaIntelectual.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaIntelectual.getText()+";","");
                    cadastroPessoaActivity.situacaoSaude.setQualDeficiencia(qualDeficiencia[0]);
                    checkBoxDeficienciaAuditiva.setError(null);
                    checkBoxDeficienciaVisual.setError(null);
                    checkBoxDeficienciaFisica.setError(null);
                    checkBoxDeficienciaIntelectual.setError(null);
                }
            });
            radioGroupGestante.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (((RadioButton)rootView.findViewById(i)) != null)
                        cadastroPessoaActivity.situacaoSaude.setGestante(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupPeso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setNivelPeso(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            radioGroupFumante.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setFumante(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupBebidas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setAlcool(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupDroga.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setDrogas(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            ragioGroupHipertenção.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setHipertenso(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment6 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment6() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment6 newInstance(int sectionNumber) {
            PlaceholderFragment6 fragment = new PlaceholderFragment6();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        static RadioGroup radioGroupDiabetes, radioGroupAvc, radioGroupInfarto, radioGroupCardiaca, radioGroupRins, radioGroupRespiratorio, radioGroupHanseniase, ragioGroupTuberculose;
        static EditText qualCardiacaEditText, qualRinsEditText;
        static CadastroPessoaActivity cadastroPessoaActivity;

        @Override
        public void onResume(){
            super.onResume();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 5 && cadastroPessoaActivity.lPage != 5) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 5;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui 6");
            }
            else if ( cadastroPessoaActivity.cPage == 5) {
                cadastroPessoaActivity.lPage = 5;
                cadastroPessoaActivity.valide = false;
            }
        }

        @Override
        public void onPause(){
            super.onPause();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 5){
                String qualCardiaca =  qualCardiacaEditText.getText().toString();
                String qualRins = qualRinsEditText.getText().toString();
                if (radioGroupDiabetes.getCheckedRadioButtonId() <= 0 || radioGroupAvc.getCheckedRadioButtonId()<=0
                        || radioGroupInfarto.getCheckedRadioButtonId() <=0 || radioGroupCardiaca.getCheckedRadioButtonId()<=0
                        || radioGroupRins.getCheckedRadioButtonId()<=0 || radioGroupRespiratorio.getCheckedRadioButtonId()<=0
                        || radioGroupHanseniase.getCheckedRadioButtonId()<=0 || ragioGroupTuberculose.getCheckedRadioButtonId()<=0
                        || (radioGroupCardiaca.getCheckedRadioButtonId() == R.id.radioButtonCardiacaSim && qualCardiaca.equals(""))
                        || (radioGroupRins.getCheckedRadioButtonId() == R.id.radioButtonRinsSim && qualRins.equals(""))) {

                    if (radioGroupDiabetes.getCheckedRadioButtonId() <= 0)
                        for (int k = 0; k < radioGroupDiabetes.getChildCount(); k++)
                            ((RadioButton) radioGroupDiabetes.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupAvc.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < radioGroupAvc.getChildCount(); k++)
                            ((RadioButton) radioGroupAvc.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupInfarto.getCheckedRadioButtonId() <=0)
                        for (int k = 0; k < radioGroupInfarto.getChildCount(); k++)
                            ((RadioButton) radioGroupInfarto.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupCardiaca.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < radioGroupCardiaca.getChildCount(); k++)
                            ((RadioButton) radioGroupCardiaca.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupCardiaca.getCheckedRadioButtonId() == R.id.radioButtonCardiacaSim && qualCardiaca.equals(""))
                        qualCardiacaEditText.setError("Diga o motivo");
                    if (radioGroupRins.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < radioGroupRins.getChildCount(); k++)
                            ((RadioButton) radioGroupRins.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupRins.getCheckedRadioButtonId() == R.id.radioButtonRinsSim && qualRins.equals(""))
                        qualRinsEditText.setError("Diga o problema");
                    if (radioGroupRespiratorio.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < radioGroupRespiratorio.getChildCount(); k++)
                            ((RadioButton) radioGroupRespiratorio.getChildAt(k)).setError("Selecione Item");
                    if (radioGroupHanseniase.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < radioGroupHanseniase.getChildCount(); k++)
                            ((RadioButton) radioGroupHanseniase.getChildAt(k)).setError("Selecione Item");
                    if (ragioGroupTuberculose.getCheckedRadioButtonId()<=0)
                        for (int k = 0; k < ragioGroupTuberculose.getChildCount(); k++)
                            ((RadioButton) ragioGroupTuberculose.getChildAt(k)).setError("Selecione Item");
                }
                else {
                    cadastroPessoaActivity.situacaoSaude.setQualDoencaCardiaca(qualCardiaca);
                    cadastroPessoaActivity.situacaoSaude.setQualProblemaRins(qualRins);
                    cadastroPessoaActivity.valide = true;
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_6, container, false);
            radioGroupDiabetes = (RadioGroup) rootView.findViewById(R.id.radioGroupDiabetes);
            radioGroupAvc = (RadioGroup) rootView.findViewById(R.id.radioGroupAvc);
            radioGroupInfarto = (RadioGroup) rootView.findViewById(R.id.radioGroupInfarto);
            radioGroupCardiaca = (RadioGroup) rootView.findViewById(R.id.radioGroupCardiaca);
            radioGroupRins = (RadioGroup) rootView.findViewById(R.id.radioGroupRins);
            radioGroupRespiratorio = (RadioGroup) rootView.findViewById(R.id.radioGroupRespiratorio);
            radioGroupHanseniase = (RadioGroup) rootView.findViewById(R.id.radioGroupHanseniase);
            ragioGroupTuberculose = (RadioGroup) rootView.findViewById(R.id.ragioGroupTuberculose);
            qualCardiacaEditText = (EditText) rootView.findViewById(R.id.qualCardiacaEditText);
            qualRinsEditText = (EditText) rootView.findViewById(R.id.qualRinsEditText);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            radioGroupDiabetes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    for (int k = 0; k < radioGroupDiabetes.getChildCount(); k++)
                        ((RadioButton)radioGroupDiabetes.getChildAt(k)).setError(null);
                    cadastroPessoaActivity.situacaoSaude.setDiabete(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupAvc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setAVC_Derrame(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    for (int k = 0; k < radioGroupAvc.getChildCount(); k++)
                        ((RadioButton) radioGroupAvc.getChildAt(k)).setError(null);
                }
            });
            radioGroupInfarto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setInfarto(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    for (int k = 0; k < radioGroupInfarto.getChildCount(); k++)
                        ((RadioButton) radioGroupInfarto.getChildAt(k)).setError(null);
                }
            });
            radioGroupCardiaca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setDoencaCardiaca(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonCardiacaSim)
                        qualCardiacaEditText.setEnabled(true);
                    else {
                        qualCardiacaEditText.setEnabled(false);
                        qualCardiacaEditText.setText("");
                        qualCardiacaEditText.setError(null);
                    }
                    for (int k = 0; k < radioGroupCardiaca.getChildCount(); k++)
                        ((RadioButton) radioGroupCardiaca.getChildAt(k)).setError(null);
                }
            });
            radioGroupRins.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setProblemaRins(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonRinsSim)
                        qualRinsEditText.setEnabled(true);
                    else {
                        qualRinsEditText.setEnabled(false);
                        qualRinsEditText.setText("");
                        qualRinsEditText.setError(null);
                    }
                    for (int k = 0; k < radioGroupRins.getChildCount(); k++)
                        ((RadioButton) radioGroupRins.getChildAt(k)).setError(null);

                }
            });
            radioGroupRespiratorio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setProblemaRespiratorios(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    for (int k = 0; k < radioGroupRespiratorio.getChildCount(); k++)
                        ((RadioButton) radioGroupRespiratorio.getChildAt(k)).setError(null);
                }
            });
            radioGroupHanseniase.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setHanseniase(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    for (int k = 0; k < radioGroupHanseniase.getChildCount(); k++)
                        ((RadioButton) radioGroupHanseniase.getChildAt(k)).setError(null);
                }
            });
            ragioGroupTuberculose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setTuberculose(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    for (int k = 0; k < ragioGroupTuberculose.getChildCount(); k++)
                        ((RadioButton) ragioGroupTuberculose.getChildAt(k)).setError(null);
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment7 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment7() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment7 newInstance(int sectionNumber) {
            PlaceholderFragment7 fragment = new PlaceholderFragment7();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        static Button addPessoa;
        static RadioGroup radioGroupInternacao, radioGroupMental, radioGroupPsiquiatra, radioGroupSaude, radioGroupPlantas;
        static EditText motivoInternacaoEditText, qualPlantaEditText;
        static CadastroPessoaActivity cadastroPessoaActivity;

        @Override
        public void onResume(){
            super.onResume();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.cPage == 6 && cadastroPessoaActivity.lPage != 6) {
                cadastroPessoaActivity.cPage = cadastroPessoaActivity.lPage;
                cadastroPessoaActivity.lPage = 6;
                cadastroPessoaActivity.valide = true;
                cadastroPessoaActivity.selectPage(cadastroPessoaActivity.cPage);
                System.out.println("Entrou aqui 7");
            }
            else if ( cadastroPessoaActivity.cPage == 6) {
                cadastroPessoaActivity.lPage = 6;
                cadastroPessoaActivity.valide = false;
            }

        }

        @Override
        public void onPause(){
            String motivoInternacao = motivoInternacaoEditText.getText().toString();
            String qualPlanta = qualPlantaEditText.getText().toString();
            if (!cadastroPessoaActivity.valide && cadastroPessoaActivity.lPage == 6){
                if (radioGroupInternacao.getCheckedRadioButtonId() <=0 || (radioGroupInternacao.getCheckedRadioButtonId() == R.id.radioButtonInternacaoSim && motivoInternacao.equals(""))
                        || radioGroupMental.getCheckedRadioButtonId() <=0 || (radioGroupMental.getCheckedRadioButtonId() == R.id.radioButtonMentalSim && radioGroupPsiquiatra.getCheckedRadioButtonId() <= 0)
                        || radioGroupSaude.getCheckedRadioButtonId() <= 0 || radioGroupPlantas.getCheckedRadioButtonId() <= 0
                        || (radioGroupPlantas.getCheckedRadioButtonId() == R.id.radioButtonPlantaSim && qualPlanta.equals(""))){
                    if (radioGroupInternacao.getCheckedRadioButtonId() <=0)
                        for (int k = 0; k < radioGroupInternacao.getChildCount(); k++)
                            ((RadioButton)radioGroupInternacao.getChildAt(k)).setError("Selecione um item");
                    else if (radioGroupInternacao.getCheckedRadioButtonId() == R.id.radioButtonInternacaoSim && motivoInternacao.equals(""))
                        motivoInternacaoEditText.setError("Campo não pode estar vazio");
                }
                else {
                    cadastroPessoaActivity.situacaoSaude.setMotivoInternacao(motivoInternacao);
                    cadastroPessoaActivity.situacaoSaude.setPlantasMedicinais(qualPlanta);
                    cadastroPessoaActivity.valide = true;
                }
            }
            super.onPause();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_7, container, false);
            addPessoa = (Button) rootView.findViewById(R.id.buttonAddPessoaFinalizar);
            //7
            radioGroupInternacao = (RadioGroup) rootView.findViewById(R.id.radioGroupInternacao);
            motivoInternacaoEditText = (EditText) rootView.findViewById(R.id.motivoInternacaoEditText);
            radioGroupMental = (RadioGroup) rootView.findViewById(R.id.radioGroupMental);
            radioGroupPsiquiatra = (RadioGroup) rootView.findViewById(R.id.radioGroupPsiquiatra);
            radioGroupSaude = (RadioGroup) rootView.findViewById(R.id.radioGroupSaude);
            radioGroupPlantas = (RadioGroup) rootView.findViewById(R.id.radioGroupPlantas);
            qualPlantaEditText = (EditText) rootView.findViewById(R.id.qualPlantaEditText);
            cadastroPessoaActivity = (CadastroPessoaActivity)getActivity();
            radioGroupInternacao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setInternacao(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonInternacaoSim)
                        motivoInternacaoEditText.setEnabled(true);
                    else {
                        motivoInternacaoEditText.setEnabled(false);
                        motivoInternacaoEditText.setText("");
                    }
                }
            });
            radioGroupMental.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setProblemaMental(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonMentalNao) {
                        radioGroupPsiquiatra.setEnabled(false);
                        for (int k = 0; k < radioGroupPsiquiatra.getChildCount(); k++)
                            radioGroupPsiquiatra.getChildAt(k).setEnabled(false);
                        radioGroupPsiquiatra.clearCheck();
                        cadastroPessoaActivity.situacaoSaude.setTratamento(false);
                    }
                    else {
                        radioGroupPsiquiatra.setEnabled(true);
                        for (int k = 0; k < radioGroupPsiquiatra.getChildCount(); k++)
                            radioGroupPsiquiatra.getChildAt(k).setEnabled(true);
                    }
                }
            });
            radioGroupPsiquiatra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null)
                        cadastroPessoaActivity.situacaoSaude.setTratamento(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupSaude.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setNivelSaude(Integer.parseInt(((RadioButton)rootView.findViewById(i)).getText().toString()));
                }
            });
            radioGroupPlantas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    cadastroPessoaActivity.situacaoSaude.setUsaPlantas(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonPlantaSim)
                        qualPlantaEditText.setEnabled(true);
                    else {
                        qualPlantaEditText.setEnabled(false);
                        qualPlantaEditText.setText("");
                    }
                }
            });
            
            addPessoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cadastroPessoaActivity.validarAtributos()){
                        cadastroPessoaActivity.pessoa.setSaude(cadastroPessoaActivity.situacaoSaude);
                        Intent intent = new Intent();
                        intent.putExtra("pessoa", cadastroPessoaActivity.pessoa);
                        intent.putExtra("situacaoSaude", cadastroPessoaActivity.situacaoSaude);
                        intent.putExtra("isUpdate", cadastroPessoaActivity.isUpdate);
                        if (!cadastroPessoaActivity.isUpdate) {
                            //insert aqui
                            getActivity().setResult(999, intent);
                            getActivity().finish();
                        } else {
                            //updtate aqui
                            intent.putExtra("id", cadastroPessoaActivity.id);
                            getActivity().setResult(888, intent);
                            getActivity().finish();
                        }
                    }
                }
            });
            
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return PlaceholderFragment2.newInstance(position + 1);
                case 2:
                    return PlaceholderFragment3.newInstance(position + 1);
                case 3:
                    return PlaceholderFragment4.newInstance(position + 1);
                case 4:
                    return PlaceholderFragment5.newInstance(position + 1);
                case 5:
                    return PlaceholderFragment6.newInstance(position + 1);
                case 6:
                    return PlaceholderFragment7.newInstance(position + 1);
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 7 total pages.
            return 7;
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
                case 4:
                    return "SECTION 5";
                case 5:
                    return "SECTION 6";
                case 6:
                    return "SECTION 7";
            }
            return null;
        }
    }
}
