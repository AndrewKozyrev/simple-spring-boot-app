document.addEventListener("DOMContentLoaded", function() {
   fetch("/users").then(response => response.json()).then(data => {
      const tbody = document.getElementById("users-table").getElementsByTagName("tbody")[0];
      for (const user of data) {
         const row = document.createElement("tr");
         // Update cell structure to include button within action cell
         row.innerHTML = `<td>${user.id}</td> <td>${user.name}</td> <td>${user.surname}</td> <td>${user.age}</td> <td><button class="delete-button" data-user-id="${user.id}">Delete</button></td>`;
         tbody.appendChild(row);
      }
      // Add event listener for delete buttons
      const deleteButtons = document.querySelectorAll(".delete-button");
      deleteButtons.forEach(button => {
         button.addEventListener("click", handleDeleteClick);
      });
   }).catch(error => console.error(error));
});

function handleDeleteClick(event) {
   // Get user ID from button data attribute
   const userId = event.target.dataset.userId;
   fetch(`/users/${userId}`, {
      method: "DELETE"
   }).then(response => {
      if (response.ok) {
         // User deleted successfully, remove the row from the table
         event.target.parentElement.parentElement.remove();
         // Optionally, consider fetching the entire user list again for a complete refresh
      } else {
         console.error("Error deleting user:", response.statusText);
      }
   }).catch(error => console.error(error));
}

// Function to handle "Create User" button click
const createButton = document.getElementById("create-button");

createButton.addEventListener("click", () => {
   // Redirect to user-form endpoint using window.location.href
   window.location.href = "/user-form";
});