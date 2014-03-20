package br.com.zara.horarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.zara.horarios.R;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GravarHorarioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gravar_horario);
		gravarHorarioNoArquivo();

		this.finish();
	}
	
	public void gravarHorarioNoArquivo(){
	    try
	    {
	    	String sBody;
	    	Calendar cal = Calendar.getInstance();
	    	Date date = cal.getTime();

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
	        
	        Toast.makeText(GravarHorarioActivity.this, "Arquivo salvo!",Toast.LENGTH_SHORT).show();
	    }
	    catch(IOException e)
	    {
	    	Toast.makeText(this, "Erro gerando o arquivo.", Toast.LENGTH_SHORT).show();
	         e.printStackTrace();
	         e.getMessage();
	    }
	    
	    //MediaScannerConnection.scanFile(this, paths, mimeTypes, callback);
	    
	   }

	

	

	

}
