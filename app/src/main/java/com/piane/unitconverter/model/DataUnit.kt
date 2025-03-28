package com.piane.unitconverter.model

class DataUnit() {
    object Temperature {
        fun convertCelsiusToF(celsius: Double): Double {
            return celsius * 1.8 + 32
        }

        fun convertFahrenheitToC(fahr: Double): Double {
            return (fahr - 32) / 1.8
        }

        fun convertKelvinToC(kelvin: Double): Double {
            return kelvin - 273.15
        }

        fun convertKelvinToF(kelvin: Double): Double {
            return kelvin * 1.8 - 459.67
        }
    }


    object Distance {
        fun convertCmToFt(cm: Double): Double {
            return cm * 0.0328084
        }

        fun convertFtToCm(ft: Double): Double {
            return ft * 30.48
        }

        fun convertFtToIn(ft: Double): Double {
            return ft * 12
        }

        fun convertInToFt(inFt: Double): Double {
            return inFt / 12
        }

        fun convertInToCm(inFt: Double): Double {
            return inFt * 2.54
        }

        fun convertInToM(inFt: Double): Double {
            return inFt * 0.0254
        }

        fun YardsToMeters(yd: Double): Double {
            return yd * 0.9144
        }

        fun convertMetersToYards(m: Double): Double {
            return m / 0.9144
        }

        fun convertMetersToMiles(m: Double): Double {
            return m / 1609.34
        }

        fun convertMilesToMeters(mi: Double): Double {
            return mi * 1609.34
        }

        fun convertMilesToKm(mi: Double): Double {
            return mi * 1.60934
        }

        fun convertKmToMiles(km: Double): Double {
            return km / 1.60934
        }
    }

    object Weight {
        fun convertKgToPounds(kg: Double): Double {
            return kg * 0.453592
        }

        fun convertPoundsToKg(pounds: Double): Double {
            return pounds / 0.453592
        }
    }
}