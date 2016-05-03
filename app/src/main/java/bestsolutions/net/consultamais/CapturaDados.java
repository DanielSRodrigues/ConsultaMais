package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;


/**
 * Created by MMS on 02/05/2016.
 */
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class CapturaDados extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_dados);

        // capturando dado
        Intent intent = getIntent();
        Long dateSelected = intent.getLongExtra("dataLongMiliseconds", 0);
        Date date = new Date(dateSelected);

        // fazendo alguma coisa com o dado capturado
        TextView txt = (TextView) findViewById(R.id.textView1);
        txt.setText(date.toString());

    }

}
