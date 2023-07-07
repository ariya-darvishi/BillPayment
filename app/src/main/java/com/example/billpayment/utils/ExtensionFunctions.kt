package com.example.billpayment.utils

import com.example.billpayment.R


fun Any.formatWithComma(): String {
    return toString().reversed().chunked(3).joinToString(",").reversed()
}

fun Int.getBillInfo(): Pair<String, Int> {
    return when (this) {
        1 -> Pair("قبض آب", R.drawable.logo_01)
        2 -> Pair("قبض برق", R.drawable.logo_02)
        3 -> Pair("قبض گاز", R.drawable.logo_03)
        4 -> Pair("قبض تلف ثابت", R.drawable.logo_04)
        5 -> Pair("قبض تلفن همراه", R.drawable.logo_05)
        6 -> Pair("قبض عوارض شهرداری", R.drawable.logo_06)
        7 -> Pair("قبض سازمان مالیات", R.drawable.logo_07)
        8 -> Pair("قبض جرایم رانندگی", R.drawable.logo_08)
        else -> Pair("موردی یافت نشد!", R.drawable.nodata)
    }
}

fun String.padNumber(): String {
    val numberString = this
    val paddingLength = 13 - numberString.length
    if (paddingLength <= 0) {
        return numberString
    }
    val padding = "0".repeat(paddingLength)
    return padding + numberString
}


fun String.padLeft(userInputLength: Int, inputText: Char): String {
    val trimmedString = this.trim()
    if (trimmedString.length > userInputLength) {
        return trimmedString
    }
    val stringBuilder = StringBuilder(userInputLength)
    val fill = userInputLength - trimmedString.length
    repeat(fill) {
        stringBuilder.append(inputText)
    }
    stringBuilder.append(trimmedString)
    return stringBuilder.toString()
}

fun String.getSecondDigitFromRight(): Int {
    val numberString = this
    if (numberString.length < 2) {
        throw IllegalArgumentException("Number should have at least 2 digits.")
    }
    return Character.getNumericValue(numberString[numberString.length - 2])
}


fun String.getCompanyCode(): Int {
    return try {
        if (this.length >= 6) {
            val length = this.length
            this.substring(length - 5, length - 2).toInt()
        } else {
            -1
        }
    } catch (ignored: Exception) {
        -1
    }
}

fun String.getServiceCode(): Int {
    return try {
        if (this.length >= 6) {
            val length = this.length
            this.substring(length - 2, length - 1).toInt()
        } else {
            -1
        }
    } catch (ignored: Exception) {
        -1
    }
}

fun String.getAmount(): Long {
    return try {
        if (this.length >= 6) {
            this.substring(0, this.length - 5).toLong() * 1000
        } else {
            -1
        }
    } catch (ignored: Exception) {
        -1
    }
}


fun String.generateBillId(companyCode: String, serviceCode: String): String {
    var billCode = this
    try {
        if (billCode.length < 8) {
            billCode = billCode.padLeft(8, '0')
        }
    } catch (ignored: Exception) {
    }

    var modifiedCompanyCode = companyCode
    try {
        if (companyCode.length < 3) {
            modifiedCompanyCode = companyCode.padLeft(3, '0')
        }
    } catch (ignored: Exception) {
    }

    var result = billCode + modifiedCompanyCode + serviceCode
    result += result.getCheckDigit()

    return result
}

fun generatePaymentId(billId: String, amount: String, year: String, periodCode: String): String {
    var result: String

    var modifiedAmount = amount.substring(0, amount.length - 3)
    try {
        if (modifiedAmount.length < 8) {
            modifiedAmount = modifiedAmount.padLeft(8, '0')
        }
    } catch (ignored: Exception) {
    }

    val modifiedYear = year.substring(year.length - 1)

    var modifiedPeriodCode = periodCode
    try {
        if (modifiedPeriodCode.length < 8) {
            modifiedPeriodCode = modifiedPeriodCode.padLeft(2, '0')
        }
    } catch (ignored: Exception) {
    }

    result = modifiedAmount + modifiedYear + modifiedPeriodCode
    val temp = result + result.getCheckDigit()

    result = billId + temp.toLong()
    result += result.getCheckDigit()

    return result.toLong().toString()
}

fun String.getCheckDigit(): String {
    val numbers = ArrayList<Int>()
    var m = 2
    val d = this.toCharArray()
    for (i in this.length - 1 downTo 0) {
        val c = d[i].toString()
        numbers.add(c.toInt() * m)
        ++m

        if (m > 7) {
            m = 2
        }
    }

    var sum = 0
    for (n in numbers) {
        sum += n
    }

    val mod = sum % 11

    return if (mod == 0 || mod == 1) {
        "0"
    } else {
        (11 - mod).toString()
    }
}

fun String.isBillValid(): Boolean {
    try {
        if (this.length != 13) return false

        val dataArray = arrayOf(
            this.substring(0, this.length - 1), this.substring(this.length - 1)
        )

        return dataArray[0].getCheckDigit() == dataArray[1]
    } catch (e: Exception) {
        return false
    }
}

fun isPaymentValid(billId: String, paymentId: String): Boolean {
    try {
        if (billId.length > 13 || paymentId.length < 6) return false

        val dataArray = arrayOf(
            paymentId.substring(0, paymentId.length - 2),
            paymentId.substring(paymentId.length - 2, paymentId.length - 1),
            paymentId.substring(paymentId.length - 1)
        )

        if (dataArray[0].length < 12)
            dataArray[0] = dataArray[0].padLeft(12, '0')

        val lastCheckData = (billId + dataArray[0].toLong() + dataArray[1]).getCheckDigit().toString()
        return dataArray[0].getCheckDigit().toString() == dataArray[1] && dataArray[2] == lastCheckData

    } catch (e: Exception) {
        return false
    }
}