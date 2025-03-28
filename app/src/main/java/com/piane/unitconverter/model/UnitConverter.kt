package com.piane.unitconverter.model

object UnitConverter {

    /**
     * Temperature unit conversions.
     */
    object Temperature {
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