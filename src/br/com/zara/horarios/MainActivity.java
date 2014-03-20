package br.com.zara.horarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.zara.horarios.R;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity {
	Button btnGravar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnGravar = (Button) findViewById(R.id.btnGravar);
		btnGravar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				gravarHorarioNoArquivo();
			}
		});
	}
	
	public void gravarHorarioNoArquivo()
	{
	    try
	    {
	    	String sBody;
	    	Calendar cal = Calendar.getInstance();
	    	Date date = cal.getTime();

	    	//Formatação antiga e não ideal
	    	//sBody = cal.get(Calendar.YEAR) + ";" + (cal.get(Calendar.MONTH)+1) + ";" + cal.get(Calendar.DAY_OF_MONTH) + ";" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE)+"\r\n";
	    	
	    	//Formata a data
	    	//DateFormat formataData = DateFormat.getDateInstance();
	    	//System.out.println("Data atual com formatação: "+ formataData.format(date));
	    	//Formata Hora
	    	//DateFormat hora = DateFormat.getTimeInstance();
	    	//System.out.println("Hora formatada: "+hora.format(date));
	    	//Formata Data e Hora 
	    	//DateFormat dtHora = DateFormat.getDateTimeInstance();
	    	//System.out.println(dtHora.format(date));
	    	//sBody = dtHora.format(date);
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy;MM;dd;HH:mm");
	    	sBody = sdf.format(date)+"\r\n";

	        File root = new File(Environment.getExternalStorageDirectory(), "Horarios");
	        if (!root.exists()) {
	            root.mkdirs();
	        }

	        File gpxfile = new File(root, cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+".txt");
	        FileWriter writer = new FileWriter(gpxfile,true);
	        writer.append(sBody);
	        writer.flush();
	        writer.close();
	        
	        MediaScannerConnection.scanFile(this, new String[] { gpxfile.getAbsolutePath() }, null, null);
	        
	        Toast.makeText(MainActivity.this, "Arquivo salvo!",Toast.LENGTH_SHORT).show();
	    }
	    catch(IOException e)
	    {
	    	Toast.makeText(this, "Erro gerando o arquivo.", Toast.LENGTH_SHORT).show();
	         e.printStackTrace();
	         e.getMessage();
	    }
	}
}
