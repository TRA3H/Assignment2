package csu.fullerton.fz.assignment2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var redSlider: SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    private lateinit var redSwitch: Switch
    private lateinit var greenSwitch: Switch
    private lateinit var blueSwitch: Switch
    private lateinit var colorBox: View
    private lateinit var resetButton: Button


    // Slider Bar values
    private var redValue = 0
    private var greenValue = 0
    private var blueValue = 0

    private var redValueOff = 0
    private var greenValueOff = 0
    private var blueValueOff = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init ui elements
        redSlider = findViewById(R.id.red_slider)
        greenSlider = findViewById(R.id.green_slider)
        blueSlider = findViewById(R.id.blue_slider)

        // Initialize the red, green, and blue switches
        redSwitch = findViewById(R.id.red_switch)
        greenSwitch = findViewById(R.id.green_switch)
        blueSwitch = findViewById(R.id.blue_switch)

        // Initialize the colored box view
        colorBox = findViewById(R.id.color_box)
        colorBox.setBackgroundColor(getColorFromRgb(0,0,0))

        resetButton = findViewById(R.id.reset_button)

        resetButton.setOnClickListener{
            // Reset the red slider to 0
            redSlider.progress = 0
            redValue = 0
            redValueOff = 0

            // Reset the green slider to 0
            greenSlider.progress = 0
            greenValue = 0
            greenValueOff = 0

            // Reset the blue slider to 0
            blueSlider.progress = 0
            blueValue = 0
            blueValueOff = 0

        }

        redSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                redValue = progress
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                updateRedValueTextView()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        redSwitch.setOnCheckedChangeListener{ _, isChecked ->
            if (!isChecked) {
                // Disable the red seekbar
                redSlider.isEnabled = false

                //Save red value
                redValueOff = redValue

                // Set the red value to 0
                redValue = 0

                // Set the color_box to update the value
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
            } else {
                // Restore the previous red value
                redValue = redValueOff
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                redSlider.isEnabled = true
            }
        }

        greenSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                greenValue = progress
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                updateGreenValueTextView()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        greenSwitch.setOnCheckedChangeListener{ _, isChecked ->
            if (!isChecked) {
                // Disable the red seekbar
                greenSlider.isEnabled = false

                // Save green value before setting to 0
                greenValueOff = greenValue

                // Set the red value to 0
                greenValue = 0

                // Set the color_box to update the value
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
            } else {
                // Enable the red seekbar
                greenValue = greenValueOff
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                // Enable the green seekbar
                greenSlider.isEnabled = true
            }
        }

        blueSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blueValue = progress
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                updateBlueValueTextView()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        blueSwitch.setOnCheckedChangeListener{ _, isChecked ->
            if (!isChecked) {
                // Disable the red seekbar
                blueSlider.isEnabled = false

                // Save blue value before setting to 0
                blueValueOff = blueValue

                // Set the red value to 0
                redValue = 0

                // Set the color_box to update the value
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
            } else {
                // Restore the previous red value
                blueValue = blueValueOff
                colorBox.setBackgroundColor(getColorFromRgb(redValue, greenValue, blueValue))
                blueSlider.isEnabled = true
            }
        }


    }

    private fun updateRedValueTextView() {
        val redTextView = findViewById<TextView>(R.id.redValue)
        redTextView.text = "%.2f".format(redValue / 255.0f)
    }

    private fun updateGreenValueTextView() {
        val greenTextView = findViewById<TextView>(R.id.greenValue)
        greenTextView.text = "%.2f".format(greenValue / 255.0f)
    }

    private fun updateBlueValueTextView() {
        val blueTextView = findViewById<TextView>(R.id.blueValue)
        blueTextView.text = "%.2f".format(blueValue / 255.0f)
    }


    private fun getColorFromRgb(red: Int, green: Int, blue: Int): Int {
        return android.graphics.Color.rgb(red, green, blue)
    }

}
