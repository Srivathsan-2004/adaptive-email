package com.example.emailapplication

import androidx.room.*

@Dao
interface EmailDao {

    @Query("SELECT * FROM email_table WHERE subject = :subject")
    suspend fun getOrderBySubject(subject: String): Email?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmail(email: Email)

    @Update
    suspend fun updateEmail(email: Email)

    @Delete
    suspend fun deleteEmail(email: Email)

    // New methods to update favorite and draft status
    @Query("UPDATE email_table SET favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("UPDATE email_table SET draft = :isDraft WHERE id = :id")
    suspend fun updateDraftStatus(id: Int, isDraft: Boolean)

    // Methods to search for drafts and favorite emails
    @Query("SELECT * FROM email_table WHERE draft = 1")
    suspend fun getDraftEmails(): List<Email>

    @Query("SELECT * FROM email_table WHERE favorite = 1")
    suspend fun getFavoriteEmails(): List<Email>
}
