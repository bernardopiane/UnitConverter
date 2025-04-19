package com.piane.unitconverter.model

object UnitConverter {

    /**
     * Temperature unit conversions.
     */
    object Temperature {

        enum class TemperatureUnit {
            CELSIUS,
            FAHRENHEIT,
            KELVIN
        }

        fun convert(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): Double {
            return when (fromUnit) {
                TemperatureUnit.CELSIUS -> when (toUnit) {
                    TemperatureUnit.FAHRENHEIT -> celsiusToFahrenheit(value)
                    TemperatureUnit.KELVIN -> celsiusToKelvin(value)
                    TemperatureUnit.CELSIUS -> TODO()
                }

                TemperatureUnit.FAHRENHEIT -> when (toUnit) {
                    TemperatureUnit.CELSIUS -> fahrenheitToCelsius(value)
                    TemperatureUnit.KELVIN -> fahrenheitToKelvin(value)
                    TemperatureUnit.FAHRENHEIT -> TODO()
                }

                TemperatureUnit.KELVIN -> when (toUnit) {
                    TemperatureUnit.CELSIUS -> kelvinToCelsius(value)
                    TemperatureUnit.FAHRENHEIT -> kelvinToFahrenheit(value)
                    TemperatureUnit.KELVIN -> TODO()
                }
            }
        }

        /**
         * Converts Celsius to Fahrenheit.
         *
         * @param celsius The temperature in Celsius.
         * @return The temperature in Fahrenheit.
         */
        fun celsiusToFahrenheit(celsius: Double): Double {
            return celsius * 1.8 + 32
        }

        /**
         * Converts Fahrenheit to Celsius.
         *
         * @param fahrenheit The temperature in Fahrenheit.
         * @return The temperature in Celsius.
         */
        fun fahrenheitToCelsius(fahrenheit: Double): Double {
            return (fahrenheit - 32) / 1.8
        }

        /**
         * Converts Kelvin to Celsius.
         *
         * @param kelvin The temperature in Kelvin.
         * @return The temperature in Celsius.
         */
        fun kelvinToCelsius(kelvin: Double): Double {
            return kelvin - 273.15
        }

        /**
         * Converts Kelvin to Fahrenheit.
         *
         * @param kelvin The temperature in Kelvin.
         * @return The temperature in Fahrenheit.
         */
        fun kelvinToFahrenheit(kelvin: Double): Double {
            return kelvin * 1.8 - 459.67
        }

        /**
         * Converts Celsius to Kelvin.
         *
         * @param celsius the temperature in Celsius
         * @return The temperature in Kelvin.
         */
        fun celsiusToKelvin(celsius: Double): Double {
            return celsius + 273.15
        }

        /**
         * Converts Fahrenheit to Kelvin
         * @param fahrenheit the temperature in Fahrenheit
         * @return The temperature in Kelvin.
         */
        fun fahrenheitToKelvin(fahrenheit: Double): Double {
            return (fahrenheit + 459.67) / 1.8
        }

    }

    /**
     * Distance unit conversions.
     */
    object Distance {

        enum class DistanceUnit {
            CENTIMETERS,
            FEET,
            INCHES,
            METERS,
            MILES,
            YARDS
        }

        fun convert(value: Double, fromUnit: DistanceUnit, toUnit: DistanceUnit): Double {
            return when (fromUnit) {
                DistanceUnit.CENTIMETERS -> when (toUnit) {
                    DistanceUnit.FEET -> centimetersToFeet(value)
                    DistanceUnit.INCHES -> centimetersToInches(value)
                    DistanceUnit.METERS -> centimetersToMeters(value)
                    DistanceUnit.MILES -> centimetersToMiles(value)
                    DistanceUnit.YARDS -> centimetersToYards(value)
                    DistanceUnit.CENTIMETERS -> TODO()
                }

                DistanceUnit.FEET -> when (toUnit) {
                    DistanceUnit.CENTIMETERS -> feetToCentimeters(value)
                    DistanceUnit.INCHES -> feetToInches(value)
                    DistanceUnit.METERS -> feetToMeters(value)
                    DistanceUnit.MILES -> feetToMiles(value)
                    DistanceUnit.YARDS -> feetToYards(value)
                    DistanceUnit.FEET -> TODO()
                }

                DistanceUnit.INCHES -> when (toUnit) {
                    DistanceUnit.CENTIMETERS -> inchesToCentimeters(value)
                    DistanceUnit.FEET -> inchesToFeet(value)
                    DistanceUnit.METERS -> inchesToMeters(value)
                    DistanceUnit.MILES -> inchesToMiles(value)
                    DistanceUnit.YARDS -> inchesToYards(value)
                    DistanceUnit.INCHES -> TODO()
                }

                DistanceUnit.METERS -> when (toUnit) {
                    DistanceUnit.CENTIMETERS -> metersToCentimeters(value)
                    DistanceUnit.FEET -> metersToFeet(value)
                    DistanceUnit.INCHES -> metersToInches(value)
                    DistanceUnit.MILES -> metersToMiles(value)
                    DistanceUnit.YARDS -> metersToYards(value)
                    DistanceUnit.METERS -> TODO()
                }

                DistanceUnit.MILES -> when (toUnit) {
                    DistanceUnit.CENTIMETERS -> milesToCentimeters(value)
                    DistanceUnit.FEET -> milesToFeet(value)
                    DistanceUnit.INCHES -> milesToInches(value)
                    DistanceUnit.METERS -> milesToMeters(value)
                    DistanceUnit.MILES -> TODO()
                    DistanceUnit.YARDS -> milesToYards(value)
                }

                DistanceUnit.YARDS -> when (toUnit) {
                    DistanceUnit.CENTIMETERS -> yardsToCentimeters(value)
                    DistanceUnit.FEET -> yardsToFeet(value)
                    DistanceUnit.INCHES -> yardsToInches(value)
                    DistanceUnit.METERS -> yardsToMeters(value)
                    DistanceUnit.MILES -> yardsToMiles(value)
                    DistanceUnit.YARDS -> TODO()
                }
            }
        }

