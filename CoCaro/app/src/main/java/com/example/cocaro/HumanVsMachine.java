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

public class HumanVsMachine extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
    ArrayList<clsTextView > listTextView;
    TextView txtCurrentPlayer,txtCountDownTime;
    ImageView imgNewGame,imgDanhLai,imgMusic;

    boolean isMachineplay,isClickNewGame=false,isPlayMusic=false,isWantNewGame=false;
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
        setContentView(R.layout.activity_human_vs_machine);

        init();
        addData();
        txtCurrentPlayer.setText("Bạn");
        isMachineplay=false;

        gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l)//index bắt đầu từ 0
            {
                currentRow=index/numberOfColumn;
                currentColumn=index%numberOfColumn;

                if(chessBoard[currentRow][currentColumn]==66 || chessBoard[currentRow][currentColumn]==88)
                {
                    Toast.makeText(HumanVsMachine.this,"ô này đã được đánh, vui lòng đánh ô khác!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(waitTimer != null) {
                    waitTimer.cancel();
                    waitTimer = null;
                }
                CountDownTime();

                if(!isMachineplay)
                {
                    chessBoard[currentRow][currentColumn]=66;//66 đại diện cho x-người
                    listTextView.set(index,new clsTextView("X","#ff0000"));
                    adapterGridView.notifyDataSetChanged();
                    stack_O_Da_Danh.push(new Integer(index));
                    isMachineplay=!isMachineplay;
                    txtCurrentPlayer.setText("Máy");

                    if(isEndGame(66,currentRow,currentColumn))
                    {
                        EndGame(66);
                        return;
                    }

                    oDaDanh++;
                    if(oDaDanh>=totalOVuong)
                    {
                        EndGame(44);//44 đại diện cho game hòa
                        return;
                    }
                    /** người đánh song thì gọi hàm cho máy đánh luôn*/
                    MachinePlay();
                    oDaDanh++;
                    if(oDaDanh>=totalOVuong)
                    {
                        EndGame(44);//44 đại diện cho game hòa
                        return;
                    }
                }
                else
                {
                 Toast.makeText(HumanVsMachine.this,"Đang là lượt máy đánh, bạn không có quyền đánh",Toast.LENGTH_SHORT).show();
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

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(HumanVsMachine.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.icongame);
        alertDialog.setMessage("Bạn có chắc chắn muốn thoát khỏi trận đấu này?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Intent intent=new Intent(HumanVsMachine.this,HumanVsMachine.class);
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
    public void onBackPressed()
    {
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

    private void init()
    {
        txtCurrentPlayer=(TextView)findViewById(R.id.txtCurrentPlayer);
        isMachineplay=false;
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

        mediaPlayer=MediaPlayer.create(HumanVsMachine.this,R.raw.despacito);
        mediaPlayer.setLooping(true);
        if(isPlayMusic)
        {
            mediaPlayer.start();
        }
        else {
            imgMusic.setImageResource(R.drawable.mute);
        }

        for(int i=0;i<numberOfRow;i++)
            for(int j=0;j<numberOfColumn;j++)
            {
                chessBoard[i][j]=0;
               // checkChessBoard[i][j]=0;
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
            Toast.makeText(HumanVsMachine.this,"bàn cờ chưa đánh quân cờ nào!",Toast.LENGTH_LONG).show();
            return;
        }
        Integer index= (Integer) stack_O_Da_Danh.pop();
        listTextView.set(index,new clsTextView("","#000000"));
        adapterGridView.notifyDataSetChanged();
        chessBoard[index/numberOfColumn][index%numberOfColumn]=0;//giá trị mặc định của int là 0
        if(isMachineplay)//đến lượt máy đánh thì lượt vừa mói đánh là bạn
        {
            txtCurrentPlayer.setText("Bạn");
            isMachineplay=!isMachineplay;
        }
        else{
            txtCurrentPlayer.setText("Máy");
            isMachineplay=!isMachineplay;
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
            strWinner="Bạn thắng";
        else if(winner==88)
            strWinner="Máy thắng";
        else strWinner="Hòa";

        db=openOrCreateDatabase("carohistory.db",MODE_PRIVATE,null);
        String sql="Insert into tblcaro(time,winner) values ('"+getNowDateTime()+"','"+strWinner+"');";
        db.execSQL(sql);
        db.close();
    }
    private void DialogEndGame(int quanCoWin)
    {
        final Dialog dialog=new Dialog(HumanVsMachine.this);
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
            winer.setText("Máy thắng");
        }
        else if(quanCoWin==66)
        {
            winer.setText("Bạn thắng");

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
                Intent intent=new Intent(HumanVsMachine.this,TrangChu.class);
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

    private void CountDownTime()
    {
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
                if(isMachineplay)
                    EndGame(66);//nếu hết thời gian mà đang là lượt máy đánh thì người thắng.
                else
                    EndGame(88);
            }
        }.start();
    }

    //các hàm phục vụ cho việc máy đánh.
    /** 66 đại diện cho người, 88 đại diện cho máy */
    //khỏi tạo bác biến phục vụ cho máy đánh
    int indexMax=-1;//là vị trí để cho máy đánh có trọng số cao nhất tìm được
    int valueMax=-1;//là giá trị quy định trọng số máy có thể đánh, số lớn nhất máy sẽ đánh
    /**danh sách trọng số mà tại ô đang xét mà máy sẽ đánh, càng cao càng tốt
     * 12  - là theo hàng (cột, chéo) nếu có 4 quân 88 liên tiếp ko bị chặn hoặc 5 quân 88 bị chặn 1 đầu
     * 11- là ô đó mà người đánh thì người sẽ thắng.
     * 10 - là theo hàng (cột, chéo) nếu có 3 quân 88 liên tiếp ko bị chặn và còn thừa ít nhất 1 ô trống
     *                                  hoặc 4 quân 88 bị chặn 1 đầu và còn thừa ít nhất 1 ô trống
     * 9 - là theo hàng (cột, chéo) nếu có 2 quân 88 liên tiếp ko bị chặn và còn thừa ít nhất 2 ô trống
     *                              hoặc 3 quân 88 bị chặn 1 đầu và còn thừa ít nhất 2 ô trống
     * 8 - là theo hàng (cột, chéo) nếu có 1 quân 88 liên tiếp ko bị chặn và còn thừa ít nhất 3 ô trống
     *                              hoặc 2 quân 88 bị chặn 1 đầu và còn thừa ít nhất 3 ô trống
     * 7 - là theo hàng (cột, chéo) nếu có 0 quân 88 liên tiếp ko bị chặn và còn thừa ít nhất 4 ô trống
     *                              hoặc 1 quân 88 bị chặn 1 đầu và còn thừa ít nhất 4 ô trống
     * 0 - là vd còn thừa 3 ô trống và hết ô để đi, ko còn có thể thắng được, sắp đánh hết bàn cờ
     * */
    private  void MachinePlay()
    {
        /**
         * ý tưởng huật toán
         * lặp từng ô có thể đánh để xét các trọng số cho từng ô rồi đánh.
         */
        indexMax=-1;
        valueMax=-1;
        for(int i=0;i<totalOVuong;i++)
            if(chessBoard[i/numberOfColumn][i%numberOfColumn]!=66 && chessBoard[i/numberOfColumn][i%numberOfColumn]!=88)
            {
                if(valueMax==-1)
                {
                    valueMax=0;indexMax=i;
                }
                MayDanh(i);
            }
        int currentRowMachineClicked=indexMax/numberOfColumn;
        int currentColumnMachineClicked=indexMax%numberOfColumn;
        chessBoard[currentRowMachineClicked][currentColumnMachineClicked]=88;//88 đại diện cho O-máy
        listTextView.set(indexMax,new clsTextView("O","#00cc00"));
        adapterGridView.notifyDataSetChanged();
        stack_O_Da_Danh.push(new Integer(indexMax));
        isMachineplay=!isMachineplay;
        txtCurrentPlayer.setText("Bạn");

        if(isEndGame(88,currentRowMachineClicked,currentColumnMachineClicked))
        {
            EndGame(88);
            return;
        }
    }

    private void MayDanh(int index)
    {
        int currentRow=index/numberOfColumn;
        int currentColumn=index%numberOfColumn;
        MachineCanWinHorizontal(index,currentRow,currentColumn);
        MachineCanWinVertical(index,currentRow,currentColumn);
        MachineCanWinPrimary(index,currentRow,currentColumn);
        MachineCanWinSubsidiary(index,currentRow,currentColumn);

        chessBoard[currentRow][currentColumn]=66;//giả định người đánh vào ô này thì có thắng không
        if(isEndGame(66,currentRow,currentColumn))
        {
            if(valueMax<11){
                indexMax=index;
                valueMax=11;
            }
        }
        chessBoard[currentRow][currentColumn]=0;

    }
    private void MachineCanWinHorizontal(int index,int currentRow,int currentColumn)
    {
        int countLeft=1,countRight=0;//countLeft cho =1 vì giả định sẽ đánh vào ô đang xét
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int so_O_trong=0; //số ô trống liên tiếp còn lại hoặc là ô 88 do máy đánh
        for(int i=currentColumn-1 ; i>=0 ; i--)//đếm bên trái của ô hiện tại đang xét
        {
            if(chessBoard[currentRow][i]==88)
            {
                countLeft++;
            }
            else
            {
                if(chessBoard[currentRow][i]==66)
                {
                    isBlockedAbove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        for(int i=currentColumn+1 ; i<numberOfColumn ; i++)//đếm bên phải của ô hiện tại
        {
            if(chessBoard[currentRow][i]==88)
            {
                countRight++;
            }
            else
            {
                if(chessBoard[currentRow][i]==66){
                    isBlockedBelove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        if( (countLeft+countRight>=5 && (isBlockedAbove || isBlockedBelove)) ||
                (countLeft+countRight>=4 && (!isBlockedAbove && !isBlockedBelove))
          )
        {
            if(valueMax<12)
            {
                valueMax=12;indexMax=index;
            }
        }
        else if(   ((countLeft+countRight>=4 && (isBlockedAbove || isBlockedBelove)) ||
                        (countLeft+countRight>=3 && (!isBlockedAbove && !isBlockedBelove)))
                    && so_O_trong>=1
                )
        {
            if(valueMax<10)
            {
                valueMax=10;indexMax=index;
            }
        }

       else if(   ((countLeft+countRight>=3 && (isBlockedAbove || isBlockedBelove)) ||
                        (countLeft+countRight>=2 && (!isBlockedAbove && !isBlockedBelove)))
                   && so_O_trong>=2
              )
       {
           if(valueMax<9)
           {
               valueMax=9;indexMax=index;
           }
       }

       else if(   ((countLeft+countRight>=2 && (isBlockedAbove || isBlockedBelove)) ||
                         (countLeft+countRight>=1 && (!isBlockedAbove && !isBlockedBelove)))
                   && so_O_trong>=3
                )
       {
           if(valueMax<8)
           {
               valueMax=8;indexMax=index;
           }
       }

       else if(   ((countLeft+countRight>=1 && (isBlockedAbove || isBlockedBelove)) ||
                    (countLeft+countRight>=0 && (!isBlockedAbove && !isBlockedBelove)))
               && so_O_trong>=4
            )
       {
           if(valueMax<7)
           {
               valueMax=7;indexMax=index;
           }
       }

    }
    private void MachineCanWinVertical(int index,int currentRow,int currentColumn)
    {
        if(valueMax==12) //nếu tìm thấy ô sẽ đánh để chiến thắng thì ko cần tìm theo hàng dọc nữa
            return;
        int countAbove=1,countBelow=0;//countAbove cho = 1 vì giả định đánh vào ô đang xét
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int so_O_trong=0; //số ô trống liên tiếp còn lại hoặc là ô 88 do máy đánh
        for(int i=currentRow-1 ; i>=0 ; i--)//đếm phía trên của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==88)
            {
                countAbove++;
            }
            else
            {
                if(chessBoard[i][currentColumn]==66)
                {
                    isBlockedAbove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        for(int i=currentRow+1 ; i<numberOfRow ; i++)//đếm phía dưới của ô hiện tại
        {
            if(chessBoard[i][currentColumn]==88)
            {
                countBelow++;
            }
            else
            {
                if(chessBoard[i][currentColumn]==66){
                    isBlockedBelove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        if( (countAbove+countBelow>=5 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=4 && (!isBlockedAbove && !isBlockedBelove))
        )
        {
            if(valueMax<12)
            {
                valueMax=12;indexMax=index;
            }
        }
        else if(   ((countAbove+countBelow>=4 && (isBlockedAbove || isBlockedBelove)) ||
                        (countAbove+countBelow>=3 && (!isBlockedAbove && !isBlockedBelove)))
                   && so_O_trong>=1
                )
        {
            if(valueMax<10)
            {
                valueMax=10;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=3 && (isBlockedAbove || isBlockedBelove)) ||
                        (countAbove+countBelow>=2 && (!isBlockedAbove && !isBlockedBelove)))
                    && so_O_trong>=2
                )
        {
            if(valueMax<9)
            {
                valueMax=9;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=2 && (isBlockedAbove || isBlockedBelove)) ||
                         (countAbove+countBelow>=1 && (!isBlockedAbove && !isBlockedBelove)))
                   && so_O_trong>=3
                )
        {
            if(valueMax<8)
            {
                valueMax=8;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=1 && (isBlockedAbove || isBlockedBelove)) ||
                        (countAbove+countBelow>=0 && (!isBlockedAbove && !isBlockedBelove)))
                      && so_O_trong>=4
                 )
        {
            if(valueMax<7)
            {
                valueMax=7;indexMax=index;
            }
        }

    }
    private void MachineCanWinPrimary(int index,int currentRow,int currentColumn)
    {
        if(valueMax==12) //nếu tìm thấy ô sẽ đánh để chiến thắng thì ko cần tìm theo đường chéo chính nữa
            return;
        int countAbove=1,countBelow=0;//countAbove cho = 1 vì giả định đánh vào ô đang xét
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int so_O_trong=0; //số ô trống liên tiếp còn lại hoặc là ô 88 do máy đánh
        for(int i=1;i<=currentColumn;i++)//đếm phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn-i <0)
                break;
            if(chessBoard[currentRow-i][currentColumn-i]==88)
            {
                countAbove++;
            }
            else
            {
                if(chessBoard[currentRow-i][currentColumn-i]==66)
                {
                    isBlockedAbove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        for(int i=1;i<numberOfColumn-currentColumn;i++)//đếm phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow+i][currentColumn+i]==88)
            {
                countBelow++;
            }
            else
            {
                if(chessBoard[currentRow+i][currentColumn+i]==66){
                    isBlockedBelove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        if( (countAbove+countBelow>=5 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=4 && (!isBlockedAbove && !isBlockedBelove))
        )
        {
            if(valueMax<12)
            {
                valueMax=12;indexMax=index;
            }
        }
        else if(   ((countAbove+countBelow>=4 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=3 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=1
        )
        {
            if(valueMax<10)
            {
                valueMax=10;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=3 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=2 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=2
        )
        {
            if(valueMax<9)
            {
                valueMax=9;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=2 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=1 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=3
        )
        {
            if(valueMax<8)
            {
                valueMax=8;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=1 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=0 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=4
        )
        {
            if(valueMax<7)
            {
                valueMax=7;indexMax=index;
            }
        }
    }
    private void MachineCanWinSubsidiary(int index,int currentRow,int currentColumn)
    {
        if(valueMax==12) //nếu tìm thấy ô sẽ đánh để chiến thắng thì ko cần tìm theo đường chéo phụ nữa
            return;
        int countAbove=1,countBelow=0;//countAbove cho = 1 vì giả định đánh vào ô đang xét
        boolean isBlockedAbove=false,isBlockedBelove=false;
        int so_O_trong=0; //số ô trống liên tiếp còn lại hoặc là ô 88 do máy đánh
        for(int i=1;i<=currentRow;i++)//đếm phía trên của ô hiện tại
        {
            if(currentRow-i<0 || currentColumn+i>=numberOfColumn)
                break;
            if(chessBoard[currentRow-i][currentColumn+i]==88)
            {
                countAbove++;
            }
            else
            {
                if(chessBoard[currentRow-i][currentColumn+i]==66)
                {
                    isBlockedAbove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        for(int i=1;i<=currentColumn;i++)//đếm phía dưới của ô hiện tại
        {
            if(currentRow+i>=numberOfRow || currentColumn-i<0)
                break;
            if(chessBoard[currentRow+i][currentColumn-i]==88)
            {
                countBelow++;
            }
            else
            {
                if(chessBoard[currentRow+i][currentColumn-i]==66){
                    isBlockedBelove=true;
                    break;
                }
                so_O_trong++;
                if(so_O_trong>=5)
                    break;
            }
        }
        if( (countAbove+countBelow>=5 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=4 && (!isBlockedAbove && !isBlockedBelove))
        )
        {
            if(valueMax<12)
            {
                valueMax=12;indexMax=index;
            }
        }
        else if(   ((countAbove+countBelow>=4 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=3 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=1
        )
        {
            if(valueMax<10)
            {
                valueMax=10;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=3 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=2 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=2
        )
        {
            if(valueMax<9)
            {
                valueMax=9;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=2 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=1 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=3
        )
        {
            if(valueMax<8)
            {
                valueMax=8;indexMax=index;
            }
        }

        else if(   ((countAbove+countBelow>=1 && (isBlockedAbove || isBlockedBelove)) ||
                (countAbove+countBelow>=0 && (!isBlockedAbove && !isBlockedBelove)))
                && so_O_trong>=4
        )
        {
            if(valueMax<7)
            {
                valueMax=7;indexMax=index;
            }
        }
    }
}