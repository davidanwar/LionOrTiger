package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{
        one, two, noOne
    }

    Player currentplayer = Player.one;
    Player [] playerCoices = new Player[9];
    int [][] winnerRowsColums = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*playerCoices[0] = Player.noOne;
        playerCoices[1] = Player.noOne;
        playerCoices[2] = Player.noOne;
        playerCoices[3] = Player.noOne;
        playerCoices[4] = Player.noOne;
        playerCoices[5] = Player.noOne;
        playerCoices[6] = Player.noOne;
        playerCoices[7] = Player.noOne;
        playerCoices[8] = Player.noOne;*/

        for (int index = 0; index < playerCoices.length; index++){
            playerCoices[index] = Player.noOne;
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });
    }

    public void imageViewTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerCoices[tiTag] == Player.noOne && gameOver == false) {


            tappedImageView.setTranslationX(-2000);

            playerCoices[tiTag] = currentplayer;
            if (currentplayer == Player.one) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentplayer = Player.two;
            } else if (currentplayer == Player.two) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentplayer = Player.one;
            }

            tappedImageView.animate().translationXBy(2000)
                    .alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColums : winnerRowsColums) {
                if (playerCoices[winnerColums[0]] == playerCoices[winnerColums[1]]
                        && playerCoices[winnerColums[1]] == playerCoices[winnerColums[2]]
                        && playerCoices[winnerColums[0]] != Player.noOne) {
                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerEndGame = "";
                    if (currentplayer == Player.one) {
                        winnerEndGame = "Player Two ";
                    } else if (currentplayer == Player.two) {
                        winnerEndGame = "Player One ";
                    }
                    Toast.makeText(this, winnerEndGame + "is the winner", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    // reset game function

    private void resetTheGame(){
        for (int index = 0; index < gridLayout.getChildCount(); index++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.3f);
            currentplayer = Player.one;
//            playerCoices[0] = Player.noOne;
//            playerCoices[1] = Player.noOne;
//            playerCoices[2] = Player.noOne;
//            playerCoices[3] = Player.noOne;
//            playerCoices[4] = Player.noOne;
//            playerCoices[5] = Player.noOne;
//            playerCoices[6] = Player.noOne;
//            playerCoices[7] = Player.noOne;
//            playerCoices[8] = Player.noOne;

            for (int index2 = 0; index < playerCoices.length; index2++){
                playerCoices[index2] = Player.noOne;
            }

            gameOver = false;
            btnReset.setVisibility(View.GONE);
        }

    }
}
