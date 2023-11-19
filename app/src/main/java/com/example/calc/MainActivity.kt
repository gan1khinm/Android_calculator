package com.example.calc

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.calc.databinding.ActivityMainBinding
import kotlin.math.sqrt
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val solver = ExpressionSolver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var x: Double? = null
        var y: Double? = null

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.log.visibility = View.VISIBLE
            binding.prc.visibility = View.VISIBLE
            binding.sqrt.visibility = View.VISIBLE
            binding.x.visibility = View.VISIBLE
            binding.y.visibility = View.VISIBLE
        }


        binding.btn0.setOnClickListener { setText("0")}
        binding.btn1.setOnClickListener { setText("1")}
        binding.btn2.setOnClickListener { setText("2")}
        binding.btn3.setOnClickListener { setText("3")}
        binding.btn4.setOnClickListener { setText("4")}
        binding.btn5.setOnClickListener { setText("5")}
        binding.btn6.setOnClickListener { setText("6")}
        binding.btn7.setOnClickListener { setText("7")}
        binding.btn8.setOnClickListener { setText("8")}
        binding.btn9.setOnClickListener { setText("9")}
        binding.minus.setOnClickListener { setText("-") }
        binding.plus.setOnClickListener { setText("+")}
        binding.multiplication.setOnClickListener { setText("*")}
        binding.division.setOnClickListener { setText("/")}
        binding.leftBracket.setOnClickListener { setText("(")}
        binding.rightBracket.setOnClickListener { setText(")")}
        binding.dot.setOnClickListener { setText(".") }
        binding.ac.setOnClickListener {
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }

        binding.x.setOnClickListener {
            if (x == null) {
                x = solver.evaluateExpression(binding.mathOperation.text.toString(), x, y)
                binding.mathOperation.text = ""
            } else {
                setText("x")
            }
        }

        binding.y.setOnClickListener {
            if (y == null) {
                y = solver.evaluateExpression(binding.mathOperation.text.toString(), x, y)
                binding.mathOperation.text = ""
            } else {
                setText("y")
            }
        }

        binding.back.setOnClickListener {
            val str = binding.mathOperation.text.toString()
            if(str.isNotEmpty()){
                binding.mathOperation.text = str.substring(0, str.length - 1)
                binding.resultText.text = ""
            }
        }

        binding.sqrt.setOnClickListener {
            try{
                val result = sqrt(solver.evaluateExpression(binding.mathOperation.text.toString(), x, y))
                binding.resultText.text = result.toString()
                x = null
                y = null
            } catch(e:Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }

        binding.log.setOnClickListener {
            try{
                val result = ln(solver.evaluateExpression(binding.mathOperation.text.toString(), x, y))
                binding.resultText.text = result.toString()
                x = null
                y = null
            } catch(e:Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }

        binding.eq.setOnClickListener {
            try{
                val result = solver.evaluateExpression(binding.mathOperation.text.toString(), x, y)
                binding.resultText.text = result.toString()
                x = null
                y = null
            } catch(e:Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }

        binding.prc.setOnClickListener {
            try{
                val result = solver.evaluateExpression(binding.mathOperation.text.toString(), x, y) / 100
                binding.resultText.text = result.toString()
                binding.mathOperation.text = ""
                x = null
                y = null
            } catch(e:Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }
    }

    fun setText(str: String) {
        if(binding.resultText.text != ""){
            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }
}