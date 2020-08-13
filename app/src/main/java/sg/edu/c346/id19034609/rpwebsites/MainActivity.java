package sg.edu.c346.id19034609.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCategory, spnSubCategory;
    Button btnGo;
    ArrayList<String> alSubCategory;
    ArrayAdapter<String> aaSubCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCategory = findViewById(R.id.spinnerCategory);
        spnSubCategory = findViewById(R.id.spinnerSubCategory);
        btnGo = findViewById(R.id.buttonGo);

        final String [][] sites = {
                {
                    "https://www.yahoo.com.sg/",
                        "https://www.rp.edu.sg/student-life",
                },
                {
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                }
        };

        alSubCategory = new ArrayList<>();
        aaSubCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alSubCategory);
        spnSubCategory.setAdapter(aaSubCategory);

        String[] strSubCat = getResources().getStringArray(R.array.rp_subcategory);
        alSubCategory.addAll(Arrays.asList(strSubCat));

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alSubCategory.clear();
                String[] strSubCat;
                switch (position){
                    case 0:
                        strSubCat = getResources().getStringArray(R.array.rp_subcategory);
                        alSubCategory.addAll(Arrays.asList(strSubCat));
                        break;
                    case 1:
                        strSubCat = getResources().getStringArray(R.array.soi_subcategory);
                        alSubCategory.addAll(Arrays.asList(strSubCat));
                        break;
                }
                aaSubCategory.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sites[spnCategory.getSelectedItemPosition()][spnSubCategory.getSelectedItemPosition()];

                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("url",url);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        int categoryPosition = spnCategory.getSelectedItemPosition();
        int subCategoryPosition = spnSubCategory.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int categoryPosition = prefs.getInt("category_position", 0);
        int subCategoryPosition = prefs.getInt("subcategory_position", 0);

        spnCategory.setSelection(categoryPosition);

        alSubCategory.clear();

        if (categoryPosition == 0) {
            String[] strSubCat = getResources().getStringArray(R.array.rp_subcategory);

        }
        else if (categoryPosition == 1) {
            String[] strSubCat = getResources().getStringArray(R.array.soi_subcategory);
            alSubCategory.addAll(Arrays.asList(strSubCat));
        }
        spnSubCategory.setSelection(subCategoryPosition);
    }
}