package pl.wsei.pam.lectures.lecture5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("Select * from tasks where is_done = :done")
     fun findAllDone(done: Boolean): Array<TaskEntity>

    @Query("Select * from tasks where id = :taskId")
     fun findById(taskId: Int): TaskEntity?

    @Query("Select * from tasks")
     fun findAll(): Array<TaskEntity>

    @Insert
     fun insert(vararg item: TaskEntity)
    @Delete
     fun delete(item: TaskEntity)

    @Update
     fun update(item: TaskEntity)
}