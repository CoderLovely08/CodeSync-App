package com.codesync.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String APP_URL = "https://code-sync-backend-api-5a72177b25c8.herokuapp.com/";
    public ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = (WebView) findViewById(R.id.webView);

        // Check internet connectivity and display popup if not available
        if (!isInternetAvailable()) {
            showInternetUnavailablePopup();
        } else {
            pd = new ProgressDialog(this, 5);
            pd.setCancelable(false);
            pd.setTitle("CodeSync");
            pd.setMessage("Loading compiler...");
            pd.show();

            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(APP_URL);

            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    pd.hide();
                }
            });
        }
    }

    /**
     * Checks if the device has an active internet connection.
     *
     * @return {@code true} if internet connection is available, {@code false} otherwise.
     */
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Displays a popup dialog informing the user that internet connection is unavailable.
     * Provides an option to close the app.
     */
    private void showInternetUnavailablePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Internet Unavailable");
        builder.setMessage("Please check your internet connection and try again.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Close the app or perform other actions as needed
            finish();
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void compileCode(String code, String language) {
        // This is a TODO method may work properly, still in testing mode
        String result = "";

        preprocessCode(code);

        switch (language) {
            case "Java":
                result = compileJavaCode(code);
                break;
            case "Python":
                result = compilePythonCode(code);
                break;
            case "C++":
                result = compileCppCode(code);
                break;
            default:
                result = "Error: Unsupported language.";
                break;
        }

        analyzeCompilationResult(result);

        // Display the compiler result
        displayCompilerResult(result);
    }

    // This is a TODO method may work properly, still in testing mode

    /**
     * Preprocesses the code before compilation.
     * Performs tasks such as removing comments, formatting code, and checking syntax errors.
     *
     * @param code The code to be preprocessed.
     */
    private void preprocessCode(String code) {
        // Remove comments
        code = removeComments(code);

        // Format code (for demonstration, we'll just convert to uppercase)
        code = formatCode(code);

        // Check for syntax errors (dummy implementation)
        boolean syntaxError = hasSyntaxError(code);

        if (syntaxError) {
            // Perform actions for syntax error
            System.out.println("Syntax error detected. Code preprocessing failed.");
        } else {
            // Perform actions for successful preprocessing
            System.out.println("Code preprocessing completed successfully.");
        }
    }

    /**
     * Removes comments from the code.
     *
     * @param code The code from which comments are to be removed.
     * @return The code with comments removed.
     */
    private String removeComments(String code) {
        // Dummy implementation: Remove comments starting with "//"
        return code.replaceAll("//.*", "");
    }

    /**
     * Formats the code.
     *
     * @param code The code to be formatted.
     * @return The formatted code.
     */
    private String formatCode(String code) {
        // Dummy implementation: Convert code to uppercase
        return code.toUpperCase();
    }

    /**
     * Checks for syntax errors in the code.
     *
     * @param code The code to be checked for syntax errors.
     * @return {@code true} if syntax error is detected, {@code false} otherwise.
     */
    private boolean hasSyntaxError(String code) {
        // Dummy implementation: Check for the presence of a specific syntax error
        return code.contains("SYNTAX_ERROR_KEYWORD");
    }


    // This is a TODO method may work properly, still in testing mode
    private String compileJavaCode(String code) {
        boolean compilationSuccess = getRandomBoolean();

        if (compilationSuccess) {
            return "Compilation Successful.\nOutput: Hello, World!";
        } else {
            return "Compilation Error: Syntax error in line 42.";
        }
    }

    // This is a TODO method may work properly, still in testing mode
    private String compilePythonCode(String code) {
        boolean compilationSuccess = getRandomBoolean();

        if (compilationSuccess) {
            return "Compilation Successful.\nOutput: 42";
        } else {
            return "Compilation Error: IndentationError: unexpected indent";
        }
    }

    // This is a TODO method may work properly, still in testing mode
    private String compileCppCode(String code) {
        boolean compilationSuccess = getRandomBoolean();

        if (compilationSuccess) {
            return "Compilation Successful.\nOutput: Program executed successfully.";
        } else {
            return "Compilation Error: Undefined reference to main";
        }
    }

    // This is a TODO method may work properly, still in testing mode
    private void analyzeCompilationResult(String result) {
        System.out.println("Analyzing compilation result...");
    }

    // This is a TODO method may work properly, still in testing mode
    private void displayCompilerResult(String result) {
        // For demonstration, we'll just print the result
        System.out.println("Compiler Result:\n" + result);
    }

    // Helper method to get a boolean value
    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    // This is a TODO method may work properly, still in testing mode
    private void sendMessage(String message) {
        // This is a TODO method may work properly, still in testing mode
        displayMessage("You", message);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayMessage("Bot", "This is a dummy response.");
            }
        }, 2000);
    }

    // This is a TODO method may work properly, still in testing mode
    private void displayMessage(String sender, String message) {
        // This is a TODO method may work properly, still in testing mode
        // You can replace this with actual chat UI updates
        String formattedMessage = sender + ": " + message;
//        chatAdapter.add(formattedMessage);
//        chatAdapter.notifyDataSetChanged();
    }

    // This is a TODO method may work properly, still in testing mode
    private void initChat() {
//        ListView chatListView = findViewById(R.id.chatListView);
//        chatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
//        chatListView.setAdapter(chatAdapter);
    }

    // This is a TODO method may work properly, still in testing mode
    private void createChatRoom(String roomName) {
        // This is a TODO method may work properly, still in testing mode
        displayMessage("System", "Chat room '" + roomName + "' created successfully.");
    }

    // This is a TODO method may work properly, still in testing mode
    private void joinChatRoom(String roomName) {
        // This is a TODO method may work properly, still in testing mode
        displayMessage("System", "Joined chat room '" + roomName + "'.");
    }

    // This is a TODO method may work properly, still in testing mode
    private void leaveChatRoom(String roomName) {
        // This is a TODO method may work properly, still in testing mode
        displayMessage("System", "Left chat room '" + roomName + "'.");
    }

    // This is a TODO method may work properly, still in testing mode
    private List<String> getAvailableChatRooms() {
        // This is a TODO method may work properly, still in testing mode
        List<String> rooms = new ArrayList<>();
        rooms.add("General");
        rooms.add("Programming");
        rooms.add("Random");
        return rooms;
    }


    // This is a TODO method may work properly, still in testing mode
    private boolean authenticateUser(String username, String password) {
        // This is a TODO method may work properly, still in testing mode
        if (username.equals("dummyUser") && password.equals("dummyPassword")) {
            return true;
        } else {
            return false;
        }
    }

    // This is a TODO method may work properly, still in testing mode
    private UserProfile fetchUserProfile(String username) {
        // This is a TODO method may work properly, still in testing mode
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        userProfile.setEmail("dummy@example.com");
        userProfile.setBio("I'm a dummy user.");
        return userProfile;
    }

    // This is a TODO method may work properly, still in testing mode
    public class UserProfile {
        private String username;
        private String email;
        private String bio;

        // This is a TODO method may work properly, still in testing mode
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
    }

}