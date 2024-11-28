package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarTB: Toolbar
    private lateinit var inputET: EditText
    private lateinit var resultTV: TextView
    private lateinit var oneBTN: Button
    private lateinit var twoBTN: Button
    private lateinit var threeBTN: Button
    private lateinit var fourBTN: Button
    private lateinit var fiveBTN: Button
    private lateinit var sixBTN: Button
    private lateinit var sevenBTN: Button
    private lateinit var eightBTN: Button
    private lateinit var nineBTN: Button
    private lateinit var zeroBTN: Button
    private lateinit var divBTN: Button
    private lateinit var mulBTN: Button
    private lateinit var subBTN: Button
    private lateinit var sumBTN: Button
    private lateinit var equBTN: Button
    private lateinit var resBTN: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        toolbarTB = findViewById(R.id.toolbarMain)
        inputET = findViewById(R.id.inputET)
        resultTV = findViewById(R.id.resultTV)
        oneBTN = findViewById(R.id.oneBTN)
        twoBTN = findViewById(R.id.twoBTN)
        threeBTN = findViewById(R.id.threeBTN)
        fourBTN = findViewById(R.id.fourBTN)
        fiveBTN = findViewById(R.id.fiveBTN)
        sixBTN = findViewById(R.id.sixBTN)
        sevenBTN = findViewById(R.id.sevenBTN)
        eightBTN = findViewById(R.id.eightBTN)
        nineBTN = findViewById(R.id.nineBTN)
        zeroBTN = findViewById(R.id.zeroBTN)
        divBTN = findViewById(R.id.divBTN)
        mulBTN = findViewById(R.id.mulBTN)
        subBTN = findViewById(R.id.subBTN)
        sumBTN = findViewById(R.id.sumBTN)
        equBTN = findViewById(R.id.equBTN)
        resBTN = findViewById(R.id.resBTN)

        setSupportActionBar(toolbarTB)
        toolbarTB.setNavigationIcon(R.drawable.exit_fill)
        toolbarTB.setNavigationOnClickListener {
            finish()
        }


        oneBTN.setOnClickListener { setFigure("1") }
        twoBTN.setOnClickListener { setFigure("2") }
        threeBTN.setOnClickListener { setFigure("3") }
        fourBTN.setOnClickListener { setFigure("4") }
        fiveBTN.setOnClickListener { setFigure("5") }
        sixBTN.setOnClickListener { setFigure("6") }
        sevenBTN.setOnClickListener { setFigure("7") }
        eightBTN.setOnClickListener { setFigure("8") }
        nineBTN.setOnClickListener { setFigure("9") }
        zeroBTN.setOnClickListener { setFigure("0") }

        divBTN.setOnClickListener { setFigure("/") }
        mulBTN.setOnClickListener { setFigure("*") }
        subBTN.setOnClickListener { setFigure("-") }
        sumBTN.setOnClickListener { setFigure("+") }
        equBTN.setOnClickListener {
            setFigure("=")
            if (checkingForAnExample(inputET.text.toString())) {
                val firstPart = getFirstPart(inputET.text.toString())
                val secondPart = getSecondPart(inputET.text.toString())
                if (firstPart.isEmpty() || secondPart.isEmpty()) {
                    resultTV.text = "Ошибка: некорректное выражение"
                    return@setOnClickListener
                }
                val operator = getOperator(inputET.text.toString())
                try {
                    val num1 = firstPart.toInt()
                    val num2 = secondPart.toInt()
                    when (operator) {
                        "/" -> {
                            if (num2 == 0) {
                                resultTV.text = "Ошибка: деление на ноль"
                            } else {
                                resultTV.text = (num1 / num2).toString()
                            }
                        }
                        "*" -> resultTV.text = (num1 * num2).toString()
                        "+" -> resultTV.text = (num1 + num2).toString()
                        "-" -> resultTV.text = (num1 - num2).toString()
                        else -> resultTV.text = "Ошибка: неверный оператор"
                    }
                } catch (e: NumberFormatException) {
                    resultTV.text = "Ошибка: неверный формат числа"
                }
            } else {
                resultTV.text = "Ошибка: некорректное выражение"
            }
        }

        resBTN.setOnClickListener {
            inputET.text.clear()
            resultTV.setText(R.string.result)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {R.id.exit -> finishAffinity() }  // выйти из приложения
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    fun setFigure(figure: String) {
        //var text = inputET.text.toString()
        //inputET.text.clear()
        inputET.setText("${inputET.text}${figure}")
    }

    fun checkingForAnExample(string: String) : Boolean {
        val regex = Regex("^[0-9]+[*/\\+\\-][0-9]+[ =]$")

        if (string.matches(regex)) return true
        return false
    }

    fun getFirstPart(input: String): String {
        val operator = getOperator(input)
        val operatorIndex = input.indexOf(operator)
        return if (operatorIndex != -1) {
            input.substring(0, operatorIndex).trim()
        } else {
            ""
        }
    }

    fun getSecondPart(input: String): String {
        val operator = getOperator(input)
        val operatorIndex = input.indexOf(operator)
        val equalIndex = input.indexOf('=')
        if (operatorIndex != -1) {
            val endIndex = if (equalIndex != -1) equalIndex else input.length
            return input.substring(operatorIndex + 1, endIndex).trim()
        }
        return ""
    }

    fun getOperator(input: String): String {
        val operatorRegex = Regex("[*/\\+\\-]")
        return operatorRegex.find(input)?.value ?: ""
    }

    fun messeg(string: String) {
        Toast.makeText(
            applicationContext,
            string,
            Toast.LENGTH_LONG
        ).show()
    }
}