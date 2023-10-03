package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor's name: Khushi Singh","Hospital Add.: Ahiyapur", "Exp: 2 months", "Mobile: 9999999999","1000"},
            {"Doctor's name: Ayush Kumar","Hospital Add.: Akharaghat", "Exp: 2 years", "Mobile: 9999999988","2000"},
            {"Doctor's name: Jayant Newton","Hospital Add.: Chhapra", "Exp: 3 years", "Mobile: 9999999969","100"},
            {"Doctor's name: Raushan Pandey","Hospital Add.: Minapur", "Exp: 5 years", "Mobile: 8888888888","500"},
            {"Doctor's name: Jaydev Pathak","Hospital Add.: Shivaji Marg", "Exp: 2 years", "Mobile: 1431431437","10000"}
    };
    private String[][] doctor_details2 = {
            {"Doctor's name: Nitin Singh","Hospital Add.: Ahiyapur", "Exp: 2 months", "Mobile: 9999999999","1000"},
            {"Doctor's name: Raushan Kumar","Hospital Add.: Akharaghat", "Exp: 2 years", "Mobile: 9999999988","2000"},
            {"Doctor's name: Kahani Newton","Hospital Add.: Chhapra", "Exp: 3 years", "Mobile: 9999999969","100"},
            {"Doctor's name: Narendra Pandey","Hospital Add.: Minapur", "Exp: 5 years", "Mobile: 8888888888","500"},
            {"Doctor's name: Raunak Kumar","Hospital Add.: Shivaji Marg", "Exp: 2 years", "Mobile: 1431431437","10000"}
    };
    private String[][] doctor_details3 = {
            {"Doctor's name: Khushi Padmavat","Hospital Add.: Ahiyapur", "Exp: 2 months", "Mobile: 9999999999","1000"},
            {"Doctor's name: Ayush Lodha","Hospital Add.: Akharaghat", "Exp: 2 years", "Mobile: 9999999988","2000"},
            {"Doctor's name: Jayant Beluri","Hospital Add.: Chhapra", "Exp: 3 years", "Mobile: 9999999969","100"},
            {"Doctor's name: Raushan Ranjan","Hospital Add.: Minapur", "Exp: 5 years", "Mobile: 8888888888","500"},
            {"Doctor's name: Ramesh Singhania","Hospital Add.: Pune", "Exp: 2 years", "Mobile: 1431431437","10000"}
    };
    private String[][] doctor_details4 = {
            {"Doctor's name: Keshav Raj","Hospital Add.: Ahiyapur", "Exp: 2 months", "Mobile: 9996999999","1000"},
            {"Doctor's name: Kumar Shanu","Hospital Add.: Akharaghat", "Exp: 2 years", "Mobile: 2999999988","2000"},
            {"Doctor's name: Sonu Nigam","Hospital Add.: Chhapra", "Exp: 3 years", "Mobile: 7999999969","100"},
            {"Doctor's name: Khesari Yadav","Hospital Add.: Minapur", "Exp: 5 years", "Mobile: 8988888888","500"},
            {"Doctor's name: Mahi","Hospital Add.: Heartland", "Exp: 19 years", "Mobile: 7777777777","10000"}
    };
    private String[][] doctor_details5 = {
            {"Doctor's name: Vaibhav Tiwari","Hospital Add.: Jampur", "Exp: 2 months", "Mobile: 9999499999","1000"},
            {"Doctor's name: Utkarsh Deep","Hospital Add.: Akharabodh", "Exp: 2 years", "Mobile: 9999989988","2000"},
            {"Doctor's name: Aditya Aman","Hospital Add.: Chhapranagar", "Exp: 3 years", "Mobile: 9991999969","100"},
            {"Doctor's name: Beshumar aadmi","Hospital Add.: Minahills", "Exp: 5 years", "Mobile: 8882888888","500"},
            {"Doctor's name: John Sindri","Hospital Add.: Hazarigarden", "Exp: 2 years", "Mobile: 1431431437","10000"}
    };
    TextView tvTitle;
    String [][] doctor_details = {};
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    HashMap hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tvTitle = findViewById(R.id.tvLTTitle);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
        if(title.compareTo("Family Physicians")==0){
            doctor_details = doctor_details1;
        }
        if(title.compareTo("Dietician")==0){
            doctor_details = doctor_details2;
        }
        if(title.compareTo("Dentist")==0){
            doctor_details = doctor_details3;
        }
        if(title.compareTo("Surgeon")==0){
            doctor_details = doctor_details4;
        }
        if(title.compareTo("Cardiologists")==0){
            doctor_details = doctor_details5;
        }

        arrayList = new ArrayList();
        for(int i=0; i < doctor_details.length; i++){
            hashMap = new HashMap<>();
            hashMap.put("line1",doctor_details[i][0]);
            hashMap.put("line2",doctor_details[i][1]);
            hashMap.put("line3",doctor_details[i][2]);
            hashMap.put("line4",doctor_details[i][3]);
            hashMap.put("line5","Const.Fees:"+doctor_details[i][4]+"/-");
            arrayList.add(hashMap);

        }
        simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lvDoctorDetail = findViewById(R.id.etLabDetails);
        lvDoctorDetail.setAdapter(simpleAdapter);

        lvDoctorDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DoctorDetails.this,BookAppointment.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2",doctor_details[position][0]);
                intent.putExtra("text3",doctor_details[position][1]);
                intent.putExtra("text4",doctor_details[position][3]);
                intent.putExtra("text5",doctor_details[position][4]);
                startActivity(intent);

            }
        });
    }
}