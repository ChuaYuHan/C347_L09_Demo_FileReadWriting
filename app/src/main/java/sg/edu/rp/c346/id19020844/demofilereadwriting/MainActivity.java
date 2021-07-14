package sg.edu.rp.c346.id19020844.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    // UI handlers to be defined
    Button btnWrite, btnRead;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI handlers to be defined
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        tv = findViewById(R.id.tv);

        // Internal - Folder creation
        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
        File folder = new File(folderLocation);
        if (folder.exists() == false) {
            boolean result = folder.mkdir();
            if (result == true) {
                Log.d("File Read/Write", "Folder created");
            }
        }

        // External - folder creation
//        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder";
//        File folder = new File(folderLocation);
//        if (folder.exists() == false) {
//            boolean result = folder.mkdir();
//            if (result == true) {
//                Log.d("File Read/Write", "Folder created");
//            }
//        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Internal - creating and writing file
                try {
                    folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
                    File targetFile = new File(folderLocation, "data.txt");
                    // set to:
                    // true - for appending to existing data
                    // false - for overwriting existing data
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write("test data" + "\n");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                // External - file creation and writing
//        try {
//            String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder";
//            File targetFile = new File(folderLocation, "data.txt");
//            FileWriter writer = new FileWriter(targetFile, true);
//            writer.write("Hello World" + "\n");
//            writer.flush();
//            writer.close();
//        } catch (Exception e) {
//            Toast.makeText(MainActivity.this, "Failed to write", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }


            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // read data
                String folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
                File targetFile = new File(folderLocation, "data.txt");

                if (targetFile.exists() == true) {
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);

                        String line = br.readLine();
                        while (line != null) {
                            data += line + "\n";
                            line = br.readLine();
                        }
                        tv.setText(data);

                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }
}