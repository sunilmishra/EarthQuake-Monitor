package com.codewithmisu.earthquakedemo.usgs.data
import com.codewithmisu.earthquakedemo.usgs.domain.*

data class EarthquakeResponseDTO(
    val type: String,
    val metadata: MetadataDTO,
    val features: List<FeatureDTO>,
    val bbox: List<Double>
)

data class MetadataDTO(
    val generated: Long,
    val url: String?,
    val title: String?,
    val status: Int?,
    val api: String?,
    val count: Int?
)

data class FeatureDTO(
    val type: String?,
    val properties: PropertiesDTO?,
    val geometry: GeometryDTO?,
    val id: String
)

data class PropertiesDTO(
    val mag: Double?,
    val place: String,
    val time: Long?,
    val updated: Long?,
    val tz: Int?,
    val url: String?,
    val detail: String?,
    val felt: Int?,
    val cdi: Double?,
    val mmi: Double?,
    val alert: String?,
    val status: String?,
    val tsunami: Int?,
    val sig: Int?,
    val net: String?,
    val code: String?,
    val ids: String?,
    val sources: String?,
    val types: String?,
    val nst: Int?,
    val dmin: Double?,
    val rms: Double?,
    val gap: Int?,
    val magType: String?,
    val type: String?,
    val title: String
)

data class GeometryDTO(
    val type: String?,
    val coordinates: List<Double>?
)


//// MAPPER



/* -------------------- Response -------------------- */

fun EarthquakeResponseDTO.toDomain(): Earthquake {
    return Earthquake(
        type = type,
        metadata = metadata.toDomain(),
        features = features.map { it.toDomain() },
        bbox = bbox
    )
}

/* -------------------- Metadata -------------------- */
fun MetadataDTO.toDomain(): Metadata {
    return Metadata(
        generated = generated,
        url = url,
        title = title,
        status = status,
        api = api,
        count = count
    )
}

/* -------------------- Feature -------------------- */

fun FeatureDTO.toDomain(): Feature {
    return Feature(
        type = type,
        properties = properties?.toDomain(),
        geometry = geometry?.toDomain(),
        id = id
    )
}

/* -------------------- Properties -------------------- */

fun PropertiesDTO.toDomain(): Properties {
    return Properties(
        mag = mag,
        place = place,
        time = time,
        updated = updated,
        tz = tz,
        url = url,
        detail = detail,
        felt = felt,
        cdi = cdi,
        mmi = mmi,
        alert = alert,
        status = status,
        tsunami = tsunami,
        sig = sig,
        net = net,
        code = code,
        ids = ids,
        sources = sources,
        types = types,
        nst = nst,
        dmin = dmin,
        rms = rms,
        gap = gap,
        magType = magType,
        type = type,
        title = title
    )
}

/* -------------------- Geometry -------------------- */

fun GeometryDTO.toDomain(): Geometry {
    return Geometry(
        type = type,
        coordinates = coordinates
    )
}

