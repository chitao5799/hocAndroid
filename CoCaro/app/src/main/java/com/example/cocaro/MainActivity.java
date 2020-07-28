package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
    ArrayList<clsTextView > listTextView;
    TextView txtCurrentPlayer,txtCountDownTime;
    boolean isXplayer;
    int totalOVuong=266,numberOfColumn=14,numberOfRow=totalOVuong/numberOfColumn;
    int chessBoard[][]=new int[numberOfRow][numberOfColumn];
    int currentRow=-1,currentColumn=-1;
    Timer timer;
    CountDownTimer waitTimer;
    int secondsPerPlayer=1*60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addData();
        txtCurrentPlayer.setText("X");
        isXplayer=true;

        gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l)//index bắt đầu từ 0
            {
                if(waitTimer != null) {
                    waitTimer.cancel();
                    waitTimer = null;
                }
                CountDownTime();

                if(isXplayer)
                {
                    currentRow=index/numberOfColumn;
                    currentColumn=index%numberOfColumn;
                    if(chessBoard[currentRow][currentColumn]==66 || chessBoard[currentRow][currentColumn]==88)
                    {
                        Toast.makeText(MainActivity.this,"ô này đã được đánh, vui lòng đánh ô khác!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    chessBoard[currentRow][currentColumn]=66;//66 đại diện cho x

                    listTextView.set(index,new clsTextView("X","#ff0000"));
                    adapterGridView.notifyDataSetChanged();
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("O");

                    if(isEndGame(66,currentRow,currentColumn))
                    {
                        EndGame(66);
                        return;
                    }
                }
                else
                {
                    currentRow=index/numberOfColumn;
                    currentColumn=index%numberOfColumn;
                    if(chessBoard[currentRow][currentColumn]==66 || chessBoard[currentRow][currentColumn]==88)
                    {
                        Toast.makeText(MainActivity.this,"ô này đã được đánh, vui lòng đánh ô khác!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    chessBoard[currentRow][currentColumn]=88;//88 đại diện cho O


                    listTextView.set(index,new clsTextView("O","#00cc00"));
                    adapterGridView.notifyDataSetChanged();
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("X");

                    if(isEndGame(88,currentRow,currentColumn))
                    {
                        EndGame(88);
                        return;
                    }
                }

            }
        });
    }

    private void addData()
    {
        for (int i=0;i<totalOVuong;i++)
            listTextView.add(new clsTextView("","#ff0000"));
        adapterGridView.notifyDataSetChanged();
    }

    private void init() {
        txtCurrentPlayer=(TextView)findViewById(R.id.txtCurrentPlayer);
        isXplayer=true;
        gridBanCo=(GridView)findViewById(R.id.gridBanCo);
        txtCountDownTime=(TextView)findViewById(R.id.txtCountDownTime);

        listTextView=new ArrayList<>();
        adapterGridView=new AdapterGridViewCustom(this,R.layout.o_caro,listTextView);
        gridBanCo.setAdapter(adapterGridView);
        
        int phut= secondsPerPlayer/60;
        int giay=secondsPerPlayer%60;
        String strMinute=phut<10?"0"+phut:phut+"";
        String strSecond=giay<10?"0"+giay:giay+"";
        txtCountDownTime.setText(strMinute+":"+strSecond);
    }

    private  void EndGame(int quanco)
    {
        if(quanco==88)
            Toast.makeText(MainActivity.this,"O thắng",Toast.LENGTH_SHORT).show();
        else if(quanco==66)
            Toast.makeText(MainActivity.this,"X thắng",Toast.LENGTH_SHORT).show();
        else  Toast.makeText(MainActivity.this,"kết thúc game",Toast.LENGTH_SHORT).show();

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
    }

    private boolean isEndGame(int quanCo,int currentRow,int currentColumn)
    {
        return isEndHorizontal(quanCo,currentRow,currentColumn) || isEndVertical(quanCo,currentRow,currentColumn) ||
                isEndPrimary(quanCo,currentRow,currentColumn) || isEndSubsidiary(quanCo,currentRow,currentColumn);
    }

    private boolean isEndHorizontal(int quanCo,int currentRow,int currentColumn)
    {
        int countLeft=0,countRight=0;
        for(int i=currentColumn ; i>=0 ; i--)//đếm bên trái của ô hiện tại
        {
            if(chessBoard[currentRow][i]==quanCo)
            {
                countLeft++;
            }
            else break;
        }
        for(int i=currentColumn+1 ; i<numberOfColumn ; i++)//đếm bên phải của ô hiện tại
        {
            if(chessBoard[currentRow][i]==quanCo)
            {
                countRight++;
            }
            else break;
        }
        return countLeft+countRight==5;
    }

    private boolean isEndVertical(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        for(int i=currentRow ; i>=0 ; i--)//đếm phía trên của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==quanCo)
            {
                countAbove++;
            }
            else break;
        }
        for(int i=currentRow+1 ; i<numberOfRow ; i++)//đếm phía dưới của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==quanCo)
            {
                countBelow++;
            }
            else break;
        }
        return countAbove+countBelow==5;
    }

    private boolean isEndPrimary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        for(int i=0 ; i<=currentColumn ; i++)//đếm đường chéo chính phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn-i<0)
                break;
            if(chessBoard[currentRow-i][currentColumn-i]==quanCo)
            {
                countAbove++;
            }
            else break;
        }
        for(int i=1 ; i<numberOfColumn - currentColumn ; i++)//đếm đường chéo chính phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow+i][currentColumn+i]==quanCo)
            {
                countBelow++;
            }
            else break;
        }
        return countAbove+countBelow==5;
    }

    private boolean isEndSubsidiary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        for(int i=0 ; i<=currentColumn ; i++)//đếm đường chéo phụ phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow-i][currentColumn+i]==quanCo)
            {
                countAbove++;
            }
            else break;
        }
        for(int i=1 ; i<numberOfColumn - currentColumn ; i++)//đếm đường chéo phụ phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn-i<0)
                break;
            if(chessBoard[currentRow+i][currentColumn-i]==quanCo)
            {
                countBelow++;
            }
            else break;
        }
        return countAbove+countBelow==5;
    }

   /* private class MyTask extends TimerTask {
        @Override
        public void run() {
           int phut=secondsPerPlayer/60;
           int giay=secondsPerPlayer%60;
           txtCountDownTime.setText(phut+":"+giay);
           secondsPerPlayer-=1;
           if(secondsPerPlayer==0)
                timer.cancel();
        }
    }*/

    private void CountDownTime()  {
       // timer=new Timer("Timer");
      //  timer.schedule(new MyTask(), 0, 1000);

      /*  timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                int phut=secondsPerPlayer/60;
                int giay=secondsPerPlayer%60;
                txtCountDownTime.setText(phut+":"+giay);
                secondsPerPlayer-=1;
                if(secondsPerPlayer==0)
                    timer.cancel();
            }
        }, 0, 1000);*/


        waitTimer = new CountDownTimer(secondsPerPlayer*1000+1000, 1000)// + thêm 1s để chạy hàm này set lại thời gian cho view là 00:00
        {
            int thoigian=secondsPerPlayer;
            public void onTick(long millisUntilFinished) {
                //called every 1000 milliseconds, which could be used to
                //send messages or some other action
                int phut= thoigian/60;
                int giay=thoigian%60;
                String strMinute=phut<10?"0"+phut:phut+"";
                String strSecond=giay<10?"0"+giay:giay+"";

                txtCountDownTime.setText(strMinute+":"+strSecond);
                thoigian-=1;
            }

            public void onFinish() {
                //After secondPerPlayer milliseconds (60 sec) finish current
                //if you would like to execute something when time finishes
                if(isXplayer)
                    EndGame(88);//nếu hết thời gian mà đang là lượt X đánh thì O thắng.
                else
                    EndGame(66);
            }
        }.start();
    }
    /*class ViewTitleThread extends Thread {

        public ViewTitleThread() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    int phut=secondsPerPlayer/60;
                    int giay=secondsPerPlayer%60;
                    txtCountDownTime.setText(phut+":"+giay);
                    secondsPerPlayer-=1;
                    if(secondsPerPlayer==0)
                        break;
                    sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }*/

}