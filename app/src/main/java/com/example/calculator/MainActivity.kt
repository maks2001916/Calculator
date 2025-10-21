package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var currentBase = 10 // Текущая система счисления (по умолчанию десятичная)
    private var convertedValue = "" // Последнее сконвертированное значение

    private lateinit var toolbarTB: Toolbar
    private lateinit var inputET: EditText
    private lateinit var resultTV: TextView
    //
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
    private lateinit var aBTN: Button
    private lateinit var bBTN: Button
    private lateinit var cBTN: Button
    private lateinit var dBTN: Button
    private lateinit var eBTN: Button
    private lateinit var fBTN: Button

    private lateinit var divBTN: Button
    private lateinit var mulBTN: Button
    private lateinit var subBTN: Button
    private lateinit var sumBTN: Button
    private lateinit var dotBTN: Button

    private lateinit var equBTN: Button
    private lateinit var resBTN: Button
    private lateinit var binTV: TextView
    private lateinit var decTV: TextView
    private lateinit var octTV: TextView
    private lateinit var hexTV: TextView
    private lateinit var binSC: SwitchCompat
    private lateinit var decSC: SwitchCompat
    private lateinit var octSC: SwitchCompat
    private lateinit var hexSC: SwitchCompat



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
        binTV = findViewById(R.id.binTV)
        decTV = findViewById(R.id.decTV)
        octTV = findViewById(R.id.octTV)
        hexTV = findViewById(R.id.hexTV)
        binSC = findViewById(R.id.binSC)
        decSC = findViewById(R.id.decSC)
        octSC = findViewById(R.id.octSC)
        hexSC = findViewById(R.id.hexSC)


        setSupportActionBar(toolbarTB)

        //toolbarTB.setNavigationIcon(R.drawable.exit_fill)

        fun setupNumberSystemButtons() {
            binSC.setOnClickListener { convertNumber(2) }
            octSC.setOnClickListener { convertNumber(8) }
            decSC.setOnClickListener { convertNumber(10) }
            hexSC.setOnClickListener { convertNumber(16) }
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

        aBTN.setOnClickListener { setFigure("A") }
        bBTN.setOnClickListener { setFigure("B") }
        cBTN.setOnClickListener { setFigure("C") }
        dBTN.setOnClickListener { setFigure("D") }
        eBTN.setOnClickListener { setFigure("E") }
        fBTN.setOnClickListener { setFigure("F") }
        dotBTN.setOnClickListener { setFigure(".") }

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
        val regex = Regex("^[0-9A-Fa-f.]+[*/\\+\\-][0-9A-Fa-f.]+[ =]$")
        return string.matches(regex)
    }

    fun getFirstPart(input: String): String {
        val operator = getOperator(input)
        val operatorIndex = input.indexOf(operator)
        return if (operatorIndex != -1) {
            input.substring(0, operatorIndex).trim()
        } else { "" }
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

    fun convertNumber(newBase: Int) {
        val inputText = inputET.text.toString()

        if (inputText.isNotEmpty() && !inputText.contains("[+\\-*/]".toRegex())) {
            try {
                val valueToConvert = if
                        (resultTV.text.toString() != getString(R.string.result) &&
                        !resultTV.text.toString().startsWith("Ошибка")) {
                    resultTV.text.toString()
                } else {
                    inputText
                }
                val decimalValue = convertToDecimal(valueToConvert, currentBase)
                val converted = convertFromDecimal(decimalValue, currentBase)

                inputET.setText(converted)
                convertedValue = converted
                currentBase = newBase
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    "Ошибка конвертации: ${e.message}",
                    Toast.LENGTH_LONG)
                    .show()
            }
        } else if (convertedValue.isNotEmpty()) {
            inputET.setText(convertedValue)
        }

        currentBase = newBase
    }

    fun convertToDecimal(number: String, fromBase: Int): Double {
        if (fromBase == 10) {
            return number.toDoubleOrNull() ?: throw IllegalArgumentException("Некорректное десятичное число")
        }

        var cleanNumber = number.uppercase()
        if (cleanNumber.startsWith("0X") ||
            cleanNumber.startsWith("0B") ||
            cleanNumber.startsWith("0O"))
            cleanNumber = cleanNumber.substring(2)

        return  if (cleanNumber.contains('.')) {
            val parts = cleanNumber.split('.')
            val integerPart = convertIntegerPart(parts[0], fromBase)
            val fractionalPart = convertFractionalPart(parts[1], fromBase)
            integerPart + fractionalPart
        } else {
            convertIntegerPart(cleanNumber, fromBase).toDouble()
        }
    }

    private fun convertIntegerPart(integerPart: String, fromBase: Int): Int {
        if (integerPart.isEmpty()) return 0

        var result = 0
        val power = 1
        for (i in integerPart.length - 1 downTo 0) {
            val digit = charToDigit(integerPart[i])
            if (digit >=fromBase) {
                throw IllegalArgumentException("Цифра $digit недопустима в системе счисления $fromBase")
            }
            result += digit * power
            result *= fromBase
        }
        return result
    }

    private fun convertFractionalPart(fractionalPart: String, fromBase: Int): Double {
        if (fractionalPart.isEmpty()) return  0.0

        var result = 0.0
        var division = fromBase.toDouble()
        for (i in fractionalPart.indices) {
            val digit = charToDigit(fractionalPart[i])
            if (digit >= fromBase) {
                throw IllegalArgumentException("Цифра $digit не доступна в системе счисления $fromBase")
            }
            result += digit / division
            division *= fromBase
        }
        return result
    }

    fun convertFromDecimal(decimalNumber: Double, toBase: Int): String {
        if (toBase == 10) {
            return if (decimalNumber % 1 == 0.0) {
                decimalNumber.toInt().toString()
            } else {
                String.format("%.6f", decimalNumber).trimEnd('0').trimEnd('.')
            }
        }
        val integerPart = decimalNumber.toInt()
        val fractionalPart = decimalNumber - integerPart

        val integerResult = convertIntegerPartFromDecimal(integerPart, toBase)

        return if (fractionalPart > 0) {
            val fractionalResult = convertFractionalPartFromDecimal(fractionalPart, toBase)
            "$integerResult.$fractionalResult"
        } else {
            integerResult
        }
    }

    private fun convertIntegerPartFromDecimal(number: Int, toBase: Int): String {
        if (number == 0) return "0"

        var n = number
        val result = StringBuilder()

        while (n > 0) {
            val reminder = n % toBase
            result.insert(0, digitToChar(reminder))
            n /= toBase
        }
        return result.toString()
    }

    private fun convertFractionalPartFromDecimal(fraction: Double, toBase: Int, precision: Int = 6): String {
        if (fraction == 0.0) return ""

        var fractional = fraction
        val result = StringBuilder()
        var count = 0

        while (fractional > 0 && count < precision) {
            fractional *= toBase
            val digit = fractional.toInt()
            result.append(digitToChar(digit))
            fractional -= digit
            count++
        }
        return result.toString()
    }

    private fun charToDigit(char: Char): Int {
        return when (char) {
            in '0'..'9' -> char - '0'
            in 'A'..'F' -> char - 'A' + 10
            in 'a'..'f' -> char - 'a' + 10
            else -> throw IllegalArgumentException("Недоаустимый символ: $char")
        }
    }

    private fun digitToChar(digit: Int): Char {
        return when (digit) {
            in 0..9 -> '0' + digit
            in 10..15 -> 'A' + (digit - 10)
            else -> throw IllegalArgumentException("Недопустимая цифра: $digit")
        }
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