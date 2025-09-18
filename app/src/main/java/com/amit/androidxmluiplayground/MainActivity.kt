package com.amit.androidxmluiplayground

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Demonstrate programmatic FlexBox configuration
        setupDynamicFlexBoxDemo()
    }

    private fun setupDynamicFlexBoxDemo() {
        // Find the first FlexBox layout (Basic Row Layout)
        val flexboxLayout = findViewById<FlexboxLayout>(R.id.flexbox_basic_row)

        // Example: Add a click listener to dynamically change flex direction
        flexboxLayout?.setOnClickListener {
            val currentDirection = flexboxLayout.flexDirection
            when (currentDirection) {
                FlexDirection.ROW -> {
                    flexboxLayout.flexDirection = FlexDirection.COLUMN
                }
                FlexDirection.COLUMN -> {
                    flexboxLayout.flexDirection = FlexDirection.ROW
                }
                else -> {
                    flexboxLayout.flexDirection = FlexDirection.ROW
                }
            }
            flexboxLayout.requestLayout()
        }

        // Example: Modify layout parameters programmatically
        val firstChild = flexboxLayout?.getChildAt(0) as? TextView
        firstChild?.setOnClickListener {
            val lp = firstChild.layoutParams as? FlexboxLayout.LayoutParams
            lp?.let {
                // Toggle flex grow
                it.flexGrow = if (it.flexGrow == 0f) 1f else 0f
                firstChild.layoutParams = it
                firstChild.text = if (it.flexGrow > 0) "Growing!" else "Item 1"
            }
        }

        // Example: Change justify content dynamically
        val justifyContentLayout = findViewById<FlexboxLayout>(R.id.flexbox_justify_content)
        justifyContentLayout?.setOnClickListener {
            val currentJustify = justifyContentLayout.justifyContent
            justifyContentLayout.justifyContent = when (currentJustify) {
                JustifyContent.SPACE_AROUND -> JustifyContent.CENTER
                JustifyContent.CENTER -> JustifyContent.FLEX_END
                JustifyContent.FLEX_END -> JustifyContent.SPACE_BETWEEN
                else -> JustifyContent.SPACE_AROUND
            }
            justifyContentLayout.requestLayout()
        }
    }
}