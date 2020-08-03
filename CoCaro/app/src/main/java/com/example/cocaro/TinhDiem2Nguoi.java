package com.example.cocaro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TinhDiem2Nguoi extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
    ArrayList<clsTextView > listTextView;
    TextView txtCurrentPlayer,txtCountDownTime,txtScoreX,txtScoreO;
    ImageView imgNewGame,imgDanhLai,imgMusic;
    boolean isXplayer,isClickNewGame=false,isPlayMusic=false,isWantNewGame=false;
    int totalOVuong=266,numberOfColumn=14,numberOfRow=totalOVuong/numberOfColumn,oDaDanh=0;//266
    int chessBoard[][]=new int[numberOfRow][numberOfColumn];
    int currentRow=-1,currentColumn=-1;
    CountDownTimer waitTimer;
    int secondsPerPlayer=1*60, thoigian=secondsPerPlayer;
    int scoreX=0,scoreO=0;
    MediaPlayer mediaPlayer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_diem2_nguoi);

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
                if(chessBoard[currentRow][currentColumn]==66 || chessBoard[currentRow][currentColumn]==88 ||
                        chessBoard[currentRow][currentColumn]==-1)
                {
                    Toast.makeText(TinhDiem2Nguoi.this,"ô này đã được đánh, vui lòng đánh ô khác!",Toast.LENGTH_SHORT).show();
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
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("O");

                    if(isEndGame(66,currentRow,currentColumn))
                    {
                        scoreX++;
                        txtScoreX.setText(scoreX+"");
                        Toast.makeText(TinhDiem2Nguoi.this,"X ghi điểm",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    chessBoard[currentRow][currentColumn]=88;//88 đại diện cho O

                    listTextView.set(index,new clsTextView("O","#00cc00"));
                    adapterGridView.notifyDataSetChanged();
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("X");

                    if(isEndGame(88,currentRow,currentColumn))
                    {
                        scoreO++;
                        txtScoreO.setText(""+scoreO);
                        Toast.makeText(TinhDiem2Nguoi.this,"O ghi điểm",Toast.LENGTH_LONG).show();
                    }
                }
                oDaDanh++;
                if(oDaDanh>=totalOVuong)
                {
                    if(scoreX>scoreO)
                        EndGame(66);
                    else
                        if(scoreX<scoreO)
                            EndGame(88);
                        else
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
                Toast.makeText(TinhDiem2Nguoi.this,"Chức năng đánh lại hiện không khả dụng khi chơi tính điểm",
                        Toast.LENGTH_LONG).show();
            }
        });
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayMusic();
            }
        });
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
    private void NewGame()
    {
        isClickNewGame=true;
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(TinhDiem2Nguoi.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.icongame);
        alertDialog.setMessage("Bạn có chắc chắn muốn dừng trận đấu này ?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Intent intent=new Intent(TinhDiem2Nguoi.this,TinhDiem2Nguoi.class);
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
        txtCurrentPlayer=(TextView)findViewById(R.id.txtCurrentPlayer2);
        isXplayer=true;
        gridBanCo=(GridView)findViewById(R.id.gridBanCo2);
        txtCountDownTime=(TextView)findViewById(R.id.txtCountDownTime2);
        imgNewGame=(ImageView)findViewById(R.id.imgNewGame2);
        txtScoreX=(TextView)findViewById(R.id.txtScoreX);
        txtScoreO=(TextView)findViewById(R.id.txtScoreO); 
        imgDanhLai=(ImageView)findViewById(R.id.imgDanhLai2);
        imgMusic=(ImageView)findViewById(R.id.imgMusic2);

        txtScoreX.setText("0");
        txtScoreO.setText("0");

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

        mediaPlayer= MediaPlayer.create(TinhDiem2Nguoi.this,R.raw.despacito);
        mediaPlayer.setLooping(true);
        if(isPlayMusic)
        {
            mediaPlayer.start();
        }
        else {
            imgMusic.setImageResource(R.drawable.mute);
        }
    }

    private  void EndGame(int quanco)
    {
        DialogEndGame(quanco);

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
    }
    private void DialogEndGame(int quanCoWin)
    {
        final Dialog dialog=new Dialog(TinhDiem2Nguoi.this);
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
                Intent intent=new Intent(TinhDiem2Nguoi.this,TrangChu.class);
                startActivity(intent);
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
        int nhungOThang[]=new int[numberOfColumn];
        int index=0;
        for(int i=currentColumn ; i>=0 ; i--)//đếm bên trái của ô hiện tại
        {
            if(chessBoard[currentRow][i]==quanCo)
            {
                countLeft++;
                nhungOThang[index]=currentRow*numberOfColumn+i;
                index++;
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
                nhungOThang[index]=currentRow*numberOfColumn+i;
                index++;
            }
            else
            {
                if((chessBoard[currentRow][i]==66 && quanCo==88) ||
                        (chessBoard[currentRow][i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        //xét thắng thua.
        if(isBlockedAbove || isBlockedBelove)
        {
            if(countLeft+countRight>=5)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else if (!isBlockedAbove && !isBlockedBelove)
        {
            if(countLeft+countRight>=4)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else  return false;
    }

    private boolean isEndVertical(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int nhungOThang[]=new int[numberOfColumn];
        int index=0;
        for(int i=currentRow ; i>=0 ; i--)//đếm phía trên của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==quanCo)
            {
                countAbove++;
                nhungOThang[index]=i*numberOfColumn+currentColumn;
                index++;
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
                nhungOThang[index]=i*numberOfColumn+currentColumn;
                index++;
            }
            else {
                if((chessBoard[i][currentColumn]==66 && quanCo==88) ||
                        (chessBoard[i][currentColumn]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        //xét thắng thua.
        if(isBlockedAbove || isBlockedBelove)
        {
            if(countAbove+countBelow>=5)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else if (!isBlockedAbove && !isBlockedBelove)
        {
            if(countAbove+countBelow>=4)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else  return false;
    }

    private boolean isEndPrimary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int nhungOThang[]=new int[numberOfColumn];
        int index=0;
        for(int i=0 ; i<=currentColumn ; i++)//đếm đường chéo chính phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn-i<0)
                break;
            if(chessBoard[currentRow-i][currentColumn-i]==quanCo)
            {
                countAbove++;
                nhungOThang[index]=(currentRow-i)*numberOfColumn+(currentColumn-i);
                index++;
            }
            else {
                if((chessBoard[currentRow-i][currentColumn-i]==66 && quanCo==88) ||
                        (chessBoard[currentRow-i][currentColumn-i]==88 && quanCo==66))
                    isBlockedAbove=true;
                break;
            }
        }
        for(int i=1 ; i<=numberOfColumn-currentColumn; i++)//đếm đường chéo chính phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow+i][currentColumn+i]==quanCo)
            {
                countBelow++;
                nhungOThang[index]=(currentRow+i)*numberOfColumn+(currentColumn+i);
                index++;
            }
            else {
                if((chessBoard[currentRow+i][currentColumn+i]==66 && quanCo==88) ||
                        (chessBoard[currentRow+i][currentColumn+i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        //xét thắng thua.
        if(isBlockedAbove || isBlockedBelove)
        {
            if(countAbove+countBelow>=5)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else if (!isBlockedAbove && !isBlockedBelove)
        {
            if(countAbove+countBelow>=4)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else  return false;
    }

    private boolean isEndSubsidiary(int quanCo,int currentRow,int currentColumn)
    {
        int countAbove=0,countBelow=0;
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int nhungOThang[]=new int[numberOfColumn];
        int index=0;
        for(int i=0 ; i<=currentRow ; i++)//đếm đường chéo phụ phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow-i][currentColumn+i]==quanCo)
            {
                countAbove++;
                nhungOThang[index]=(currentRow-i)*numberOfColumn+(currentColumn+i);
                index++;
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
                nhungOThang[index]=(currentRow+i)*numberOfColumn+(currentColumn-i);
                index++;
            }
            else {
                if((chessBoard[currentRow+i][currentColumn-i]==66 && quanCo==88) ||
                        (chessBoard[currentRow+i][currentColumn-i]==88 && quanCo==66))
                    isBlockedBelove=true;
                break;
            }
        }
        //xét thắng thua.
        if(isBlockedAbove || isBlockedBelove)
        {
            if(countAbove+countBelow>=5)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else if (!isBlockedAbove && !isBlockedBelove)
        {
            if(countAbove+countBelow>=4)
            {
                for(int y=0;y<index;y++)
                {
                    chessBoard[nhungOThang[y] / numberOfColumn][nhungOThang[y] % numberOfColumn] = -1;//-1 thể hiện những ô ko được xét thắng thua
                    listTextView.set(nhungOThang[y],new clsTextView("i","#000000"));
                    adapterGridView.notifyDataSetChanged();
                }
                return true;
            }
            else return false;
        }
        else  return false;
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