package com.example.catapp.data

import com.example.catapp.data.entity.CatBreedEntity
import com.example.catapp.data.entity.toBreed
import com.example.catapp.data.entity.toEntity
import com.example.catapp.networking.model.Breed
import org.junit.Assert.assertEquals
import org.junit.Test

class CatBreedEntityMappingTest {

    @Test
    fun `should map Breed to CatBreedEntity correctly`() {
        val breed = Breed(
            id = "abc123",
            name = "Persian",
            origin = "Iran",
            temperament = "Calm",
            description = "Fluffy and gentle",
            life_span = "12-17",
            reference_image_id = "img001",
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

        val entity = breed.toEntity()

        assertEquals("abc123", entity.id)
        assertEquals("Persian", entity.name)
        assertEquals("Iran", entity.origin)
        assertEquals("Calm", entity.temperament)
        assertEquals("Fluffy and gentle", entity.description)
        assertEquals("12-17", entity.lifeSpan)
        assertEquals("img001", entity.referenceImageId)
    }

    @Test
    fun `should map CatBreedEntity to Breed correctly`() {
        val entity = CatBreedEntity(
            id = "abc123",
            name = "Persian",
            origin = "Iran",
            temperament = "Calm",
            description = "Fluffy and gentle",
            lifeSpan = "12-17",
            referenceImageId = "img001"
        )

        val breed = entity.toBreed()

        assertEquals("abc123", breed.id)
        assertEquals("Persian", breed.name)
        assertEquals("Iran", breed.origin)
        assertEquals("Calm", breed.temperament)
        assertEquals("Fluffy and gentle", breed.description)
        assertEquals("12-17", breed.life_span)
        assertEquals("img001", breed.reference_image_id)
    }

    @Test
    fun `should map null id and name in Breed to empty string in Entity`() {
        val breed = Breed(
            id = null,
            name = null,
            origin = "Unknown",
            temperament = null,
            description = null,
            life_span = null,
            reference_image_id = null,
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

        val entity = breed.toEntity()

        assertEquals("", entity.id)
        assertEquals("", entity.name)
        assertEquals("Unknown", entity.origin)
    }
}
