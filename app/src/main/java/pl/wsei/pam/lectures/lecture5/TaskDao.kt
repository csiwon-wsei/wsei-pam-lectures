package pl.wsei.pam.lectures.lecture5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("Select * from tasks where is_done = :done")
    suspend fun findAllDone(done: Boolean): Array<TaskEntity>

    @Query("Select * from tasks where id = :id")
    suspend fun findById(id: Int): TaskEntity?

    @Query("Select * from tasks")
    suspend fun findAll(): Array<TaskEntity>

    @Insert
    suspend fun insert(vararg item: TaskEntity)
    @Delete
    suspend fun delete(item: TaskEntity)

    @Update
    suspend fun update(item: TaskEntity)
}