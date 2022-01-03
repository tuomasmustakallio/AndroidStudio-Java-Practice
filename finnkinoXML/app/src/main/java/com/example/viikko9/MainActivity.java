package com.example.viikko9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String selectedID;
    String selectedDate;
    ListView listView;
    ArrayAdapter viewAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initializeSpinner();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Theater selectedTheater = (Theater) parent.getItemAtPosition(position);
        selectedID = selectedTheater.getId();
        selectedDate = selectedTheater.getDate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initializeSpinner(){
        try {
            ArrayList<Theater> theaterArrayList = new ArrayList<>();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");
            for (int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    theaterArrayList.add(new Theater(element.getElementsByTagName("ID").item(0).getTextContent(), element.getElementsByTagName("Name").item(0).getTextContent()));
                    //element.getElementsByTagName("ID").item(0).getTextContent()<-theater id
                    //element.getElementsByTagName("Name").item(0).getTextContent()<-theater name
                }
            }
            Spinner theaterSpinner = findViewById(R.id.theaterSpinner);
            ArrayAdapter<Theater> theaterAdapter = new ArrayAdapter<Theater>(getApplicationContext(), R.layout.spinner_item, theaterArrayList);
            theaterAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
            theaterSpinner.setAdapter(theaterAdapter);
            theaterSpinner.setOnItemSelectedListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void search(View v){
        try{
            String scheduleString = "https://www.finnkino.fi/xml/Schedule/?area=" + selectedID + "&dt=" + selectedDate + "%3E";
            List scheduleList =  new ArrayList();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(scheduleString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    scheduleList.add(element.getElementsByTagName("Title").item(0).getTextContent());
                }
            }
            listView = (ListView)findViewById(R.id.listView);
            viewAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_item, scheduleList);
            listView.setAdapter(viewAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}