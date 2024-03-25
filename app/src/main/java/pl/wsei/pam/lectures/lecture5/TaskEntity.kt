package pl.wsei.pam.lectures.lecture5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean){

    override fun toString(): String {
        return "TaskEntity(id=$id, title='$title', isDone=$isDone)"
    }
}