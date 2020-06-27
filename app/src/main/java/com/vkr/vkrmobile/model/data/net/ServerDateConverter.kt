package com.vkr.vkrmobile.model.data.net

import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ServerDateConverter : JsonSerializer<Date>, JsonDeserializer<Date> {

    private const val DATE_FORMAT = "yyyy-MM-dd"
    private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm"

    private val deserializationFormats = arrayOf(
        DATE_TIME_FORMAT,
        DATE_FORMAT
    )

    private val df = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())

    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return if (src == null) null else JsonPrimitive(convertToServerDate(src))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        for (format in deserializationFormats) {
            try {
                val timeZone = TimeZone.getDefault()
                val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
                simpleDateFormat.timeZone = timeZone
                val t = simpleDateFormat.parse(json?.asString)
                return simpleDateFormat.parse(json?.asString)
            } catch (ignored: ParseException) {
            }

        }
        throw JsonParseException("Unparseable date: ${json?.asString}. Supported formats: ${Arrays.toString(deserializationFormats)}")
    }

    fun convertToServerDate(date: Date): String = df.format(date)
}
