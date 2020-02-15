package com.timmyg.kotlinproject.data.entity.provider


import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.data.entity.User
import com.timmyg.kotlinproject.data.entity.errors.NoAuthException

class FireStoreProvider: RemoteDataProvider {

        companion object{
            private const val NOTES_COLLECTION = "notes"
            private const val USER_COLLECTION = "users"
        }

    private val store by lazy { FirebaseFirestore.getInstance()}

    private val UserNoteCollection : CollectionReference
    get() =
        currentUser?.let {
            store.collection(USER_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
        } ?: throw NoAuthException()



    private val currentUser
            get() = FirebaseAuth.getInstance().currentUser


    override fun getCurrentUser() = MutableLiveData<User?>().apply {
        value = currentUser?.let { firebaseUser ->
            User(firebaseUser.displayName?: "", firebaseUser.email?:"")
        }
    }

    override fun subscribeToAllNotes() = MutableLiveData<NoteResult>().apply {
        try {
            UserNoteCollection.addSnapshotListener { snapshot, e ->
                e?.let {
                    throw it
                } ?: let {
                    snapshot?.let { snapshot ->
                        value = NoteResult.Success(snapshot.map { it.toObject(Note::class.java) })
                    }
                }
            }

        } catch (e: Throwable){
            value = NoteResult.Error(e)
        }

    }

    override fun getNoteById(id: String)= MutableLiveData<NoteResult>().apply {
        try {
            UserNoteCollection.document(id).get()
                    .addOnSuccessListener {snap->
                        value = NoteResult.Success(snap.toObject(Note::class.java))
                    }.addOnFailureListener{
                        throw  it
                    }

        } catch (e: Throwable){
            value = NoteResult.Error(e)
        }

    }

    override fun saveNote(note: Note) = MutableLiveData<NoteResult>().apply {
        try {
            UserNoteCollection.document(note.id).set(note)
                    .addOnSuccessListener {
                        value = NoteResult.Success(note)
                    }.addOnFailureListener{
                        throw it
                    }

        } catch (e: Throwable){
            value = NoteResult.Error(e)
        }

    }
}