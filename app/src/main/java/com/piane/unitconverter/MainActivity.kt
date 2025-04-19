package com.piane.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piane.unitconverter.model.UnitConverter
import com.piane.unitconverter.ui.theme.UnitConverterTheme
import kotlin.enums.EnumEntries

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    UnitConverterApp()
                }
            }
        }
    }
}

@Composable
fun UnitConverterApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        var tabIndex by remember { mutableIntStateOf(0) }
        val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index; println("Tab $index clicked") },
                        text = { Text(text = title) })
                }
            }
            when (tabIndex) {
                0 -> WeightTab()
                1 -> SizeTab()
                2 -> DistanceTab()
            }
        }
    }
}

@Composable
fun UnitSelector(unitOptions: EnumEntries<*>) {

    var originalUnit by remember { mutableIntStateOf(0) }
    var convertedUnit by remember { mutableIntStateOf(0) }
    var originalDropdownExpanded by remember { mutableStateOf(false) }
    var convertedDropdownExpanded by remember { mutableStateOf(false) }

    var inputValue by remember { mutableDoubleStateOf(0.0) }
    var result by remember { mutableDoubleStateOf(0.0) }


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Select unit", modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { originalDropdownExpanded = true }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = unitOptions[originalUnit].toString(),
                    modifier = Modifier.padding(16.dp)
                )
                DropdownMenu(
                    expanded = originalDropdownExpanded,
                    onDismissRequest = { originalDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    unitOptions.forEachIndexed { index, unit ->
                        DropdownMenuItem(
                            text = { Text(text = unit.toString()) },
                            onClick = {
                                originalUnit = index
                                originalDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }
        Text(text = "Convert to:", modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { convertedDropdownExpanded = true }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = unitOptions[convertedUnit].toString(),
                    modifier = Modifier.padding(16.dp)
                )
                DropdownMenu(
                    expanded = convertedDropdownExpanded,
                    onDismissRequest = { convertedDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    unitOptions.forEachIndexed { index, unit ->
                        DropdownMenuItem(
                            text = { Text(text = unit.toString()) },
                            onClick = {
                                convertedUnit = index
                                convertedDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Text(text = "Enter value:", modifier = Modifier.padding(16.dp))
        TextField(
            value = inputValue.toString(),
            onValueChange = {
                inputValue =
                    it.toDoubleOrNull() ?: 0.0
                println("Input value changed to $inputValue")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            // Hardcoded for now
            result = when (unitOptions) {
                UnitConverter.Weight.WeightUnit.entries -> {
                    val originalUnitEnum = unitOptions[originalUnit] as UnitConverter.Weight.WeightUnit
                    val convertedUnitEnum = unitOptions[convertedUnit] as UnitConverter.Weight.WeightUnit
                    UnitConverter.Weight.convert(inputValue, originalUnitEnum, convertedUnitEnum)
                }
                UnitConverter.Temperature.TemperatureUnit.entries -> {
                    val originalUnitEnum = unitOptions[originalUnit] as UnitConverter.Temperature.TemperatureUnit
                    val convertedUnitEnum = unitOptions[convertedUnit] as UnitConverter.Temperature.TemperatureUnit
                    UnitConverter.Temperature.convert(inputValue, originalUnitEnum, convertedUnitEnum)
                }
                UnitConverter.Distance.DistanceUnit.entries -> {
                    val originalUnitEnum = unitOptions[originalUnit] as UnitConverter.Distance.DistanceUnit
                    val convertedUnitEnum = unitOptions[convertedUnit] as UnitConverter.Distance.DistanceUnit
                    UnitConverter.Distance.convert(inputValue, originalUnitEnum, convertedUnitEnum)
                }
                else -> 0.0
            }

        }) {
            Text(text = "Convert")
        }

        ResultTextDisplay(result = result)
    }
}

@Composable
fun ResultTextDisplay(result: Double) {
    // Round to 2 decimal places
    Text(text = "Result: ${"%.2f".format(result)}",
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
fun WeightTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Weight tab", modifier = Modifier.padding(16.dp))
        UnitSelector(unitOptions = UnitConverter.Weight.WeightUnit.entries)
    }
}

@Composable
fun SizeTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Size tab", modifier = Modifier.padding(16.dp))
        UnitSelector(UnitConverter.Temperature.TemperatureUnit.entries)
    }
}

@Composable
fun DistanceTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Distance tab", modifier = Modifier.padding(16.dp))
        UnitSelector(UnitConverter.Distance.DistanceUnit.entries)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterAppPreview() {
    UnitConverterTheme {
        UnitConverterApp()
    }
}