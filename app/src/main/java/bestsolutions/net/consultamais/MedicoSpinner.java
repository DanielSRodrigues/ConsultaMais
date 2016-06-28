package bestsolutions.net.consultamais;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bestsolutions.net.consultamais.entidades.Medico;

public class MedicoSpinner extends ArrayAdapter<Medico> {
    private Activity mContext;
    private ArrayList<Medico> mMedicos;

    public MedicoSpinner(Activity context, int resource, int textview, ArrayList<Medico> medicos) {
        super(context, resource, textview, medicos);
        mContext = context;
        mMedicos = medicos;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            row = inflater.inflate(R.layout.spinner_medico_layout, parent, false);
        }

        Medico item = mMedicos.get(position);
        if (item != null) {
            TextView text1 = (TextView) row.findViewById(R.id.NomeMedico);
            text1.setText(item.getNome());
            TextView text2 = (TextView) row.findViewById(R.id.Especialidade);
            text2.setText(item.getEspecialidade());
        }

        return row;
    }
}
