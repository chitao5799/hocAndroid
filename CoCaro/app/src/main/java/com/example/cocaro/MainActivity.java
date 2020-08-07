package com.example.cocaro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
    ArrayList<clsTextView > listTextView;
    TextView txtCurrentPlayer,txtCountDownTime;
    ImageView imgNewGame,imgDanhLai,imgMusic;

    boolean isXplayer,isClickNewGame=false,isPlayMusic=false,isWantNewGame=false;
    int totalOVuong=266,numberOfColumn=14,numberOfRow=totalOVuong/numberOfColumn,oDaDanh=0;//266
    int chessBoard[][]=new int[numberOfRow][numberOfColumn];
    int currentRow=-1,currentColumn=-1;
    CountDownTimer waitTimer;
    int secondsPerPlayer=1*60, thoigian=secondsPerPlayer;
    Stack stack_O_Da_Danh=new Stack();
    MediaPlayer mediaPlayer=null;

    SQLiteDatabase db;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
                currentRow=index/numberOfColumn;
                currentColumn=index%numberOfColumn;

                if(chessBoard[currentRow][currentColumn]==66 || chessBoard[currentRow][currentColumn]==88)
                {
                    Toast.makeText(MainActivity.this,"ô này đã được đánh, vui lòng đánh ô khác!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(waitTimer != null) {
                    waitTimer.cancel();
                    waitTimer = null;
                }
                CountDownTime();

                if(isXplayer)
                {
                    chessBoard[currentRow][currentColumn]=66;//66 đại diện cho x
                    listTextView.set(index,new clsTextView("X","#ff0000"));
                    adapterGridView.notifyDataSetChanged();
                    stack_O_Da_Danh.push(new Integer(index));
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
                    chessBoard[currentRow][currentColumn]=88;//88 đại diện cho O
                    listTextView.set(index,new clsTextView("O","#00cc00"));
                    adapterGridView.notifyDataSetChanged();
                    stack_O_Da_Danh.push(new Integer(index));
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("X");

                    if(isEndGame(88,currentRow,currentColumn))
                    {
                        EndGame(88);
                        return;
                    }
                }
                oDaDanh++;
                if(oDaDanh>=totalOVuong)
                {
                    EndGame(44);//44 đại diện cho game hòa
                    return;
                }
            }
        });
        imgNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGame();
            }
        });
        imgDanhLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhLai();
            }
        });
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayMusic();
            }
        });
    }

    private void NewGame()
    {
        isClickNewGame=true;
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.icongame);
        alertDialog.setMessage("Bạn có chắc chắn muốn thoát khỏi trận đấu này?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // Intent intent=new Intent(MainActivity.this,MainActivity.class);
               // startActivity(intent);

                mediaPlayer.pause();
                mediaPlayer=null;
                isWantNewGame=true;
                onBackPressed();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CountDownTime();
            }
        });
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        if(isWantNewGame)
        {
            super.onBackPressed();
        }
        else
        {
            NewGame();
        }
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
        imgNewGame=(ImageView)findViewById(R.id.imgNewGame);
        imgDanhLai=(ImageView)findViewById(R.id.imgDanhLai);
        imgMusic=(ImageView)findViewById(R.id.imgMusic);

        Intent intent=getIntent();
        secondsPerPlayer=intent.getIntExtra("timePerPlay",1)*60;
        thoigian=secondsPerPlayer;
        isPlayMusic=intent.getBooleanExtra("isPlayMusic",false);

        listTextView=new ArrayList<>();
        adapterGridView=new AdapterGridViewCustom(this,R.layout.o_caro,listTextView);
        gridBanCo.setAdapter(adapterGridView);

        int phut= secondsPerPlayer/60;
        int giay=secondsPerPlayer%60;
        String strMinute=phut<10?"0"+phut:phut+"";
        String strSecond=giay<10?"0"+giay:giay+"";
        txtCountDownTime.setText(strMinute+":"+strSecond);

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.despacito);
        mediaPlayer.setLooping(true);
        if(isPlayMusic)
        {
            mediaPlayer.start();
        }
        else {
            imgMusic.setImageResource(R.drawable.mute);
        }
    }
    private void PlayMusic()
    {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            imgMusic.setImageResource(R.drawable.mute);
        }
        else {
            mediaPlayer.start();
            imgMusic.setImageResource(R.drawable.loaloa);
        }
    }
    private  void EndGame(int quanco)
    {
        if(quanco==88)
        {
            ghiFile(88);
        }
        else if(quanco==66)
        {
            ghiFile(66);
        }
        else
        {
            ghiFile(44);
        }
        DialogEndGame(quanco);

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
    }
    private void DanhLai()
    {
        if(stack_O_Da_Danh.isEmpty())
        {
            Toast.makeText(MainActivity.this,"bàn cờ chưa đánh quân cờ nào!",Toast.LENGTH_LONG).show();
            return;
        }
        Integer index= (Integer) stack_O_Da_Danh.pop();
        listTextView.set(index,new clsTextView("","#000000"));
        adapterGridView.notifyDataSetChanged();
        chessBoard[index/numberOfColumn][index%numberOfColumn]=0;//giá trị mặc định của int là 0
        if(isXplayer)//đến lượt X đánh thì lượt vừa mói đánh là O
        {
            txtCurrentPlayer.setText("O");
            isXplayer=!isXplayer;
        }
        else{
            txtCurrentPlayer.setText("X");
            isXplayer=!isXplayer;
        }

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        CountDownTime();
    }
    private String getNowDateTime()
    {
        int nam=calendar.get(Calendar.YEAR);
        int thang=calendar.get(Calendar.MONTH);
        int ngay=calendar.get(Calendar.DATE);
        int gio=calendar.get(Calendar.HOUR_OF_DAY);//trả về giời định dạng 24 giờ
        int phut=calendar.get(Calendar.MINUTE);
        int giay=calendar.get(Calendar.SECOND);

        calendar.set(nam,thang,ngay,gio,phut,giay); //set lại ngày tháng năm hiện tại của calender để dùng hàm getTime() phía dưới
        SimpleDateFormat dinhDangNgay=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dinhDangNgay.format(calendar.getTime());

    }
    private void ghiFile(int winner)
    {
        String strWinner="";
        if(winner==66)
            strWinner="X thắng";
        else if(winner==88)
            strWinner="O thắng";
        else strWinner="Hòa";

        db=openOrCreateDatabase("carohistory.db",MODE_PRIVATE,null);
        String sql="Insert into tblcaro(time,winner) values ('"+getNowDateTime()+"','"+strWinner+"');";
        db.execSQL(sql);
        db.close();
    }
    private void DialogEndGame(int quanCoWin)
    {
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//bỏ title của dialog, dòng này phải nằm trên dòng setContentView
        dialog.setContentView(R.layout.layout_end_game);
        //dialog.setTitle("form đăng nhập");//title của dialog thì 1 số máy có 1 số máy ko có
        dialog.setCanceledOnTouchOutside(false);//true - mặc định: thì khi click ra ngoài dialog sẽ tự đóng dialog
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //dialog bằng 18/20 chiều rộng màn hình, 6/7 chiều cao màn hình
        dialog.getWindow().setLayout((18 * width)/20, (6* height)/7);

        TextView winer;
        ImageView btnNewGame,btnHome;
        winer=(TextView) dialog.findViewById(R.id.txtThang);
        btnNewGame=(ImageView)dialog.findViewById(R.id.imgNewGameInLaoutEndGame);
        btnHome=(ImageView)dialog.findViewById(R.id.imgHomeInLayoutEndGame);

        if(quanCoWin==88)
        {
            winer.setText("O thắng");
        }
        else if(quanCoWin==66)
        {
            winer.setText("X thắng");

        }
        else if(quanCoWin==44)
        {
            winer.setText("Hòa");

        }

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                mediaPlayer=null;
                isWantNewGame=true;
                onBackPressed();

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                mediaPlayer=null;
               Intent intent=new Intent(MainActivity.this,TrangChu.class);
               startActivity(intent);
               finish();

            }
        });

        dialog.show();
    }
    private boolean isEndGame(int quanCo,int currentRow,int currentColumn)
    {
        return isEndHorizontal(quanCo,currentRow,currentColumn) || isEndVertical(quanCo,currentRow,currentColumn) ||
            isEndPrimary(quanCo,currentRow,currentColumn) || isEndSubsidiary(quanCo,currentRow,currentColumn);
    }

    private boolean isEndHorizontal(int quanCo,int currentRow,int currentColumn)
    {
        int countLeft=0,countRight=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        for(int i=currentColumn ; i>=0 ; i--)//đếm bên trái của ô hiện tại và cả ô hiện tại
        {
            if(chessBoard[currentRow][i]==quanCo)
            {
                countLeft++;
            }
            else
            {
                if((chessBoard[currentRow][i]==66 && quanCo==88) ||
                        (chessBoard[currentRow][i]==88 && quanCo==66))
                    isBlockedAbove=true;
                break;
            }
        }
        for(int i=currentColumn+1 ; i<numberOfColumn ; i++)//đếm bên phải của ô hiện tại
        {
            if(chessBoard[currentRow][i]==quanCo)
            {
                countRight++;
            }
            else
            {
                if((chessBoard[currentRow][i]==66 && quanCo==88) ||
                        (chessBoard[currentRow][i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        if(isBlockedAbove || isBlockedBelove)
            return countLeft+countRight>=5;
        else if (!isBlockedAbove && !isBlockedBelove)
            return countLeft+countRight>=4;
        else  return countLeft+countRight>=5;
    }

    private boolean isEndVertical(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        for(int i=currentRow ; i>=0 ; i--)//đếm phía trên của ô hiện tại và cả ô hiện tại
        {
            if(chessBoard[i][currentColumn]==quanCo)
            {
                countAbove++;
            }
            else {
                if((chessBoard[i][currentColumn]==66 && quanCo==88) ||
                        (chessBoard[i][currentColumn]==88 && quanCo==66))
                    isBlockedAbove=true;
                break;
            }
        }
        for(int i=currentRow+1 ; i<numberOfRow ; i++)//đếm phía dưới của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==quanCo)
            {
                countBelow++;
            }
            else {
                if((chessBoard[i][currentColumn]==66 && quanCo==88) ||
                        (chessBoard[i][currentColumn]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        if(isBlockedAbove || isBlockedBelove)
            return countAbove+countBelow>=5;
        else if (!isBlockedAbove && !isBlockedBelove)
            return countAbove+countBelow>=4;
        else  return countAbove+countBelow>=5;
    }

    private boolean isEndPrimary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        for(int i=0 ; i<=currentColumn ; i++)//đếm đường chéo chính phía trên của ô hiện tại và cả ô hiện tại
        {
            if(currentRow-i<0 || currentColumn-i<0)
                break;
            if(chessBoard[currentRow-i][currentColumn-i]==quanCo)
            {
                countAbove++;
            }
            else {
                if((chessBoard[currentRow-i][currentColumn-i]==66 && quanCo==88) ||
                        (chessBoard[currentRow-i][currentColumn-i]==88 && quanCo==66))
                    isBlockedAbove=true;
                break;
            }
        }
        for(int i=1 ; i<numberOfColumn - currentColumn ; i++)//đếm đường chéo chính phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow+i][currentColumn+i]==quanCo)
            {
                countBelow++;
            }
            else {
                if((chessBoard[currentRow+i][currentColumn+i]==66 && quanCo==88) ||
                        (chessBoard[currentRow+i][currentColumn+i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        if(isBlockedAbove || isBlockedBelove)
            return countAbove+countBelow>=5;
        else if (!isBlockedAbove && !isBlockedBelove)
            return countAbove+countBelow>=4;
        else  return countAbove+countBelow>=5;
    }

    private boolean isEndSubsidiary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        for(int i=0 ; i<=currentRow ; i++)//đếm đường chéo phụ phía trên của ô hiện tại và cả ô hiện tại
        {
            if(currentRow-i<0 || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow-i][currentColumn+i]==quanCo)
            {
                countAbove++;
            }
            else {
                if((chessBoard[currentRow-i][currentColumn+i]==66 && quanCo==88) ||
                        (chessBoard[currentRow-i][currentColumn+i]==88 && quanCo==66))
                    isBlockedAbove=true;
                break;
            }
        }
        for(int i=1 ; i<=currentColumn ; i++)//đếm đường chéo phụ phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn-i<0)
                break;
            if(chessBoard[currentRow+i][currentColumn-i]==quanCo)
            {
                countBelow++;
            }
            else {
                if((chessBoard[currentRow+i][currentColumn-i]==66 && quanCo==88) ||
                        (chessBoard[currentRow+i][currentColumn-i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        if(isBlockedAbove || isBlockedBelove)
            return countAbove+countBelow>=5;
        else if (!isBlockedAbove && !isBlockedBelove)
            return countAbove+countBelow>=4;
        else  return countAbove+countBelow>=5;
    }

    private void CountDownTime()  {
        if(isClickNewGame)
        {
            thoigian=thoigian;
            isClickNewGame=false;
        }

        else
        {
            thoigian=secondsPerPlayer;
        }

        waitTimer = new CountDownTimer(thoigian*1000+1000, 1000)// + thêm 1s để chạy hàm này set lại thời gian cho view là 00:00
        {

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

}