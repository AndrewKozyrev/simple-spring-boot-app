document.addEventListener("DOMContentLoaded", function() {
    fetch("/users")
      .then(response => response.json()) // Parse JSON response
      .then(data => {
        // Process user data and populate table
        const tbody = document.getElementById("users-table").getElementsByTagName("tbody")[0];
        for (const user of data) {
          const row = document.createElement("tr");
          row.innerHTML = `<td>${user.id}</td> <td>${user.name}</td> <td>${user.surname}</td> <td>${user.age}</td> <td></td>`;
          tbody.appendChild(row);
        }
      })
      .catch(error => console.error(error)); // Handle errors
  });