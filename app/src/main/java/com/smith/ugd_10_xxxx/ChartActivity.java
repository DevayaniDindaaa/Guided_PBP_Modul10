package com.smith.ugd_10_xxxx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Align;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.TooltipPositionMode;
import com.smith.ugd_10_xxxx.database.DatabaseClient;
import com.smith.ugd_10_xxxx.model.FacultyCountSummary;
import com.smith.ugd_10_xxxx.model.MajorAverageGPA;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    List<FacultyCountSummary> facultydata = new ArrayList<>();
    List<MajorAverageGPA> majordata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        //get data untuk pie chart
        getFacultyCountSummary();
    }

    //fungsi untuk get data pie chart dari room
    public void getFacultyCountSummary(){
        class GetFacultyCountSummary extends AsyncTask<Void, Void, List<FacultyCountSummary>> {

            @Override
            protected List<FacultyCountSummary> doInBackground(Void... voids) {
                List<FacultyCountSummary> mahasiswaList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getFacultyCountSummary();
                return mahasiswaList;
            }

            @Override
            protected void onPostExecute(List<FacultyCountSummary> ListMahasiswa) {
                super.onPostExecute(ListMahasiswa);

                //setup chartview1
                AnyChartView ChartView1 = (AnyChartView) findViewById(R.id.chart1);
                ChartView1.setProgressBar(findViewById(R.id.progress_bar_1));
                APIlib.getInstance().setActiveAnyChartView(ChartView1);
                List<DataEntry> facultydatasets = new ArrayList<>();

                //get data dari room
//                facultydata.addAll(ListMahasiswa);
//                System.out.println(ListMahasiswa.size());
//                for (FacultyCountSummary new_mhs:ListMahasiswa
//                ) {
//                    System.out.println(new_mhs.getFakultas()+ " " + new_mhs.getJumlah());
//                };

                //creating dataset
                for (FacultyCountSummary row: ListMahasiswa
                ) {
                    facultydatasets.add(new ValueDataEntry(row.getFakultas(),row.getJumlah()));
                }

                Pie pie_chart = AnyChart.pie();
//                pie_chart.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//                    @Override
//                    public void onClick(Event event) {
//                        Toast.makeText(ChartActivity.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//                    }
//                });

                //setup pie chart yang akan ditampilkan di chartview 1
                pie_chart.data(facultydatasets);
                pie_chart.title("Persentase Jumlah Mahasiswa di UAJY Berdasarkan Fakultas");
                pie_chart.labels().position("outside");
                pie_chart.legend().title().enabled(true);
                pie_chart.legend().title()
                        .text("Fakultas")
                        .padding(0d, 0d, 10d, 0d);
                pie_chart.legend()
                        .position("center-bottom")
                        .itemsLayout(LegendLayout.HORIZONTAL)
                        .align(Align.CENTER);
                ChartView1.setChart(pie_chart);

                if (ListMahasiswa.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }

                //get data untuk bar chart
                getMajorAverageGPA();
            }
        }
        GetFacultyCountSummary get = new GetFacultyCountSummary();
        get.execute();
    }

    //fungsi untuk get data pie chart dari room
    public void getMajorAverageGPA(){
        class GetMajorAverageGPA extends AsyncTask<Void, Void, List<MajorAverageGPA>> {

            @Override
            protected List<MajorAverageGPA> doInBackground(Void... voids) {
                List<MajorAverageGPA> mahasiswaList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getMajorAverageGPA();
                return mahasiswaList;
            }

            @Override
            protected void onPostExecute(List<MajorAverageGPA> ListMahasiswa) {
                super.onPostExecute(ListMahasiswa);

                //chartview 2 setup
                AnyChartView ChartView2 = (AnyChartView) findViewById(R.id.chart2);
                ChartView2.setProgressBar(findViewById(R.id.progress_bar_2));
                APIlib.getInstance().setActiveAnyChartView(ChartView2);
                List<DataEntry> majordatasets = new ArrayList<>();

                //get data dari room
//                majordata.addAll(ListMahasiswa);
//                System.out.println(ListMahasiswa.size());
//                for (MajorAverageGPA new_mhs:ListMahasiswa
//                ) {
//                    System.out.println(new_mhs.getJurusan()+ " " + new_mhs.getIpk());
//                };

                //creating dataset
                for (MajorAverageGPA row: ListMahasiswa
                ) {
                    majordatasets.add(new ValueDataEntry(row.getJurusan(),row.getIpk()));
                }

                //setup bar chart yang akan ditampilkan di chartview 2
                Cartesian cartesian = AnyChart.column();
                Column bar_chart = cartesian.column(majordatasets);
                cartesian.animation(true);
                cartesian.title("Rata-rata IPK Mahasiswa Pada Setiap Jurusan di UAJY");
                cartesian.yScale().minimum(0d);
                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");
                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.tooltip().format("Rata-rata IPK: {%Value}");
                cartesian.interactivity().hoverMode(HoverMode.BY_X);
                cartesian.xAxis(0).title("Jurusan");
                cartesian.yAxis(0).title("Rata-rata IPK");
                ChartView2.setChart(cartesian);

                if (ListMahasiswa.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }
        GetMajorAverageGPA get = new GetMajorAverageGPA();
        get.execute();
    }
}