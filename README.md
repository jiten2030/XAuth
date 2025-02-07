# XAuth

*XAuth* is an Android library that provides user authentication using Firebase Authentication. It enables you to manage user logins and ensure secure data handling, with support for multiple authentication methods, including Email, Phone Number, and Facebook login.

## Features

- **Multiple Authentication Methods**: Support for email, phone number, and Facebook authentication.
- **Secure User Verification**: Robust verification processes to ensure user authenticity.
- **Firebase Integration**: Built on Firebase Authentication, ensuring reliable backend support.
- **User Data Management**: Keeps user data secure, accessible only after authentication.

## Getting Started

### Prerequisites

- **Android Studio**
- **Firebase project** with Firebase Authentication enabled
- **Facebook Developer account** (for Facebook authentication)

### **Using Git Command Line:**

1. **Open a terminal.**
2. **Clone the repository:**
   ```sh
   git clone https://github.com/jiten2030/XAuth.git
   ```
3. **Navigate to the project directory:**
   ```sh
   cd XAuth
   ```
4. **Open the project in Android Studio:**
   ```sh
   android-studio .
   ```

### Firebase Setup

1. Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project (or use an existing one).
2. Enable **Authentication** for Email, Phone, and Facebook under **Authentication** > **Sign-in method**.
3. Download the `google-services.json` file and place it in your app directory.

### Facebook Setup (Optional)

1. Go to the[ ](https://developers.facebook.com/)[Facebook Developer Console](https://developers.facebook.com/) and create a new app.
2. Enable **Facebook Login** and configure the app.
3. Add the Facebook App ID and Client Token in your project’s `strings.xml` file.

```xml
<resources>
    <string name="facebook_app_id">YOUR_FACEBOOK_APP_ID</string>
    <string name="fb_login_protocol_scheme">fbYOUR_FACEBOOK_APP_ID</string>
</resources>
```

### Initialization

Initialize Firebase in your `Application` class:

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
```

## Usage

### Email Authentication

To use email authentication, call the `signInWithEmailAndPassword` method:

```java
FirebaseAuth auth = FirebaseAuth.getInstance();
auth.signInWithEmailAndPassword(email, password)
    .addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            // User is signed in
        } else {
            // Authentication failed
        }
    });
```

### Phone Number Authentication

To use phone number authentication, initiate the verification process:

```java
PhoneAuthProvider.verifyPhoneNumber(
    PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(phoneNumber) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
        .setActivity(activity) // Activity for callback binding
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        .build());
```

### Facebook Authentication

To use Facebook authentication, set up a login button:

```java
LoginButton loginButton = findViewById(R.id.login_button);
loginButton.setPermissions("email", "public_profile");

loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
    @Override
    public void onSuccess(LoginResult loginResult) {
        handleFacebookAccessToken(loginResult.getAccessToken());
    }
    
    @Override
    public void onCancel() {
        // Handle cancel
    }

    @Override
    public void onError(FacebookException error) {
        // Handle error
    }
});
```

### Firebase Authentication Listeners

To monitor authentication state, you can set up an `AuthStateListener`:

```java
FirebaseAuth auth = FirebaseAuth.getInstance();
FirebaseAuth.AuthStateListener authListener = firebaseAuth -> {
    FirebaseUser user = firebaseAuth.getCurrentUser();
    if (user != null) {
        // User is signed in
    } else {
        // User is signed out
    }
};

auth.addAuthStateListener(authListener);
```

## Contributing

Feel free to submit issues and pull requests! For major changes, please open an issue first to discuss the intended modifications.

## License

This project is licensed under the **MIT License**.
