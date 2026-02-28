<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get the form data
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];

    // Check if passwords match
    if ($password !== $confirm_password) {
        echo "❌ Passwords do not match!";
        exit;
    }

    // Hash the password (NEVER store plain text passwords)
    $hashed_password = password_hash($password, PASSWORD_DEFAULT);

    // You can now insert this into a database — but for now, just display it
    echo "<h2>✅ Registration Successful!</h2>";
    echo "<p>Welcome, <strong>$fullname</strong>!</p>";
    echo "<p>Your email: $email</p>";
    echo "<p>Your username: $username</p>";
}
?>