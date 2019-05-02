package `in`.org.celesta.iitp.account

fun map(value: Float, iStart: Float, iStop: Float, oStart: Float, oStop: Float): Float {
    return oStart + (oStop - oStart) * ((value - iStart) / (iStop - iStart))
}