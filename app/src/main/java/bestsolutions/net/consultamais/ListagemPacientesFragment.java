package bestsolutions.net.consultamais;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bestsolutions.net.consultamais.database.PacienteDB;
import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Paciente;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ListagemPacientesFragment extends Fragment {


    public RecyclerView mListagem;
    public PacientesRecycleAdapter mAdapter;
    public ArrayList<Paciente> mPacientes;
    public FloatingActionButton mAddPaciente;
    @Bind(R.id.qtdItens)
    public TextView mQtdIntes;

    public ListagemPacientesFragment() {
    }

    public static ListagemPacientesFragment newInstance() {
        ListagemPacientesFragment fragment = new ListagemPacientesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listagem_pacientes, container, false);
        ButterKnife.bind(this, view);

        mListagem = (RecyclerView) view.findViewById(R.id.listaPacientes);
        mListagem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListagem.setItemAnimator(new DefaultItemAnimator());

        PacienteDB db = new PacienteDB(getActivity());
        mPacientes = db.Listagem();
        mAdapter = new PacientesRecycleAdapter(getActivity(), mPacientes);
        mListagem.setAdapter(mAdapter);
        AtualizaListagem();

        mAddPaciente = (FloatingActionButton) view.findViewById(R.id.addPaciente);
        mAddPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CrudPacienteActivity.class);
                startActivityForResult(i, AtividadesCrud.ACAO_NOVO_CRUD);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Paciente p = null;
        PacienteDB db = new PacienteDB(getActivity());
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AtividadesCrud.ACAO_NOVO_CRUD) {
                p = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_PACIENTE));
                InserirPacienteTask task = new InserirPacienteTask(p);
                task.execute();
            } else if (requestCode == AtividadesCrud.ACAO_ALTERAR_CRUD) {
                p = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_PACIENTE));
                UpdatePacienteTask task = new UpdatePacienteTask(p);
                task.execute();
            }
        }
    }

    public void AtualizaListagem() {
        mPacientes.clear();
        PacienteDB db = new PacienteDB(getActivity());
        mPacientes.addAll(db.Listagem());
        mAdapter.notifyDataSetChanged();
        mQtdIntes.setText(String.valueOf(mPacientes.size()));
    }

    private class PacientesRecycleAdapter extends RecyclerView.Adapter<PacientesViewHolderAdapter> {

        private ArrayList<Paciente> mValues;

        public PacientesRecycleAdapter(Context context, ArrayList<Paciente> items) {
            mValues = items;
        }

        @Override
        public PacientesViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_paciente, parent, false);
            return new PacientesViewHolderAdapter(view);
        }

        public Paciente getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public void onBindViewHolder(final PacientesViewHolderAdapter holder, final int position) {
            holder.bind(getValueAt(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public class PacientesViewHolderAdapter extends RecyclerView.ViewHolder {
        private View context;
        @Bind(R.id.lblPaciente)
        public TextView mNomePaciente;
        @Bind(R.id.lblEndereco)
        public TextView mEndereco;
        @Bind(R.id.lblComplemento)
        public TextView mComplemento;

        @Bind(R.id.toolbarPaciente)
        public Toolbar mToolbar;

        public PacientesViewHolderAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = view;
        }

        public void bind(final Paciente paciente) {
            mNomePaciente.setText(paciente.getNome());
            mEndereco.setText(paciente.getRua() + ", " + paciente.getNumero());
            mComplemento.setText(paciente.getComplemento());

            mToolbar.getMenu().clear();
            mToolbar.inflateMenu(R.menu.crud_menu);
            mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.modificar) {
                        Intent i = new Intent(context.getContext(), CrudPacienteActivity.class);
                        i.putExtra(AtividadesCrud.CONTEXTO_CRUD, true);
                        Parcelable parcelable = Parcels.wrap(paciente);
                        i.putExtra(AtividadesCrud.OBJETO_PACIENTE, parcelable);
                        startActivityForResult(i, AtividadesCrud.ACAO_ALTERAR_CRUD);
                    } else if (id == R.id.excluir) {

                        DeletePacienteTask task = new DeletePacienteTask(paciente);
                        try {
                            task.execute().get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public class InserirPacienteTask extends AsyncTask<Void, Void, Paciente> {

        ProgressDialog progress = null;
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Paciente mPaciente;
        String msgErro = "";

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Sincronizando registro.");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
        }

        public InserirPacienteTask(Paciente paciente) {
            mPaciente = paciente;
        }

        @Override
        protected Paciente doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.0.2.2:5000/api/pacientes";
            Gson json = new GsonBuilder().create();
            RequestBody body = RequestBody.create(JSON, json.toJson(mPaciente));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = null;
            Paciente p = null;
            try {
                response = client.newCall(request).execute();
                Gson pacienteCadastrado = new GsonBuilder().create();
                p = pacienteCadastrado.fromJson(response.body().string(), Paciente.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;
        }

        @Override
        protected void onPostExecute(Paciente paciente) {
            if (progress != null) {
                progress.dismiss();
            }

            if (paciente != null) {
                PacienteDB db = new PacienteDB(getActivity());
                db.Inserir(paciente);
                AtualizaListagem();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao alterar registro, por favor tentar novamente." + msgErro, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class UpdatePacienteTask extends AsyncTask<Void, Void, Paciente> {

        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Paciente mPaciente;

        String msgErro = "";

        ProgressDialog progress = null;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Sincronizando registro.");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
        }

        public UpdatePacienteTask(Paciente paciente) {
            mPaciente = paciente;
        }

        @Override
        protected Paciente doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.0.2.2:5000/api/pacientes/" + mPaciente.getId();
            Gson json = new GsonBuilder().create();
            RequestBody body = RequestBody.create(JSON, json.toJson(mPaciente));
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .build();
            Response response = null;
            Paciente p = null;
            try {
                response = client.newCall(request).execute();
                Gson pacienteCadastrado = new GsonBuilder().create();
                p = pacienteCadastrado.fromJson(response.body().string(), Paciente.class);

            } catch (IOException e) {
                e.printStackTrace();
                msgErro = e.getMessage();
            }
            return p;
        }

        @Override
        protected void onPostExecute(Paciente paciente) {
            if (progress != null) {
                progress.dismiss();
            }

            if (paciente != null) {
                PacienteDB db = new PacienteDB(getActivity());
                db.Update(paciente);
                AtualizaListagem();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao alterar registro, por favor tentar novamente." + msgErro, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class DeletePacienteTask extends AsyncTask<Void, Void, Paciente> {

        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Paciente mPaciente;
        String msgErro = "";

        ProgressDialog progress = null;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Sincronizando registro.");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
        }

        public DeletePacienteTask(Paciente paciente) {
            mPaciente = paciente;
        }

        @Override
        protected Paciente doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.0.2.2:5000/api/pacientes/" + mPaciente.getId();
            Gson json = new GsonBuilder().create();
            RequestBody body = RequestBody.create(JSON, json.toJson(mPaciente));
            Request request = new Request.Builder()
                    .url(url)
                    .delete()
                    .build();
            Response response = null;
            Paciente p = null;
            try {
                response = client.newCall(request).execute();
                Gson pacienteCadastrado = new GsonBuilder().create();
                p = pacienteCadastrado.fromJson(response.body().string(), Paciente.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;
        }

        @Override
        protected void onPostExecute(Paciente paciente) {
            if (progress != null) {
                progress.dismiss();
            }

            if (paciente != null) {
                PacienteDB db = new PacienteDB(getActivity());
                db.Delete(mPaciente.getId());
                AtualizaListagem();
                Toast.makeText(getContext(),
                        "Deletado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao alterar registro, por favor tentar novamente." + msgErro, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
