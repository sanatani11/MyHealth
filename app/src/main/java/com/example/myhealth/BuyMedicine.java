package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicine extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise-D3 1000IU Capsule","","","",
                            "50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule","","","",
                            "305"},
                    {"Vitamin B Complex Capsules","","","",
                            "448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule","","","",
                            "539"},
                    {"Dolo 650 Tablet","","","",
                            "30"},
                    {"Crocin 658 Advance Tablet","","","",
                            "50"},
                    {"Strepsils Medicated Lozenges for Sore Throat","","","",
                            "40"},
                    {"Tata 1mg Calcium + Vitamin D3","","","",
                            "30"},
                    {"Feronia -XT Tablet", "","","","",
                            "130"},
            };
    private String[] package_details =

    {
        "Building and keeping the bones & teeth strong\n" +
                "Reducing Fatigue/stress and muscular pains\n" +
                "Boosting immunity and increasing resistance against infection",
                "Chromium is an essential trace mineral that plays an important role in helping insulin regular",
            "Provides relief from vitamin B deficiencies\n" +
        "Helps in formation of red blood cells\n" + "Maintains healthy nervous system",
                "It promotes health as well as skin benefit.\n" +
                        "It helps reduce skin blemish and pigmentation.\n" +
                        "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
                "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical mess","Helps relieve fever and bring down a high temperature \n " +
        "Suitable for people with a heart condition or high blood pressure",
                "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n"+
        "Provides a warm and comforting feeling during sore throat",
                "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                        "Promotes mobility and flexibility of joints",
                "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    };
    HashMap hashMap;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    ListView lvBM;
    Button btnBMGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnBMGoToCart = findViewById(R.id.btnAddToCart);
        lvBM = findViewById(R.id.lvBM);

        btnBMGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicine.this,CartBuyMedicine.class));
            }
        });

        arrayList = new ArrayList();
        for (int i=0;i<packages.length;i++){
            hashMap = new HashMap<>();
            hashMap.put("line1", packages[i][0]);
            hashMap.put("line2", packages[i][1]);
            hashMap.put("line3", packages[i][2]);
            hashMap.put("line4", packages[i][3]);
            hashMap.put("line5", "Total Cost: "+packages[i][4]+"/");
            arrayList.add(hashMap);
        }
        simpleAdapter = new SimpleAdapter( this, arrayList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5" },
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lvBM.setAdapter(simpleAdapter);
        lvBM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuyMedicine.this,BuyMedicineDetails.class);
                intent.putExtra("text1",packages[position][0]);
                intent.putExtra("text2",package_details[position]);
                intent.putExtra("text3",packages[position][4]);
                startActivity(intent);

            }
        });
    }
}