package com.example.catapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catapp.networking.model.Breed

@Entity(tableName = "cat_breeds")
data class CatBreedEntity(
    @PrimaryKey val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val lifeSpan: String?,
    val referenceImageId: String?
)

fun Breed.toEntity(): CatBreedEntity {
    return CatBreedEntity(
        id = this.id ?: "",
        name = this.name ?: "",
        origin = this.origin,
        temperament = this.temperament,
        description = this.description,
        lifeSpan = this.life_span,
        referenceImageId = this.reference_image_id
    )
}

fun CatBreedEntity.toBreed(): Breed {
    return Breed(
        id = this.id,
        name = this.name,
        origin = this.origin,
        temperament = this.temperament,
        description = this.description,
        life_span = this.lifeSpan,
        reference_image_id = this.referenceImageId,
        weight = null,
        cfa_url = null,
        vetstreet_url = null,
        vcahospitals_url = null,
        country_codes = null,
        country_code = null,
        indoor = null,
        lap = null,
        alt_names = null,
        adaptability = null,
        affection_level = null,
        child_friendly = null,
        dog_friendly = null,
        energy_level = null,
        grooming = null,
        health_issues = null,
        intelligence = null,
        shedding_level = null,
        social_needs = null,
        stranger_friendly = null,
        vocalisation = null,
        experimental = null,
        hairless = null,
        natural = null,
        rare = null,
        rex = null,
        suppressed_tail = null,
        short_legs = null,
        wikipedia_url = null,
        hypoallergenic = null
    )
}

