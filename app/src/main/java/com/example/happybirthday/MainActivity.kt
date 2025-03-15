package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var textResult: TextView

    private var state: Int = 1
    private var op: Int = 0
    private var op1: Double = 0.0
    private var op2: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.result_text)

        val buttonIds = listOf(
            R.id.button_BS, R.id.button_CE, R.id.button_C,
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9, R.id.button_SumAndSub,
            R.id.button_Div, R.id.button_Mul, R.id.button_Sub,
            R.id.button_Sum, R.id.button_Equal
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_0 -> addDigit(0)
            R.id.button_1 -> addDigit(1)
            R.id.button_2 -> addDigit(2)
            R.id.button_3 -> addDigit(3)
            R.id.button_4 -> addDigit(4)
            R.id.button_5 -> addDigit(5)
            R.id.button_6 -> addDigit(6)
            R.id.button_7 -> addDigit(7)
            R.id.button_8 -> addDigit(8)
            R.id.button_9 -> addDigit(9)
            R.id.button_Sum -> setOperation(1)
            R.id.button_Sub -> setOperation(2)
            R.id.button_Mul -> setOperation(3)
            R.id.button_Div -> setOperation(4)
            R.id.button_SumAndSub -> toggleSign()
            R.id.button_Equal -> calculate()
            R.id.button_C -> reset()
            R.id.button_CE -> clearEntry()
            R.id.button_BS -> backspace()
        }
    }

    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
            textResult.text = op1.toString()
        } else {
            op2 = op2 * 10 + digit
            textResult.text = op2.toString()
        }
    }

    private fun setOperation(operation: Int) {
        op = operation
        state = 2
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textResult.text = op1.toString()
        } else {
            op2 = -op2
            textResult.text = op2.toString()
        }
    }
private fun calculate() {
    val result = when (op) {
        1 -> op1 + op2
        2 -> op1 - op2
        3 -> op1 * op2
        4 -> if (op2 != 0.0) op1 / op2 else Double.NaN
        else -> op1
    }

    textResult.text = if (result.isNaN()) {
        "Error" // Hiển thị thông báo lỗi khi chia cho 0
    } else {
        result.toString()
    }

    reset()
    op1 = result
}

    private fun reset() {
        state = 1
        op1 = 0.0
        op2 = 0.0
        op = 0
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = 0.0
        } else {
            op2 = 0.0
        }
        textResult.text = "0"
    }

    private fun backspace() {
        if (state == 1) {
            op1 = (op1 / 10).toInt().toDouble()
            textResult.text = op1.toString()
        } else {
            op2 = (op2 / 10).toInt().toDouble()
            textResult.text = op2.toString()
        }
    }
}
