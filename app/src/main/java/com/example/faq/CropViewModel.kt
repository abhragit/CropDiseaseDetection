package com.example.faq

import android.util.Log
import com.example.faq.AuthState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CropViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CropUiState())
    val uiState: StateFlow<CropUiState> = _uiState.asStateFlow()

    private val _user = MutableStateFlow<FirebaseUser?>(null)
   val user: StateFlow<FirebaseUser?> get() = _user
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        CheckAuthStatus()
    }

    fun CheckAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            Log.e("AuthError", "Authentication failed: Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = AuthState.Authenticated
                Log.d("LoginDebug", "Login successful for email=$email")
            } else {
                _authState.value = AuthState.Error(task.exception?.message ?: "Unknown Error")
                Log.e("AuthError", "Authentication failed: ${task.exception?.message}")
            }
        }
    }

    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            Log.e("AuthError", "Signup failed: Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = AuthState.Authenticated
                Log.d("LoginDebug", "Signup successful for email=$email")
            } else {
                _authState.value = AuthState.Error(task.exception?.message ?: "Unknown Error")
                Log.e("AuthError", "Signup failed: ${task.exception?.message}")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        Log.d("LoginDebug", "User logged out")
    }

    // Other methods related to UI state...


    fun updateClickText(updatedText:String){
        _uiState.update {
            it.copy(clickStatus = updatedText)
        }
    }
    fun updateSelectedCategory(updatedCategory:String){
        _uiState.update {
            it.copy(selectedCategory = updatedCategory)
        }

    }
    private val _selectedCamera = MutableStateFlow("")
    val selectedDate: StateFlow<String> = _selectedCamera

    fun updateSelectedDate(date: String) {
        _selectedCamera.value = date
    }


}
sealed class AuthState{
    object Authenticated:AuthState()
    object Unauthenticated:AuthState()
    object Loading:AuthState()
    data class Error(val message:String):AuthState()
}