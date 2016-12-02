package saacs.ufc.com.br.saacs.activity;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.model.Pessoa;
import saacs.ufc.com.br.saacs.model.SituacaoSaude;

public class CadastroPessoaActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public Pessoa pessoa;
    public SituacaoSaude situacaoSaude;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

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
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int currentPosition = 0;

            @Override
            public void onPageSelected(int position) {
                Fragment fa = (Fragment) mSectionsPagerAdapter.getItem(currentPosition);
                fa.onPause();

                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
        EditText numSusEditText, dtNascimentoEditText, nomeCompletoEditText;
        RadioGroup radioGroupRaca, radioGroupSex;
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
        public void onPause(){
            super.onPause();
            System.out.println("pause");
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

            radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).pessoa.setSexo(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            radioGroupRaca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity) getActivity()).pessoa.setEtnia(((RadioButton)rootView.findViewById(i)).getText().toString());
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_2, container, false);
            RadioGroup nacionalidadeGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupNacionalidade);
            final EditText paisOrigemEditText = (EditText) rootView.findViewById(R.id.paisDeOrigemEditText);
            nacionalidadeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i != R.id.radioButtonNacionalidadeBrasileiro) {
                        paisOrigemEditText.setEnabled(true);
                        paisOrigemEditText.setText("");
                    }
                    else {
                        paisOrigemEditText.setEnabled(false);
                        paisOrigemEditText.setText("Brasileiro");
                    }
                    ((CadastroPessoaActivity)getActivity()).pessoa.setNacionalidade(paisOrigemEditText.getText().toString());
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_3, container, false);
            RadioGroup responsavelGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupResponsavel);

            final RadioGroup relacaoGroup = (RadioGroup) rootView.findViewById(R.id.RadioGroupParentesco);
            relacaoGroup.setEnabled(false);
            for (int k = 0; k < relacaoGroup.getChildCount(); k++)
                relacaoGroup.getChildAt(k).setEnabled(false);
            responsavelGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.radioButtonResponsavelSim) {
                        relacaoGroup.setEnabled(false);
                        for (int k = 0; k < relacaoGroup.getChildCount(); k++)
                            relacaoGroup.getChildAt(k).setEnabled(false);
                        relacaoGroup.clearCheck();
                        ((CadastroPessoaActivity)getActivity()).pessoa.setRelacaoParentRF(null);
                    }
                    else {
                        relacaoGroup.setEnabled(true);
                        for (int k = 0; k < relacaoGroup.getChildCount(); k++)
                            relacaoGroup.getChildAt(k).setEnabled(true);
                    }
                    ((CadastroPessoaActivity)getActivity()).pessoa.setResponsavelFamiliar(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            relacaoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null)
                        ((CadastroPessoaActivity)getActivity()).pessoa.setRelacaoParentRF(((RadioButton)rootView.findViewById(i)).getText().toString());
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
        RadioGroup radioGroupEscolaridade;
        RadioGroup radioGroupTrabalho;
        EditText profissaoEditText;

        @Override
        public void onResume(){
            super.onResume();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_4, container, false);
            radioGroupEscolaridade = (RadioGroup) rootView.findViewById(R.id.radioGroupEscolaridade);
            radioGroupTrabalho = (RadioGroup) rootView.findViewById(R.id.radioGroupTrabalho);
            profissaoEditText = (EditText) rootView.findViewById(R.id.profissaoEditText);
            radioGroupEscolaridade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null)
                        ((CadastroPessoaActivity)getActivity()).pessoa.setEscolaridade(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            radioGroupTrabalho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rootView.findViewById(i) != null)
                        ((CadastroPessoaActivity)getActivity()).pessoa.setSituacaoMercado(((RadioButton)rootView.findViewById(i)).getText().toString());
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

        RadioGroup radioGroupGestante;

        public PlaceholderFragment5() {
        }

        @Override
        public void onResume() {
            super.onResume();
            System.out.println(((CadastroPessoaActivity)getActivity()).pessoa.getSexo());
            if (((CadastroPessoaActivity)getActivity()).pessoa.getSexo() != null  && ((CadastroPessoaActivity)getActivity()).pessoa.getSexo().equals("Masculino")) {
                radioGroupGestante.setEnabled(false);
                for (int k = 0; k < radioGroupGestante.getChildCount(); k++)
                    radioGroupGestante.getChildAt(k).setEnabled(false);
                radioGroupGestante.clearCheck();
                ((CadastroPessoaActivity)getActivity()).situacaoSaude.setGestante(false);
            }
            else if (((CadastroPessoaActivity)getActivity()).pessoa.getSexo() != null){
                radioGroupGestante.setEnabled(true);
                for (int k = 0; k < radioGroupGestante.getChildCount(); k++)
                    radioGroupGestante.getChildAt(k).setEnabled(true);
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            System.out.println(container.getChildCount());
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_5, container, false);
            RadioGroup radioGroupDeficiencia = (RadioGroup) rootView.findViewById(R.id.radioGroupDeficiencia);
            final CheckBox checkBoxDeficienciaAuditiva = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaAuditiva);
            final CheckBox checkBoxDeficienciaVisual = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaVisual);
            final CheckBox checkBoxDeficienciaFisica = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaFisica);
            final CheckBox checkBoxDeficienciaIntelectual = (CheckBox) rootView.findViewById(R.id.checkBoxDeficienciaIntelectual);
            radioGroupGestante = (RadioGroup) rootView.findViewById(R.id.radioGroupGestante);
            RadioGroup radioGroupPeso = (RadioGroup) rootView.findViewById(R.id.radioGroupPeso);
            RadioGroup radioGroupFumante = (RadioGroup) rootView.findViewById(R.id.radioGroupFumante);
            RadioGroup radioGroupBebidas = (RadioGroup) rootView.findViewById(R.id.radioGroupBebidas);
            RadioGroup radioGroupDroga = (RadioGroup) rootView.findViewById(R.id.radioGroupDroga);
            RadioGroup ragioGroupHipertenção = (RadioGroup) rootView.findViewById(R.id.ragioGroupHipertenção);
            final String[] qualDeficiencia = {""};
            radioGroupDeficiencia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.radioButtonDeficienciaSim){
                        checkBoxDeficienciaAuditiva.setEnabled(true);
                        checkBoxDeficienciaVisual.setEnabled(true);
                        checkBoxDeficienciaFisica.setEnabled(true);
                        checkBoxDeficienciaIntelectual.setEnabled(true);
                    }
                    else {
                        checkBoxDeficienciaAuditiva.setEnabled(false);
                        checkBoxDeficienciaVisual.setEnabled(false);
                        checkBoxDeficienciaFisica.setEnabled(false);
                        checkBoxDeficienciaIntelectual.setEnabled(false);
                        checkBoxDeficienciaAuditiva.setSelected(false);
                        checkBoxDeficienciaVisual.setSelected(false);
                        checkBoxDeficienciaFisica.setSelected(false);
                        checkBoxDeficienciaIntelectual.setSelected(false);
                        qualDeficiencia[0] = "";


                    }
                    ((CadastroPessoaActivity)getActivity()).pessoa.setDeficiencia(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            checkBoxDeficienciaAuditiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaAuditiva.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaAuditiva.getText()+";","");
                    ((CadastroPessoaActivity)getActivity()).pessoa.setQualDeficiencia(qualDeficiencia[0]);
                }
            });

            checkBoxDeficienciaVisual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaVisual.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaVisual.getText()+";","");
                    ((CadastroPessoaActivity)getActivity()).pessoa.setQualDeficiencia(qualDeficiencia[0]);
                }
            });

            checkBoxDeficienciaFisica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaFisica.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaFisica.getText()+";","");
                    ((CadastroPessoaActivity)getActivity()).pessoa.setQualDeficiencia(qualDeficiencia[0]);
                }
            });
            checkBoxDeficienciaIntelectual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        qualDeficiencia[0] += checkBoxDeficienciaIntelectual.getText().toString() + ";";
                    else
                        qualDeficiencia[0] = qualDeficiencia[0].replace(checkBoxDeficienciaIntelectual.getText()+";","");
                    ((CadastroPessoaActivity)getActivity()).pessoa.setQualDeficiencia(qualDeficiencia[0]);
                }
            });
            radioGroupGestante.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (((RadioButton)rootView.findViewById(i)) != null)
                        ((CadastroPessoaActivity)getActivity()).situacaoSaude.setGestante(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupPeso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setNivelPeso(((RadioButton)rootView.findViewById(i)).getText().toString());
                }
            });
            radioGroupFumante.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setFumante(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupBebidas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setAlcool(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupDroga.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setDrogas(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            ragioGroupHipertenção.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setHipertenso(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_6, container, false);
            RadioGroup radioGroupDiabetes = (RadioGroup) rootView.findViewById(R.id.radioGroupDiabetes);
            RadioGroup radioGroupAvc = (RadioGroup) rootView.findViewById(R.id.radioGroupAvc);
            RadioGroup radioGroupInfarto = (RadioGroup) rootView.findViewById(R.id.radioGroupInfarto);
            RadioGroup radioGroupCardiaca = (RadioGroup) rootView.findViewById(R.id.radioGroupCardiaca);
            RadioGroup radioGroupRins = (RadioGroup) rootView.findViewById(R.id.radioGroupRins);
            RadioGroup radioGroupRespiratorio = (RadioGroup) rootView.findViewById(R.id.radioGroupRespiratorio);
            RadioGroup radioGroupHanseniase = (RadioGroup) rootView.findViewById(R.id.radioGroupHanseniase);
            RadioGroup ragioGroupTuberculose = (RadioGroup) rootView.findViewById(R.id.ragioGroupTuberculose);
            final EditText qualCardiacaEditText = (EditText) rootView.findViewById(R.id.qualCardiacaEditText);
            final EditText qualRinsEditText = (EditText) rootView.findViewById(R.id.qualRinsEditText);
            radioGroupDiabetes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setDiabete(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupAvc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setAVC_Derrame(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupInfarto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setInfarto(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupCardiaca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setDoencaCardiaca(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonCardiacaSim)
                        qualCardiacaEditText.setEnabled(true);
                    else {
                        qualCardiacaEditText.setEnabled(false);
                        qualCardiacaEditText.setText("");
                    }
                }
            });
            radioGroupRins.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setProblemaRins(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonRinsSim)
                        qualRinsEditText.setEnabled(true);
                    else {
                        qualRinsEditText.setEnabled(false);
                        qualRinsEditText.setText("");
                    }
                }
            });
            radioGroupRespiratorio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setProblemaRespiratorios(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupHanseniase.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setHanseniase(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            ragioGroupTuberculose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setTuberculose(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_7, container, false);
            Button addPessoa = (Button) rootView.findViewById(R.id.buttonAddPessoaFinalizar);
            //1
            //2
            EditText numeroNisEditText = (EditText) rootView.findViewById(R.id.numeroNisEditText);
            EditText nomeDaMaeEditText = (EditText) rootView.findViewById(R.id.nomeDaMaeEditText);
            EditText paisDeOrigemEditText = (EditText) rootView.findViewById(R.id.paisDeOrigemEditText);
            EditText cidadeNatalEditText = (EditText) rootView.findViewById(R.id.cidadeNatalEditText);
            EditText estadoEditText = (EditText) rootView.findViewById(R.id.estadoEditText);
            EditText telefoneEditText = (EditText) rootView.findViewById(R.id.telefoneEditText);
            EditText emailEditText = (EditText) rootView.findViewById(R.id.emailEditText);
            //3
            //4
            //5
            //6
            EditText qualCardiacaEditText = (EditText) rootView.findViewById(R.id.qualCardiacaEditText);
            EditText qualRinsEditText = (EditText) rootView.findViewById(R.id.qualRinsEditText);
            //7
            RadioGroup radioGroupInternacao = (RadioGroup) rootView.findViewById(R.id.radioGroupInternacao);
            final EditText motivoInternacaoEditText = (EditText) rootView.findViewById(R.id.motivoInternacaoEditText);
            RadioGroup radioGroupMental = (RadioGroup) rootView.findViewById(R.id.radioGroupMental);
            final RadioGroup radioGroupPsiquiatra = (RadioGroup) rootView.findViewById(R.id.radioGroupPsiquiatra);
            RadioGroup radioGroupSaude = (RadioGroup) rootView.findViewById(R.id.radioGroupSaude);
            RadioGroup radioGroupPlantas = (RadioGroup) rootView.findViewById(R.id.radioGroupPlantas);
            final EditText qualPlantaEditText = (EditText) rootView.findViewById(R.id.qualPlantaEditText);

            radioGroupInternacao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setInternacao(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
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
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setProblemaMental(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                    if (i == R.id.radioButtonMentalNao) {
                        radioGroupPsiquiatra.setEnabled(false);
                        for (int k = 0; k < radioGroupPsiquiatra.getChildCount(); k++)
                            radioGroupPsiquiatra.getChildAt(k).setEnabled(false);
                        radioGroupPsiquiatra.clearCheck();
                        ((CadastroPessoaActivity)getActivity()).situacaoSaude.setTratamento(false);
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
                        ((CadastroPessoaActivity)getActivity()).situacaoSaude.setTratamento(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
                }
            });
            radioGroupSaude.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setNivelSaude(Integer.parseInt(((RadioButton)rootView.findViewById(i)).getText().toString()));
                }
            });
            radioGroupPlantas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((CadastroPessoaActivity)getActivity()).situacaoSaude.setUsaPlantas(((RadioButton)rootView.findViewById(i)).getText().toString().equals("Sim"));
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
                    Pessoa p = new Pessoa();
                    SituacaoSaude stSaude = new SituacaoSaude();

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
