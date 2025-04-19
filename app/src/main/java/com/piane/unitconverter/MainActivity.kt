@file:OptIn(ExperimentalMaterial3Api::class)

package com.piane.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    UnitConverterApp(Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun UnitConverterApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        AppBar(title = "Unit Converter")

        var tabIndex by remember { mutableIntStateOf(0) }
        val tabTitles = listOf("Weight", "Temperature", "Distance")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (tabIndex) {
                0 -> WeightTab()
                1 -> TemperatureTab()
                2 -> DistanceTab()
            }
        }
    }
}

@Composable
fun UnitSelector(
    unitOptions: EnumEntries<*>,
    title: String = "Convert Units"
) {
    var originalUnit by remember { mutableIntStateOf(0) }
    var convertedUnit by remember { mutableIntStateOf(1) } // Default to a different unit
    var originalDropdownExpanded by remember { mutableStateOf(false) }
    var convertedDropdownExpanded by remember { mutableStateOf(false) }

    var inputValue by remember { mutableStateOf("") }
    var result by remember { mutableDoubleStateOf(0.0) }
    var hasCalculated by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // From Unit Section
            Text(
                text = "From:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { originalDropdownExpanded = true }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = unitOptions[originalUnit].toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select unit",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                    DropdownMenu(
                        expanded = originalDropdownExpanded,
                        onDismissRequest = { originalDropdownExpanded = false }
                    ) {
                        unitOptions.forEachIndexed { index, unit ->
                            DropdownMenuItem(
                                text = { Text(text = unit.toString()) },
                                onClick = {
                                    originalUnit = index
                                    originalDropdownExpanded = false
                                    // Recalculate when changing units
                                    if (hasCalculated && inputValue.isNotEmpty()) {
                                        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
                                        result = performConversion(unitOptions, originalUnit, convertedUnit, inputDouble)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // To Unit Section
            Text(
                text = "To:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { convertedDropdownExpanded = true }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = unitOptions[convertedUnit].toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select unit",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                    DropdownMenu(
                        expanded = convertedDropdownExpanded,
                        onDismissRequest = { convertedDropdownExpanded = false }
                    ) {
                        unitOptions.forEachIndexed { index, unit ->
                            DropdownMenuItem(
                                text = { Text(text = unit.toString()) },
                                onClick = {
                                    convertedUnit = index
                                    convertedDropdownExpanded = false
                                    // Recalculate when changing units
                                    if (hasCalculated && inputValue.isNotEmpty()) {
                                        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
                                        result = performConversion(
                                            unitOptions,
                                            originalUnit,
                                            convertedUnit,
                                            inputDouble
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // Value Input
            Text(
                text = "Value to convert:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    // Only allow numeric input with decimal
                    if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*$"))) {
                        inputValue = it

                        // Auto-calculate when input changes if we've already calculated once
                        if (hasCalculated && it.isNotEmpty()) {
                            val inputDouble = it.toDoubleOrNull() ?: 0.0
                            result = performConversion(
                                unitOptions,
                                originalUnit,
                                convertedUnit,
                                inputDouble
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                label = { Text("Enter value") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (inputValue.isNotEmpty()) {
                        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
                        result =
                            performConversion(unitOptions, originalUnit, convertedUnit, inputDouble)
                        hasCalculated = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = inputValue.isNotEmpty()
            ) {
                Text(text = "Convert")
            }

            AnimatedVisibility(
                visible = hasCalculated,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                ResultCard(
                    result = result,
                    fromUnit = unitOptions[originalUnit].toString(),
                    toUnit = unitOptions[convertedUnit].toString()
                )
            }
        }
    }
}

fun performConversion(
    unitOptions: EnumEntries<*>,
    fromIndex: Int,
    toIndex: Int,
    value: Double
): Double {
    return when (unitOptions) {
        UnitConverter.Weight.WeightUnit.entries -> {
            val originalUnitEnum = unitOptions[fromIndex] as UnitConverter.Weight.WeightUnit
            val convertedUnitEnum = unitOptions[toIndex] as UnitConverter.Weight.WeightUnit
            UnitConverter.Weight.convert(value, originalUnitEnum, convertedUnitEnum)
        }

        UnitConverter.Temperature.TemperatureUnit.entries -> {
            val originalUnitEnum =
                unitOptions[fromIndex] as UnitConverter.Temperature.TemperatureUnit
            val convertedUnitEnum =
                unitOptions[toIndex] as UnitConverter.Temperature.TemperatureUnit
            UnitConverter.Temperature.convert(value, originalUnitEnum, convertedUnitEnum)
        }

        UnitConverter.Distance.DistanceUnit.entries -> {
            val originalUnitEnum = unitOptions[fromIndex] as UnitConverter.Distance.DistanceUnit
            val convertedUnitEnum = unitOptions[toIndex] as UnitConverter.Distance.DistanceUnit
            UnitConverter.Distance.convert(value, originalUnitEnum, convertedUnitEnum)
        }

        else -> 0.0
    }
}

@Composable
fun ResultCard(result: Double, fromUnit: String, toUnit: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Result",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "%.4f".format(result),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = "$fromUnit → $toUnit",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun ResultTextDisplay(result: Double) {
    // Round to 2 decimal places
    Text(
        text = "Result: ${"%.2f".format(result)}",
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
fun WeightTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        UnitSelector(
            unitOptions = UnitConverter.Weight.WeightUnit.entries,
            title = "Weight Conversion"
        )
    }
}
@Composable
fun TemperatureTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        UnitSelector(
            unitOptions = UnitConverter.Temperature.TemperatureUnit.entries,
            title = "Temperature Conversion"
        )
    }
}

@Composable
fun DistanceTab() {
    Column(modifier = Modifier.fillMaxSize()) {
        UnitSelector(
            unitOptions = UnitConverter.Distance.DistanceUnit.entries,
            title = "Distance Conversion"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterAppPreview() {
    UnitConverterTheme {
        UnitConverterApp()
    }
}