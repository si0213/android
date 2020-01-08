package jp.ac.nkc.kadai03_ct02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    var flg = false
    var playerHand = ""
    var dice1 = 0
    var dice2 = 0
    var diceDisp1 = 0
    var diceDisp2 = 0
    var result = ""
    val handler = Handler()

    var money = 1000
    var bet = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            playerHand = findViewById<RadioButton>(checkedId).text.toString()
        }

        timer(period = 500){
            handler.post{
                rollDice()
            }
        }

        startButton.setOnClickListener { chohan() }

        resetButton.setOnClickListener {
            flg = false

            playerHand = ""

            money = 1000
            moneyText.setText(money.toString())
            bet = 0
            betText.setText(bet.toString())

            result = ""
            resultText.text = ""

            dice1 = 0
            dice2 = 0
            diceDisp1 = 0
            diceDisp2 = 0

            whiteDice.setImageResource(R.drawable.dice_siro_1)
            blackDice.setImageResource(R.drawable.dice_kuro_1)
        }
    }

    fun chohan(){
        if(betText.text.toString().toInt() > 1){
            if(flg == false){
                resultText.text = "張った、張った！"
                startButton.text = "勝負！"

                if(betText.text.toString().toInt() <= money){
                    bet = betText.text.toString().toInt()
                    betText.isEnabled = false
                    flg = true
                }else{
                    resultText.text = "所持金不足です"
                }
            }
            else{
                dice1 = (Math.random() * 6).toInt() + 1
                dice2 = (Math.random() * 6).toInt() + 1

                when(dice1){
                    1 -> whiteDice.setImageResource(R.drawable.dice_siro_1)
                    2 -> whiteDice.setImageResource(R.drawable.dice_siro_2)
                    3 -> whiteDice.setImageResource(R.drawable.dice_siro_3)
                    4 -> whiteDice.setImageResource(R.drawable.dice_siro_4)
                    5 -> whiteDice.setImageResource(R.drawable.dice_siro_5)
                    6 -> whiteDice.setImageResource(R.drawable.dice_siro_6)
                }

                when(dice2){
                    1 -> blackDice.setImageResource(R.drawable.dice_kuro_1)
                    2 -> blackDice.setImageResource(R.drawable.dice_kuro_2)
                    3 -> blackDice.setImageResource(R.drawable.dice_kuro_3)
                    4 -> blackDice.setImageResource(R.drawable.dice_kuro_4)
                    5 -> blackDice.setImageResource(R.drawable.dice_kuro_5)
                    6 -> blackDice.setImageResource(R.drawable.dice_kuro_6)
                }

                if((dice1 + dice2) % 2 === 0){
                    result = "丁"
                }
                else{
                    result = "半"
                }

                if(choButton.isChecked == true){
                    if(result === "丁"){
                        money = money + bet
                        moneyText.setText(money.toString())

                        resultText.text = dice1.toString() + "、" + dice2.toString() + "の" + result + "！\nお見事！"
                    }
                    else {
                        money = money - bet
                        moneyText.setText(money.toString())

                        resultText.text = dice1.toString() + "、" + dice2.toString() + "の" + result + "！\n残念！！"
                    }
                }
                else if(hanButton.isChecked === true){
                    if(result === "半"){
                        money = money + bet
                        moneyText.setText(money.toString())

                        resultText.text = dice1.toString() + "、" + dice2.toString() + "の" + result + "！\nお見事！"
                    }
                    else {
                        money = money - bet
                        moneyText.setText(money.toString())

                        resultText.text = dice1.toString() + "、" + dice2.toString() + "の" + result + "！\n残念！！"
                    }
                }

                startButton.text = "ツボ、入ります！"
                betText.isEnabled = true
                flg = false
            }
        }
        else{
            resultText.text = "1以上の賭け金を\n入力してください"
        }

    }

    fun rollDice(){
        Timer().schedule(0,100,{
            if(flg == false){
                this.cancel()
            }else{
                diceDisp1 = (Math.random() * 6).toInt() + 1
                diceDisp2  = (Math.random() * 6).toInt() + 1

                when(diceDisp1){
                    1 -> whiteDice.setImageResource(R.drawable.dice_siro_1)
                    2 -> whiteDice.setImageResource(R.drawable.dice_siro_2)
                    3 -> whiteDice.setImageResource(R.drawable.dice_siro_3)
                    4 -> whiteDice.setImageResource(R.drawable.dice_siro_4)
                    5 -> whiteDice.setImageResource(R.drawable.dice_siro_5)
                    6 -> whiteDice.setImageResource(R.drawable.dice_siro_6)
                }

                when(diceDisp2){
                    1 -> blackDice.setImageResource(R.drawable.dice_kuro_1)
                    2 -> blackDice.setImageResource(R.drawable.dice_kuro_2)
                    3 -> blackDice.setImageResource(R.drawable.dice_kuro_3)
                    4 -> blackDice.setImageResource(R.drawable.dice_kuro_4)
                    5 -> blackDice.setImageResource(R.drawable.dice_kuro_5)
                    6 -> blackDice.setImageResource(R.drawable.dice_kuro_6)
                }
            }
        })

    }
}
