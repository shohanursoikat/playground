<?php
// Check if form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    // Collect data from the form
    $username = $_POST['username'];
    $password = $_POST['password'];

    // 🔐 Fake login info (for testing)
    $correct_username = "shohanur";
    $correct_password = "1234";

    // ✅ Check if input matches
    if ($username === $correct_username && $password === $correct_password) {
        echo "<h2 style='color: green;'>Login successful! ✅</h2>";
        echo "<p>Welcome, <strong>$username</strong></p>";
    } else {
        echo "<h2 style='color: red;'>Login failed! ❌</h2>";
        echo "<p>Invalid username or password.</p>";
    }

} else {
    echo "No data submitted.";
}
?>
