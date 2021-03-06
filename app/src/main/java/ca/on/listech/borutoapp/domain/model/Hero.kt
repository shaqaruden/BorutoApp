package ca.on.listech.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.on.listech.borutoapp.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.HERO_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)
