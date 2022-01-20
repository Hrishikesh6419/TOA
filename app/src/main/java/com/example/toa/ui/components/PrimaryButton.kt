package com.example.toa.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.toa.R
import com.example.toa.ui.theme.ButtonShape
import com.example.toa.ui.theme.TOATheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    val buttonColors = buttonColors(
        backgroundColor = backgroundColor
    )

    Button(
        onClick = onClick,
        colors = buttonColors,
        shape = ButtonShape,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth()
    ) {
        Text(
            text = text.toUpperCase(Locale.current)
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PrimaryButtonPreview() {
    TOATheme {
        PrimaryButton(
            text = "Default",
            onClick = {},

        )
    }
}
