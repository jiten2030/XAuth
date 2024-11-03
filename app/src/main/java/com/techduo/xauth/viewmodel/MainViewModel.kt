package com.techduo.xauth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class MainViewModel : ViewModel() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authStatus = MutableLiveData<AuthState>()
    val authStatus: LiveData<AuthState> = _authStatus

    private val mUser = mAuth.currentUser

    init {
        checkIfUserAuthenticated()
    }

    private fun checkIfUserAuthenticated() {
        if (mUser != null) {
            _authStatus.value = AuthState.Authenticated
        } else {
            _authStatus.value = AuthState.UnAuthenticated
        }
    }

    fun login(email: String, password: String) {
        _authStatus.value = AuthState.IsLoading
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _authStatus.value = AuthState.Authenticated
            } else {
                _authStatus.value = AuthState.OnError(it.exception?.message.toString())
            }
        }
    }

    fun register(email: String, password: String) {
        _authStatus.value = AuthState.IsLoading
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _authStatus.value = AuthState.Authenticated
//                updateProfile(profileBuilder(name))
            } else {
                _authStatus.value = AuthState.OnError(it.exception?.message.toString())
            }
        }
    }

//    private fun profileBuilder(name: String): UserProfileChangeRequest =
//        UserProfileChangeRequest.Builder()
//            .setDisplayName(name)
//            .build()

//    private fun updateProfile(profileUpdates: UserProfileChangeRequest) =
//        mUser?.updateProfile(profileUpdates)
//            ?.addOnCompleteListener {
//                if (it.isSuccessful) {
//                    _authStatus.value = AuthState.Authenticated
//                    Log.d("TAG", "updateProfile: Success")
//                } else {
//                    _authStatus.value = AuthState.OnError(it.exception?.message.toString())
//                    Log.d("TAG", "updateProfile: ${it.exception?.message}")
//                }
//            }

    fun logout() {
        mAuth.signOut()
        _authStatus.value = AuthState.UnAuthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object IsLoading : AuthState()
    data class OnError(val error: String) : AuthState()
}