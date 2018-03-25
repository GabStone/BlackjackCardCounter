package com.oak.stone.blackjackcardcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class CountingPage extends Activity {

    boolean visible = false;

    private static final int NUMBER_OF_CARDS_IN_DECK = 50;

    private static final String MIN_BET = "Min Bet";
    private static final String ONE_AND_HALF_TIMES_MIN_BET = "1.5 x Min Bet";
    private static final String TWO_TIMES_MIN_BET = "2 x Min Bet";
    private static final String TWO_AND_HALF_TIMES_MIN_BET = "2.5 x Min Bet";

    private int amountDecks = 1;
    private double trueCount = 0;
    private int runningCount = 0;
    private int numberOfCards = NUMBER_OF_CARDS_IN_DECK;
    private TextView remainingDecksTextView;
    private TextView runningCountTextView;
    private TextView trueCountTextView;
    private TextView recommendedBetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_couting_page);

        // Setting the textview and shit
        remainingDecksTextView = (TextView) findViewById(R.id.textView11);
        runningCountTextView = (TextView) findViewById(R.id.textView10);
        trueCountTextView = (TextView) findViewById(R.id.textView9);
        recommendedBetTextView = (TextView) findViewById(R.id.textView13);


        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Amount of decks being used");
        String[] types = {"One Deck", "Two Decks", "Three Decks", "Four Decks", "Five Decks", "Six Decks"};
        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch (which) {
                    case 0:
                        amountDecks = 1;
                        numberOfCards = NUMBER_OF_CARDS_IN_DECK;
                        remainingDecksTextView.setText("1");
                        break;
                    case 1:
                        amountDecks = 2;
                        numberOfCards = 100;
                        remainingDecksTextView.setText("2");
                        break;
                    case 2:
                        amountDecks = 3;
                        numberOfCards = 150;
                        remainingDecksTextView.setText("3");
                        break;
                    case 3:
                        amountDecks = 4;
                        numberOfCards = 200;
                        remainingDecksTextView.setText("4");
                        break;
                    case 4:
                        amountDecks = 5;
                        numberOfCards = 250;
                        remainingDecksTextView.setText("5");
                        break;
                    case 5:
                        amountDecks = 6;
                        numberOfCards = 300;
                        remainingDecksTextView.setText("6");
                        break;
                    default:
                        amountDecks = 1;
                        numberOfCards = NUMBER_OF_CARDS_IN_DECK;
                        remainingDecksTextView.setText("1");
                        break;
                }
            }
        });
        b.show();
    }

    public void plusOne(View view) {
        if (numberOfCards == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.deckofcards)
                    .setTitle("Out of Cards")
                    .setMessage("Shuffling Cards...")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            runningCount = 0;
            numberOfCards = NUMBER_OF_CARDS_IN_DECK * amountDecks;
            runningCountTextView.setText(Integer.toString(runningCount));
            remainingDecksTextView.setText(Integer.toString(amountDecks));
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
            recommendedBetTextView.setText(MIN_BET);
            return;
        }

        numberOfCards--;
        double remainingDecks = (double)numberOfCards/(double)(amountDecks*NUMBER_OF_CARDS_IN_DECK) * amountDecks;
        // check the length of remaining decks
        String remDecks = Double.toString(remainingDecks);
        if (remDecks.length() > 4) {
            remDecks = remDecks.substring(0, 5);
        }

        remainingDecksTextView.setText(remDecks);

        if (remainingDecks == 0) {
            runningCount = 0;
            runningCountTextView.setText(Integer.toString((runningCount)));
        } else {
            runningCount++;
            runningCountTextView.setText(Integer.toString(runningCount));
        }

        if (remainingDecks == 0) {
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        } else {
            trueCount = runningCount / remainingDecks;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        }


        if (trueCount <=1) {
            recommendedBetTextView.setText(MIN_BET);
        } else if (trueCount >= 2 && trueCount <= 3) {
            recommendedBetTextView.setText(ONE_AND_HALF_TIMES_MIN_BET);
        } else if (trueCount >= 4 && trueCount <= 5) {
            recommendedBetTextView.setText(TWO_TIMES_MIN_BET);
        } else if (trueCount >= 6) {
            recommendedBetTextView.setText(TWO_AND_HALF_TIMES_MIN_BET);
        }
    }

    public void minusOne(View view) {
        if (numberOfCards == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.deckofcards)
                    .setTitle("Out of Cards")
                    .setMessage("Shuffling Cards...")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            runningCount = 0;
            numberOfCards = NUMBER_OF_CARDS_IN_DECK * amountDecks;
            runningCountTextView.setText(Integer.toString(runningCount));
            remainingDecksTextView.setText(Integer.toString(amountDecks));
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
            return;
        }

        numberOfCards--;
        double remainingDecks = (double)numberOfCards/(double)(amountDecks*NUMBER_OF_CARDS_IN_DECK) * amountDecks;
        // check the length of remaining decks
        String remDecks = Double.toString(remainingDecks);
        if (remDecks.length() > 4) {
            remDecks = remDecks.substring(0, 5);
        }

        remainingDecksTextView.setText(remDecks);

        if (remainingDecks == 0) {
            runningCount = 0;
            runningCountTextView.setText(Integer.toString(runningCount));
        } else {
            runningCount--;
            runningCountTextView.setText(Integer.toString(runningCount));
        }

        if (remainingDecks == 0) {
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        } else {
            trueCount = runningCount / remainingDecks;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        }

        if (trueCount <=1) {
            recommendedBetTextView.setText(MIN_BET);
        } else if (trueCount <=3 && trueCount >= 2) {
            recommendedBetTextView.setText(ONE_AND_HALF_TIMES_MIN_BET);
        } else if (trueCount <=5 && trueCount >= 4) {
            recommendedBetTextView.setText(TWO_TIMES_MIN_BET);
        } else if (trueCount >= 6) {
            recommendedBetTextView.setText(TWO_AND_HALF_TIMES_MIN_BET);
        }
    }

    public void neutral(View view) {
        if (numberOfCards == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.deckofcards)
                    .setTitle("Out of Cards")
                    .setMessage("Shuffling Cards...")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            runningCount = 0;
            numberOfCards = NUMBER_OF_CARDS_IN_DECK * amountDecks;
            runningCountTextView.setText(Integer.toString(runningCount));
            remainingDecksTextView.setText(Integer.toString(amountDecks));
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
            return;
        }
        numberOfCards--;
        double remainingDecks = (double)numberOfCards/(double)(amountDecks*NUMBER_OF_CARDS_IN_DECK) * amountDecks;
        String remDecks = Double.toString(remainingDecks);
        if (remDecks.length() > 4) {
            remDecks = remDecks.substring(0, 3);
        }

        remainingDecksTextView.setText(remDecks);

        if (remainingDecks == 0) {
            trueCount = 0;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        } else {
            trueCount = runningCount / remainingDecks;
            trueCountTextView.setText(Integer.toString((int) trueCount));
        }
    }

    public void shuffleDeck(View view) {
        AlertDialog dialog = null;
        dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.deckofcards)
                .setTitle("Out of Cards")
                .setMessage("Shuffling Cards...")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
        numberOfCards = NUMBER_OF_CARDS_IN_DECK * amountDecks;

        runningCount = 0;
        runningCountTextView.setText(Integer.toString(runningCount));

        remainingDecksTextView.setText(Integer.toString(amountDecks));

        trueCount = 0;
        trueCountTextView.setText(Integer.toString((int)trueCount));

        recommendedBetTextView.setText(MIN_BET);
    }

    public void hideCountingPage(View view) {
        ImageView hangoutsPic = (ImageView) findViewById(R.id.imageView3);
        if (visible == false) {
            hangoutsPic.setVisibility(view.VISIBLE);
            visible = true;
        } else {
            hangoutsPic.setVisibility(view.INVISIBLE);
            visible = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_couting_page, menu);
        return true;
    }

    public void stopCounting(View view) {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}