package com.example.formatter

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.formatter.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val df = DecimalFormat("#00.#####")

    private val textWatcher = object: EditTextWatcher() {
        override fun afterTextChanged(editable: Editable?) {
            val text = editable?.toString().orEmpty()
            binding.idEtInputer.removeTextChangedListener(this)
            if (text.isNotEmpty()) {
                val reverse = StringBuffer(text).reverse().toString()
                val formatText = formatText(reverse.toFloat())
                binding.idTvInputer.text = formatText
            } else {
                binding.idTvInputer.text = "00.00000"
            }
            binding.idEtInputer.addTextChangedListener(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idEtInputer.addTextChangedListener(textWatcher)
    }

    override fun onDestroy() {
        binding.idEtInputer.removeTextChangedListener(textWatcher)
        super.onDestroy()
    }

    private fun formatText(text: Float): String {
        val value = text / 100000
        return df.format(value)
    }
}