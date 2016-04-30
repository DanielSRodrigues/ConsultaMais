package bestsolutions.net.consultamais;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bestsolutions.net.consultamais.database.DB;
import bestsolutions.net.consultamais.entidades.Atendimento;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListagemConsultas extends Fragment {

    private OnConsultaClicked mListener;
    @Bind(R.id.listConsultas)
    public RecyclerView mListagemConsulta;
    @Bind(R.id.swipe)
    public SwipeRefreshLayout mSwipe;
    public AtendimentosRecycleAdapter mAdapter;

    public ArrayList<Atendimento> mAtendimentos = new ArrayList<>();

    public ListagemConsultas() {
        // Required empty public constructor
    }

    public static ListagemConsultas newInstance() {
        ListagemConsultas fragment = new ListagemConsultas();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listagem_consultas, container, false);
        ButterKnife.bind(this, view);

        /*AtendimentosTask task = new AtendimentosTask();
        task.execute();*/

        mListagemConsulta.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListagemConsulta.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AtendimentosRecycleAdapter(getActivity(), mAtendimentos);
        mListagemConsulta.setAdapter(mAdapter);
        AtualizaListagem();

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AtualizaListagem();
            }
        });
/*Snackbar.make(view, "Criar método de Adição da Consulta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CadastrarConsulta.class);
                startActivity(i);
            }
        });


        return view;
    }

   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnAtualizaListagem(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnConsultaClicked) {
            mListener = (OnConsultaClicked) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    public void AtualizaListagem() {
        mSwipe.setRefreshing(true);
        mAtendimentos.clear();
        mAtendimentos.addAll(DB.Atendimentos);
        mAdapter.notifyDataSetChanged();
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnConsultaClicked {
        // TODO: Update argument type and name
        void OnConsultaClicked(ArrayList<Atendimento> atendimentos);
    }

    class AtendimentosTask extends AsyncTask<Void, Void, ArrayList<Atendimento>> {

        @Override
        protected ArrayList<Atendimento> doInBackground(Void... params) {
            //VerificaConexao.verificaConexao(_Activity.getContext());
            final String url = "https://3ezp6a-ch3302.files.1drv.com/y3mpELFQ0zimR7sS5jbVmkTvzeQtyRauFKWNVieAo3TscoM3FgP4SKKdyHtVC76_wp3U5QQtR9Mckk1GqDKSGqLI3fhqZ83SA7b4YCkFp4yDMrWogbEge_fglNrQb5MxyZArG6nqK9mhkzi1Ha5ACS0_g/Atendimentos.json?download&psid=1";
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder().url(url).build();
            try {
                Response resp = client.newCall(req).execute();
                String jsonString = resp.body().string();
                Gson json = new Gson();


            } catch (Exception ex) {

            }

            return null;
        }
    }

    private class AtendimentosRecycleAdapter extends RecyclerView.Adapter<ConsultaViewHolderAdapter> {

        private ArrayList<Atendimento> mValues;

        public AtendimentosRecycleAdapter(Context context, ArrayList<Atendimento> items) {
            mValues = items;
        }

        @Override
        public ConsultaViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_consulta, parent, false);
            return new ConsultaViewHolderAdapter(view);
        }

        public Atendimento getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public void onBindViewHolder(final ConsultaViewHolderAdapter holder, final int position) {
            holder.bindOS(getValueAt(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public class ConsultaViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.lblDataAtendimento)
        public TextView DataAtendimento;
        @Bind(R.id.lblHoraAtendimento)
        public TextView HoraAtendimento;
        @Bind(R.id.lblPaciente)
        public TextView Paciente;
        @Bind(R.id.lblEspecialidade)
        public TextView Especialidade;


        public ConsultaViewHolderAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          /*  Intent i = new Intent(v.getContext(), OSConsulta.class);
            i.putExtra("Item", _OSTemp);
            v.getContext().startActivity(i);*/
        }

        public void bindOS(Atendimento atendimento) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat dh = new SimpleDateFormat("HH:mm");

            // Get the date today using Calendar object.

            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            String reportDate = df.format(atendimento.getDataHoraAtendimento());
            String hora = dh.format(atendimento.getDataHoraAtendimento());
            HoraAtendimento.setText(hora);
            DataAtendimento.setText(reportDate);
            Paciente.setText(atendimento.getPaciente());
            Especialidade.setText(atendimento.getEspecialidadeMedico());
        }

    }

}