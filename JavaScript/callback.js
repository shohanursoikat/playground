function processUser(input, callback) {
    console.log("Processing:", input);
    callback(input.toUpperCase());
}

processUser("security", function (result) {
    console.log("Result:", result);
});
