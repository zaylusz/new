const WEBHOOK_URL = "https://discord.com/api/webhooks/1046954476013092904/4yAxU1Sn860kcgym3maL8IC9R8PPiSYyQBtg-LumDulsxJzsFdAPrxbPi6gEdwv8Hhwy";


// Get the message text area and submit button
const messageTextArea = document.getElementById("message");
const submitButton = document.getElementById("submit-button");

// Keep track of the time when the last message was sent
let lastMessageSent = 0;

// Handle the submit button click event
submitButton.addEventListener("click", (event) => {
  event.preventDefault(); // Prevent form from submitting

  // Get the current time
  const currentTime = Date.now();

  // Check if 20 minutes have passed since the last message was sent
  if (currentTime - lastMessageSent < 1000 * 60 * 20) {
    // Show error message
    const responseDiv = document.getElementById("response");
    responseDiv.innerHTML = "Please wait 20 minutes before sending another message.";
    return;
  }

  // Get the message from the text area
  const message = messageTextArea.value;

  // Create the webhook payload
  const payload = {
    embeds: [
      {
        description: message,
        title: "Got another beam"

      },
    ],
  };

  // Send the payload to the Discord webhook
  fetch(WEBHOOK_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  })
    .then((response) => {
      // Update the time when the last message was sent
      lastMessageSent = currentTime;

      // Show success message
      const responseDiv = document.getElementById("response");
      responseDiv.innerHTML = "sent successfully. | thanks! zaylusz#7864";

      // Wait for 3 seconds before clearing the message text area and response div
      setTimeout(() => {
        messageTextArea.value = "";
        responseDiv.innerHTML = "";
      }, 3000);
    })
    .catch((error) => {
      // Show error message
      const responseDiv = document.getElementById("response");
      responseDiv.innerHTML = `Error: ${error}.`;
    });
});
