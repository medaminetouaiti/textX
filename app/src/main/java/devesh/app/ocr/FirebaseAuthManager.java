package devesh.app.ocr;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    public static boolean isUserLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    public static FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public static String getCurrentUserEmail() {
        return auth.getCurrentUser() != null ? auth.getCurrentUser().getEmail() : null;
    }

    public static void logout() {
        auth.signOut();
    }
}