        private fun yardsToMiles(d: Double): Double {
            return d * 0.000568182
        }

        private fun yardsToInches(d: Double): Double {
            return d * 0.092903
        }

        private fun yardsToFeet(d: Double): Double {
            return d * 3.0
        }

        private fun yardsToCentimeters(d: Double): Double {
            return d * 0.9144
        }

        private fun milesToYards(d: Double): Double {
            return d * 0.000568182
        }

        private fun milesToInches(d: Double): Double {
            return d * 0.000621371
        }

        private fun milesToFeet(d: Double): Double {
            return d * 5280
        }

        private fun milesToCentimeters(d: Double): Double {
            return d * 0.000621371
        }

        private fun metersToInches(d: Double): Double {
            return d * 39.3701
        }

        private fun metersToFeet(d: Double): Double {
            return d * 3.28084
        }

        private fun metersToCentimeters(d: Double): Double {
            return d * 100
        }

        private fun inchesToYards(d: Double): Double {
            return d * 0.000568182
        }

        private fun inchesToMiles(d: Double): Double {
            return d * 0.0000254
        }

        fun feetToYards(d: Double): Double {
            return d * 0.0003048
        }

        fun feetToMiles(d: Double): Double {
            return d * 0.000189394
        }

        fun feetToMeters(d: Double): Double {
            return d * 0.3048
        }

        fun centimetersToYards(d: Double): Double {
            return d * 0.0009144
        }

        fun centimetersToMiles(d: Double): Double {
            return d * 0.00000621371
        }

        fun centimetersToMeters(d: Double): Double {
            return d * 0.01
        }

        fun centimetersToInches(d: Double): Double {
            return d * 0.393701
        }

        /**
         * Converts centimeters to feet.
         *
         * @param centimeters The distance in centimeters.
         * @return The distance in feet.
         */
        fun centimetersToFeet(centimeters: Double): Double {
            return centimeters * 0.0328084
        }

        /**
         * Converts feet to centimeters.
         *
         * @param feet The distance in feet.
         * @return The distance in centimeters.
         */
        fun feetToCentimeters(feet: Double): Double {
            return feet * 30.48
        }

        /**
         * Converts feet to inches.
         *
         * @param feet The distance in feet.
         * @return The distance in inches.
         */
        fun feetToInches(feet: Double): Double {
            return feet * 12
        }

        /**
         * Converts inches to feet.
         *
         * @param inches The distance in inches.
         * @return The distance in feet.
         */
        fun inchesToFeet(inches: Double): Double {
            return inches / 12
        }

        /**
         * Converts inches to centimeters.
         *
         * @param inches The distance in inches.
         * @return The distance in centimeters.
         */
        fun inchesToCentimeters(inches: Double): Double {
            return inches * 2.54
        }

        /**
         * Converts inches to meters.
         *
         * @param inches The distance in inches.
         * @return The distance in meters.
         */
        fun inchesToMeters(inches: Double): Double {
            return inches * 0.0254
        }

        /**
         * Converts yards to meters.
         *
         * @param yards The distance in yards.
         * @return The distance in meters.
         */
        fun yardsToMeters(yards: Double): Double {
            return yards * 0.9144
        }

        /**
         * Converts meters to yards.
         *
         * @param meters The distance in meters.
         * @return The distance in yards.
         */
        fun metersToYards(meters: Double): Double {
            return meters / 0.9144
        }

        /**
         * Converts meters to miles.
         *
         * @param meters The distance in meters.
         * @return The distance in miles.
         */
        fun metersToMiles(meters: Double): Double {
            return meters / 1609.34
        }

        /**
         * Converts miles to meters.
         *
         * @param miles The distance in miles.
         * @return The distance in meters.
         */
        fun milesToMeters(miles: Double): Double {
            return miles * 1609.34
        }

        /**
         * Converts miles to kilometers.
         *
         * @param miles The distance in miles.
         * @return The distance in kilometers.
         */
        fun milesToKilometers(miles: Double): Double {
            return miles * 1.60934
        }

        /**
         * Converts kilometers to miles.
         *
         * @param kilometers The distance in kilometers.
         * @return The distance in miles.
         */
        fun kilometersToMiles(kilometers: Double): Double {
            return kilometers / 1.60934
        }
    }

    /**
     * Weight unit conversions.
     */
    object Weight {

        enum class WeightUnit {
            KILOGRAMS,
            POUNDS
        }

        fun convert(value: Double, fromUnit: WeightUnit, toUnit: WeightUnit): Double {
            return when (fromUnit) {
                WeightUnit.KILOGRAMS -> when (toUnit) {
                    WeightUnit.POUNDS -> kilogramsToPounds(value)
                    WeightUnit.KILOGRAMS -> TODO()
                }

                WeightUnit.POUNDS -> when (toUnit) {
                    WeightUnit.KILOGRAMS -> poundsToKilograms(value)
                    WeightUnit.POUNDS -> TODO()
                }
            }
        }

        /**
         * Converts kilograms to pounds.
         *
         * @param kilograms The weight in kilograms.
         * @return The weight in pounds.
         */
        fun kilogramsToPounds(kilograms: Double): Double {
            return kilograms * 2.20462
        }

        /**
         * Converts pounds to kilograms.
         *
         * @param pounds The weight in pounds.
         * @return The weight in kilograms.
         */
        fun poundsToKilograms(pounds: Double): Double {
            return pounds / 2.20462
        }
    }
}